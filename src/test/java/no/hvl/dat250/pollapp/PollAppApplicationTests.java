package no.hvl.dat250.pollapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PollAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFullScenario() throws Exception {
        // Step 1: Create a new user (User 1)
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user1\", \"email\":\"user1@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("user1"));

        // Step 2: List all users (should show the newly created user)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username").value("user1"));

        // Step 3: Create another user (User 2)
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\", \"email\":\"user2@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("user2"));

        // Step 4: List all users again (should show both users)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[1].username").value("user2"));

        // Step 5: User 1 creates a new poll
        mockMvc.perform(post("/polls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"creatorUsername\":\"user1\", \"question\":\"What's your favorite color?\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.creatorUsername").value("user1"))
                .andExpect(jsonPath("$.question").value("What's your favorite color?"));

        // Step 6: List polls (should show the new poll)
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].question").value("What's your favorite color?"));

        // Step 7: Create a vote option for the poll
        mockMvc.perform(post("/voteOptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pollId\":0, \"caption\":\"Blue\", \"presentationOrder\":1}"))
                .andExpect(status().isCreated());

        // Step 8: User 2 votes on the poll
        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\", \"pollId\":0, \"voteOptionId\":0}"))
                .andExpect(status().isCreated());

        // Step 9: User 2 changes his vote (create a second option and vote for it)
        mockMvc.perform(post("/voteOptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pollId\":0, \"caption\":\"Red\", \"presentationOrder\":2}"))
                .andExpect(status().isCreated());

        mockMvc.perform(put("/votes/0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\", \"pollId\":0, \"voteOptionId\":1}")) // Changing vote to second option
                .andExpect(status().isOk());

        // Step 10: List votes (should show the most recent vote for User 2)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username").value("user2"))
                .andExpect(jsonPath("$[0].voteOptionId").value(1));

        // Step 11: Delete the poll
        mockMvc.perform(delete("/polls/0"))
                .andExpect(status().isOk());

        // Step 12: List votes (should be empty after poll deletion)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}