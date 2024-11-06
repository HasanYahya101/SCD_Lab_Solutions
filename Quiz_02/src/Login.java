import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public javax.swing.JButton loginButton;
    public javax.swing.JButton employeeLoginButton;
    public javax.swing.JLabel loginLabel;
    public javax.swing.JLabel usernameLabel;
    public javax.swing.JLabel passwordLabel;
    public javax.swing.JPanel mainPanel;
    public javax.swing.JTextField usernameTextField;
    public javax.swing.JTextField passwordTextField;

    public Login() {
        init();
    }

    private void init() {

        mainPanel = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        employeeLoginButton = new javax.swing.JButton();
        setTitle("Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(500, 100, 499, 600);
        setSize(499, 600);
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setFont(new java.awt.Font("Arial", 0, 12));
        mainPanel.setLayout(null);

        loginLabel.setFont(new java.awt.Font("Arial", 1, 24));
        loginLabel.setText("Login");
        loginLabel.setBounds(230, 70, 100, 30);
        mainPanel.add(loginLabel);

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

        loginButton.setFont(new java.awt.Font("Arial", 1, 18));
        loginButton.setText("Login");
        loginButton.setBounds(200, 360, 100, 30);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
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
                PreparedStatement authenticate = null;
                ResultSet resultSet = null;

                try {
                    conn = Connect.getConnection();
                    authenticate = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
                    authenticate.setString(1, user);
                    authenticate.setString(2, pass);
                    resultSet = authenticate.executeQuery();

                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        new DashBoard(user).setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (resultSet != null) {
                            resultSet.close();
                        }
                        if (authenticate != null) {
                            authenticate.close();
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
        mainPanel.add(loginButton);

        employeeLoginButton.setText("Go to Signup Screen");
        employeeLoginButton.setBounds(150, 460, 180, 30);
        employeeLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new Signup().setVisible(true);
                dispose();
            }
        });
        mainPanel.add(employeeLoginButton);

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

        setVisible(true);
    }

}