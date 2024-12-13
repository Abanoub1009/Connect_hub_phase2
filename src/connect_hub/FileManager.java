package connect_hub;

import connect_hub.ContentCreation.*;
import connect_hub.Groups.Group;
import connect_hub.UserManagement.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class FileManager<T> {

    private final String filePath;
    private final String idPrefix;

    public FileManager(String filePath, String idPrefix) {
        this.filePath = filePath;
        this.idPrefix = idPrefix;
    }

    public static JSONArray loadFromFile(String filePath) {
        JSONArray jsonArray = new JSONArray();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();  // Create the file if it doesn't exist
            } catch (IOException e) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "Error creating file", e);
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            if (!jsonData.isEmpty()) {
                jsonArray = new JSONArray(jsonData.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonArray;
    }

    public void writeToJson(T object) throws IOException {
        JSONArray jsonArray = new JSONArray();
        int nextId = 0;

        // Read existing data from file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }

            if (!jsonData.isEmpty()) {
                jsonArray = new JSONArray(jsonData.toString());

                // Extract last ID and calculate next ID
                String lastId = jsonArray.getJSONObject(jsonArray.length() - 1).getString("id");
                nextId = Integer.parseInt(lastId.substring(1)) + 1;
            }
        } catch (FileNotFoundException e) {
        }

        // Create a JSON object for the new entry
        JSONObject jsonObject = new JSONObject();
        String newId = idPrefix + nextId;  // Generate new ID

        // Handle different object types with appropriate reflection
        if (object instanceof Post) {
            Post post = (Post) object;

            // Use reflection to get all fields and their values dynamically for Post
            Field[] fields = Post.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(post);
                    if (field.getName().equals("id")) {
                        jsonObject.put(field.getName(), newId);
                    } else {
                        jsonObject.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else if (object instanceof Story) {
            Story story = (Story) object;

            // Use reflection to get all fields and their values dynamically for Story
            Field[] fields = Story.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(story);
                    if (field.getName().equals("id")) {
                        jsonObject.put(field.getName(), newId);
                    } else {
                        jsonObject.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else if (object instanceof UserDetails) {
            jsonObject.put("id", newId);
            UserDetails userDetails = (UserDetails) object;

            // Use reflection to get all fields and their values dynamically for UserDetails
            Field[] fields = UserDetails.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(userDetails);
                    jsonObject.put(field.getName(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else if (object instanceof Group) {
            Group group = (Group) object;

            // Use reflection to get all fields and their values dynamically for Story
<<<<<<< HEAD
           Field[] fields = Group.class.getDeclaredFields();
for (Field field : fields) {
    field.setAccessible(true);
    try {
        Object value = field.get(group);

        if (field.getName().equals("id")) {
            jsonObject.put(field.getName(), newId);
        } else if (field.getName().equals("posts")) {
          List<?> posts = (List<?>) value; // Cast to a generic list
    JSONArray postsArray = new JSONArray();

    for (Object post : posts) {
        if (post instanceof Post) {
            Post postObject = (Post) post;
            JSONObject postJson = new JSONObject();
            
            // Add fields explicitly
            postJson.put("caption", postObject.getCaption());
            postJson.put("photo", postObject.getPhoto());
            postJson.put("id", postObject.getid());
            postJson.put("authorId", postObject.getAuthorId());
            postJson.put("timestamp", postObject.getTimestamp().toString()); // Convert timestamp to String
            
            postsArray.put(postJson); // Add to posts array
        }
    }
    jsonObject.put("posts", postsArray); // A
        } else if (field.getName().equals("requestPosts")) {  // Check for requestedPosts field
                List<?> requestedPosts = (List<?>) value; // Cast to a generic list
                JSONArray requestedPostsArray = new JSONArray();

                for (Object requestedPost : requestedPosts) {
                    if (requestedPost instanceof Post) {
                        Post postObject = (Post) requestedPost;
                        JSONObject postJson = new JSONObject();

                        // Add fields explicitly for Post
                        postJson.put("caption", postObject.getCaption());
                        postJson.put("photo", postObject.getPhoto());
                        postJson.put("id", postObject.getid());
                        postJson.put("authorId", postObject.getAuthorId());
                        postJson.put("timestamp", postObject.getTimestamp().toString()); // Convert timestamp to String

                        requestedPostsArray.put(postJson); // Add to requestedPosts array
                    }
                }
                jsonObject.put("requestPosts", requestedPostsArray);}
        
        else {
            jsonObject.put(field.getName(), value);
        }
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
}

=======
            Field[] fields = Group.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(group);
                    if (field.getName().equals("id")) {
                        jsonObject.put(field.getName(), newId);
                    } else {
                        jsonObject.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
>>>>>>> 5bc3f581823a999bf7e723b97efa6986b9b6402a
        }

        // Add the new object to the JSON array
        jsonArray.put(jsonObject);

        // Write the updated array back to the file
        try (FileWriter file = new FileWriter(filePath)) {
              System.out.println("waitttt2");
            file.write(jsonArray.toString(4));  // Pretty print with indentation
        }
    }

    public void deleteJsonFile() {
        File file = new File(filePath);
        file.delete();
    }
}
