import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends javax.swing.JFrame {

    public javax.swing.JButton loginButton;
    public javax.swing.JButton customerPanelButton;
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
        customerPanelButton = new javax.swing.JButton();
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
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                if (username.length() < 7) {
                    JOptionPane.showMessageDialog(mainPanel, "Username must be at least 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (password.length() < 7) {
                    JOptionPane.showMessageDialog(mainPanel, "Password must be at least 7 characters long", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(mainPanel, "Login Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Dashboard();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(loginButton);

        employeeLoginButton.setText("Go to Signup");
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

    private boolean authenticate(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/lab_08_scd";
        String dbUser = "admin";
        String dbPassword = "admin";

        try (Connection connection = DriverManager.getConnection(url, dbUser, dbPassword)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainPanel, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
