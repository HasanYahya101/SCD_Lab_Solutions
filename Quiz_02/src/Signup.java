import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

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
                String user = usernameTextField.getText();
                String pass = passwordTextField.getText();

                if (user.equals("") || pass.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields!");
                    return;
                } else if (user.length() < 5 || pass.length() < 5) {
                    JOptionPane.showMessageDialog(null, "Username and Password must be at least 5 characters long!");
                    return;
                }
                Connection conn = null;
                PreparedStatement checkUserStmt = null;
                PreparedStatement insertUserStmt = null;
                ResultSet resultSet = null;
                try {
                    conn = Connect.getConnection();
                    if (conn != null && conn.isValid(2)) {
                        System.out.println("Connection is valid.");
                    } else {
                        System.out.println("Connection is invalid. Stop!");
                        return;
                    }

                    String checkUserQuery = "SELECT * FROM users WHERE username = ?";
                    checkUserStmt = conn.prepareStatement(checkUserQuery);
                    checkUserStmt.setString(1, user);
                    resultSet = checkUserStmt.executeQuery();
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Username already exists!");
                        return;
                    }

                    String insertUserQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
                    insertUserStmt = conn.prepareStatement(insertUserQuery);
                    insertUserStmt.setString(1, user);
                    insertUserStmt.setString(2, pass);
                    int affectedRows = insertUserStmt.executeUpdate();
                    if (affectedRows > 0) {
                        JOptionPane.showMessageDialog(null, "User registered successfully! Going to Login Screen.");
                        new Login().setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "User registration failed!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (checkUserStmt != null) {
                            checkUserStmt.close();
                        }
                        if (insertUserStmt != null) {
                            insertUserStmt.close();
                        }
                        if (conn != null) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        mainPanel.add(signUpButton);

        goToLoginButton.setText("Go to Login Screen");
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