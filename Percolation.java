import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// TODO: 1) TEST RESULT 2) CHECK STYLE BASED ON CHECKLIST 3) SOLVE BACKWASH PROBLEM

public class Percolation {
    private final int SIZE, VIRTUAL_TOP, VIRTUAL_BTM;
    private boolean[] openSites;
    private WeightedQuickUnionUF ufObj;
    private int num;

    public Percolation(int n) {
        // create n-by-n grid, with all sites blocked
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        SIZE = n;
        VIRTUAL_TOP = n * n;
        VIRTUAL_BTM = n * n + 1;
        openSites = new boolean[n * n];
        num = 0;
        ufObj = new WeightedQuickUnionUF(n * n + 2);
    }

    public void open(int row, int col) {
        // open site(row, col) if it's not open already

        assertIndexWithinRange(row, col);
        int index = to1DIndex(row, col);
        openSites[index] = true;
        num++;
        unionVirtualTop(row, col);
        unionVirtualBtm(row, col);
        unionAdjacentNode(row, col);

    }

    public boolean isOpen(int row, int col) {
        assertIndexWithinRange(row, col);
        return openSites[to1DIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        assertIndexWithinRange(row, col);
        return ufObj.connected(VIRTUAL_TOP, to1DIndex(row, col));
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        // does the system percolate?
        return ufObj.connected(VIRTUAL_TOP, VIRTUAL_BTM);
    }

    public static void main (String[] args) {
        // test
    }

    private int to1DIndex(int row, int col) {
        return (row - 1) * SIZE + (col - 1);
    }

    private void assertIndexWithinRange(int row, int col) {
        if (row < 0 || row > SIZE || col < 0 || col > SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void unionVirtualTop(int row, int col) {
        if (row == 1) {
            ufObj.union(VIRTUAL_TOP, to1DIndex(row, col));
        }
    }

    private void unionVirtualBtm(int row, int col) {
        if (row == SIZE) {
            ufObj.union(VIRTUAL_BTM, to1DIndex(row, col));
        }
    }

    private void unionAdjacentNode(int row, int col) {
        if (row > 1 && isOpen(row - 1, col)) {
            // union adjacent top
            ufObj.union(to1DIndex(row - 1, col), to1DIndex(row, col));
        }

        if (row < SIZE && isOpen(row + 1, col)) {
            // union adjacent bottom
            ufObj.union(to1DIndex(row + 1, col), to1DIndex(row, col));
        }

        if (col > 1 && isOpen(row, col - 1)) {
            // union adjacent left
            ufObj.union(to1DIndex(row, col - 1), to1DIndex(row, col));
        }

        if (col < SIZE && isOpen(row, col + 1)) {
            ufObj.union(to1DIndex(row, col + 1), to1DIndex(row, col));
        }
    }

    private void backwash() {
        // loop through the bottom line

        // find parent of each node and check if it's VIRTUAL_TOP

        // if not, union
    }
}
