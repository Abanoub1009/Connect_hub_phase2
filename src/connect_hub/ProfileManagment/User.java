//package connect_hub.ProfileManagment;
//
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//
///**
// *
// * @author HP
// */
//public class User {
//    private String username;
//    private String hashedPassword;
//    private String id; 
//    private String bio;
//     private String Email;
//    private String profilePhoto;
//    
//    private String coverPhoto;
//private ArrayList<Friends> friends;
//private ArrayList<Posts> posts;
//  
//    public User(String username, String hashedPassword, String id,String bio,String profilePhoto,String coverPhoto,ArrayList<Friends> friends,ArrayList<Posts> posts,String Email) throws NoSuchAlgorithmException {
//        this.username = username;
//        this.hashedPassword = UpdatePassword.HashedPassword(hashedPassword);
//        this.id = id;
//        this.bio=bio;
//        this.profilePhoto=profilePhoto;
//        this.coverPhoto=coverPhoto;
//       this.friends = (friends == null) ? new ArrayList<>() : friends;
//       this.posts = (posts == null) ? new ArrayList<>() : posts;
//        this.Email=Email;
//    }
//
//    public User() {
//    }
//    
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getHashedPassword() {
//        
//        return hashedPassword;
//    }
//
//    public void setHashedPassword(String hashedPassword)  {
//       
//        this.hashedPassword = hashedPassword;
//       
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getBio() {
//        return bio;
//    }
//
//    public void setBio(String bio) {
//        this.bio = bio;
//    }
//
//    public String getProfilePhoto() {
//        return profilePhoto;
//    }
//
//    public void setProfilePhoto(String profilePhoto) {
//        this.profilePhoto = profilePhoto;
//    }
//
//    public String getCoverPhoto() {
//        return coverPhoto;
//    }
//
//    public void setCoverPhoto(String coverPhoto) {
//        this.coverPhoto = coverPhoto;
//    }
//
//    public ArrayList<Friends> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(ArrayList<Friends> friends) {
//        this.friends = friends;
//    }
//
//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String Email) {
//        this.Email = Email;
//    }
//
//   
//    public ArrayList<Posts> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(ArrayList<Posts> posts) {
//        this.posts = posts;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" + "username=" + username + ", hashedPassword=" + hashedPassword + ", id=" + id + ", bio=" + bio + ", profilePhoto=" + profilePhoto + ", Email=" + Email + ", coverPhoto=" + coverPhoto + ", friends=" + friends + ", posts=" + posts + '}';
//    }
//   
//    
//}
