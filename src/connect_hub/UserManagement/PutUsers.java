package connect_hub.UserManagement;

import connect_hub.*;
import connect_hub.ContentCreation.Post;
import connect_hub.ProfileManagment.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class PutUsers {

    private final FileManager<UserDetails> userManager = new FileManager<>("users.json", "U");

    public void writeUserToJson(UserDetails user) {
        try {
            userManager.writeToJson(user);
            System.out.println("User added successfully.");
        } catch (IOException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
   public static void save(ArrayList<UserDetails> users) throws IOException {
    JSONArray usersArray = new JSONArray();
    
    for (UserDetails user : users) {
        JSONObject userJson = new JSONObject();
        userJson.put("id", user.getUserId());
        userJson.put("userName", user.getUserName());
        userJson.put("password", user.getPassword());
        userJson.put("email", user.getEmail());
        userJson.put("profilePhoto", user.getProfilePhoto());
        userJson.put("coverPhoto", user.getCoverPhoto());
        userJson.put("dateOfBirth", user.getDateOfBirth());
        userJson.put("status", user.getStatus());  
        userJson.put("bio", user.getBio());

        JSONArray friendsArray = new JSONArray();
        for (Friends friend : user.getFriends()) {
            JSONObject friendJson = new JSONObject();
            friendJson.put("FriendUserName", friend.getFriendd());
            friendJson.put("status", friend.isStatus());
            friendsArray.put(friendJson);
        }
         JSONArray friendsrequestedArray = new JSONArray();
        for (Friends friend : user.getRequest()) {
            JSONObject friendJson = new JSONObject();
            friendJson.put("FriendUserName", friend.getFriendd());
            friendJson.put("status", friend.isStatus());
            friendsrequestedArray.put(friendJson);
        }
          JSONArray friendsBlockedArray = new JSONArray();
        for (Friends friend : user.getBlocked()) {
            JSONObject friendJson = new JSONObject();
            friendJson.put("FriendUserName", friend.getFriendd());
            friendJson.put("status", friend.isStatus());
            friendsBlockedArray.put(friendJson);
        }
         JSONArray friendsSentArray = new JSONArray();
        for (Friends friend : user.getSent()) {
            JSONObject friendJson = new JSONObject();
            friendJson.put("FriendUserName", friend.getFriendd());
            friendJson.put("status", friend.isStatus());
            friendsSentArray.put(friendJson);
        }
        JSONArray postsArray = new JSONArray();
        for (Post post : user.getPosts()) {
            JSONObject postJson = new JSONObject();
             postJson.put("ContentId", post.getid());
               postJson.put("autherId", post.getAuthorId());
            postJson.put("Content", post.getCaption());
            postJson.put("Image", post.getPhoto());
            postJson.put("Timestamp", post.getTimestamp());
            postsArray.put(postJson);
        }
        userJson.put("request", friendsrequestedArray);
        userJson.put("blocked", friendsBlockedArray);
            userJson.put("friends", friendsArray);
            userJson.put("sent", friendsSentArray);
        userJson.put("posts", postsArray);
        usersArray.put(userJson);
    }
    
    // Write the users array to the JSON file
    try (FileWriter file = new FileWriter("users.json")) {
        file.write(usersArray.toString(3));  // Write with indentation level 3
    }
}
}