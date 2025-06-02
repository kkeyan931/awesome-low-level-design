package stackoverflow_;

import java.util.UUID;

public class Tag {
    private final String id;
    private final String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }
}
