package pcd.ass01.controller;

import pcd.ass01.view.BoidsView;

import java.awt.*;

public class BoidsSimulation {

    public static void main(String[] args) {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
    	var sim = new BoidsSimulator();
    	var view = new BoidsView(sim, width, height);
    	sim.attachView(view);
		view.selectNumberOfBoidsView();
		sim.runSimulation();
    }
}
