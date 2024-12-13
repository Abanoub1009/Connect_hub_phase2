/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package connect_hub.Groups;

import connect_hub.UserManagement.ReadUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author bibos_bz87qw5
 */
public class GetMemberForAdmin extends javax.swing.JFrame {

    Group g;
    String email;
    ArrayList<Member> members;
    ArrayList<UserDetails> users;

    /**
     * Creates new form GetMembersWindow
     */
    public GetMemberForAdmin(Group g, String email) {
        initComponents();
        this.g = g;
        this.email = email;
        openWindow();
    }

    public void openWindow() {
        users = new ArrayList<>();
        try {
            users = ReadUsers.readUsersFromFile("users.json");
        } catch (IOException ex) {
            Logger.getLogger(GroupActivites.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserDetails user = new UserDetails();
        user = user.getSpecificUser(users, email);
        members = g.getMembers();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < members.size(); i++) {
            if (!user.getUserName().equals(members.get(i).getMemberUsername())) {
                String postInfo = "UserName:" + members.get(i).getMemberUsername() + "    Role:" + members.get(i).getRole();

                listModel.addElement(postInfo);
            }
        }
        jList1.setModel(listModel);
    }

    public GetMemberForAdmin() {
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();

<<<<<<< HEAD
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
=======
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
>>>>>>> 5bc3f581823a999bf7e723b97efa6986b9b6402a

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(55, 204, 255));
        jLabel1.setText("List of members ");

        jScrollPane1.setViewportView(jList1);

        jButton1.setText("remove member");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jButton1)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int index = jList1.getSelectedIndex();
        users = new ArrayList<>();
        try {
            users = ReadUsers.readUsersFromFile("users.json");
        } catch (IOException ex) {
            Logger.getLogger(GroupActivites.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserDetails user = new UserDetails();
        user = user.getSpecificUser(users, email);
        members = g.getMembers();
        Member m = members.get(index);
        System.out.println(m);
        for (Member member : members) {

            if (member.getMemberUsername().equals(user.getUserName())) {
                String x = member.getRole();
                System.out.println(x);
                if (x.equals("admin") || x.equals("primary_admin")) {
                    if (m.getRole().equals("primary_admin")) {
                        JOptionPane.showMessageDialog(this, "Can't remove this member", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        g.removeMember(m);
                        JOptionPane.showMessageDialog(this, "Member removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        openWindow();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Only admins can remove members", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        openWindow();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GetMemberForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetMemberForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetMemberForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetMemberForAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetMemberForAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
