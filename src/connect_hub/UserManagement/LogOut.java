package connect_hub.UserManagement;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class LogOut {

    public LogOut() {
        // No need for password or other details
    }

    // Method to log out the user and set status to "Offline"
    public String logOut(String email) throws IOException {
        // Read the users from the file
        JSONArray usersArray = ReadUsers.readUsersFromFile();
        boolean userFound = false;

        // Loop through users to find the matching email and set status to "Offline"
        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            String storedEmail = userJson.getString("email");

            // If email matches, set status to "Offline"
            if (email.equals(storedEmail)) {
                userJson.put("status", "Offline");  // Set status to Offline
                userFound = true;
                break;  // No need to continue after finding the user
            }
        }

        // If the user was found and status is updated, save to the file
        if (userFound) {
            saveUsersToFile(usersArray);  // Save updated users list back to the file
            return "Logout successful";  // Return success message
        } else {
            return "Invalid email";  // Return error if email is not found
        }
    }

    // Save the updated users array to the file
    private void saveUsersToFile(JSONArray usersArray) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.json"))) {
            writer.write(usersArray.toString(4));  // Pretty print with indentation
        }
    }
}
