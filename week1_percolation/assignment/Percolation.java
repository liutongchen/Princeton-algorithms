/**
 * Created by Liutong Chen on 8/7/2018
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int size; // size of the grid
    private int openSitesNum; // number of open sites
    private boolean[] open; // an array recording the open status of each site
    private boolean[] connectTop; // an array recording if each site is connected to the top
    private boolean[] connectBtm; // an array recording if each site is connected to the bottom
    private final WeightedQuickUnionUF ufObj;
    private boolean percolateFlag = false;

    /**
     * Initialize the class, including:
     * Create n-by-n grid, with all sites blocked
     * Create three status array open, connectTop and connectBtm
     * @param n
     */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        openSitesNum = 0;
        open = new boolean[n * n];
        connectTop = new boolean[n * n];
        connectBtm = new boolean[n * n];
        ufObj = new WeightedQuickUnionUF(n * n);
    }

    /**
     * Open site(row, col) if it's not open already
     * Union adjacent sites to site(row, col)
     * Update connectTop, connectBtm and percolate flag
     * @param row
     * @param col
     */
    public void open(int row, int col) {
        assertIndexWithinRange(row, col);
        if (!isOpen(row, col)) {
            open[to1DIndex(row, col)] = true;
            openSitesNum++;
        }
        updateStatusAndUnionNode(row, col);
    }

    /**
     * Check if site(row, col) is open
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isOpen(int row, int col) {
        assertIndexWithinRange(row, col);
        return open[to1DIndex(row, col)];
    }

    /**
     * Check if site(row, col) is full
     * @param row
     * @param col
     * @return boolean
     */
    public boolean isFull(int row, int col) {
        assertIndexWithinRange(row, col);
        return connectTop[ufObj.find(to1DIndex(row, col))];
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return openSitesNum;
    }

    /**
     * Check if the system percolate
     * @return boolean
     */
    public boolean percolates() {
        return percolateFlag;
    }

    /**
     * Convert 2D index to 1D index
     * @param row
     * @param col
     * @return 1D index
     */
    private int to1DIndex(int row, int col) {
        return (row - 1) * size + (col - 1);
    }

    /**
     * Throw exception if 2D index is out of bound
     * @param row
     * @param col
     */
    private void assertIndexWithinRange(int row, int col) {
        if (row <= 0 || row > size || col <= 0 || col > size) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Check if the root of curId nextId is connected to the top or bottom
     * @param curId
     * @param nextId
     * @return the first element of the array represents connection to top and the last to bottom
     */
    private boolean[] isConnectToTopOrBtm(int curId, int nextId) {
        boolean[] res = new boolean[2];
        if (connectTop[ufObj.find(curId)] || connectTop[ufObj.find(nextId)]) {
            res[0] = true;
        }
        if (connectBtm[ufObj.find(curId)] || connectBtm[ufObj.find(nextId)]) {
            res[1] = true;
        }
        return res;
    }

    /**
     * Union adjacent sites to site(row, col)
     * Update status of connectTop and connectBtm and change percolateFlag
     * @param row
     * @param col
     */
    private void updateStatusAndUnionNode(int row, int col) {
        int index = to1DIndex(row, col);
        boolean[] topConnectRes = new boolean[2]; // status of adjacent top site
        boolean[] btmConnectRes = new boolean[2]; // status of adjacent bottom site
        boolean[] leftConnectRes = new boolean[2]; // status of adjacent left site
        boolean[] rightConnectRes = new boolean[2]; // status of adjacent right site
        boolean top;
        boolean btm;
        if (row > 1 && isOpen(row - 1, col)) {
            int topIndex = to1DIndex(row - 1, col);
            topConnectRes = isConnectToTopOrBtm(index, topIndex);
            ufObj.union(topIndex, index);
        }

        if (row < size && isOpen(row + 1, col)) {
            int btmIndex = to1DIndex(row + 1, col);
            btmConnectRes = isConnectToTopOrBtm(index, btmIndex);
            ufObj.union(btmIndex, index);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            int leftIndex = to1DIndex(row, col - 1);
            leftConnectRes = isConnectToTopOrBtm(index, leftIndex);
            ufObj.union(leftIndex, index);
        }

        if (col < size && isOpen(row, col + 1)) {
            int rightIndex = to1DIndex(row, col + 1);
            rightConnectRes = isConnectToTopOrBtm(index, rightIndex);
            ufObj.union(rightIndex, index);
        }

        top = topConnectRes[0] || btmConnectRes[0] || leftConnectRes[0] || rightConnectRes[0];
        btm = topConnectRes[1] || btmConnectRes[1] || leftConnectRes[1] || rightConnectRes[1];

        if (row == 1) {
            top = true;
        }

        if (row == size) {
            btm = true;
        }

        connectTop[ufObj.find(index)] = top;
        connectBtm[ufObj.find(index)] = btm;

        if (connectTop[ufObj.find(index)] && connectBtm[ufObj.find(index)]) {
            percolateFlag = true;
        }

    }
}
