package no.hvl.dat250.pollapp.Exception;

public class VoteOptionNotFoundException extends RuntimeException {
    public VoteOptionNotFoundException(String message) {
        super(message);
    }
}
