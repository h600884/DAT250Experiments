package no.hvl.dat250.pollapp.models;

import java.util.ArrayList;
import java.util.List;

public class VoteOption {

    private Integer voteOptionId;
    private Integer pollId;
    private String caption;
    private Integer presentationOrder;
    private List<Vote> votes;

    public VoteOption(Integer pollId, String caption, Integer presentationOrder) {
        this.pollId = pollId;
        this.caption = caption;
        this.presentationOrder = presentationOrder;
        this.votes = new ArrayList<>();

    }

    public Integer getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Integer voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public Integer getPollId() {
        return pollId;
    }

    public void setPollId(Integer pollId) {
        this.pollId = pollId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(Integer presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
