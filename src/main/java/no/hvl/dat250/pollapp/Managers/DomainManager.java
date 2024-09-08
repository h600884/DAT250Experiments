package no.hvl.dat250.pollapp.Managers;

import no.hvl.dat250.pollapp.models.Poll;
import no.hvl.dat250.pollapp.models.User;
import no.hvl.dat250.pollapp.models.Vote;
import no.hvl.dat250.pollapp.models.VoteOption;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DomainManager {

    // Lagring i minnet ved bruk av HashMaps
    private Map<String, User> users = new HashMap<>();
    private Map<Integer, Poll> polls = new HashMap<>();
    private Map<Integer, VoteOption> voteOptions = new HashMap<>();
    private Map<Integer, Vote> votes = new HashMap<>();


    private Integer nextPollId = 0;
    private Integer nextVoteId = 0;
    private Integer nextVoteOptionId = 0;

    // User metoder
    public User createUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User updateUser(String username, User updatedUser) {
        User existingUser = users.get(username);

        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            if (!username.equals(updatedUser.getUsername())) {
                users.remove(username);
                users.put(updatedUser.getUsername(), existingUser);
            } else {
                users.put(username, existingUser);
            }
        }
        return existingUser;
    }


    public void deleteUser(String username) {
        users.remove(username);
    }

    // Poll metoder
    public Poll addPoll(Poll poll) {
        poll.setPollId(nextPollId++);
        polls.put(poll.getPollId(), poll);
        return poll;
    }

    public Poll getPoll(Integer pollId) {
        return polls.get(pollId);
    }

    public List<Poll> getAllPolls() {
        return new ArrayList<>(polls.values());
    }

    public Poll updatePoll(Integer pollId, Poll updatedPoll) {
        Poll existingPoll = polls.get(pollId);

        if (existingPoll != null) {
            existingPoll.setQuestion(updatedPoll.getQuestion());
            existingPoll.setValidUntil(updatedPoll.getValidUntil());
        }

        polls.put(pollId, updatedPoll);
        return existingPoll;
    }

    public void deletePoll(Integer pollId) {
        polls.remove(pollId);

        List<Integer> voteOptionIds = voteOptions.values().stream()
                .filter(voteOption -> voteOption.getPollId().equals(pollId))
                .map(VoteOption::getVoteOptionId)
                .collect(Collectors.toList());

        for (Integer voteOptionId : voteOptionIds) {
            voteOptions.remove(voteOptionId);

            votes.entrySet().removeIf(entry -> entry.getValue().getVoteOptionId().equals(voteOptionId));
        }
    }

    // Vote metoder

    public Vote addVoteOnOption(String username, Integer pollId, Integer voteOptionId, Instant publishedAt) {
        User user = users.get(username);
        Poll poll = polls.get(pollId);
        VoteOption voteOption = voteOptions.get(voteOptionId);

        Vote newVote = new Vote(username, pollId, voteOptionId, publishedAt);
        newVote.setVoteId(nextVoteId++);
        voteOption.getVotes().add(newVote);
        votes.put(newVote.getVoteId(), newVote);

        return newVote;
    }

    public Vote getVote(Integer id) {
        return votes.get(id);
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes.values());
    }

    public Vote updateVote(Integer id, Vote updatedVote) {
        Vote existingVote = votes.get(id);

        if (!existingVote.getVoteOptionId().equals(updatedVote.getVoteOptionId()) &&
                existingVote.getPollId().equals(updatedVote.getPollId())) {

            removeVoteFromOption(existingVote);

            VoteOption newOption = voteOptions.get(updatedVote.getVoteOptionId());
            existingVote.setVoteOptionId(updatedVote.getVoteOptionId());
            existingVote.setPublishedAt(updatedVote.getPublishedAt());
            newOption.getVotes().add(existingVote);
        } else {
            existingVote.setPublishedAt(updatedVote.getPublishedAt());
        }

        votes.put(id, existingVote);
        return existingVote;
    }

    public void deleteVote(Integer id) {
        Vote existingVote = votes.get(id);
        if (existingVote != null) {
            removeVoteFromOption(existingVote);
            votes.remove(id);
        }
    }

    public void removeVoteFromOption(Vote existingVote) {
        VoteOption currentOption = voteOptions.get(existingVote.getVoteOptionId());
        if (currentOption != null) {
            currentOption.getVotes().removeIf(vote -> vote.getVoteId().equals(existingVote.getVoteId()));
        }
    }


    // Vote option metoder
    public VoteOption createVoteOption(VoteOption voteOption) {
        Poll poll = polls.get(voteOption.getPollId());


        voteOption.setVoteOptionId(nextVoteOptionId++);
        voteOptions.put(voteOption.getVoteOptionId(), voteOption);

        List<VoteOption> pollVoteOptions = poll.getVoteOptions();

        if (pollVoteOptions == null) {
            pollVoteOptions = new ArrayList<>();
        }

        int insertIndex = 0;
        for (int i = 0; i < pollVoteOptions.size(); i++) {
            if (pollVoteOptions.get(i).getPresentationOrder() > voteOption.getPresentationOrder()) {
                insertIndex = i;
                break;
            }
        }

        pollVoteOptions.add(insertIndex, voteOption);
        poll.setVoteOptions(pollVoteOptions);

        return voteOption;
    }

    public VoteOption getVoteOption(Integer id) { return voteOptions.get(id); }

    public List<VoteOption> getAllVoteOptions() {
        return new ArrayList<>(voteOptions.values());
    }

    public VoteOption updateVoteOption(Integer voteOptionId, VoteOption updatedVoteOption) {
        VoteOption existingVoteOption = voteOptions.get(voteOptionId);

        existingVoteOption.setCaption(updatedVoteOption.getCaption());
        existingVoteOption.setPresentationOrder(updatedVoteOption.getPresentationOrder());

        voteOptions.put(voteOptionId, existingVoteOption);
        return existingVoteOption;
    }

    public void deleteVoteOption(Integer voteOptionId) {
        VoteOption voteOption = voteOptions.remove(voteOptionId);

        votes.entrySet().removeIf(entry -> entry.getValue().getVoteOptionId().equals(voteOptionId));

        Poll parentPoll = polls.get(voteOption.getPollId());
        if (parentPoll != null) {
            parentPoll.getVoteOptions().removeIf(vo -> vo.getVoteOptionId().equals(voteOptionId));
        }
    }

}

