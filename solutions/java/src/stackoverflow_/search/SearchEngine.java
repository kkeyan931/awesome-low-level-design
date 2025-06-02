package stackoverflow_.search;

import stackoverflow_.Question;
import stackoverflow_.Tag;
import stackoverflow_.User;

import java.util.List;

public interface SearchEngine {
    List<Question> searchQuestion(String keyword);

    List<Question> searchQuestion(Tag tag);

    List<Question> searchQuestion(User user);

    void processQuestion(Question question);
}
