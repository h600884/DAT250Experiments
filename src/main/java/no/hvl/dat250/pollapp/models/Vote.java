package no.hvl.dat250.pollapp.models;

import java.time.Instant;

public class Vote {

    private Integer voteId;
    private final String username;
    private final Integer pollId;
    private Integer voteOptionId;
    private Instant publishedAt;

    public Vote(String username, Integer pollId, Integer voteOptionId, Instant publishedAt) {
        this.username = username;
        this.pollId = pollId;
        this.voteOptionId = voteOptionId;
        this.publishedAt = publishedAt;
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public String getUsername() {
        return username;
    }

    public Integer getPollId() {
        return pollId;
    }

    public Integer getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Integer voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
