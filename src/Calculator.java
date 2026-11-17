//packages
import java.awt.*;
import javax.swing.*;

public class Calculator {
    int width = 360;
    int height = 540;

    Color lightGrey = new Color(212, 212, 210);
    Color darkGrey = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color orange = new Color(255, 149, 0);

    JFrame frame = new JFrame("Calculator");
    JPanel displayPanel = new JPanel();
    JLabel displayLabel = new JLabel();

    Calculator(){
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        //designing the label
        displayLabel.setBackground(black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);
        //setting up the panel
        displayPanel.setLayout(new BorderLayout()); //divide the panel in NSEW
        //putting the label inside the panel
        displayPanel.add(displayLabel);
        //putting the panel inside the frame
        frame.add(displayPanel);
    }
}
