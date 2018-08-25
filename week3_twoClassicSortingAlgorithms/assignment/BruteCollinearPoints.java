import java.util.Arrays;

/**
 * Created by Liutong Chen on 08/24/2018
 */

public class BruteCollinearPoints {
    private int numberOfSegments = 0;
    private LineSegment[] segmentsArr;

    /**
     * Finds all line segmentsArr containing 4 points.
     * To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes between p and q, between p and r, and between p and s are all equal.
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        int N = points.length;
        segmentsArr = new LineSegment[N];
        for (int point1Id = 0;  point1Id < N; point1Id++) {
            for (int point2Id = point1Id+1; point2Id < N; point2Id++) {
                for (int point3Id = point2Id + 1; point3Id < N; point3Id++) {
                    for (int point4Id = point3Id + 1; point4Id < N; point4Id++) {
                        Point point1 = points[point1Id];
                        Point point2 = points[point2Id];
                        Point point3 = points[point3Id];
                        Point point4 = points[point4Id];
                        double slope1To2 = point1.slopeTo(point2);
                        double slope1To3 = point1.slopeTo(point3);
                        double slope1To4 = point1.slopeTo(point4);
                        if (slope1To2 == slope1To3 && slope1To2 == slope1To4) {
                            numberOfSegments++;
                            segmentsArr[numberOfSegments] = new LineSegment(point1, point4);
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
}