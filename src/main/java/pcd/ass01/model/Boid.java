package pcd.ass01.model;

public class Boid {

    private P2d pos;
    private V2d vel;

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

    public void setPos(P2d position) {
        this.pos = position;
    }

    public void setVel(V2d velocity) {
        this.vel = velocity;
    }
}
