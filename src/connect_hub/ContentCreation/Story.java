package connect_hub.ContentCreation;

import connect_hub.UserManagement.ReadUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Story implements Content {

    private String id;
    private String authorId;
    private String caption;
    private String photo;
    private LocalDateTime timestamp;
    private String authorName; // Cached author name

    // Constructor
    public Story(String contentId, String authorId, String caption, String photo) {
        this.id = contentId;
        this.authorId = authorId;
        this.caption = caption;
        this.photo = photo;
        this.timestamp = LocalDateTime.now();
        this.authorName = resolveAuthorName(); // Cache the author's name
    }

    private String resolveAuthorName() {
        try {
            ArrayList<UserDetails> users = ReadUsers.readUsersFromFile("users.json");
            for (UserDetails user : users) {
                if (user.getUserId().equals(authorId)) {
                    return user.getUserName();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Unknown"; // Default if user not found
    }

    // Getters
    @Override
    public String getid() {
        return id;
    }

    @Override
    public String getAuthorId() {
        return authorId;
    }

    @Override
    public String getCaption() {
        return caption;
    }

    @Override
    public String getPhoto() {
        return photo;
    }

    @Override
    public String getTimestamp() {
        return timestamp.toString();
    }

    @Override
    public boolean isExpired() {
        return Duration.between(timestamp, LocalDateTime.now()).toHours() >= 24;
    }

    public String getAuthor() {
        return authorName;
    }
}
