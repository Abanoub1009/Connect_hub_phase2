package connect_hub.Groups;

public class GroupController {

    private final GroupService groupService;

    public GroupController() {
        this.groupService = new GroupService();
    }

    public void createGroup(String name, String description, String groupPhoto, String createdBy) {
        try {
            groupService.createGroup(name, description, groupPhoto, createdBy);
            System.out.println("Group created successfully!");
        } catch (Exception e) {
            System.err.println("Error creating group: " + e.getMessage());
        }
    }

    public void promoteToAdmin(String groupname, String targetUsername) {
        try {
            groupService.promoteToAdmin(groupname, targetUsername);
            System.out.println("User promoted to admin successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public String viewGroupDetails(String groupName) {
        try {
            Group group = groupService.getGroupByName(groupName);
            return "Group Name: " + group.getName() + "\n"
                    + "Description: " + group.getDescription() + "\n"
                    + "Created By: " + group.getCreatedBy() + "\n"
                    + "Members: " + group.getMembers().size();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

