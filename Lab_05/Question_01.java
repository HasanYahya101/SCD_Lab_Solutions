import javax.swing.*;
import java.awt.*;

class Login_Frame extends JFrame {
    public Login_Frame() {
        super("Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Login Form
        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setLayout(new GridLayout(2, 2));
        setSize(250, 150);
        loginFormPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();

        loginFormPanel.add(usernameLabel);
        loginFormPanel.add(usernameField);
        loginFormPanel.add(passwordLabel);
        loginFormPanel.add(passwordField);

        // Login Button
        JPanel loginButtonPanel = new JPanel();
        loginButtonPanel.setLayout(new FlowLayout());
        JButton loginButton = new JButton("Login");
        loginButtonPanel.add(loginButton);

        // Add panels to frame
        add(loginFormPanel, BorderLayout.CENTER);
        add(loginButtonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged in successfully!!!", "Login", JOptionPane.INFORMATION_MESSAGE);
        });

        // pack(); // use pack if you want screen to fit its components size
        setVisible(true);
    }
}

public class Question_01 {
    public static void main(String[] args) {
        new Login_Frame();
    }
}