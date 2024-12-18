/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect_hub.Groups;

/**
 *
 * @author bibos_bz87qw5
 */
public class Member {
    private String memberUsername;
    private String role;

    public Member(String memberUsername) {
        this.memberUsername = memberUsername;
        this.role = "member";
    }

    Member() {
      
    }

    public String getMemberUsername() {
        return memberUsername;
    }

    public void setMemberUsername(String memberUsername) {
        this.memberUsername = memberUsername;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" + "memberUsername=" + memberUsername + ", role=" + role + '}';
    }
    
}
