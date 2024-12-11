package connect_hub.ProfileManagment;

import connect_hub.UserManagement.UserDetails;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class UpdatePhoto {
     ArrayList<UserDetails> users;

  
    public UpdatePhoto(ArrayList<UserDetails> users) {
        this.users = users;
    }
    public void changeProfilePhoto(String userId,String profilePhoto){
        for(UserDetails user:users){
             if (user.getUserId().equals(userId)) { {
                 user.setProfilePhoto(profilePhoto);
             }
        }}}
        public void changeCoverPhoto(String userId,String profilePhoto){
        for(UserDetails user:users){
             if (user.getUserId().equals(userId)) { 
                 user.setProfilePhoto(profilePhoto);
             
        }
    }
    }}
