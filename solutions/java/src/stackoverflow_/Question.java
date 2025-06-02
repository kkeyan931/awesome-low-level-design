package stackoverflow_;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Question implements Commentable, Votable, Taggable {
    private final String id;
    private final User user;
    private final String content;
    private final Map<String, Answer> answers;
    private final Map<String, Comment> comments;
    private final Map<String, Vote> votes;
    private final Map<String, Tag> tags;

    public Question(User user, String content) {
        id = UUID.randomUUID().toString();
        this.user = user;
        this.content = content;
        answers = new ConcurrentHashMap<>();
        comments = new ConcurrentHashMap<>();
        votes = new ConcurrentHashMap<>();
        tags = new ConcurrentHashMap<>();
    }

    public void addAnswer(Answer answer) {
        answers.put(answer.getId(), answer);
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

    public String getContent() {
        return content;
    }

    public Map<String, Answer> getAnswers() {
        return answers;
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public Map<String, Vote> getVotes() {
        return votes;
    }

    @Override
    public void addTag(Tag tag) {
        tags.put(tag.getId(), tag);
    }

    @Override
    public void removeTag(String id) {
        tags.remove(id);
    }

    @Override
    public List<Tag> getTags() {
        return new ArrayList<>(tags.values());
    }
}
