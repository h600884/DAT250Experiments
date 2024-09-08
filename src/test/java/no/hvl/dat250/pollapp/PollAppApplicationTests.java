package no.hvl.dat250.pollapp;

import com.jayway.jsonpath.JsonPath;
import no.hvl.dat250.pollapp.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PollAppApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testScenario() throws Exception {
        // 1: Create a new user (User 1)
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user1\", \"email\":\"user1@example.com\"}"))
                .andExpect(status().isCreated());

        // 2: List all users (should show User 1)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("user1")));

        // 3: Create another user (User 2)
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\", \"email\":\"user2@example.com\"}"))
                .andExpect(status().isCreated());

        // 4: List all users (should show User 1 and User 2)
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].username", is("user2")));

        // 5: User 1 creates a new poll
        mockMvc.perform(post("/polls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pollCreator\":\"user1\", \"question\":\"What's your favorite color?\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pollCreator").value("user1"))
                .andExpect(jsonPath("$.question").value("What's your favorite color?"));
      /*
        mockMvc.perform(post("/polls")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pollCreator\":\"user1\", \"question\":\"What's your favorite programming language?\"}"))
                .andExpect(status().isCreated());*/

        // 6: List polls (should show the new poll)
        mockMvc.perform(get("/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].question", is("What's your favorite color?")));

        // 7: User 2 votes on the poll

        mockMvc.perform(post("/voteOptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pollId\":0, \"caption\":\"Blue\", \"presentationOrder\":1}"))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\", \"pollId\":0, \"voteOptionId\":0}"))
                .andExpect(status().isCreated());

        // 8: User 2 changes his vote
        mockMvc.perform(put("/api/votes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"pollCreator\":\"user2\", \"pollId\":1, \"voteOptionId\":2, \"publishedAt\":\"2024-09-04T12:00:00Z\"}"))
                .andExpect(status().isOk());

        // 9: List votes (should show the most recent vote for User 2)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].voteOptionId", is(2)));

        // 10: Delete the one poll
        mockMvc.perform(delete("/polls/1"))
                .andExpect(status().isOk());

        // 11: List votes (should be empty)
        mockMvc.perform(get("/votes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
