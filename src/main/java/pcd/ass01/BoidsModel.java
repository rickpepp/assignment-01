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

    public Flock getFlock() {return this.flock; }
    
    public Collection<Boid> getBoids(){
    	return flock.getBoids();
    }
    
    public double getWidth() {
    	return this.flock.getWidth();
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
}
