package com.example.calculator;

// Import necessary components for the calculator
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Create a Calculator class
public class Calculator implements ActionListener {

    // Define variables for calculator state
    boolean isOperatorClicked = false; // Indicates if an operator (+, -, *, /) has been clicked
    boolean isResultDisplayed = false; // Indicates if the result is displayed
    JFrame jf; // The calculator window
    JTextField inputTextField; // The display for input and output
    JButton clearButton; // The clear button (C)
    String currentValue = ""; // The current input value
    String operator = ""; // The current operator (+, -, *, /)
    float result = 0.0f; // The current result
    float lastResult = 0.0f; // Added to store the last result

    // Constructor for the Calculator
    public Calculator() {
        // Create the calculator window
        jf = new JFrame("Calculator");
        jf.setLayout(null);
        jf.setSize(350, 500);
        jf.setLocation(300, 150);

        // Create the input/output text field
        inputTextField = new JTextField();
        inputTextField.setBounds(30, 50, 210, 40);
        inputTextField.setFont(new Font("Arial", Font.PLAIN, 24));
        jf.add(inputTextField);

        // Create the clear button (C)
        clearButton = new JButton("C");
        clearButton.setBounds(250, 50, 60, 40);
        clearButton.setFont(new Font("Arial", Font.PLAIN, 24));
        clearButton.setBackground(new Color(255, 0, 0));
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        jf.add(clearButton);

        // Define button labels for the calculator
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            ".", "0", "=", "+"
        };

        // Create a panel for the calculator buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10)); // Grid layout for buttons
        panel.setBounds(30, 100, 280, 320);

        // Create buttons with labels and customize their appearance
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            if (label.matches("[0-9.]")) {
                button.setBackground(new Color(220, 220, 220));
            } else if (label.matches("[/+x\\-=]")) {
                button.setBackground(new Color(255, 165, 0));
                button.setForeground(Color.WHITE);
            }
            button.addActionListener(this);
            panel.add(button);
        }

        // Add the panel with buttons to the calculator window
        jf.add(panel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Main method to run the calculator
    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the text of the button that triggered the action
        String buttonText = ((JButton) e.getSource()).getText();

        // Handle button clicks based on their content
        if (buttonText.matches("[0-9.]")) {
            // Handle digit and decimal point buttons
            if (isResultDisplayed) {
                inputTextField.setText("");
                isResultDisplayed = false;
            }
            currentValue += buttonText;
            inputTextField.setText(currentValue);
        } else if (buttonText.matches("[/+x\\-=]")) {
            // Handle operator buttons
            if (!isOperatorClicked) {
                operator = buttonText;
                lastResult = Float.parseFloat(currentValue); // Store the last result
                currentValue = "";
                isOperatorClicked = true;
            } else {
                // Perform calculations based on the operator
                float currentValueFloat = Float.parseFloat(currentValue);
                switch (operator) {
                    case "+":
                        lastResult += currentValueFloat;
                        break;
                    case "-":
                        lastResult -= currentValueFloat;
                        break;
                    case "x":
                        lastResult *= currentValueFloat;
                        break;
                    case "/":
                        if (currentValueFloat != 0) {
                            lastResult /= currentValueFloat;
                        } else {
                            inputTextField.setText("Error");
                            isResultDisplayed = true;
                            return;
                        }
                        break;
                }
                inputTextField.setText(String.valueOf(lastResult));
                isOperatorClicked = true;
                operator = buttonText;
                isResultDisplayed = false;
                currentValue = "";
            }
        } else if (buttonText.equals("=")) {
            // Handle the equals button to show the final result
            if (!isResultDisplayed) {
                float currentValueFloat = Float.parseFloat(currentValue);
                switch (operator) {
                    case "+":
                        lastResult += currentValueFloat;
                        break;
                    case "-":
                        lastResult -= currentValueFloat;
                        break;
                    case "x":
                        lastResult *= currentValueFloat;
                        break;
                    case "/":
                        if (currentValueFloat != 0) {
                            lastResult /= currentValueFloat;
                        } else {
                            inputTextField.setText("Error");
                            isResultDisplayed = true;
                            return;
                        }
                        break;
                }
                inputTextField.setText(String.valueOf(lastResult));
                isResultDisplayed = true;
                currentValue = "";
            }
        } else if (buttonText.equals("C")) {
            // Handle the clear button to reset the calculator
            currentValue = "";
            operator = "";
            result = 0.0f;
            isOperatorClicked = false;
            inputTextField.setText("");
            isResultDisplayed = false;
        }
    }
}
