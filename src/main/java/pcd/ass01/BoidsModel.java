package pcd.ass01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BoidsModel {
    
    private final Flock flock;

    public BoidsModel(int nboids,  
    						double initialSeparationWeight, 
    						double initialAlignmentWeight, 
    						double initialCohesionWeight,
    						double width, 
    						double height,
    						double maxSpeed,
    						double perceptionRadius,
    						double avoidRadius){

        this.flock = new FlockBuilder()
                .width(width)
                .height(height)
                .maxSpeed(maxSpeed)
                .perceptionRadius(perceptionRadius)
                .avoidRadius(avoidRadius)
                .separationWeight(initialSeparationWeight)
                .alignmentWeight(initialAlignmentWeight)
                .cohesionWeight(initialCohesionWeight)
                .buildFlock();
        for (int i = 0; i < nboids; i++) {
        	P2d pos = new P2d(-this.flock.getWidth()/2 + Math.random() * this.flock.getWidth(),
                    -height/2 + Math.random() * height);
        	V2d vel = new V2d(Math.random() * maxSpeed/2 - maxSpeed/4, Math.random() * maxSpeed/2 - maxSpeed/4);
            flock.addBoid(new Boid(pos, vel));
        }

    }
    
    public Collection<Boid> getBoids(){
    	return flock.getBoids();
    }
    
    public double getMinX() {
    	return -this.flock.getWidth()/2;
    }

    public double getMaxX() {
    	return this.flock.getWidth()/2;
    }

    public double getMinY() {
    	return -this.flock.getHeight()/2;
    }

    public double getMaxY() {
    	return this.flock.getHeight()/2;
    }
    
    public double getWidth() {
    	return this.flock.getWidth();
    }
 
    public double getHeight() {
    	return this.flock.getHeight();
    }

    public void setSeparationWeight(double value) {
    	this.flock.setSeparationWeight(value);
    }

    public void setAlignmentWeight(double value) {
    	this.flock.setAlignmentWeight(value);
    }

    public void setCohesionWeight(double value) {
    	this.flock.setCohesionWeight(value);
    }

    public double getSeparationWeight() {
    	return this.flock.getSeparationWeight();
    }

    public double getCohesionWeight() {
    	return this.flock.getCohesionWeight();
    }

    public double getAlignmentWeight() {
    	return this.flock.getAlignmentWeight();
    }
    
    public double getMaxSpeed() {
    	return this.flock.getMaxSpeed();
    }

    public double getAvoidRadius() {
    	return this.flock.getAvoidRadius();
    }

    public double getPerceptionRadius() {
    	return this.flock.getPerceptionRadius();
    }
}
