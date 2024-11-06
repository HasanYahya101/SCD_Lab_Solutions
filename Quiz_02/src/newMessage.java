import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Exception;

public class newMessage extends javax.swing.JPanel {

    private javax.swing.JButton sendButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel recipientLabel;
    private javax.swing.JScrollPane messageScrollPane;
    private javax.swing.JTextArea messageTextArea;
    private javax.swing.JTextField recipientTextField;

    public newMessage(String username) {
        init(username);
    }

    private void init(String username) {

        messageLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        recipientTextField = new javax.swing.JTextField();
        recipientLabel = new javax.swing.JLabel();
        messageScrollPane = new javax.swing.JScrollPane();
        messageTextArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setSize(730, 700);
        setLayout(null);

        messageLabel.setFont(new java.awt.Font("Arial", 1, 18));
        messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLabel.setText("Message:");
        messageLabel.setBounds(300, 250, 140, 30);
        add(messageLabel);

        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        titleLabel.setText("New Message");
        titleLabel.setBounds(290, 50, 200, 30);
        add(titleLabel);

        recipientTextField.setFont(new java.awt.Font("Arial", 1, 12));
        recipientTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recipientTextField.setBounds(320, 150, 220, 30);
        add(recipientTextField);

        recipientLabel.setFont(new java.awt.Font("Arial", 1, 18));
        recipientLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recipientLabel.setText("Recipient:");
        recipientLabel.setBounds(200, 150, 100, 30);
        add(recipientLabel);

        messageTextArea.setColumns(20);
        messageTextArea.setRows(5);
        messageScrollPane.setViewportView(messageTextArea);
        messageScrollPane.setBounds(50, 300, 640, 260);
        // the text needs to be centered
        messageTextArea.setFont(new java.awt.Font("Arial", 1, 12));
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setAlignmentX(javax.swing.JTextArea.CENTER_ALIGNMENT);
        add(messageScrollPane);

        sendButton.setFont(new java.awt.Font("Arial", 1, 18));
        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String recipient = recipientTextField.getText();
                String message = messageTextArea.getText();

                if (recipient.isEmpty() || message.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Recipient or message cannot be empty.");
                    return;
                }

                if (recipient.length() < 5 || message.length() < 5) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Recipient or message is too short.");
                    return;
                }

                Connection conn = null;
                PreparedStatement recipientExists = null;
                PreparedStatement sendMessage = null;
                ResultSet rs = null;
                try {
                    conn = Connect.getConnection();
                    recipientExists = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                    recipientExists.setString(1, recipient);
                    rs = recipientExists.executeQuery();

                    if (rs.next()) {
                        sendMessage = conn
                                .prepareStatement(
                                        "INSERT INTO messages (sender_username, recipient_username, message_content) VALUES (?, ?, ?)");
                        sendMessage.setString(1, username);
                        sendMessage.setString(2, recipient);
                        sendMessage.setString(3, message);
                        sendMessage.executeUpdate();
                        javax.swing.JOptionPane.showMessageDialog(null, "Message sent successfully.");
                        recipientTextField.setText("");
                        messageTextArea.setText("");
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Recipient does not exist.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                        }
                        if (recipientExists != null) {
                            recipientExists.close();
                        }
                        if (sendMessage != null) {
                            sendMessage.close();
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
        sendButton.setBounds(310, 600, 130, 40);
        add(sendButton);
    }
}
