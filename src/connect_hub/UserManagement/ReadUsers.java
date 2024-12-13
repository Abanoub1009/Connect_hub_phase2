package connect_hub.UserManagement;

import connect_hub.ContentCreation.Post;
import connect_hub.FileManager;
import connect_hub.ProfileManagment.*;
import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONObject;

public class ReadUsers {

     private static ArrayList<Friends> parseFriends(JSONArray friendsArray) {
        ArrayList<Friends> friendsList = new ArrayList<>();
        if (friendsArray != null) {
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObject = friendsArray.getJSONObject(i);
                // Parse friend object (adjust fields based on Friends class)
                String friendName = friendObject.getString("FriendUserName"); // Example field
                String friendStatus = friendObject.getString("status"); // Example field
                friendsList.add(new Friends(friendName, friendStatus)); // Adjust constructor
            }
        }
        return friendsList;}
     
      private static ArrayList<Friends> parseFriendsSent(JSONArray friendsArray) {
        ArrayList<Friends> friendsList = new ArrayList<>();
        if (friendsArray != null) {
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObject = friendsArray.getJSONObject(i);
                // Parse friend object (adjust fields based on Friends class)
                String friendName = friendObject.getString("FriendUserName"); // Example field
                String friendStatus = friendObject.getString("status"); // Example field
                friendsList.add(new Friends(friendName, friendStatus)); // Adjust constructor
            }
        }
        return friendsList;}
     private static ArrayList<Friends> parseFriendsRequest(JSONArray friendsArray) {
        ArrayList<Friends> friendsList = new ArrayList<>();
        if (friendsArray != null) {
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObject = friendsArray.getJSONObject(i);
                // Parse friend object (adjust fields based on Friends class)
                String friendName = friendObject.getString("FriendUserName"); // Example field
                String friendStatus = friendObject.getString("status"); // Example field
                friendsList.add(new Friends(friendName, friendStatus)); // Adjust constructor
            }
        }
        return friendsList;
        
    }
       private static ArrayList<Friends> parseFriendsBlock (JSONArray friendsArray) {
        ArrayList<Friends> friendsList = new ArrayList<>();
        if (friendsArray != null) {
            for (int i = 0; i < friendsArray.length(); i++) {
                JSONObject friendObject = friendsArray.getJSONObject(i);
                // Parse friend object (adjust fields based on Friends class)
                String friendName = friendObject.getString("FriendUserName"); // Example field
                String friendStatus = friendObject.getString("status"); // Example field
                friendsList.add(new Friends(friendName, friendStatus)); // Adjust constructor
            }
        }
        return friendsList;
    }

    private static ArrayList<Post> parsePosts(JSONArray postsArray) {
        ArrayList<Post> postsList = new ArrayList<>();
        if (postsArray != null) {
            for (int i = 0; i < postsArray.length(); i++) {
                JSONObject postObject = postsArray.getJSONObject(i);
                // Parse post object (adjust fields based on Posts class)
                
                 
                  
                String content = postObject.getString("Content");
                String image = postObject.getString("Image");// Example field
              String timestamp = postObject.getString("Timestamp");
           LocalDateTime datestamp = LocalDateTime.parse(timestamp);  
                // Example field
                 // Adjust format if necessary
               
                postsList.add(new Post(content, image,datestamp)); // Adjust constructor
            }
        }
        return postsList;
    }

    public static ArrayList<UserDetails> readUsersFromFile(String filePath) throws IOException {
        ArrayList<UserDetails> users = new ArrayList<>();
        
        // Using FileManager to load JSON from file
        JSONArray jsonArray = FileManager.loadFromFile(filePath);
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String password = jsonObject.getString("password");
            String profilePhoto = jsonObject.optString("profilePhoto", "");
            String coverPhoto = jsonObject.optString("coverPhoto");
            String bio = jsonObject.optString("bio", "");
            String dateOfBirth = jsonObject.getString("dateOfBirth");
            String id = jsonObject.getString("id");
            String userName = jsonObject.getString("userName");
            ArrayList<Post> posts = parsePosts(jsonObject.optJSONArray("posts"));
            String email = jsonObject.getString("email");
            ArrayList<Friends> friends = parseFriends(jsonObject.optJSONArray("friends"));
             ArrayList<Friends> friendsrequest = parseFriendsRequest(jsonObject.optJSONArray("request"));
              ArrayList<Friends> friendsblock = parseFriendsRequest(jsonObject.optJSONArray("blocked"));
              ArrayList<Friends>sent= parseFriendsSent(jsonObject.optJSONArray("sent"));
            String status = jsonObject.getString("status");
            UserDetails user = new UserDetails(id, email, userName, password, dateOfBirth, status, friends, posts,friendsrequest,friendsblock,sent);
            user.getPasswordFromFile(id);
            user.setProfilePhoto(profilePhoto);
            user.setCoverPhoto(coverPhoto);
            user.setBio(bio);
            users.add(user);
        }
        return users;
    }
    public static JSONArray readUsersFromFile() throws IOException {
        JSONArray usersArray = new JSONArray();
        try (BufferedReader reader = new BufferedReader(new FileReader("users.json"))) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            if (!jsonData.isEmpty()) {
                usersArray = new JSONArray(jsonData.toString());
            }
        }
        return usersArray;
    }
}
