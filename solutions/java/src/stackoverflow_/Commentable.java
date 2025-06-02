package stackoverflow_;

public interface Commentable {
    void addComment(Comment comment);

    void removeComment(String id);

    Comment getComment(String id);
}
