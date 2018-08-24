Write a program to recognize line patterns in a given set of points.

## Point data type

Create an immutable data type Point that represents a point in the plane

**Corner cases:**
To avoid potential complications with integer overflow or floating-point precision, you may assume that the constructor arguments x and y are each between 0 and 32,767.

## BruteCollinearPoints

Examines 4 points at a time and checks whether they all lie on the same line segment, returning all such line segments.

**Performance requirement:**
The order of growth of the running time of your program should be n4 in the worst case and it should use space proportional to n plus the number of line segments returned.

## FastCollinearPoints

A faster algorithm to the `BruteCollinearPoints` problem

**Performace requirement:** 

The order of growth of the running time of your program should be n2 log n in the worst case and it should use space proportional to n plus the number of line segments returned. FastCollinearPoints should work properly even if the input has 5 or more collinear points.

## Score


