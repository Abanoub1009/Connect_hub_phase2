//package connect_hub.ProfileManagment;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//public class ReadUsers {
//
//    private static ArrayList<Friends> parseFriends(JSONArray friendsArray) {
//        ArrayList<Friends> friendsList = new ArrayList<>();
//        if (friendsArray != null) {
//            for (int i = 0; i < friendsArray.length(); i++) {
//                JSONObject friendObject = friendsArray.getJSONObject(i);
//                // Parse friend object (adjust fields based on Friends class)
//                String friendName = friendObject.getString("FriendUserName"); // Example field
//                String friendStatus = friendObject.getString("status"); // Example field
//                friendsList.add(new Friends(friendName, friendStatus)); // Adjust constructor
//            }
//        }
//        return friendsList;
//    }
//
//    private static ArrayList<Posts> parsePosts(JSONArray postsArray) {
//        ArrayList<Posts> postsList = new ArrayList<>();
//        if (postsArray != null) {
//            for (int i = 0; i < postsArray.length(); i++) {
//                JSONObject postObject = postsArray.getJSONObject(i);
//                // Parse post object (adjust fields based on Posts class)
//                String content = postObject.getString("Content");
//                String image = postObject.getString("Image");// Example field
//                String timestamp = postObject.getString("Timestamp"); // Example field
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Adjust format if necessary
//                LocalDateTime dateTime = LocalDateTime.parse(timestamp, formatter);
//                postsList.add(new Posts(content, image, dateTime)); // Adjust constructor
//            }
//        }
//        return postsList;
//    }
//
//    static ArrayList<User> readUsersFromFile() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
//        ArrayList<User> userList = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader("users.json"))) {
//            StringBuilder jsonData = new StringBuilder();
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                jsonData.append(line);
//            }
//
//            if (!jsonData.isEmpty()) {
//                // Parse JSON data
//                JSONArray usersArray = new JSONArray(jsonData.toString());
//
//                for (int i = 0; i < usersArray.length(); i++) {
//                    JSONObject userObject = usersArray.getJSONObject(i);
//
//                    // Extract user data from JSON object
//                    String username = userObject.getString("userName");
//                    String hashedPassword = userObject.getString("password");
//                    String id = userObject.getString("userId");
//                    String bio = userObject.optString("Bio", "");
//                    String profilePhoto = userObject.optString("ProfilePhoto", "");
//                    String coverPhoto = userObject.optString("CoverPhoto");
//                    String email = userObject.getString("email");
//
//                    // Friends and posts
//                    ArrayList<Friends> friends = parseFriends(userObject.optJSONArray("Friends"));
//                    ArrayList<Posts> posts = parsePosts(userObject.optJSONArray("Posts"));
//
//                    // Create User object
//                    User user = new User(username, hashedPassword, id, bio, profilePhoto, coverPhoto, friends, posts, email);
//
//                    // Add user to the list
//                    userList.add(user);
//                }
//            }
//        }
//
//        return userList;
//    }
//
//}
