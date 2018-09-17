/**
 * Created by Liutong Chen on 09/13/2018
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Iterator;

public class Solver {
    private MinPQ solutionPQ;
    private int moves = 0;
    private class Move implements Comparable<Move> {
        private Board board;
        public Move(Board board) {
            this.board = board;

        }
        public int compareTo(Move move) {

        }
    }
    /**
     * Find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
        // Create new Move with initial board
        // add initial move to MinPQ
        // if initial move is the goal: return moves = 0
        // else:
        // add all neighbors of initial moves to the PQ if not exist before
        // loop: delMin() -- bestMove from PQ and check bestMove
    }

    /**
     * Is the initial board solvable?
     */
    public boolean isSolvable() {

    }            

    /**
     * Min number of moves to solve initial board; -1 if unsolvable
     */
    public int moves() {
        
    }                     

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {

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