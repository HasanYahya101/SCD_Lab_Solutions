import javax.swing.*;
import java.awt.*;

interface JFrameBuilder {
    JFrameBuilder setTitle(String title);

    JFrameBuilder setDimensions(int width, int height);

    JFrameBuilder addPositiveButton(String text);

    JFrameBuilder addNegativeButton(String text);

    JFrame build();
}

class CustomJFrameBuilder implements JFrameBuilder {
    private JFrame frame;
    private String title = "Default Title";
    private int width = 400;
    private int height = 300;
    private String positiveButtonText = "Yes";
    private String negativeButtonText = "No";

    public CustomJFrameBuilder() {
        frame = new JFrame();
    }

    @Override
    public JFrameBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public JFrameBuilder setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    @Override
    public JFrameBuilder addPositiveButton(String text) {
        this.positiveButtonText = text;
        return this;
    }

    @Override
    public JFrameBuilder addNegativeButton(String text) {
        this.negativeButtonText = text;
        return this;
    }

    @Override
    public JFrame build() {
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton positiveButton = new JButton(positiveButtonText);
        JButton negativeButton = new JButton(negativeButtonText);

        frame.add(positiveButton);
        frame.add(negativeButton);

        return frame;
    }
}

public class Question_01 {
    public static void main(String[] args) {
        JFrame frame = new CustomJFrameBuilder()
                .setTitle("Custom Title")
                .setDimensions(300, 200)
                .addPositiveButton("Yes")
                .addNegativeButton("No")
                .build();

        frame.setVisible(true);
    }
}