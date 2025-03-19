package pcd.ass01.view;

import pcd.ass01.controller.BoidsSimulator;
import pcd.ass01.model.BoidsModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

public class BoidsView  {

	private JFrame frame;
	private BoidsSimulator simulator;
	private int width, height;

	private BoidsSimulationView simulationView;
	
	public BoidsView(BoidsSimulator simulator, int width, int height) {
		this.simulator = simulator;
		this.width = width;
		this.height = height;
		frame = new JFrame("Boids Simulation");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void selectNumberOfBoidsView() {
		new SelectNumberBoidsView(frame, this).show();
	}

	private void boidsSimulationView() {
		simulationView = new BoidsSimulationView(frame, this);
		simulationView.show();
	}

	
	public void update(int frameRate) {
		simulationView.update(frameRate);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BoidsSimulator getSimulator() {
		return this.simulator;
	}

	public void selectedNumberOfBoids(int i) {
		simulator.setNumberOfBoids(i);
		boidsSimulationView();
	}
}
