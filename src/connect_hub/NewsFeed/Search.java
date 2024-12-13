package connect_hub.NewsFeed;

import connect_hub.Groups.Group;
import connect_hub.Groups.GroupRepository;
import connect_hub.UserManagement.ReadUsers;
import connect_hub.UserManagement.UserDetails;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Search extends javax.swing.JFrame {

    private DefaultListModel<String> combinedListModel; // Model for the combined list
    private List<String> allUsers; // List to store all users
    private List<String> allGroups; // List to store all groups
    private ArrayList<UserDetails> users;
    private List<Group> groups;
    private GroupRepository groupRepository = new GroupRepository("groups.json");
    private String email;
    /**
     * Creates new form Search
     */
    public Search(String email) {
        this.email = email;
        initComponents();
        initializeSearch();
    }
    public Search()
    {
        initComponents();
        initializeSearch();
    }
    private void initializeSearch() {
        try {
            users = ReadUsers.readUsersFromFile("users.json");
        } catch (IOException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        groups = groupRepository.getAllGroups();
        allGroups = new ArrayList<>();
        allUsers = new ArrayList<>();
        for (Group group : groups) {
            allGroups.add(group.getName());
        }

        for (UserDetails user : users) {
            allUsers.add(user.getUserName());
        }

        combinedListModel = new DefaultListModel<>();

        jCombinedList.setModel(combinedListModel);

        updateCombinedList(allUsers, allGroups); // Populate combined list initially

        // Add a DocumentListener to the text field to handle real-time filtering
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterCombinedList();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterCombinedList();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterCombinedList();
            }
        });

        // Add an ActionListener to the "View" button
        jButtonView.addActionListener(e -> viewSelectedItem());
    }

    private void filterCombinedList() {
        String query = jTextField1.getText().toLowerCase(); // Get the input text in lowercase

        List<String> filteredUsers = new ArrayList<>();
        for (String user : allUsers) {
            if (user.toLowerCase().contains(query)) {
                filteredUsers.add(user);
            }
        }

        List<String> filteredGroups = new ArrayList<>();
        for (String group : allGroups) {
            if (group.toLowerCase().contains(query)) {
                filteredGroups.add(group);
            }
        }

        updateCombinedList(filteredUsers, filteredGroups);
    }

    private void updateCombinedList(List<String> users, List<String> groups) {
        combinedListModel.clear(); // Clear the existing items
        for (String user : users) {
            combinedListModel.addElement("[User] " + user); // Add each user with a prefix
        }
        for (String group : groups) {
            combinedListModel.addElement("[Group] " + group); // Add each group with a prefix
        }
    }

    private void viewSelectedItem() {
        String selectedItem = jCombinedList.getSelectedValue();
        if(selectedItem.contains("User"))
        {
            String username = selectedItem.replace("[User] ", "").trim();
            for(UserDetails user: users)
            {
                if(user.getUserName().equals(username))
                {
                    new viewFrinedProfile(user.getEmail()).setVisible(true);
                    setVisible(false);
                }
            }
        }
        else
        {
            String GroupName = selectedItem.replace("[Group] ", "").trim();
            for(Group group: groups)
            {
                if(group.getName().equals(GroupName))
                {
                    new viewGroup(group, email).setVisible(true);
                    setVisible(false);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jCombinedList = new javax.swing.JList<>();
        jButtonView = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setText("Search...");
        jScrollPane1.setViewportView(jCombinedList);

        jButtonView.setText("View");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jButtonView, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonView)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Search().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JList<String> jCombinedList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jButtonView;
    // End of variables declaration
}
