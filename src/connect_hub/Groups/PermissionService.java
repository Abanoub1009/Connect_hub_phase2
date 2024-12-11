package connect_hub.Groups;

import java.util.ArrayList;

/**
 *
 * @author bibos_bz87qw5
 */
public class PermissionService {
    public static boolean canPromoteOrDemote(String username, Group group) {
        return isPrimaryAdmin(username, group);
    }

    public static boolean canManagePosts(String username, Group group) {
        return isAdmin(username, group) || isPrimaryAdmin(username, group);
    }

    public static boolean canRemoveMembers(String username, Group group) {
        return isAdmin(username, group) || isPrimaryAdmin(username, group);
    }

    public static boolean canDeleteGroup(String username, Group group) {
        return isPrimaryAdmin(username, group);
    }

    private static boolean isPrimaryAdmin(String username, Group group) {
        return getRole(username, group).equals("primary_admin");
    }

    private static boolean  isAdmin(String username, Group group) {
        String role = getRole(username, group);
        return role.equals("admin") || role.equals("primary_admin");
    }

    private static String getRole(String username, Group group) {
        ArrayList<Member> members= group.getMembers();
        for(Member member: members)
        {
            if(member.getMemberUsername().equals(username))
            {
                return member.getRole();
            }
        }
        return null;
    }
}
