package connect_hub.ContentCreation;

import connect_hub.*;
import java.io.*;
import java.util.*;
import org.json.*;

public class ContentRepository {

    private String filePath;
    private final FileManager<Content> postManager = new FileManager<>("posts.json", "P");
    private final FileManager<Content> storyManager = new FileManager<>("stories.json", "S");

    // Constructor to set the file path for content storage
    public ContentRepository(String filePath) {
        this.filePath = filePath;
    }

    // Load posts from posts.json
    public ArrayList<Content> loadPosts() {
        JSONArray jsonArray = FileManager.loadFromFile("posts.json");
        ArrayList<Content> posts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String contentId = jsonObject.getString("id");
            String authorId = jsonObject.getString("authorId");
            String caption = jsonObject.getString("caption");
            String photo = jsonObject.getString("photo");
            posts.add(ContentFactory.createContent("post", contentId, authorId, caption, photo));
        }
        return posts;
    }

    // Load stories from stories.json
    public ArrayList<Content> loadStories() {
        JSONArray jsonArray = FileManager.loadFromFile("stories.json");
        ArrayList<Content> stories = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String contentId = jsonObject.getString("id");
            String authorId = jsonObject.getString("authorId");
            String caption = jsonObject.getString("caption");
            String photo = jsonObject.getString("photo");
            stories.add(ContentFactory.createContent("story", contentId, authorId, caption, photo));
        }
        return stories;
    }

    // Save posts to posts.json
    public void savePosts(ArrayList<Content> posts) {
        try {
            for (Content content : posts) {
                Post post = (Post) content;
                postManager.writeToJson(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save stories to stories.json
    public void saveStories(ArrayList<Content> stories) {
        try {
            for (Content content : stories) {
                Story story = (Story) content;
                storyManager.writeToJson(story);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eraseTheFileAfterLoad(String type) {
        if (type.equals("post")) {
            postManager.deleteJsonFile();
        } else if (type.equals("story")) {
            storyManager.deleteJsonFile();
        }
    }
}
