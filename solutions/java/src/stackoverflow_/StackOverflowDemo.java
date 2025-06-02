package stackoverflow_;

import stackoverflow_.search.SearchEngine;
import stackoverflow_.search.impl.InvertedIndexSearchEngine;

import java.util.List;

public class StackOverflowDemo {
    public static void main(String[] args) {
        SearchEngine searchEngine = new InvertedIndexSearchEngine();
        StackOverflowSystem system = new StackOverflowSystem(searchEngine);


        User userKarthik = system.createUser("kkeyan931@gmail.com", "Karthikeyan");
        User userJhon = system.createUser("jhon@samsung.con", "jhon");
        User userSnow = system.createUser("snow@samsung.con", "snow");


        Question question1 = system.postQuestion(userKarthik.getUserId(), "How c++ works");
        Question question2 = system.postQuestion(userJhon.getUserId(), "How java works");

        Answer answer1 = system.postAnswer(userJhon.getUserId(), question1.getId(), "c++ just works");
        Answer answer2 = system.postAnswer(userKarthik.getUserId(), question2.getId(), "java just works");


        system.comment(userSnow.getUserId(), answer1.getId(), "Good answer");
        system.vote(userSnow.getUserId(), answer1.getId(), VoteType.UP_VOTE);
        system.vote(userSnow.getUserId(), answer2.getId(), VoteType.DOWN_VOTE);


        List<Question> searchResult = system.searchQuestionsByUserId(userKarthik.getUserId());
        for(Question question : searchResult) {
            System.out.println(question.getContent());
        }

        searchResult = system.searchQuestionsByKeyword("java");

        for(Question question : searchResult) {
            System.out.println(question.getContent());
        }
    }
}
