/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.FriendManagment;

import connect_hub.UserManagement.Friends;
import connect_hub.UserManagement.PutUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class FriendRequest {
   private UserDetails sender;
   private UserDetails reciver;
 ArrayList<UserDetails> users;
    public FriendRequest(UserDetails sender, UserDetails reciver, ArrayList<UserDetails> users) {
        this.sender = sender;
        this.reciver = reciver;
        this.users=users;
    }

    /**
     *
     * @param sender
     * @param email
     * @param friend
     * @param friend
     */
    
    
    public void sendRequest(UserDetails sender,UserDetails reciver, ArrayList<UserDetails> users) throws IOException{
        Friends friendSender=new Friends(sender.getUserName(),sender.getStatus());
        System.out.println(friendSender);
        
        Friends friendReciver=new Friends(reciver.getUserName(),reciver.getStatus());
        System.out.println(friendReciver);
          reciver.addRequestFriend(friendSender);
          System.out.println(reciver.getRequest());
          PutUsers.save(users);
          
          
    }
   public void acceptFriend(UserDetails sender, UserDetails receiver, ArrayList<UserDetails> users) {
    // Create friend objects
    Friends friendSender = new Friends(sender.getUserName(), sender.getStatus());
    Friends friendReceiver = new Friends(receiver.getUserName(), receiver.getStatus());

    // Add friends to each other
    sender.addFriend(friendReceiver);
    receiver.addFriend(friendSender);

    // Remove the friend request from receiver's request list
  sender.getRequest().remove(friendReceiver );

    // Update the global 'users' list with the modified sender and receiver
    for (int i = 0; i < users.size(); i++) {
        UserDetails user = users.get(i);
        if (user.getUserName().equals(sender.getUserName())) {
            users.set(i, sender); // Update sender in the users list
        } else if (user.getUserName().equals(receiver.getUserName())) {
            users.set(i, receiver); // Update receiver in the users list
        }
    }

    // Debugging output to verify changes
    System.out.println("Sender's Friends: " + sender.getFriends());
        System.out.println("Sender's Friends: " + sender.getRequest());
    System.out.println("Receiver's Friends: " + receiver.getFriends());
    System.out.println("Receiver's Requests: " + receiver.getRequest());

    // Save the updated users list back to the file
    try {
        PutUsers.save(users);
    } catch (IOException e) {
        System.err.println("Error saving users: " + e.getMessage());
    }
}
    public void declineFriend(UserDetails sender,UserDetails reciver,ArrayList<UserDetails> users) throws IOException{
         Friends friendReciver=new Friends(reciver.getUserName(),reciver.getStatus());
        sender.getRequest().remove(friendReciver);
         PutUsers.save(users);
    }
    
   
}
