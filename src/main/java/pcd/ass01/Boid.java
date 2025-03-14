package pcd.ass01;

import java.util.ArrayList;
import java.util.List;

public class Boid {

    private P2d pos;
    private V2d vel;
    private BoidsFlockFunctions functions;

    public Boid(P2d pos, V2d vel) {
    	this.pos = pos;
    	this.vel = vel;
        this.functions = new BoidsFlockFunctionsImpl();
    }
    
    public P2d getPos() {
    	return pos;
    }

    public V2d getVel() {
    	return vel;
    }

    public void setPos(P2d position) {
        this.pos = position;
    }

    public void setVel(V2d velocity) {
        this.vel = velocity;
    }
    
    public void update(Flock flock) {

    	/* change velocity vector according to separation, alignment, cohesion */
    	
    	List<Boid> nearbyBoids = getNearbyBoids(flock);
    	
    	V2d separation = this.functions.calculateSeparation(this, nearbyBoids, flock.getAvoidRadius());
        V2d alignment = this.functions.calculateAlignment(this, nearbyBoids);
    	V2d cohesion = this.functions.calculateCohesion(this, nearbyBoids);
    	
    	vel = vel.sum(alignment.mul(flock.getAlignmentWeight()))
    			.sum(separation.mul(flock.getSeparationWeight()))
    			.sum(cohesion.mul(flock.getCohesionWeight()));
        
        /* Limit speed to MAX_SPEED */

        double speed = vel.abs();
        
        if (speed > flock.getMaxSpeed()) {
            vel = vel.getNormalized().mul(flock.getMaxSpeed());
        }

        /* Update position */

        pos = pos.sum(vel);
        
        /* environment wrap-around */
        
        if (pos.x() < -flock.getWidth()/2) pos = pos.sum(new V2d(flock.getWidth(), 0));
        if (pos.x() >= flock.getWidth()/2) pos = pos.sum(new V2d(-flock.getWidth(), 0));
        if (pos.y() < -flock.getHeight()/2) pos = pos.sum(new V2d(0, flock.getHeight()));
        if (pos.y() >= flock.getHeight()/2) pos = pos.sum(new V2d(0, -flock.getHeight()));
    }
    
    private List<Boid> getNearbyBoids(Flock flock) {
    	var list = new ArrayList<Boid>();
        for (Boid other : flock.getBoids()) {
        	if (other != this) {
        		P2d otherPos = other.getPos();
        		double distance = pos.distance(otherPos);
        		if (distance < flock.getPerceptionRadius()) {
        			list.add(other);
        		}
        	}
        }
        return list;
    }
}
