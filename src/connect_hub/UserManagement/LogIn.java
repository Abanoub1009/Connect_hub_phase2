package connect_hub.UserManagement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;


public class LogIn extends UserDetails {

    public LogIn(String email, String password) throws IOException {
        super(null, email, null, password, null, "Online",null,null,null,null);
    }

    public String checkLoginCredentials(String email, String password) throws IOException {
        JSONArray usersArray = ReadUsers.readUsersFromFile();
        boolean userFound = false;

        for (int i = 0; i < usersArray.length(); i++) {
            JSONObject userJson = usersArray.getJSONObject(i);
            String storedEmail = userJson.getString("email");
            String storedHashedPass = userJson.getString("password");

            if (email.equals(storedEmail)) {
                String enteredPassHash = hashPassword(password);

                if (enteredPassHash.equals(storedHashedPass)) {
                    userJson.put("status", "Online");  
                    userFound = true;
                    break;
                } else {
                    return "invalidPassword";
                }
            }
        }

        if (userFound) {
            saveUsersToFile(usersArray);
            return "success";
        } else {
            return "invalidEmail";
        }
    }
    // save the updated user data back to the file
     private void saveUsersToFile(JSONArray usersArray) throws IOException {
        String filePath = "users.json"; 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(usersArray.toString(4));  
        }
    }
}
