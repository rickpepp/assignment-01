package pcd.ass01.controller;

import pcd.ass01.model.BoidsModel;
import pcd.ass01.view.BoidsView;

import java.awt.*;

public class BoidsSimulation {

	final static int N_BOIDS = 1500;
	final static double SEPARATION_WEIGHT = 1.0;
    final static double ALIGNMENT_WEIGHT = 1.0;
    final static double COHESION_WEIGHT = 1.0;
    static final double MAX_SPEED = 4.0;
    static final double PERCEPTION_RADIUS = 50.0;
    static final double AVOID_RADIUS = 20.0;

	

    public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
    	var model = new BoidsModel(
    					N_BOIDS, 
    					SEPARATION_WEIGHT, ALIGNMENT_WEIGHT, COHESION_WEIGHT,
						width,
						height,
    					MAX_SPEED,
    					PERCEPTION_RADIUS,
    					AVOID_RADIUS); 
    	var sim = new BoidsSimulator(model);
    	var view = new BoidsView(sim, width, height);
    	sim.attachView(view);
    	sim.runSimulation();
    }
}
