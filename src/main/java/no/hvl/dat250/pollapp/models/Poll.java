package no.hvl.dat250.pollapp.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Poll {


    private Integer pollId;
    private final String creatorUsername;
    private String question;
    private List<VoteOption> voteOptions;
    private Instant publishedAt;
    private Instant validUntil;

    public Poll(String creatorUsername, String question, Instant publishedAt, Instant validUntil) {
        this.creatorUsername = creatorUsername;
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
        this.voteOptions = new ArrayList<>();
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) { this.pollId = pollId; }

    public String getCreatorUsername() { return creatorUsername; }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil = validUntil;
    }

    public List<VoteOption> getVoteOptions() { return voteOptions; }

    public void setVoteOptions(List<VoteOption> voteOptions) { this.voteOptions = voteOptions; }
}
