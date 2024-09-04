package no.hvl.dat250.pollapp.models;

import java.time.Instant;
import java.util.UUID;

public class Poll {

    private final Integer pollId;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;

    public Poll(String question, Instant publishedAt, Instant validUntil) {
        this.pollId = Math.abs(UUID.randomUUID().hashCode());
        this.question = question;
        this.publishedAt = publishedAt;
        this.validUntil = validUntil;
    }

    public Integer getPollId() {
        return pollId;
    }

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
}
