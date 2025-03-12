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
    
    public void update(BoidsModel model) {

    	/* change velocity vector according to separation, alignment, cohesion */
    	
    	List<Boid> nearbyBoids = getNearbyBoids(model);
    	
    	V2d separation = this.functions.calculateSeparation(this, nearbyBoids, model.getAvoidRadius());
        V2d alignment = this.functions.calculateAlignment(this, nearbyBoids);
    	V2d cohesion = this.functions.calculateCohesion(this, nearbyBoids);
    	
    	vel = vel.sum(alignment.mul(model.getAlignmentWeight()))
    			.sum(separation.mul(model.getSeparationWeight()))
    			.sum(cohesion.mul(model.getCohesionWeight()));
        
        /* Limit speed to MAX_SPEED */

        double speed = vel.abs();
        
        if (speed > model.getMaxSpeed()) {
            vel = vel.getNormalized().mul(model.getMaxSpeed());
        }

        /* Update position */

        pos = pos.sum(vel);
        
        /* environment wrap-around */
        
        if (pos.x() < model.getMinX()) pos = pos.sum(new V2d(model.getWidth(), 0));
        if (pos.x() >= model.getMaxX()) pos = pos.sum(new V2d(-model.getWidth(), 0));
        if (pos.y() < model.getMinY()) pos = pos.sum(new V2d(0, model.getHeight()));
        if (pos.y() >= model.getMaxY()) pos = pos.sum(new V2d(0, -model.getHeight()));
    }
    
    private List<Boid> getNearbyBoids(BoidsModel model) {
    	var list = new ArrayList<Boid>();
        for (Boid other : model.getBoids()) {
        	if (other != this) {
        		P2d otherPos = other.getPos();
        		double distance = pos.distance(otherPos);
        		if (distance < model.getPerceptionRadius()) {
        			list.add(other);
        		}
        	}
        }
        return list;
    }
}
