/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.Groups;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class getGroupsOfUser {
ArrayList<Group>groups;
ArrayList<Group>userGroups;
String userName;
    public getGroupsOfUser(String userName) {
        this.groups=new ArrayList<>();
        this.userGroups=new ArrayList<>();
        this.userName=userName;
        System.out.println(userName);
        loadAllGroups();
        
    }
    public void loadAllGroups(){
        GroupRepository rep=new GroupRepository("groups.json");
        groups=rep.getAllGroups();
    
    }
   public ArrayList<Group> getGroupsofSpecifiecUser(){
       for(Group groupp:groups){
           ArrayList<Member>members=groupp.getMembers();
         
          for(Member member:members){
              if(member.getMemberUsername().equals(userName)){
                  userGroups.add(groupp);
              }
          }
       }
      
       return userGroups;
   }
    
}
