//packages
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int width = 360;
    int height = 540;

    String A = "0";
    String Operator = null;
    String B  = null;

    void clearAll() {
        A = "0";
        Operator = null;
        B = null;
    }

    String removeZeroDecimal(double numDisplay){
        if(numDisplay % 1 == 0){
            return Integer.toString((int)numDisplay);
        }
        return Double.toString(numDisplay);
    }

    double calculate(double numA, double numB, String op) {
        if (op.equals("+")) return numA + numB;
        if (op.equals("-")) return numA - numB;
        if (op.equals("×")) return numA * numB;
        if (op.equals("÷")) return numA / numB;
        return numB;
    }

    Color lightGrey = new Color(212, 212, 210);
    Color darkGrey = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color orange = new Color(255, 149, 0);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");
    JPanel displayPanel = new JPanel();
    JLabel displayLabel = new JLabel();
    JPanel buttonPanel = new JPanel();

    Calculator(){
        //frame.setVisible(true);
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
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5, 4));
        buttonPanel.setBackground(black);
        frame.add(buttonPanel);


        //for loop to create buttons, so no need to create buttons 1by1
        for(int i = 0; i < buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));
                if (Arrays.asList(topSymbols).contains(buttonValue)){
                    button.setBackground(lightGrey);
                    button.setForeground(black);
                }else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                    button.setBackground(orange);
                    button.setForeground(Color.white);
                }else{
                    button.setBackground(darkGrey);
                    button.setForeground(Color.white);
                }
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if (Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue == "AC"){
                            clearAll();
                            displayLabel.setText("0");
                        }else if(buttonValue == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = numDisplay*-1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }else if(buttonValue ==  "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay = numDisplay/100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                        }
                    }else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                    if(buttonValue.equals("=")){
                        if(Operator != null){
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(displayLabel.getText());
                            displayLabel.setText(removeZeroDecimal(calculate(numA, numB, Operator)));
                            clearAll();
                        }
                    }else if("+-×÷".contains(buttonValue)){
                        if(Operator != null){
                            //an operator is already pending, so finish that calculation first
                            double numA = Double.parseDouble(A);
                            double numB = Double.parseDouble(displayLabel.getText());
                            A = removeZeroDecimal(calculate(numA, numB, Operator));
                        }else{
                            A = displayLabel.getText();
                        }
                        displayLabel.setText("0");
                        Operator = buttonValue;
                    }
                }else{
                        if(buttonValue == "."){
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }else if("0987654321".contains(buttonValue)){
                            if(displayLabel.getText().equals("0")){
                                displayLabel.setText(buttonValue);
                            }else{
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }
        frame.setVisible(true);
    }
}
