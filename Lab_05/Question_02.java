import javax.swing.*;
import java.awt.*;

class DashboardFrame extends JFrame {
    public DashboardFrame(String username) {
        super("Dashboard");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 500);

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout());
        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomePanel.add(welcomeLabel);

        // Dashboard Buttons
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridLayout(2, 2));
        JButton profileButton = new JButton("Profile");
        JButton settingsButton = new JButton("Settings");
        JButton messagesButton = new JButton("Messages");
        messagesButton.addActionListener(e -> showNotificationDialog());
        JButton logoutButton = new JButton("Logout");
        dashboardPanel.add(profileButton);
        dashboardPanel.add(settingsButton);
        dashboardPanel.add(messagesButton);
        dashboardPanel.add(logoutButton);

        // Notifications Panel (East)
        JPanel notificationsPanel = new JPanel(new FlowLayout());
        JLabel notificationsLabel = new JLabel("Notifications here...");
        notificationsPanel.add(notificationsLabel);

        // Adding panels to dashboard
        add(welcomePanel, BorderLayout.NORTH);
        add(dashboardPanel, BorderLayout.CENTER);
        add(notificationsPanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void showNotificationDialog() {
        JLayeredPane layeredPane = getLayeredPane();
        JPanel overlay = new JPanel();
        overlay.setLayout(null);
        overlay.setBounds(0, 0, getWidth(), getHeight());

        // Notification label
        JLabel messageLabel = new JLabel("New Message!");
        messageLabel.setBounds(200, 200, 100, 30);
        overlay.add(messageLabel);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.setBounds(200, 230, 100, 30);
        closeButton.addActionListener(e -> layeredPane.remove(overlay));
        overlay.add(closeButton);

        // Ensure overlay has a solid background
        overlay.setBackground(new Color(200, 200, 200, 200));
        overlay.setOpaque(true);

        // Add to layered pane
        layeredPane.add(overlay, JLayeredPane.POPUP_LAYER);
        revalidate();
        repaint();
    }
}

class Login_Frame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login_Frame() {
        super("Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Login Form
        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setLayout(new GridLayout(2, 2));
        setSize(250, 150);
        // Add padding
        loginFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        loginFormPanel.add(usernameLabel);
        loginFormPanel.add(usernameField);
        loginFormPanel.add(passwordLabel);
        loginFormPanel.add(passwordField);

        // Login Button
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout());
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            this.dispose(); // Close login window after successful login
            new DashboardFrame(usernameField.getText());
        });
        loginButtonPanel.add(loginButton);

        // Add panels to frame
        add(loginFormPanel, BorderLayout.CENTER);
        add(loginButtonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}

public class Question_02 {
    public static void main(String[] args) {
        new Login_Frame();
    }
}
