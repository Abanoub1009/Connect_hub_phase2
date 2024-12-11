/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.ContentCreation;


import connect_hub.UserManagement.ReadUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post implements Content {
    private String id;
    private String authorId;
    private String caption;
    private String photo;
    private LocalDateTime timestamp;

    // Constructor
    public Post(String contentId, String authorId, String caption, String photo) {

        this.id = contentId;
        this.authorId = authorId;
        this.caption = caption;
        this.photo = photo;
        this.timestamp = LocalDateTime.now();
    }

    public Post(String caption, String photo, LocalDateTime timestamp) {
        this.caption = caption;
        this.photo = photo;
        this.timestamp = timestamp;
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
        return false;  // Posts never expire
    }
    public String getAuthor() throws IOException
    {
        String authorName = "";
        ArrayList<UserDetails> users = ReadUsers.readUsersFromFile("users.json");
        for(UserDetails user: users)
        {
            if(getAuthorId().equals(user.getUserId()))
            {
                authorName = user.getUserName();
                break;
            }
        }
        return authorName;
    }
}

