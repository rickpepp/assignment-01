package pcd.ass01.view;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class BoidsSimulationView implements ChangeListener  {
    private JFrame frame;
    private BoidsView view;
    private BoidsPanel boidsPanel;
    private JSlider cohesionSlider, separationSlider, alignmentSlider;
    private JButton pauseButton;

    public BoidsSimulationView(JFrame frame, BoidsView view) {
        this.frame = frame;
        this.view = view;
    }

    public void show() {
        frame.setSize(this.view.getWidth(), this.view.getHeight());
        JPanel cp = new JPanel();
        LayoutManager layout = new BorderLayout();
        cp.setLayout(layout);
        boidsPanel = new BoidsPanel(this.view, view.getSimulator());
        cp.add(BorderLayout.CENTER, boidsPanel);
        JPanel slidersPanel = new JPanel();
        cohesionSlider = makeSlider();
        separationSlider = makeSlider();
        alignmentSlider = makeSlider();
        slidersPanel.add(new JLabel("Separation"));
        slidersPanel.add(separationSlider);
        slidersPanel.add(new JLabel("Alignment"));
        slidersPanel.add(alignmentSlider);
        slidersPanel.add(new JLabel("Cohesion"));
        slidersPanel.add(cohesionSlider);
        pauseButton = new JButton("Start");
        slidersPanel.add(pauseButton);
        pauseButton.addActionListener(e -> changeSimulationStatus());
        cp.add(BorderLayout.SOUTH, slidersPanel);
        frame.setContentPane(cp);
        frame.setVisible(true);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == separationSlider) {
            var val = separationSlider.getValue();
            this.view.getSimulator().setSeparationWeight(0.1*val);
        } else if (e.getSource() == cohesionSlider) {
            var val = cohesionSlider.getValue();
            this.view.getSimulator().setCohesionWeight(0.1*val);
        } else {
            var val = alignmentSlider.getValue();
            this.view.getSimulator().setAlignmentWeight(0.1*val);
        }
    }

    public void update(int frameRate, double avgFramerate) {
        boidsPanel.setFrameRate(frameRate);
        boidsPanel.setAvgFramerate(avgFramerate);
        boidsPanel.repaint();
    }

    private JSlider makeSlider() {
        var slider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        Hashtable labelTable = new Hashtable<>();
        labelTable.put( 0, new JLabel("0") );
        labelTable.put( 10, new JLabel("1") );
        labelTable.put( 20, new JLabel("2") );
        slider.setLabelTable( labelTable );
        slider.setPaintLabels(true);
        slider.addChangeListener(this);
        return slider;
    }

    public void changeSimulationStatus() {
        this.view.getSimulator().changeActiveState();
        if (this.view.getSimulator().getActualState())
            this.pauseButton.setText("Pause");
        else
            this.pauseButton.setText("Resume");
    }
}
