package pcd.ass01.view;

import pcd.ass01.controller.BoidsSimulator;
import pcd.ass01.model.BoidsModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.Hashtable;

public class BoidsView implements ChangeListener {

	private JFrame frame;
	private BoidsPanel boidsPanel;
	private JSlider cohesionSlider, separationSlider, alignmentSlider;
	private BoidsSimulator simulator;
	private JButton pauseButton;
	private int width, height;
	
	public BoidsView(BoidsSimulator simulator, int width, int height) {
		this.simulator = simulator;
		this.width = width;
		this.height = height;
		
		frame = new JFrame("Boids Simulation");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel cp = new JPanel();
		LayoutManager layout = new BorderLayout();
		cp.setLayout(layout);

        boidsPanel = new BoidsPanel(this, simulator);
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

		pauseButton = new JButton("Pause");
		slidersPanel.add(pauseButton);
		pauseButton.addActionListener(e -> {
				this.simulator.changeActiveState();
				if (this.simulator.getActualState())
					this.pauseButton.setText("Pause");
				else
					this.pauseButton.setText("Resume");
			}
		);
		        
		cp.add(BorderLayout.SOUTH, slidersPanel);

		frame.setContentPane(cp);	
		
        frame.setVisible(true);
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
	
	public void update(int frameRate) {
		boidsPanel.setFrameRate(frameRate);
		boidsPanel.repaint();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == separationSlider) {
			var val = separationSlider.getValue();
			simulator.setSeparationWeight(0.1*val);
		} else if (e.getSource() == cohesionSlider) {
			var val = cohesionSlider.getValue();
			simulator.setCohesionWeight(0.1*val);
		} else {
			var val = alignmentSlider.getValue();
			simulator.setAlignmentWeight(0.1*val);
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


}
