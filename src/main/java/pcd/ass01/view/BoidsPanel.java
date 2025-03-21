package pcd.ass01.view;

import pcd.ass01.controller.BoidsSimulator;
import pcd.ass01.model.Boid;
import pcd.ass01.model.BoidsModel;
import pcd.ass01.model.P2d;

import javax.swing.*;
import java.awt.*;

public class BoidsPanel extends JPanel {

	private BoidsView view;
	private BoidsSimulator simulator;
    private int framerate;
    private double avgFramerate;

    public BoidsPanel(BoidsView view, BoidsSimulator simulator) {
    	this.simulator = simulator;
    	this.view = view;
    }

    public void setFrameRate(int framerate) {
    	this.framerate = framerate;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        
        var w = view.getWidth();
        var h = view.getHeight();
        var envWidth = view.getWidth();
        var xScale = w/envWidth;
        // var envHeight = model.getHeight();
        // var yScale = h/envHeight;

        var boids = simulator.getBoidsPosition();

        g.setColor(Color.BLUE);
        for (P2d boid : boids) {
        	var x = boid.x();
        	var y = boid.y();
        	int px = (int)(w/2 + x*xScale);
        	int py = (int)(h/2 - y*xScale);
            g.fillOval(px,py, 5, 5);
        }
        
        g.setColor(Color.BLACK);
        g.drawString("Num. Boids: " + boids.size(), 10, 25);
        g.drawString("Framerate: " + framerate, 10, 40);
        g.drawString("Avg Framerate: " + String.format("%.1f", avgFramerate), 10, 55);
   }

    public void setAvgFramerate(double avgFramerate) {
        this.avgFramerate = avgFramerate;
    }
}
