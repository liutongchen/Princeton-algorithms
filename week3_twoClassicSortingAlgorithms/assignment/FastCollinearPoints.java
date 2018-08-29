/**
 * Created by Liutong Chen on 08/27/2018
 */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class FastCollinearPoints {
    private int segmentsNum = 0;
    private LineSegment[] lineSegments;

    private void searchTarget(Point[] targets, Point origin) {
        double tempSlope = 0;
        int count = -1;
        int i = 0;
        int N = targets.length;
        while (i < N) {
            if (origin.compareTo(targets[i]) == 0) {
                // skip origin itself
                i++;
                continue;
            }

            double currentSlope = origin.slopeTo(targets[i]);
            if (count == -1 || currentSlope != tempSlope) {
                if (count >= 3) {
                    lineSegments[segmentsNum++] = new LineSegment(origin, targets[i-1]);
                }

                if (targets[i].compareTo(origin) < 0) {
                    while (i < N && origin.slopeTo(targets[i]) == currentSlope) {
                        i++;
                    }

                    count = -1;
                    continue;
                }

                currentSlope = origin.slopeTo(targets[i]);
                tempSlope = currentSlope;
                count = 0;
            }

            if (currentSlope == tempSlope) {
                count++;
            }

            i++;
        }

        if (count >= 3) {
            lineSegments[segmentsNum++] = new LineSegment(origin, targets[i-1]);
        }
    }

    /**
     * Finds all line segments containing 4 or more points
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        // sort the points by y and x order
        Arrays.sort(points);

        int N = points.length;
        lineSegments = new LineSegment[N];

        // for each point p, sort by slope to p
        for (int i = 0; i < N; i++) {
            Point origin = points[i];
            if (origin == null || (i < N - 1 && origin == points[i + 1])) {
                throw new IllegalArgumentException();
            }

            Point[] targets = Arrays.copyOf(points, points.length);
            Arrays.sort(targets, origin.slopeOrder());
            searchTarget(targets, origin);
        }
    }

    /**
     * The number of line segments
     * @return
     */
    public int numberOfSegments() {
        return segmentsNum;
    }

    /**
     * The line segments
     * @return
     */
    public LineSegment[] segments() {
        return Arrays.copyOfRange(lineSegments, 0, segmentsNum);
    }

    /**
     * Client code
     * TODO: DELETE THIS BEFORE SUBMISSION
     */
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}