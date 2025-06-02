package stackoverflow_;

import java.util.Date;
import java.util.UUID;

public class Comment {
    private final String id;
    private final User user;
    private final String content;
    private final Date date;
    private final Commentable commentable;

    public Comment(User user, String content, Commentable commentable) {
        this.user = user;
        this.content = content;
        this.commentable = commentable;
        id = UUID.randomUUID().toString();
        date = new Date();
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

    public Date getDate() {
        return date;
    }

    public Commentable getCommentable() {
        return commentable;
    }
}
