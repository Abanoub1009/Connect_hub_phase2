package connect_hub.NewsFeed;

import connect_hub.ContentCreation.Content;
import connect_hub.ContentCreation.ContentRepository;
import java.util.ArrayList;

public class NewsFeedManager {

    private ContentRepository contentRepository;

    public NewsFeedManager(String filePath) {
        contentRepository = new ContentRepository(filePath);
    }

    // Load content (posts and stories) from the repository
    public ArrayList<Content> getNewsFeed() {
        ArrayList<Content> posts = contentRepository.loadPosts();  // Load only posts
        ArrayList<Content> stories = contentRepository.loadStories();  // Load only stories
        
        posts.addAll(stories);  // Combine posts and stories
        return posts;  // Return the combined list of posts and stories
    }

    // Save posts to the repository
    public void savePosts(ArrayList<Content> posts) {
        contentRepository.savePosts(posts);
    }

    // Save stories to the repository
    public void saveStories(ArrayList<Content> stories) {
        contentRepository.saveStories(stories);
    }

    // Clear the content file after loading
    public void eraseFileAfterLoad(String type) {
        contentRepository.eraseTheFileAfterLoad(type);  // Fixed typo here
    }
}
