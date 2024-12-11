/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.SearchManager;


import connect_hub.UserManagement.UserDetails;
import connect_hub.Groups.Group;
import connect_hub.Groups.GroupRepository;

import java.util.List;

public class SearchController {
    private final SearchManager searchManager;

    public SearchController(List<UserDetails> users, GroupRepository groupRepository) {
        this.searchManager = new SearchManager(users, groupRepository);
    }

    public List<UserDetails> searchUsers(String query) {
        return searchManager.searchUsers(query);
    }

    public List<Group> searchGroups(String query) {
        return searchManager.searchGroups(query);
    }

    public void addFriend(UserDetails currentUser, UserDetails targetUser) {
        try {
            searchManager.performUserAction(new AddFriendAction(), currentUser, targetUser);
            System.out.println("Friend request sent successfully.");
        } catch (Exception e) {
            System.err.println("Error sending friend request: " + e.getMessage());
        }
    }

    public void removeFriend(UserDetails currentUser, UserDetails targetUser) {
        try {
            searchManager.performUserAction(new RemoveFriendAction(), currentUser, targetUser);
            System.out.println("Friend removed successfully.");
        } catch (Exception e) {
            System.err.println("Error removing friend: " + e.getMessage());
        }
    }

    public void blockUser(UserDetails currentUser, UserDetails targetUser) {
        try {
            searchManager.performUserAction(new BlockUserAction(), currentUser, targetUser);
            System.out.println("User blocked successfully.");
        } catch (Exception e) {
            System.err.println("Error blocking user: " + e.getMessage());
        }
    }

    public void joinGroup(UserDetails user, Group group) {
        try {
            searchManager.performGroupAction(new JoinGroupAction(), user, group);
            System.out.println("Join request sent to the group.");
        } catch (Exception e) {
            System.err.println("Error joining group: " + e.getMessage());
        }
    }

    public void leaveGroup(UserDetails user, Group group) {
        try {
            searchManager.performGroupAction(new LeaveGroupAction(), user, group);
            System.out.println("You have left the group.");
        } catch (Exception e) {
            System.err.println("Error leaving group: " + e.getMessage());
        }
    }

    public String viewUserProfile(UserDetails user) {
        return user.viewProfile();
    }

    public String viewGroupDetails(Group group) {
        return "Group Name: " + group.getName() + "\n" +
               "Description: " + group.getDescription() + "\n" +
               "Created By: " + group.getCreatedBy() + "\n" +
               "Members: " + group.getMembers().size();
    }

    public void performSearch(String query) {
        System.out.println("User Search Results:");
        List<UserDetails> userResults = searchUsers(query);
        for (UserDetails user : userResults) {
            System.out.println(user.getUserName());
            System.out.println("Options: Add Friend, Remove Friend, Block User, View Profile");
        }

        System.out.println("\nGroup Search Results:");
        List<Group> groupResults = searchGroups(query);
        for (Group group : groupResults) {
            System.out.println(group.getName());
            System.out.println("Options: Join Group, Leave Group, View Group Details");
        }
    }
}

