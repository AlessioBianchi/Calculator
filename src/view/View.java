package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Optional;

public class View {

    private static final int STANDARD_HEIGHT = 450;
    private static final int STANDARD_WIDTH = 350;

    private final JTextField display;

    private static final JButton[][] DEFAULT_BUTTONS = {
            { new JButton("<="), new JButton("AC"), new JButton("%"), new JButton("/") },
            { new JButton("7"), new JButton("8"), new JButton("9"), new JButton("X") },
            { new JButton("4"), new JButton("6"), new JButton("7"), new JButton("-") },
            { new JButton("1"), new JButton("2"), new JButton("3"), new JButton("+") },
            { new JButton("+/-"), new JButton("0"), new JButton(","), new JButton("=") }
    };

    public View() {
        JFrame frame = new JFrame("Smart Calculator");

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // screen
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFocusable(false);
        display.setText("0");
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        panel.add(display, gbc);

        // buttons
        JPanel buttonsPanel = new JPanel();
        gbc.gridy = 2;
        gbc.weighty = 0.85;
        panel.add(buttonsPanel, gbc);

        buttonsPanel.setLayout(new GridLayout(5, 4));
        buildButtons(buttonsPanel);

        frame.add(panel);
        frame.setSize(STANDARD_WIDTH, STANDARD_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public void updateDisplay(String value) {
        this.display.setText(value);
    }

    public void addNumberListener(ActionListener listener) {
        JButton[] numbers = this.getNumberButtons();
        Arrays.stream(numbers)
                .forEach(button -> button.addActionListener(listener));
    }

    public void addOperationListener(ActionListener listener) {
        JButton[] numbers = this.getOperationButtons();
        Arrays.stream(numbers)
                .forEach(button -> button.addActionListener(listener));
    }

    public JTextField getDisplay() {
        return this.display;
    }

    public JButton[] getAllButtons(){
        return Optional.of(DEFAULT_BUTTONS)
                .map(buttons -> Arrays.stream(buttons)
                        .flatMap(Arrays::stream)
                        .toArray(JButton[]::new))
                .orElse(new JButton[0]);
    }

    public JButton[] getNumberButtons(){
        return Optional.of(DEFAULT_BUTTONS)
                .map(buttons -> Arrays.stream(buttons)
                        .flatMap(Arrays::stream)
                        .filter(button -> button.getText().matches("[0-9]") ||
                                button.getText().equals(","))
                        .toArray(JButton[]::new))
                .orElse(new JButton[0]);
    }

    public JButton[] getOperationButtons(){
        return Optional.of(DEFAULT_BUTTONS)
                .map(buttons -> Arrays.stream(buttons)
                        .flatMap(Arrays::stream)
                        .filter(button -> !button.getText().matches("[0-9]") &&
                                !button.getText().equals(","))
                        .toArray(JButton[]::new))
                .orElse(new JButton[0]);
    }

    private void buildButtons(JPanel panel){
        Arrays.stream(DEFAULT_BUTTONS)
                .flatMap(Arrays::stream)
                .forEach(button -> {
                    button.setFocusable(false);
                    panel.add(button);
                });
    }
}
