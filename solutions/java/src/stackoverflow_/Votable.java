package stackoverflow_;

public interface Votable {
    void addVote(Vote vote);
    void removeVote(String id);
    Vote getVote(String id);
}
