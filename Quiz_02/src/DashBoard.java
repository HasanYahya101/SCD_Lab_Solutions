import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashBoard extends JFrame {
    private JPanel rightPanel;
    private CardLayout cardLayout;

    public DashBoard(String username) {
        setTitle("Dashboard");
        setSize(1000, 700); // 1000 x 700
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.27), getHeight())); // 27% of the width

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // add top and bottom padding
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        menuPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton msg_inbox_btn = createMenuButton("Messages Inbox");
        menuPanel.add(msg_inbox_btn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton sent_bttn = createMenuButton("Sent Messages");
        menuPanel.add(sent_bttn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton new_bttn = createMenuButton("Send new Message");
        menuPanel.add(new_bttn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        JButton logout_btt = createMenuButton("Logout");
        menuPanel.add(logout_btt);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 13))); // Add spacing between buttons

        menuPanel.setVisible(true);

        // Create right panel with CardLayout
        rightPanel = new JPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);

        // Add different panels to the right panel
        rightPanel.add(new newMessage(username), "NewMessage");
        rightPanel.add(new InboxPanel(username), "MessagesInbox");
        rightPanel.add(new SentPanel(username), "SentMessages");

        // Add action listeners to buttons
        msg_inbox_btn.addActionListener(e -> cardLayout.show(rightPanel, "MessagesInbox"));
        sent_bttn.addActionListener(e -> cardLayout.show(rightPanel, "SentMessages"));
        new_bttn.addActionListener(e -> cardLayout.show(rightPanel, "NewMessage"));
        logout_btt.addActionListener(e -> {
            dispose();
            new Login();
        });

        add(menuPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(true);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(Color.BLACK, 10)); // Rounded border with a radius of 10
        button.setPreferredSize(new Dimension(200, 40));
        button.setMaximumSize(new Dimension(200, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.BLACK);
                button.setForeground(Color.WHITE);
                button.setBorder(new RoundedBorder(Color.WHITE, 10));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setBorder(new RoundedBorder(Color.BLACK, 10));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        new DashBoard("test user");
    }
}

class RoundedBorder extends AbstractBorder {
    private Color color;
    private int radius;

    RoundedBorder(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = this.radius + 1;
        insets.right = insets.bottom = this.radius + 2;
        return insets;
    }
}