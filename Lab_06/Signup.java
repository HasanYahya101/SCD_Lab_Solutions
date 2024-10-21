import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Signup extends javax.swing.JFrame {

    public JButton signUpButton;
    public JButton goToLoginButton;
    public JLabel signUpLabel;
    public JLabel usernameLabel;
    public JLabel passwordLabel;
    public JPanel mainPanel;
    public JTextField usernameTextField;
    public JTextField passwordTextField;

    public Signup() {
        init();
    }

    private void init() {

        mainPanel = new javax.swing.JPanel();
        signUpLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        signUpButton = new javax.swing.JButton();
        goToLoginButton = new javax.swing.JButton();
        setSize(499, 600);
        setTitle("Sign Up");
        setLocationRelativeTo(null);
        setBounds(500, 100, 499, 600);
        setResizable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1000, 700));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setFont(new java.awt.Font("Arial", 0, 12));
        mainPanel.setLayout(null);

        signUpLabel.setFont(new java.awt.Font("Arial", 1, 24));
        signUpLabel.setText("Sign Up");
        signUpLabel.setBounds(210, 70, 100, 30);
        mainPanel.add(signUpLabel);

        usernameLabel.setFont(new java.awt.Font("Arial", 1, 18));
        usernameLabel.setText("Username:");
        usernameLabel.setBounds(90, 190, 100, 30);
        mainPanel.add(usernameLabel);

        usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameTextField.setBounds(210, 190, 180, 30);
        mainPanel.add(usernameTextField);

        passwordLabel.setFont(new java.awt.Font("Arial", 1, 18));
        passwordLabel.setText("Password:");
        passwordLabel.setBounds(90, 260, 100, 30);
        mainPanel.add(passwordLabel);

        passwordTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordTextField.setBounds(210, 260, 180, 30);
        mainPanel.add(passwordTextField);

        signUpButton.setFont(new java.awt.Font("Arial", 1, 18));
        signUpButton.setText("SignUp");
        signUpButton.setBounds(200, 360, 100, 30);
        signUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                if (username.length() < 7) {
                    JOptionPane.showMessageDialog(mainPanel, "Username must be at least 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (password.length() < 7) {
                    JOptionPane.showMessageDialog(mainPanel, "Password must be at least 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Database connection and insertion
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab_08_scd", "admin", "admin");
                    String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.executeUpdate();
                    connection.close();

                    JOptionPane.showMessageDialog(mainPanel, "Sign Up Successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    usernameTextField.setText("");
                    passwordTextField.setText("");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(mainPanel, "Database Error: " + e.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(signUpButton);

        goToLoginButton.setText("Go to Login");
        goToLoginButton.setBounds(150, 490, 200, 30);
        goToLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Login().setVisible(true);
                dispose();
            }
        });
        mainPanel.add(goToLoginButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

    }
}
