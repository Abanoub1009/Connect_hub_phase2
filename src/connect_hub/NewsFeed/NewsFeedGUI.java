package connect_hub.NewsFeed;

import connect_hub.ContentCreation.Content;
import connect_hub.ContentCreation.Story;
import connect_hub.ContentCreation.Post;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NewsFeedGUI extends JFrame {
    private NewsFeedManager newsFeedManager;
    private JTextArea newsFeedArea;

    public NewsFeedGUI(String filePath) {
        newsFeedManager = new NewsFeedManager(filePath);

        setTitle("News Feed");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Layout
        setLayout(new BorderLayout());

        // News feed area to display posts and stories
        newsFeedArea = new JTextArea();
        newsFeedArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(newsFeedArea);

        // Button to refresh the news feed
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshNewsFeed());

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(refreshButton, BorderLayout.SOUTH);

        // Initial load of the news feed
        refreshNewsFeed();
    }

    private void refreshNewsFeed() {
        // Get the latest content from the manager
        ArrayList<Content> contentList = newsFeedManager.getNewsFeed();

        // Clear current news feed area
        newsFeedArea.setText("");

        // Display all posts and stories in the news feed area
        for (Content content : contentList) {
            if (content instanceof Post) {
                Post post = (Post) content;
                newsFeedArea.append("Post: " + post.getCaption() + "\n");
                newsFeedArea.append("By: " + post.getAuthorId() + "\n");
                newsFeedArea.append("Photo: " + post.getPhoto() + "\n\n");
            } else if (content instanceof Story) {
                Story story = (Story) content;
                newsFeedArea.append("Story: " + story.getCaption() + "\n");
                newsFeedArea.append("By: " + story.getAuthorId() + "\n");
                newsFeedArea.append("Photo: " + story.getPhoto() + "\n");
                newsFeedArea.append("Expired: " + story.isExpired() + "\n\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NewsFeedGUI("path_to_content_file"));
    }
}
