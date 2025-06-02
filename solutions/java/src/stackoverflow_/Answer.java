package stackoverflow_;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Answer implements Commentable, Votable {
    private final String id;
    private final User user;
    private final String content;
    private final Question question;
    private final Map<String, Comment> comments;
    private final Map<String, Vote> votes;

    public Answer(User user, Question question, String content) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.content = content;
        this.question = question;
        comments = new ConcurrentHashMap<>();
        votes = new ConcurrentHashMap<>();
    }

    @Override
    public void addComment(Comment comment) {
        comments.put(comment.getId(), comment);
    }

    @Override
    public void removeComment(String id) {
        comments.remove(id);
    }

    @Override
    public Comment getComment(String id) {
        return comments.get(id);
    }

    @Override
    public void addVote(Vote vote) {
        votes.put(vote.getId(), vote);
    }

    @Override
    public void removeVote(String id) {
        votes.remove(id);
    }

    @Override
    public Vote getVote(String id) {
        return votes.get(id);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public Map<String, Vote> getVotes() {
        return votes;
    }

    public Question getQuestion() {
        return question;
    }

    public String getContent() {
        return content;
    }
}
