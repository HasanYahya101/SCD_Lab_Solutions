import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.lang.reflect.Array;
import java.sql.Connection;

public class InboxPanel extends javax.swing.JPanel {

    class dataFill {
        public String sender;
        public String message;
    }

    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel inboxLabel;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable messagesTable;

    public InboxPanel(String username) {
        init(username);
    }

    private void init(String username) {

        inboxLabel = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        messagesTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(730, 700));
        setMinimumSize(new java.awt.Dimension(730, 700));
        setLayout(null);

        inboxLabel.setFont(new java.awt.Font("Arial", 1, 24));
        inboxLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inboxLabel.setText("Inbox");
        inboxLabel.setBounds(320, 50, 80, 30);
        add(inboxLabel);

        Connection conn = null;
        PreparedStatement getmessageswithreciever_thisuser = null;
        ResultSet messageswithreciever_thisuser = null;
        int size = 0;
        Object[][] data = null;
        try {
            conn = Connect.getConnection();
            getmessageswithreciever_thisuser = conn
                    .prepareStatement("SELECT * FROM messages WHERE recipient_username = ?");
            getmessageswithreciever_thisuser.setString(1, username);
            messageswithreciever_thisuser = getmessageswithreciever_thisuser.executeQuery();
            ArrayList<dataFill> dataFillList = new ArrayList<dataFill>();
            while (messageswithreciever_thisuser.next()) {
                dataFill dataFillObj = new dataFill();
                dataFillObj.sender = messageswithreciever_thisuser.getString("sender_username");
                dataFillObj.message = messageswithreciever_thisuser.getString("message_content");
                dataFillList.add(dataFillObj);
            }
            size = dataFillList.size();
            data = new Object[size][2];
            for (int i = 0; i < size; i++) {
                data[i][0] = dataFillList.get(i).sender;
                data[i][1] = dataFillList.get(i).message;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (getmessageswithreciever_thisuser != null) {
                    getmessageswithreciever_thisuser.close();
                }
                if (messageswithreciever_thisuser != null) {
                    messageswithreciever_thisuser.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        messagesTable.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[] {
                        "From", "Message"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        messagesTable.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = messagesTable.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    String message = (String) messagesTable.getValueAt(row, 1);
                    String user = (String) messagesTable.getValueAt(row, 0);
                    javax.swing.JOptionPane.showMessageDialog(null, "" + user + ": " + message, "Message",
                            javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        messagesTable.setFillsViewportHeight(true);
        messagesTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(messagesTable);

        javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        centerRenderer.setVerticalAlignment(javax.swing.JLabel.CENTER);

        for (int i = 0; i < messagesTable.getColumnCount(); i++) {
            messagesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tableScrollPane.setBounds(0, 100, 720, 500);
        if (messagesTable.getColumnModel().getColumnCount() > 0) {
            messagesTable.getColumnModel().getColumn(0).setResizable(false);
            messagesTable.getColumnModel().getColumn(0).setPreferredWidth((int) (0.15 * tableScrollPane.getWidth()));
            messagesTable.getColumnModel().getColumn(1).setResizable(false);
            messagesTable.getColumnModel().getColumn(1).setPreferredWidth((int) (0.85 * tableScrollPane.getWidth()));
        }
        add(tableScrollPane);

        refreshButton.setFont(new java.awt.Font("Arial", 1, 18));
        refreshButton.setText("Refresh");
        refreshButton.setBounds(320, 614, 120, 30);
        // on click refresh
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InboxPanel inboxPanel = new InboxPanel(username);
                inboxPanel.setBounds(0, 0, 730, 700);
                javax.swing.JPanel rightPanel = (javax.swing.JPanel) getParent();
                rightPanel.add(inboxPanel, "MessagesInbox");
                ((java.awt.CardLayout) rightPanel.getLayout()).show(rightPanel, "MessagesInbox");
            }
        });
        add(refreshButton);
    }

}
