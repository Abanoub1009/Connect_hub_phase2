package connect_hub.ProfileManagment;

import connect_hub.UserManagement.ReadUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class UpdatePassword {

    ArrayList<UserDetails> users;

    public UpdatePassword(ArrayList<UserDetails> users) {
        this.users = users;
    }


    public void changePassword(String password, String id) throws NoSuchAlgorithmException, IOException {
        users= ReadUsers.readUsersFromFile("users.json");
        for (UserDetails user : users) {
            if (user.getUserId().equals(id)) {
                if (validatePassword(password)) {
                    String hashedPassword = UserDetails.hashPassword(password); // Get the hashed password as a String
                    if (checkPassword(user, hashedPassword)) {
                        user.setPassword(password);  // Update hashed password
                        System.out.println("Password updated for user: " + user.getUserName());
                    }
                }
            }
        }
    }

    public boolean checkPassword(UserDetails user, String password) throws NoSuchAlgorithmException {
        if (user.getPassword().equals(password)) {
            System.out.println("Password is same as the old one");
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            System.out.println("Incorrect password");
            return false;
        }
        return true;
    }

    public UserDetails getSpecificUser(String password, String email) throws NoSuchAlgorithmException {
        for (UserDetails user : users) {
            if (user.getPassword().equals(UserDetails.hashPassword(password)) && user.getEmail().equals(email)) 
            return user;
        }
        return null;
    }

}
