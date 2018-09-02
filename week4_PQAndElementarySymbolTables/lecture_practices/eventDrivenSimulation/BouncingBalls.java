/**
 * Created by Liutong Chen on 09/01/2018
 */

package eventDrivenSimulation;

import edu.princeton.cs.algs4.StdDraw;

// this is a bouncing ball without collision
public class BouncingBalls {
    public static void main(String args[]) {
        int total = Integer.parseInt(args[0]);
        Ball[] balls = new Ball[total];
        for (int i = 0; i < total; i++) {
            balls[i] = new Ball();
        }
        while (true) {
            StdDraw.clear();
            for (int i = 0; i < total; i++) {
                balls[i].move(0.5);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}
