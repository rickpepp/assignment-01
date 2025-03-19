package pcd.ass01.view;

import javax.swing.*;
import java.awt.*;

public class SelectNumberBoidsView {

    private JFrame frame;
    private BoidsView view;

    public SelectNumberBoidsView(JFrame frame, BoidsView view) {
        this.frame = frame;
        this.view = view;
    }

    public void show() {
        frame.setSize(380, 220);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Insert number of boids:");
        JTextField inputBox = new JTextField(20);
        JButton button = new JButton("Start Simulation");
        button.addActionListener(e -> {
            try {
                this.view.selectedNumberOfBoids(Integer.parseInt(inputBox.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Input must be an integer",
                        "Invalid Argument",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);
        gbc.gridy = 1;
        panel.add(inputBox, gbc);
        gbc.gridy = 2;
        panel.add(button, gbc);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
