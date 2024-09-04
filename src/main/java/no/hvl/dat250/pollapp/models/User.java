package no.hvl.dat250.pollapp.models;

import java.util.UUID;

public class User {

    private final Integer id;
    private String username;
    private String email;


    public User(String username, String email) {
        this.id = Math.abs(UUID.randomUUID().hashCode());
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
