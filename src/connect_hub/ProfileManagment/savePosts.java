//package connect_hub.ProfileManagment;
//
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
///**
// *
// * @author HP
// */
//public class savePosts {
//      public static void save(ArrayList<Posts> x) throws IOException,FileNotFoundException {
//        JSONArray postsArray = new JSONArray(); //  store all the posts
//if (x == null || x.isEmpty()) {
//            System.out.println("No posts to save.");
//            return; 
//        }if (x == null || x.isEmpty()) {
//            System.out.println("No posts to save.");
//            return; 
//        }
//        // Loop through each post a
//        for (Posts p : x) {
//            JSONObject j = new JSONObject();
//            j.put("Content", p.getContent());
//            j.put("Image", p.getImage());
//            j.put("TimeStamp", p.getFormattedDate()); 
//            postsArray.put(j); // Add the individual post 
//        }
//
//        // Write the postsArray tin file
//        try (FileWriter file = new FileWriter("posts1.json")) {
//            file.write(postsArray.toString(3)); // Indentation level 3 for better readability
//        }catch(Exception e){
//            System.out.println("error ou");
//        }
//    
//     
//}}
