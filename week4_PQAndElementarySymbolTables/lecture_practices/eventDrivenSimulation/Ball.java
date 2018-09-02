/**
 * Created by Liutong Chen on 09/01/2018
 */
package eventDrivenSimulation;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius = 1;

    public Ball() {
        // initialize position, velocity and radius
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }

    public void move(double dt) {
        double xAfterChange = rx + dt * vx;
        double yAfterChange = ry + dt * vy;
        if (xAfterChange < radius || xAfterChange > 1.0 - radius) {
            // bounce back from the left/right wall
            vx = -vx;
        }
        if (yAfterChange < radius || yAfterChange > 1.0 - radius) {
            // bounce back from the top/bottom wall
            vy = -vy;
        }
        rx += dt * vx;
        ry += dt * vy;
    }
}
