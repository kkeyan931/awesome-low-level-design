package stackoverflow_.search.impl;

import stackoverflow_.Question;
import stackoverflow_.Tag;
import stackoverflow_.User;
import stackoverflow_.search.SearchEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InvertedIndexSearchEngine implements SearchEngine {
    private final Map<String, List<Question>> questionInvertedIndex;
    private final Map<Tag, List<Question>> tagInvertedIndex;

    public InvertedIndexSearchEngine() {
        this.questionInvertedIndex = new ConcurrentHashMap<>();
        this.tagInvertedIndex = new ConcurrentHashMap<>();
    }

    @Override
    public List<Question> searchQuestion(String keyword) {
        return questionInvertedIndex.getOrDefault(keyword, new ArrayList<>());
    }

    @Override
    public List<Question> searchQuestion(Tag tag) {
        return tagInvertedIndex.getOrDefault(tag, new ArrayList<>());
    }

    @Override
    public List<Question> searchQuestion(User user) {
        return user.getQuestions();
    }

    @Override
    public void processQuestion(Question question) {
        List<String> keywords = new ArrayList<>(List.of(question.getContent().split(" ")));
        for (String keyword : keywords) {
            if (!questionInvertedIndex.containsKey(keyword)) {
                questionInvertedIndex.put(keyword, new ArrayList<>(List.of(question)));
            } else {
                questionInvertedIndex.get(keyword).add(question);
            }
        }
        for (Tag tag : question.getTags()) {
            if (!tagInvertedIndex.containsKey(tag)) {
                tagInvertedIndex.put(tag, new ArrayList<>());
            } else {
                tagInvertedIndex.get(tag).add(question);
            }
        }
    }
}
