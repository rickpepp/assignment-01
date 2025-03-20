package pcd.ass01.model;

public class Boid {

    private final P2d pos;
    private final V2d vel;

    public Boid(P2d pos, V2d vel) {
    	this.pos = pos;
    	this.vel = vel;
    }
    
    public P2d getPos() {
    	return pos;
    }

    public V2d getVel() {
    	return vel;
    }
}
