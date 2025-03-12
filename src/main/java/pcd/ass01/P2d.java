package pcd.ass01;

/**
 *
 * 2-dimensional point
 * objects are completely state-less
 *
 */
public record P2d(double x, double y) {

    public P2d(V2d velocity) {
        this(velocity.x(), velocity.y());
    }

    public P2d sum(V2d v){
        return new P2d(x+v.x(),y+v.y());
    }

    public P2d sum(P2d v){
        return new P2d(x+v.x(),y+v.y());
    }

    public P2d div(double fact) {
        if (fact == 0)
            throw new IllegalArgumentException("Can't divide for 0");
        return new P2d(x/fact, y/fact);
    }

    public P2d sub(P2d v){
        return new P2d(x-v.x,y-v.y);
    }
    
    public double distance(P2d p) {
    	double dx = p.x - x;
    	double dy = p.y - y;
    	return Math.sqrt(dx*dx + dy*dy);

    }

    public P2d mul(double fact){
        return new P2d(x*fact,y*fact);
    }
    
    public String toString(){
        return "P2d("+x+","+y+")";
    }

}
