/**
 * Created by Liutong Chen on 08/24/2018
 */

import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {
    private int numberOfSegments = 0;
    private final LineSegment[] segmentsArr;

    /**
     * Finds all line segmentsArr containing 4 points.
     * To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal.
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] copiedPoints = Arrays.copyOf(points, points.length);
        int n = copiedPoints.length;
        Arrays.sort(copiedPoints, Comparator.nullsFirst(Comparator.naturalOrder()));
        segmentsArr = new LineSegment[n * n];
        for (int point1Id = 0;  point1Id < n - 3; point1Id++) {
            for (int point2Id = point1Id+1; point2Id < n - 2; point2Id++) {
                for (int point3Id = point2Id + 1; point3Id < n - 1; point3Id++) {
                    for (int point4Id = point3Id + 1; point4Id < n; point4Id++) {
                        Point point1 = copiedPoints[point1Id];
                        Point point2 = copiedPoints[point2Id];
                        Point point3 = copiedPoints[point3Id];
                        Point point4 = copiedPoints[point4Id];

                        // throw exception if an entry is null or duplicate
                        assertArgsValid(point1, point2);
                        assertArgsValid(point2, point3);
                        assertArgsValid(point3, point4);
                        if (point4Id + 1 < n) {
                            assertArgsValid(point4, copiedPoints[point4Id + 1]);
                        }

                        double slope1To2 = point1.slopeTo(point2);
                        double slope1To3 = point1.slopeTo(point3);
                        double slope1To4 = point1.slopeTo(point4);
                        if (slope1To2 == slope1To3 && slope1To2 == slope1To4) {
                            segmentsArr[numberOfSegments] = new LineSegment(point1, point4);
                            numberOfSegments += 1;
                       }
                    }
                }
            }
        }
    }

    /**
     * The number of line segmentsArr
     * @return
     */
    public int numberOfSegments() {
        return numberOfSegments;
    }

    /**
     * The line segmentsArr
     * @return
     */
    public LineSegment[] segments() {
        return Arrays.copyOfRange(segmentsArr, 0, numberOfSegments);
    }

    private void assertArgsValid(Point curPoint, Point nextPoint) {
        if (curPoint == null || curPoint.compareTo(nextPoint) == 0) {
            throw new IllegalArgumentException();
        }
    }
}