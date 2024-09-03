package no.hvl.dat250.pollapp.Managers;

import no.hvl.dat250.pollapp.models.Poll;
import no.hvl.dat250.pollapp.models.User;
import no.hvl.dat250.pollapp.models.Vote;
import no.hvl.dat250.pollapp.models.VoteOption;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class DomainManager {

    // Lagring i minnet ved bruk av HashMaps
    private Map<String, User> users = new HashMap<>();
    private Map<String, Poll> polls = new HashMap<>();
    private Map<String, VoteOption> voteOptions = new HashMap<>();
    private Map<String, Vote> votes = new HashMap<>();

    // User management
    public User addUser(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    public Optional<User> getUser(String username) {
        return Optional.ofNullable(users.get(username));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public void deleteUser(String username) {
        users.remove(username);
    }

    // Poll management
    public Poll addPoll(Poll poll) {
        poll.setQuestion(UUID.randomUUID().toString());  // Assign a unique identifier
        polls.put(poll.getQuestion(), poll);
        return poll;
    }

    public Optional<Poll> getPoll(String pollId) {
        return Optional.ofNullable(polls.get(pollId));
    }

    public List<Poll> getAllPolls() {
        return new ArrayList<>(polls.values());
    }

    public void deletePoll(String pollId) {
        polls.remove(pollId);
    }

    // VoteOption management
    public VoteOption addVoteOption(VoteOption option) {
        voteOptions.put(option.getCaption(), option);
        return option;
    }

    public List<VoteOption> getAllVoteOptions() {
        return new ArrayList<>(voteOptions.values());
    }

    public Optional<VoteOption> getVoteOption(String optionId) {
        return Optional.ofNullable(voteOptions.get(optionId));
    }

    public void deleteVoteOption(String optionId) {
        voteOptions.remove(optionId);
    }

    // Vote management
    public Vote addVote(Vote vote) {
        votes.put(vote.getPublishedAt().toString(), vote);
        return vote;
    }

    public Optional<Vote> getVote(String voteId) {
        return Optional.ofNullable(votes.get(voteId));
    }

    public List<Vote> getAllVotes() {
        return new ArrayList<>(votes.values());
    }

    public void deleteVote(String voteId) {
        votes.remove(voteId);
    }

}

