package no.hvl.dat250.pollapp.Managers;

import no.hvl.dat250.pollapp.models.Poll;
import no.hvl.dat250.pollapp.models.User;
import no.hvl.dat250.pollapp.models.Vote;
import no.hvl.dat250.pollapp.models.VoteOption;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DomainManager {

    // Lagring i minnet ved bruk av HashMaps
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Poll> polls = new HashMap<>();
    private Map<String, VoteOption> voteOptions = new HashMap<>();
    private Map<String, Vote> votes = new HashMap<>();

    // User metoder
    public User createUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    public User getUser(Integer id) {
        return users.get(id);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(Integer id) {
        users.remove(id);
    }

    // Poll metoder
    public Poll addPoll(Poll poll) {
        polls.put(poll.getPollId(), poll);
        return poll;
    }

    public Poll getPoll(Integer pollId) {
        return polls.get(pollId);
    }

    public List<Poll> getAllPolls() {
        return new ArrayList<>(polls.values());
    }

    public void deletePoll(Integer pollId) {
        polls.remove(pollId);
    }

    // VoteOption metoder
    public VoteOption addVoteOption(VoteOption option) {
        voteOptions.put(option.getCaption(), option);
        return option;
    }

    public List<VoteOption> getAllVoteOptions() {
        return new ArrayList<>(voteOptions.values());
    }

    public VoteOption getVoteOption(String optionId) {
        return voteOptions.get(optionId);
    }

    public void deleteVoteOption(String optionId) {
        voteOptions.remove(optionId);
    }

    // Vote metoder
    public Vote addVote(Vote vote) {
        votes.put(vote.getPublishedAt().toString(), vote);
        return vote;
    }

    public Vote getVote(String voteId) {
        return votes.get(voteId);
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes.values());
    }

    public void deleteVote(String voteId) {
        votes.remove(voteId);
    }

}

