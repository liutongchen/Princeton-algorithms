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

    private boolean isSmallestPoint(double tempSlope, Point origin, Point[] leftPoints) {
        int N = leftPoints.length;
        if (N < 1) return true;
        return (tempSlope != origin.slopeTo(leftPoints[leftPoints.length - 1]));
    }

    private void searchLeft(Point[] leftPoints, Point origin) {
        int N = leftPoints.length;
        if (N >= 3) {
            double tempSlope = origin.slopeTo(leftPoints[0]);
            int tempSameSlopeNum = 1;
            for (int j = 1; j < N; j++) {
                // find point on the left that has the equal slope to p
                if (origin.slopeTo(leftPoints[j]) == tempSlope) {
                    tempSameSlopeNum++;
                } else {
                    if (tempSameSlopeNum >= 3) {
                        lineSegments[segmentsNum++] = new LineSegment(origin, leftPoints[j-1]);
                    }
                    tempSlope = origin.slopeTo(leftPoints[j]);
                }
            }
        }
    }

    private void searchRight(Point[] rightPoints, Point[] leftPoints, Point origin) {
        int N = rightPoints.length;
        if (N >= 3) {
            double tempSlope = origin.slopeTo(rightPoints[0]);
            int tempSameSlopeNum = 1;
            for (int j = 1; j < N; j++) {
                // find point on the right that has the equal slope to p
                if (origin.slopeTo(rightPoints[j]) == tempSlope) {
                    tempSameSlopeNum++;
                    if (j == N - 1 && tempSameSlopeNum >= 3 && isSmallestPoint(tempSlope, origin, leftPoints)) {
                        // if last point is collinear
                        lineSegments[segmentsNum++] = new LineSegment(origin, rightPoints[j]);
                    }
                } else {
                    if (tempSameSlopeNum >= 3 && isSmallestPoint(tempSlope, origin, leftPoints)) {
                        lineSegments[segmentsNum++] = new LineSegment(origin, rightPoints[j-1]);
                    }
                    tempSlope = origin.slopeTo(rightPoints[j]);
                }
            }
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

        // for each point p, sort by slope to this point
        for (int i = 0; i < N; i++) {
            Point origin = points[i];
            if (origin == null || (i < N - 1 && origin == points[i + 1])) {
                throw new IllegalArgumentException();
            }
            Point[] rightPoints = Arrays.copyOfRange(points, i + 1, N);
            Point[] leftPoints = Arrays.copyOfRange(points, 0, i);
            Arrays.sort(rightPoints, origin.slopeOrder());
            Arrays.sort(leftPoints, origin.slopeOrder());
            searchLeft(leftPoints, origin);
            searchRight(rightPoints, leftPoints, origin);
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