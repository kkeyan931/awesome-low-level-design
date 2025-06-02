package stackoverflow_;

import java.util.List;

public interface Taggable {
    void addTag(Tag tag);
    void removeTag(String id);
    List<Tag> getTags();
}