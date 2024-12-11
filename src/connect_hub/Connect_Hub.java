/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connect_hub;

import connect_hub.ContentCreation.*;
import connect_hub.UserManagement.*;

/**
 *
 * @author bibos_bz87qw5
 */
public class Connect_Hub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        ContentManager c = new ContentManager("posts.json");
//        c.createPost("sdad", "sads", "sdadsd", "sdsadsa");

        HomeScreen.getInstance().setVisible(true);
    }
}
