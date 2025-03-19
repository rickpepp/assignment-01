package pcd.ass01.controller;

import pcd.ass01.model.Boid;
import pcd.ass01.model.BoidsModel;
import pcd.ass01.model.P2d;
import pcd.ass01.view.BoidsView;

import java.util.Collection;
import java.util.Optional;

public class BoidsSimulator {

    private BoidsModel model;
    private Optional<BoidsView> view;
    private Boolean active = true;
    
    private static final int FRAMERATE = 25;
    private int framerate;
    
    public BoidsSimulator(BoidsModel model) {
        this.model = model;
        view = Optional.empty();
    }

    public void attachView(BoidsView view) {
    	this.view = Optional.of(view);
    }
      
    public void runSimulation() {
    	while (true) {
            if (active) {
                var t0 = System.currentTimeMillis();
                model.update();
                if (view.isPresent()) {
                    view.get().update(framerate);
                    var t1 = System.currentTimeMillis();
                    var dtElapsed = t1 - t0;
                    var framratePeriod = 1000 / FRAMERATE;

                    if (dtElapsed < framratePeriod) {
                        try {
                            Thread.sleep(framratePeriod - dtElapsed);
                        } catch (Exception ex) {
                        }
                        framerate = FRAMERATE;
                    } else {
                        framerate = (int) (1000 / dtElapsed);
                    }
                }
            } else {
                try {
                    Thread.sleep(200);
                } catch (Exception ex) {
                }
            }
    	}
    }

    public Collection<P2d> getBoidsPosition() {
        return this.model.getBoids().stream().map(Boid::getPos).toList();
    }

    public void setSeparationWeight(double separationWeight) {
        this.model.setSeparationWeight(separationWeight);
    }

    public void setCohesionWeight(double cohesionWeight) {
        this.model.setCohesionWeight(cohesionWeight);
    }

    public void setAlignmentWeight(double alignmentWeight) {
        this.model.setAlignmentWeight(alignmentWeight);
    }

    public void changeActiveState() {
        this.active = !this.active;
    }

    public Boolean getActualState() {
        return this.active;
    }
}
