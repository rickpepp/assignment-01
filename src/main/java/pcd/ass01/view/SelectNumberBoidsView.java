package pcd.ass01.view;

import javax.swing.*;
import java.awt.*;

public class SelectNumberBoidsView {

    private JFrame frame;
    private BoidsView view;
    private JComboBox<String> threadMode;
    private JTextField inputBox;

    public SelectNumberBoidsView(JFrame frame, BoidsView view) {
        this.frame = frame;
        this.view = view;
    }

    public void show() {
        frame.setSize(380, 250);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JLabel label = new JLabel("Insert number of boids:");
        inputBox = new JTextField(20);
        JLabel modelLabel = new JLabel("Select thread mode:");
        String[] optionToChoose = {"Sequential", "Default Multithread", "Executor Framework"};
        threadMode = new JComboBox<>(optionToChoose);
        JButton button = new JButton("Start Simulation");
        button.addActionListener(e -> actionStartSimulationButton());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);
        gbc.gridy = 1;
        panel.add(inputBox, gbc);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(threadMode, gbc);
        gbc.gridy = 2;
        panel.add(modelLabel, gbc);
        gbc.gridy = 4;
        panel.add(button, gbc);
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void actionStartSimulationButton() {
        try {
            this.view.passToSimulationView(Integer.parseInt(inputBox.getText()), (String) threadMode.getSelectedItem());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null,
                    "Input must be an integer",
                    "Invalid Argument",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
