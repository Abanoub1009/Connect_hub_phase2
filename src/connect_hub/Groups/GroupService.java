package connect_hub.Groups;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupService {
    private final GroupRepository groupRepository;
    private final PermissionService permissionService;

    public GroupService() {
        this.groupRepository = new GroupRepository("groups.json");
        this.permissionService = new PermissionService();
    }

    public void createGroup(String name, String description, String groupPhoto, String createdBy) throws Exception {
        String groupId = UUID.randomUUID().toString();
        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        ArrayList<Member> members = new ArrayList<>();
        Member primaryAdmin = new Member(createdBy);
        primaryAdmin.setRole("primary_admin");
        members.add(primaryAdmin);
        Group group = new Group(groupId,name,createdBy,createdAt);
        group.setDescription(description);
        group.setGroupPhoto(groupPhoto);
        group.setMembers(members);

        groupRepository.addGroup(group);
    }

    public void promoteToAdmin(String groupName, String username, String targetUsername) throws Exception {
        Group group = findGroupById(groupName);
        if (!permissionService.canPromoteOrDemote(username, group)) {
            throw new Exception("Permission denied: Only the primary admin can promote or demote admins.");
        }

        updateMemberRole(group, targetUsername, "admin");
    }

    private Group findGroupById(String groupName) throws Exception {
        ArrayList<Group> groups = groupRepository.getAllGroups();
        for(Group group: groups)
        {
            if(group.getName().equals(groupName))
            {
                return group;
            }
        }
        return null;
    }

    private void updateMemberRole(Group group, String targetUsername, String newRole) throws Exception {
        ArrayList<Group> groups = new ArrayList<>();
        groups.add(group);
        for (Member member : group.getMembers()) {
            if (member.getMemberUsername().equals(targetUsername)) {
                member.setRole(newRole);
                groupRepository.saveGroups(groups);
                return;
            }
        }
        throw new Exception("User not found in the group.");
    }
}
