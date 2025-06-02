package stackoverflow_;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final Map<String, Question> questions;
    private final Map<String, Answer> answers;
    private final AtomicInteger reputation = new AtomicInteger(0);
    private final String userId;
    private final String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        questions = new ConcurrentHashMap<>();
        answers = new ConcurrentHashMap<>();
    }

    public void addQuestion(Question question) {
        reputation.incrementAndGet();
        questions.put(question.getId(), question);
    }

    public void addAnswer(Answer answer) {
        reputation.incrementAndGet();
        answers.put(answer.getId(), answer);
    }

    public int getReputation() {
        return reputation.get();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions.values());
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers.values());
    }
}
