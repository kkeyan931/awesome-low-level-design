package stackoverflow_;

import stackoverflow_.search.SearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflowSystem {
    private final Map<String, User> users;
    private final Map<String, Commentable> commentablesMap;
    private final Map<String, Votable> votablesMap;
    private final Map<String, Tag> tags;
    private final SearchEngine searchEngine;

    public StackOverflowSystem(SearchEngine searchEngine) {
        users = new ConcurrentHashMap<>();
        tags = new ConcurrentHashMap<>();
        commentablesMap = new ConcurrentHashMap<>();
        votablesMap = new ConcurrentHashMap<>();
        this.searchEngine = searchEngine;
    }

    private void validateUserId(String userId) {
        if (!users.containsKey(userId)) {
            throw new StackOverflowException("User with id " + userId + " doest not exist");
        }
    }

    private void validateCommentableId(String id) {
        if (!commentablesMap.containsKey(id)) {
            throw new StackOverflowException("Commentable with id " + id + " doest not exist");
        }
    }

    private void validateVotableId(String id) {
        if (!votablesMap.containsKey(id)) {
            throw new StackOverflowException("Votable with id " + id + " doest not exist");
        }
    }

    private void validateTagId(String tagId) {
        if (!tags.containsKey(tagId)) {
            throw new StackOverflowException("Tag with id " + tagId + " doest not exist");
        }
    }

    public User createUser(String userId, String name) {
        if (users.containsKey(userId)) {
            throw new StackOverflowException("User with id " + userId + " already exists");
        }
        User user = new User(userId, name);
        users.put(userId, user);
        return user;
    }

    public Question postQuestion(String userId, String content) {
        validateUserId(userId);
        User user = users.get(userId);
        Question question = new Question(user, content);
        searchEngine.processQuestion(question);
        commentablesMap.put(question.getId(), question);
        votablesMap.put(question.getId(), question);
        user.addQuestion(question);
        return question;
    }

    public Question postQuestion(String userId, String content, List<String> tagNames) {
        Question question = postQuestion(userId, content);
        for (String tagName : tagNames) {
            Tag tag;
            if (tags.containsKey(tagName)) {
                tag = tags.get(tagName);
            } else {
                tag = new Tag(tagName);
                tags.put(tagName, tag);
            }
            question.addTag(tag);
        }
        return question;
    }

    public Comment comment(String userId, String id, String commentContent) {
        validateUserId(userId);
        validateCommentableId(id);
        User user = users.get(userId);
        Commentable commentable = commentablesMap.get(id);
        Comment comment = new Comment(user, commentContent, commentable);
        commentable.addComment(comment);
        return comment;
    }

    public Vote vote(String userId, String id, VoteType voteType) {
        validateUserId(userId);
        validateVotableId(id);
        User user = users.get(userId);
        Votable votable = votablesMap.get(id);
        Vote vote = new Vote(user, voteType);
        votable.addVote(vote);
        return vote;
    }

    public Answer postAnswer(String userId, String questionId, String content) {
        validateUserId(userId);
        validateCommentableId(questionId);
        User user = users.get(userId);
        Question question = (Question) commentablesMap.get(questionId);
        Answer answer = new Answer(user, question, content);
        question.addAnswer(answer);
        user.addAnswer(answer);
        commentablesMap.put(answer.getId(), answer);
        votablesMap.put(answer.getId(), answer);
        return answer;
    }

    public List<Question> searchQuestionsByUserId(String userId) {
        validateUserId(userId);
        User user = users.get(userId);
        return searchEngine.searchQuestion(user);
    }

    public List<Question> searchQuestionsByTag(String tagName) {
        validateTagId(tagName);
        Tag tag = tags.get(tagName);
        return searchEngine.searchQuestion(tag);
    }

    public List<Question> searchQuestionsByKeyword(String keyword) {
        return searchEngine.searchQuestion(keyword);
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
}
