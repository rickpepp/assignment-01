package pcd.ass01.model;

import java.util.Collection;

public class BoidsModel {
    
    private final Flock flock;
    private final UpdateFlock updateFlock;

    public BoidsModel(int nBoids,
    						double initialSeparationWeight, 
    						double initialAlignmentWeight, 
    						double initialCohesionWeight,
    						double width, 
    						double height,
    						double maxSpeed,
    						double perceptionRadius,
    						double avoidRadius,
                            String threadMode){
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
        createRandomBoids(nBoids);
        updateFlock = createUpdateFlockFromString(threadMode);
    }

    private UpdateFlock createUpdateFlockFromString(String threadMode) {
        return switch (threadMode) {
            case "Sequential" -> new SequentialUpdateFlock(flock);
            case "Default Multithread" -> new DefaultThreadUpdateFlock(flock);
            default -> throw new RuntimeException("Update Flock not Initialized");
        };
    }

    private void createRandomBoids(int nBoids) {
        for (int i = 0; i < nBoids; i++) {
        	P2d pos = new P2d(-this.flock.getWidth()/2 + Math.random() * this.flock.getWidth(),
                    -this.flock.getHeight() /2 + Math.random() * this.flock.getHeight());
        	V2d vel = new V2d(Math.random() * this.flock.getMaxSpeed() /2 - this.flock.getMaxSpeed() /4, Math.random() * this.flock.getMaxSpeed() /2 - this.flock.getMaxSpeed() /4);
            flock.addBoid(new Boid(pos, vel));
        }
    }
    
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

    public void update() {
        this.updateFlock.update();
    }
}
