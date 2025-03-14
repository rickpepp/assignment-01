package pcd.ass01;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class BoidsModel {
    
    private final Flock flock;
    private double separationWeight; 
    private double alignmentWeight; 
    private double cohesionWeight;
    private final double perceptionRadius;
    private final double avoidRadius;

    public BoidsModel(int nboids,  
    						double initialSeparationWeight, 
    						double initialAlignmentWeight, 
    						double initialCohesionWeight,
    						double width, 
    						double height,
    						double maxSpeed,
    						double perceptionRadius,
    						double avoidRadius){
        separationWeight = initialSeparationWeight;
        alignmentWeight = initialAlignmentWeight;
        cohesionWeight = initialCohesionWeight;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;

        this.flock = new FlockBuilder()
                .width(width)
                .height(height)
                .maxSpeed(maxSpeed)
                .buildFlock();
        for (int i = 0; i < nboids; i++) {
        	P2d pos = new P2d(-this.flock.getWidth()/2 + Math.random() * this.flock.getWidth(),
                    -height/2 + Math.random() * height);
        	V2d vel = new V2d(Math.random() * maxSpeed/2 - maxSpeed/4, Math.random() * maxSpeed/2 - maxSpeed/4);
            flock.addBoid(new Boid(pos, vel));
        }

    }
    
    public synchronized Collection<Boid> getBoids(){
    	return flock.getBoids();
    }
    
    public synchronized double getMinX() {
    	return -this.flock.getWidth()/2;
    }

    public synchronized double getMaxX() {
    	return this.flock.getWidth()/2;
    }

    public synchronized double getMinY() {
    	return -this.flock.getHeight()/2;
    }

    public synchronized double getMaxY() {
    	return this.flock.getHeight()/2;
    }
    
    public synchronized double getWidth() {
    	return this.flock.getWidth();
    }
 
    public synchronized double getHeight() {
    	return this.flock.getHeight();
    }

    public synchronized void setSeparationWeight(double value) {
    	this.separationWeight = value;
    }

    public synchronized void setAlignmentWeight(double value) {
    	this.alignmentWeight = value;
    }

    public synchronized void setCohesionWeight(double value) {
    	this.cohesionWeight = value;
    }

    public synchronized double getSeparationWeight() {
    	return separationWeight;
    }

    public synchronized double getCohesionWeight() {
    	return cohesionWeight;
    }

    public synchronized double getAlignmentWeight() {
    	return alignmentWeight;
    }
    
    public synchronized double getMaxSpeed() {
    	return this.flock.getMaxSpeed();
    }

    public synchronized double getAvoidRadius() {
    	return avoidRadius;
    }

    public synchronized double getPerceptionRadius() {
    	return perceptionRadius;
    }
}
