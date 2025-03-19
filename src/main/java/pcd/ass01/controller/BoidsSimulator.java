package pcd.ass01.controller;

import pcd.ass01.model.Boid;
import pcd.ass01.model.BoidsModel;
import pcd.ass01.model.P2d;
import pcd.ass01.view.BoidsView;

import java.awt.*;
import java.util.Collection;
import java.util.Optional;

public class BoidsSimulator {

    private BoidsModel model;
    private Optional<BoidsView> view;
    private Boolean active = false;
    private int framerate;
    
    private static final int FRAMERATE = 25;
    private static final int MILLIS_WAIT_PAUSE_EVERY_CYCLE = 200;
    private final static double SEPARATION_WEIGHT = 1.0;
    private final static double ALIGNMENT_WEIGHT = 1.0;
    private final static double COHESION_WEIGHT = 1.0;
    private static final double MAX_SPEED = 4.0;
    private static final double PERCEPTION_RADIUS = 50.0;
    private static final double AVOID_RADIUS = 20.0;
    
    public BoidsSimulator() {
        view = Optional.empty();
    }

    public void attachView(BoidsView view) {
    	this.view = Optional.of(view);
    }
      
    public void runSimulation() {
    	while (true) {
            runSingleCycleSimulation();
    	}
    }

    private void runSingleCycleSimulation() {
        if (active) {
            var t0 = System.currentTimeMillis();
            model.update();
            if (view.isPresent()) {
                view.get().update(framerate);
                var t1 = System.currentTimeMillis();
                var dtElapsed = t1 - t0;
                var frameRatePeriod = 1000 / FRAMERATE;
                handleFrameRate(dtElapsed, frameRatePeriod);
            }
        } else {
            pauseMainThread(MILLIS_WAIT_PAUSE_EVERY_CYCLE);
        }
    }

    private void handleFrameRate(long dtElapsed, long frameRatePeriod) {
        if (dtElapsed < frameRatePeriod) {
            pauseMainThread(frameRatePeriod - dtElapsed);
            framerate = FRAMERATE;
        } else {
            framerate = (int) (1000 / dtElapsed);
        }
    }

    private void pauseMainThread(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ex) {
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

    public void createModel(int nOfBoids, String threadMode) {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.model = new BoidsModel(
                nOfBoids,
                SEPARATION_WEIGHT, ALIGNMENT_WEIGHT, COHESION_WEIGHT,
                width,
                height,
                MAX_SPEED,
                PERCEPTION_RADIUS,
                AVOID_RADIUS,
                threadMode);
    }
}
