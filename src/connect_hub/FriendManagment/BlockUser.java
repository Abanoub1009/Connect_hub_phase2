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
public class BlockUser {

    private UserDetails sender;
    private UserDetails reciver;
    ArrayList<UserDetails> users;

    public BlockUser(UserDetails sender, UserDetails reciver, ArrayList<UserDetails> users) {
        this.sender = sender;
        this.reciver = reciver;
        this.users = users;
    }

    public void Block(UserDetails sender, UserDetails receiver, ArrayList<UserDetails> users) throws IOException {
        // Create friend objects for comparison
        Friends friendSender = new Friends(sender.getUserName(), sender.getStatus());
        Friends friendReceiver = new Friends(receiver.getUserName(), receiver.getStatus());

        // Update sender's friends list
        ArrayList<Friends> updatedSenderFriends = new ArrayList<>();
        for (Friends friend : sender.getFriends()) {
            if (!friend.equals(friendReceiver)) { // Correctly compare with receiver
                updatedSenderFriends.add(friend);
            }
        }
        sender.setFriends(updatedSenderFriends);

        // Update receiver's friends list
        ArrayList<Friends> updatedReceiverFriends = new ArrayList<>();
        for (Friends friend : receiver.getFriends()) {
            if (!friend.equals(friendSender)) { // Correctly compare with sender
                updatedReceiverFriends.add(friend);
            }
        }
        receiver.setFriends(updatedReceiverFriends);

        // Add receiver to sender's blocked list
        sender.addBlockedFriend(friendReceiver);

        // Reflect changes in the global 'users' list
        for (int i = 0; i < users.size(); i++) {
            UserDetails user = users.get(i);
            if (user.getUserName().equals(sender.getUserName())) {
                users.set(i, sender); // Update sender in users list
            } else if (user.getUserName().equals(receiver.getUserName())) {
                users.set(i, receiver); // Update receiver in users list
            }
        }

        // Debugging output
        System.out.println("Sender's Friends: " + sender.getFriends());
        System.out.println("Receiver's Friends: " + receiver.getFriends());
        System.out.println("Sender's Blocked Friends: " + sender.getBlocked());

        // Save updated users list
        PutUsers.save(users);
    }

    public void remove(UserDetails sender, UserDetails receiver, ArrayList<UserDetails> users) throws IOException {
        Friends friendSender = new Friends(sender.getUserName(), sender.getStatus());
        Friends friendReceiver = new Friends(receiver.getUserName(), receiver.getStatus());

        // Update sender's friends list
        ArrayList<Friends> updatedSenderFriends = new ArrayList<>();
        for (Friends friend : sender.getFriends()) {
            if (!(friend.equals(friendReceiver))) { // Correctly compare with receiver
                updatedSenderFriends.add(friend);
            }
        }
        sender.setFriends(updatedSenderFriends);

        // Update receiver's friends list
        ArrayList<Friends> updatedReceiverFriends = new ArrayList<>();
        for (Friends friend : receiver.getFriends()) {
            if (!(friend.equals(friendSender))) { // Correctly compare with sender
                updatedReceiverFriends.add(friend);
            }
        }
        receiver.setFriends(updatedReceiverFriends);

        // Reflect changes in the global 'users' list
        for (int i = 0; i < users.size(); i++) {
            UserDetails user = users.get(i);
            if (user.getUserName().equals(sender.getUserName())) {
                users.set(i, sender); // Update sender in the users list
            } else if (user.getUserName().equals(receiver.getUserName())) {
                users.set(i, receiver); // Update receiver in the users list
            }
        }

        // Debug: Print updated lists
        System.out.println("Sender's Friends: " + sender.getFriends());
        System.out.println("Receiver's Friends: " + receiver.getFriends());

        // Save updated users list
        PutUsers.save(users);
    }
}
