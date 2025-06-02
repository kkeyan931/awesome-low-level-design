package stackoverflow_;

import java.util.UUID;

public class Vote {
    private final String id;
    private final User user;
    private final VoteType voteType;

    public Vote(User user, VoteType voteType) {
        this.user = user;
        this.voteType = voteType;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public User getUser() {
        return user;
    }
}
