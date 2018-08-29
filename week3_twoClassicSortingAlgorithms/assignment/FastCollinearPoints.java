/**
 * Created by Liutong Chen on 08/27/2018
 */

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private int segmentsNum = 0;
    private LineSegment[] lineSegments;

    /**
     * Finds all line segments containing 4 or more points
     * @param points
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] copiedPoints = Arrays.copyOf(points, points.length);

        // sort the points by y and x order
        Arrays.sort(copiedPoints, Comparator.nullsFirst(Comparator.naturalOrder()));

        int n = copiedPoints.length;
        lineSegments = new LineSegment[n * n];

        // for each point p, sort by slope to p
        for (int i = 0; i < n; i++) {
            Point origin = copiedPoints[i];
            if (origin == null || (i < n - 1 && origin.compareTo(copiedPoints[i + 1]) == 0)) {
                throw new IllegalArgumentException();
            }

            Point[] targets = Arrays.copyOf(copiedPoints, n);
            Arrays.sort(targets, origin.slopeOrder());
            searchTarget(targets, origin);
        }
    }

    private void searchTarget(Point[] targets, Point origin) {
        double tempSlope = 0;
        int count = -1;
        int i = 0;
        int n = targets.length;
        while (i < n) {
            if (origin.compareTo(targets[i]) == 0) {
                // skip origin itself
                i++;
                continue;
            }

            double currentSlope = origin.slopeTo(targets[i]);
            if (count == -1 || currentSlope != tempSlope) {
                // beginning of each line segment
                if (count >= 3) {
                    lineSegments[segmentsNum++] = new LineSegment(origin, targets[i-1]);
                }

                if (targets[i].compareTo(origin) < 0) {
                    while (i < n && origin.slopeTo(targets[i]) == currentSlope) {
                        // skip points where origin is not the smallest point
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
}