import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Dashboard {

    private JFrame dashBoardWindow;
    private JPanel headerPanel, centerPanel;
    private JLabel welcomeLabel;
    private JButton profileButton;
    private JButton messagesButton;
    private JButton notificationButton;
    public String userName; // check things against this username

    public Dashboard(String userName) {
        this.userName = userName;
        setupWindow();
        setupHeaderPanel();
        setupCenterPanel();
        addComponentsToPanels();
        addEventHandlers();
        dashBoardWindow.setVisible(true);
    }

    private void setupWindow() {
        dashBoardWindow = new JFrame("Dashboard");
        dashBoardWindow.setLayout(new BorderLayout());
        dashBoardWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashBoardWindow.setSize(800, 600);
        dashBoardWindow.setLocationRelativeTo(null);
    }

    private void setupHeaderPanel() {
        headerPanel = new JPanel(new FlowLayout());
        welcomeLabel = new JLabel("Dashboard", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerPanel.add(welcomeLabel);
    }

    private void setupCenterPanel() {
        centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        profileButton = createButton("Profile");
        messagesButton = createButton("Messages");
        notificationButton = createButton("Notifications");
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        return button;
    }

    private void addComponentsToPanels() {
        centerPanel.add(profileButton);
        centerPanel.add(messagesButton);
        centerPanel.add(notificationButton);
        dashBoardWindow.add(headerPanel, BorderLayout.NORTH);
        dashBoardWindow.add(centerPanel, BorderLayout.CENTER);
    }

    private void addEventHandlers() {
        profileButton.addActionListener(e -> showProfileDialog());
        messagesButton.addActionListener(e -> showMessageDialog());
        notificationButton.addActionListener(e -> showNotificationDialog());
    }

    private void showProfileDialog() {
        String[] profileData = getProfileData(userName);
        if (profileData != null) {
            JDialog dialog = createDialog("Profile", 500, 250);
            dialog.setLayout(new GridLayout(2, 1, 10, 10));
            dialog.add(createLabel("Username: " + profileData[0]));
            dialog.add(createLabel("Password: " + profileData[1]));
            dialog.setVisible(true);
        }
    }

    private void showMessageDialog() {
        String messages = getMessages(userName);
        JDialog dialog = createDialog("User Messages", 600, 300);
        dialog.setLayout(new BorderLayout());
        JTextArea messageArea = createTextArea(messages);
        dialog.add(new JScrollPane(messageArea), BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private void showNotificationDialog() {
        String notifications = getNotifications(userName);
        JDialog dialog = createDialog("Notifications", 600, 300);
        dialog.setLayout(new BorderLayout());
        JTextArea messageArea = createTextArea(notifications);
        dialog.add(new JScrollPane(messageArea), BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    private JDialog createDialog(String title, int width, int height) {
        JDialog dialog = new JDialog(dashBoardWindow, title);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        return dialog;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        return label;
    }

    private JTextArea createTextArea(String text) {
        JTextArea textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        textArea.setText(text);
        return textArea;
    }

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/lab_08_scd";
        String user = "admin";
        String password = "admin";
        return DriverManager.getConnection(url, user, password);
    }

    private String[] getProfileData(String userName) {
        try (Connection conn = getConnection()) {
            String query = "SELECT username, password FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString("username"), rs.getString("password")};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getMessages(String userName) {
        StringBuilder messages = new StringBuilder();
        try (Connection conn = getConnection()) {
            String query = "SELECT message FROM messages WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                messages.append(rs.getString("message")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages.toString();
    }

    private String getNotifications(String userName) {
        StringBuilder notifications = new StringBuilder();
        try (Connection conn = getConnection()) {
            String query = "SELECT notification FROM notifications WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                notifications.append(rs.getString("notification")).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications.toString();
    }

    public static void main(String[] args) {
        new Dashboard("yourUsername");
    }
}
