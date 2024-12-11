package connect_hub.ProfileManagment;
import connect_hub.UserManagement.UserDetails;
import java.util.ArrayList;

public class UpdateBio {
  
     ArrayList<UserDetails> users;
     public UpdateBio( ArrayList<UserDetails> users){
         this.users=users;
     }
     public void changeBio(String bio,String id){
     for(UserDetails userr:users){
         if (userr.getUserId().equals(id)){
             userr.setBio(bio);
         }
     }
}
     
     public UserDetails getSpecific(String email){
         for(UserDetails user:users){
             if(user.getEmail().equals(email)){
                 return user;
             }
         }
         return null;
     }
}