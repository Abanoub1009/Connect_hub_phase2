package connect_hub.Groups;

import connect_hub.ContentCreation.Content;
import connect_hub.ContentCreation.ContentFactory;
import connect_hub.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class GroupRepository {

    private final FileManager<Group> groupManager = new FileManager<>("groups.json", "G");
    private final String filePath;

    public GroupRepository(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Group> getAllGroups() {
        JSONArray jsonArray = FileManager.loadFromFile(filePath);
        ArrayList<Group> groups = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Group group = parseGroup(jsonObject);
            groups.add(group);
        }
        return groups;
    }

    // Save all groups to file
    public void saveGroups(ArrayList<Group> groups) throws IOException {
<<<<<<< HEAD
    System.out.println("writing2");
    groupManager.deleteJsonFile();

    if (groups.isEmpty()) {
        System.out.println("No groups to write");
        return;
=======
        groupManager.deleteJsonFile();
        for (Group group : groups) {
            groupManager.writeToJson(group);
        }
>>>>>>> 5bc3f581823a999bf7e723b97efa6986b9b6402a
    }

    for (Group group : groups) {
        System.out.println("writing");
        groupManager.writeToJson(group);
    }
}

    private Group parseGroup(JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String createdBy = jsonObject.getString("createdBy");
        String createdAt = jsonObject.getString("createdAt");
        String description = jsonObject.getString("description");
        String groupPhoto = jsonObject.getString("groupPhoto");
        Group group = new Group(id, name, createdBy, createdAt);
        group.setDescription(description);
        group.setGroupPhoto(groupPhoto);
        // Parse members
        JSONArray membersArray = jsonObject.getJSONArray("members");
        ArrayList<Member> members = new ArrayList<>();
        for (int i = 0; i < membersArray.length(); i++) {
            JSONObject memberObject = membersArray.getJSONObject(i);
            String memberUsername = memberObject.getString("memberUsername");
            String role = memberObject.getString("role");
            Member member = new Member(memberUsername);
            System.out.println(member);
            member.setRole(role);
            members.add(member);
        }
        group.setMembers(members);

        // Parse posts
        JSONArray postsArray = jsonObject.getJSONArray("posts");
        ArrayList<Content> posts = new ArrayList<>();
        for (int i = 0; i < postsArray.length(); i++) {
            JSONObject postObject = postsArray.getJSONObject(i);
            String contentId = postObject.getString("id");
            String authorId = postObject.getString("authorId");
            String caption = postObject.getString("caption");
            String photo = postObject.getString("photo");
            posts.add(ContentFactory.createContent("post", contentId, authorId, caption, photo));
        }
        group.setPosts(posts);
        JSONArray requestPostsArray = jsonObject.getJSONArray("requestPosts");
        ArrayList<Content> requestPosts = new ArrayList<>();
        for (int i = 0; i < requestPostsArray.length(); i++) {
            JSONObject postObject = requestPostsArray.getJSONObject(i);
            String contentId = postObject.getString("id");
            String authorId = postObject.getString("authorId");
            String caption = postObject.getString("caption");
            String photo = postObject.getString("photo");
            requestPosts.add(ContentFactory.createContent("post", contentId, authorId, caption, photo));
        }
        group.setRequestPosts(requestPosts);
        
        JSONArray requestMemberArray = jsonObject.getJSONArray("requestMembers");
        ArrayList<Member> requestMembers = new ArrayList<>();
        for (int i = 0; i < requestMemberArray.length(); i++) {
            JSONObject memberObject = requestMemberArray.getJSONObject(i);
            String memberUsername = memberObject.getString("memberUsername");
            String role = memberObject.getString("role");
            Member member = new Member(memberUsername);
            member.setRole(role);
            requestMembers.add(member);
        }
        group.setRequestMembers(requestMembers);
        
        return group;
    }
    
    public void addGroup(Group group) throws Exception {
        ArrayList<Group> groups = getAllGroups();
        groups.add(group);
        saveGroups(groups);
    }
    public void deletGroup(Group group) throws Exception {
        ArrayList<Group> groups = getAllGroups();
        groups.remove(group);
        saveGroups(groups);
    }
}
