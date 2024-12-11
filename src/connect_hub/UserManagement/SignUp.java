package connect_hub.UserManagement;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.util.ArrayList;

public class SignUp extends UserDetails {
    
    private PutUsers putUsers = new PutUsers();
    public SignUp(String userId, String email, String userName, String password, String dateOfBirth, String status) throws IOException {
        super(userId, email, userName, password, dateOfBirth, status,null,null,null,null);
        createAccount();     
    }

    public void createAccount() throws IOException {
        if (isValid()) {  
            putUsers.writeUserToJson(this);
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Invalid account details!");
        }
    }

    private boolean isValid() {
        
        boolean isEmailValid = isValidEmail(getEmail());
        boolean isPasswordLengthValid = checkPassLength(getPassword());

        System.out.println("Email Valid: " + isEmailValid);
        System.out.println("Password Length Valid: " + isPasswordLengthValid);

        return getEmail() != null && isEmailValid && isPasswordLengthValid;
    }
}