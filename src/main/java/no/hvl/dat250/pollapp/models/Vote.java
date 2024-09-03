package no.hvl.dat250.pollapp.models;

import java.time.Instant;

public class Vote {

    private Instant publishedAt;

    public Vote(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }
}
