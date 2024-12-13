package connect_hub.Groups;

import connect_hub.ContentCreation.Content;
import connect_hub.ContentCreation.Post;
import java.util.ArrayList;

public class Group {
    private String id;
    private String name;
    private String description;
    private String groupPhoto;
    private String createdBy;
    private String createdAt;
    private ArrayList<Member> members;
    private ArrayList<Content> posts;
    private ArrayList<Content> requestPosts; // Posts pending approval by admins
    private ArrayList<Member> requestMembers; // Membership requests

    // Constructor
    public Group(String groupId, String name,String createdBy, String createdAt) {
        this.id = groupId;
        this.name = name;
        this.description = "";
        this.groupPhoto = "src/connect_hub/Images/pp.png";
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.members = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.requestPosts = new ArrayList<>();
        this.requestMembers = new ArrayList<>();
    }

    Group() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getters and Setters
    public String getGroupId() {
        return id;
    }

    public void setGroupId(String groupId) {
        this.id = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(String groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Content> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Content> posts) {
        this.posts = posts;
    }

    public ArrayList<Content> getRequestPosts() {
        return requestPosts;
    }

    public void setRequestPosts(ArrayList<Content> requestPosts) {
        this.requestPosts = requestPosts;
    }

    public ArrayList<Member> getRequestMembers() {
        return requestMembers;
    }

    public void setRequestMembers(ArrayList<Member> requestMembers) {
        this.requestMembers = requestMembers;
    }

    // Methods to manage members
    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

    public void addRequestPost(Post post) {
        requestPosts.add(post);
    }

    public void approvePost(Post post) {
        requestPosts.remove(post);
        posts.add(post);
    }
    public void rejectPost(Post post) {
        requestPosts.remove(post);
       
    }

    // Methods to handle membership requests
    public void addMembershipRequest(Member member) {
        requestMembers.add(member);
    }

    public void approveMembershipRequest(Member member) {
        requestMembers.remove(member);
        members.add(member);
    }

    public void rejectMembershipRequest(Member member) {
        requestMembers.remove(member);
    }
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
=======
>>>>>>> 5bc3f581823a999bf7e723b97efa6986b9b6402a

    @Override
    public String toString() {
        return "Group{" + "groupId=" + id + ", name=" + name + ", description=" + description + ", groupPhoto=" + groupPhoto + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", members=" + members + ", posts=" + posts + ", requestPosts=" + requestPosts + ", requestMembers=" + requestMembers + '}';
    }
<<<<<<< HEAD
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Group group = (Group) obj;
    return id.equals(group.id); // Assuming `id` is unique
}

@Override
public int hashCode() {
    return id.hashCode(); // Use the unique `id` for hashCode
}

    
>>>>>>> Stashed changes
=======
    
>>>>>>> 5bc3f581823a999bf7e723b97efa6986b9b6402a
}

