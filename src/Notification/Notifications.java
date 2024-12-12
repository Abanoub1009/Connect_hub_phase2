/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Notification;

import connect_hub.UserManagement.Friends;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Notifications {
    
private  ArrayList<Friends>requestedFriends;
   
  //  ArrayList<Group>groups;

    public Notifications( ArrayList<Friends> requestedFriends) {
   
        this.requestedFriends = requestedFriends;
       
        //this.groups = groups;
    }

   

    public ArrayList<Friends> getRequestedFriends() {
        return requestedFriends;
    }

    public void setRequestedFriends(ArrayList<Friends> requestedFriends) {
        this.requestedFriends = requestedFriends;
    }}
