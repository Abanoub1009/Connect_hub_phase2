/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.FriendManagment;

import connect_hub.UserManagement.Friends;
import connect_hub.UserManagement.UserDetails;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FriendSuggestion {
     ArrayList<UserDetails> users;
     ArrayList<Friends>friendsSuggestion;
     public FriendSuggestion( ArrayList<UserDetails> users){
         this.users=users;
         this.friendsSuggestion=new ArrayList<Friends>();
     }
     //user1 ally 3yza 2gblo suggestion list,     user2 ally 3yza check gwah lw already user1 b3tlo request 2abl kds
     public Boolean checkRequestes(UserDetails user,UserDetails user2){
         ArrayList<Friends>user2Requested=user2.getRequest();
         for(Friends friend:user2Requested){
             if(friend.getFriendd().equals(user.getUserName())){
                 return false;
             }
         }
         return true;
     }
     
     public ArrayList<Friends>checkFriends(String email){
         // user2 ==> logged user
          UserDetails user2=new UserDetails();
             user2=user2.getSpecificUser(users, email);
         ArrayList<Friends>friends=user2.getFriends();
      ArrayList<Friends>blocked=user2.getBlocked();
        ArrayList<Friends>requested=user2.getRequest();
        
         for (UserDetails user : users) {
                
        if (user.getUserName().equals(user2.getUserName())) {
            continue;
        }
        Friends suggestion = new Friends(user.getUserName(), user.getStatus());
            if (friends.contains(suggestion)) {
                continue; // Skip if already a friend
            }

            if (blocked.contains(suggestion)) {
                continue; // Skip if blocked
            }
            if (requested.contains(suggestion)) {
                continue; // Skip if blocked
            }
            if(!checkRequestes(user2,user)){
                continue;
            }

            // Add to friend suggestions
            friendsSuggestion.add(suggestion);
        }

        return friendsSuggestion;
    }

}
