/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.Groups;

import connect_hub.UserManagement.ReadUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class SuggestGroups {
    String email;
    ArrayList<UserDetails>users;
    ArrayList<Group>suggestedGroups;
     ArrayList<Group>userGroup;
      ArrayList<Group>allGroup;

    public SuggestGroups(String email) {
        this.email = email;
        this.users = new ArrayList<>();
    }
    public Boolean requestedAlready(Group request,String userName){
        for(Member member:request.getRequestMembers()){
            if(member.getMemberUsername().equals(userName)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Group> getSuggestedGroups(){
         users=new ArrayList<>();
        suggestedGroups=new ArrayList<>();
        userGroup=new ArrayList<>();
        allGroup=new ArrayList<>();
      try {
          users = ReadUsers.readUsersFromFile("users.json");
      } catch (IOException ex) {
          Logger.getLogger(GroupActivites.class.getName()).log(Level.SEVERE, null, ex);
      }
        UserDetails user=new UserDetails();
        user=user.getSpecificUser(users, email);
        getGroupsOfUser group=new getGroupsOfUser(user.getUserName());
        userGroup=group.getGroupsofSpecifiecUser();
        GroupRepository rep=new GroupRepository("groups.json");
        allGroup=rep.getAllGroups();
        for(Group groupp:allGroup){
             boolean isUserGroup = false;
        
          if(requestedAlready(groupp,user.getUserName())){
            isUserGroup = true;
            break;}
          else{
            for(Group usergroup:userGroup){
                if(usergroup.getGroupId().equals(groupp.getGroupId())){
                     isUserGroup = true;
            break;
                }
            }
            if (!isUserGroup){
           suggestedGroups.add(groupp);}
        }}
       return suggestedGroups; 
    }
    
}
