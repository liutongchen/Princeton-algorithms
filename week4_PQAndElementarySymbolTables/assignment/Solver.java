/**
 * Created by Liutong Chen on 09/13/2018
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Stack;

public class Solver {
    private class Move implements Comparable<Move> {
        private Board board;
        private int moves = 0;
        private Move previous;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move previous) {
            this.previous = previous;
            this.board = board;
            this.moves = previous.moves + 1;
        }

        public int compareTo(Move move) {
            return (this.board.manhattan() - move.board.manhattan()) + (this.moves - move.moves);
        }
    }

    private Move lastMove;

    /**
     * Find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<Move> minMovesPQ = new MinPQ<>();
        minMovesPQ.insert(new Move(initial));

        Move bestMove = minMovesPQ.delMin();
        while (!bestMove.board.isGoal() && !minMovesPQ.isEmpty()) {
            Iterable<Board> neighbors = bestMove.board.neighbors();
            for (Board neighbor : neighbors) {
                if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
                    minMovesPQ.insert(new Move(neighbor, bestMove));
                }
            }

            bestMove = minMovesPQ.delMin();
            this.lastMove = bestMove;
        }
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {
        return this.lastMove != null;
    }            

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        return isSolvable() ? this.solution.size() - 1 : -1;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        return this.solution;
    }

    /**
     * Solve a slider puzzle (given below)
     */
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}