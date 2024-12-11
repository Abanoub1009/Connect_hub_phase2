package connect_hub.ContentCreation;

import java.util.*;

public class ContentManager {

    private ContentRepository contentRepository;

    public ContentManager(String filePath) {
        this.contentRepository = new ContentRepository(filePath);
    }

    public Post createPost(String contentId, String authorId, String caption, String photo) {
        Content post = ContentFactory.createContent("post", contentId, authorId, caption, photo);
        ArrayList<Content> contents = contentRepository.loadPosts();
        contentRepository.eraseTheFileAfterLoad("post");
        contents.add(post);
        contentRepository.savePosts(contents);
        return (Post) post;
    }

    public void createStory(String contentId, String authorId, String caption, String photo) {
        Content story = ContentFactory.createContent("story", contentId, authorId, caption, photo);
        ArrayList<Content> contents = contentRepository.loadStories();
        contentRepository.eraseTheFileAfterLoad("story");
        contents.add(story);
        contentRepository.saveStories(contents);
    }

    public void deleteExpiredStories() {
        ArrayList<Content> contents = contentRepository.loadStories();  // Load only stories
        ArrayList<Content> expiredStories = new ArrayList<>();

        for (Content content : contents) {
            if (content instanceof Story && content.isExpired()) {
                expiredStories.add(content);
            }
        }

        contents.removeAll(expiredStories);
        contentRepository.saveStories(contents);
    }
}
