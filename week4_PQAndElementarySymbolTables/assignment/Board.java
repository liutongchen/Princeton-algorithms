import java.util.LinkedList;

/**
 * Created by Liutong Chen on 09/13/2018
 */

public class Board {
    private static final int SPACE = 0;
    private final int dimension;
    private final int[][] board;
    /**
     * Construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
     * @param blocks
     */
    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        this.board = copy(blocks);
    }          

    /**
     * Board dimension n
     * @return
     */
    public int dimension() {
        return dimension;
    }

    /**
     * Number of blocks out of place
     * @return
     */ 
    public int hamming() {
        int count = 0;
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                int block = block(row, col);
                if (!isSpace(block) && block != goalFor(row, col)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Sum of Manhattan distances between blocks and goal
     * @return
     */
    public int manhattan() {
        int sum = 0;
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                sum += distance(row, col);
            }
        }
        return sum;
    }

    /**
     * Is this board the goal board?
     * @return
     */
    public boolean isGoal() {
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                int block = block(row, col);
                if (!isSpace(block) && block != goalFor(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A board that is obtained by exchanging any pair of blocks.
     * Assumption: only swap adjacent blocks on the same row.
     * @return
     */
    public Board twin() {
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                if (isWithinRange(col + 1) && !isSpace(block(row, col)) && !isSpace(block(row, col + 1))) {
                    return new Board(swap(row, col, row, col + 1));
                }
            }
        }
        throw new RuntimeException();
    }

    /**
     * Does this board equal y?
     */
    public boolean equals(Object y) {
        if (y == this) return true;
        if (!y.getClass().equals(this.getClass()) || ((Board) y).dimension() != this.dimension) return false;
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                if (((Board) y).board[row][col] != board[row][col]) return false;
            }
        }
        return true;
    } 

    /**
     * All neighboring boards
     * @return
     */
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<Board>();
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                if (isSpace(block(row, col))) {
                    if (row > 0) {
                        // add top if exists
                        neighbors.add(new Board(swap(row, col, row - 1, col)));
                    }
                    if (col > 0) {
                        // add left if exists
                        neighbors.add(new Board(swap(row, col, row, col - 1)));
                    }
                    if (col < this.dimension - 1) {
                        // add right if exists
                        neighbors.add(new Board(swap(row, col, row, col + 1)));
                    }
                    if (row < this.dimension - 1) {
                        // add bottom if exists
                        neighbors.add(new Board(swap(row, col, row + 1, col)));
                    }
                    return neighbors;
                }
            }
        }
        throw new Error("No SPACE found");
    }

    /**
     * String representation of this board (in the output format specified below)
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(this.dimension + "\n");
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                str.append(String.format("%2d ", block(row, col)));
            }
            str.append("\n");
        }
        return str.toString();
    }

    // ---------- private functions -----------
    /**
     * Return the goal for a single block
     */
    private int goalFor(int row, int col) {
        return row * this.dimension + col + 1;
    }

    /**
     * Check if a block is the SPACE
     */
    private boolean isSpace(int block) {
        return block == SPACE;
    }

    /**
     * Return the value of a certain block
     */
    private int block(int row, int col) {
        return board[row][col];
    }

    /**
     * Return the target row of a block
     */
    private int targetRow(int block) {
        return (block - 1) / this.dimension;
    }

    /**
     * Return the target col of a block
     */
    private int targetCol(int block) {
        return (block - 1) % this.dimension;
    }


    /**
     * Calculate the distance between a block and its goal position
     */
    private int distance(int row, int col) {
        int block = block(row, col);
        return isSpace(block) ? 0 : Math.abs(row - targetRow(block)) + Math.abs(col - targetCol(block));
    }

    /**
     * Swap two blocks
     */
    private int[][] swap(int row1, int col1, int row2, int col2) {
        int[][] copiedBoard = copy(this.board);
        int temp = copiedBoard[row1][col1];
        copiedBoard[row1][col1] = copiedBoard[row2][col2];
        copiedBoard[row2][col2] = temp;

        return copiedBoard;
    }

    /**
     * Copy a board
     */
    private int[][] copy(int[][] board) {
        int[][] copiedBoard = new int[this.dimension][this.dimension];
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                copiedBoard[row][col] = board[row][col];
            }
        }
        return copiedBoard;
    }

    /**
     * Check if an index is within range
     */
    private boolean isWithinRange(int index){
        return index < this.dimension;
    }
}