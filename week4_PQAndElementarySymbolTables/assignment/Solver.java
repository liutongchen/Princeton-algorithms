/**
 * Created by Liutong Chen on 09/13/2018
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.Collections;
import java.util.Stack;

public class Solver {
    private Move lastMove;

    private class Move implements Comparable<Move> {
        private final Board board;
        private int moves = 0;
        private Move previous;
        private final int totalCost;

        public Move(Board board) {
            this.board = board;
            this.totalCost = this.board.manhattan() + this.moves;
        }

        public Move(Board board, Move previous) {
            this.previous = previous;
            this.board = board;
            this.moves = previous.moves + 1;
            this.totalCost = this.board.manhattan() + this.moves;

        }

        public int compareTo(Move move) {
            return this.totalCost - move.totalCost;
        }
    }

    /**
     * Find a solution to the initial board (using the A* algorithm)
     * @param initial
     */
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        MinPQ<Move> movesPQ = new MinPQ<>();
        movesPQ.insert(new Move(initial));

        MinPQ<Move> twinMovesPQ = new MinPQ<>();
        twinMovesPQ.insert(new Move(initial.twin()));

        while (true) {
            this.lastMove = expand(movesPQ);
            if (lastMove != null || expand(twinMovesPQ) != null) return;
        }

    }

    /**
     * Expand the game tree.
     * Return null if the queue is empty or goal is not reached
     */
    private Move expand(MinPQ<Move> movesPQ) {
        if (movesPQ.isEmpty()) return null;

        Move bestMove = movesPQ.delMin();

        if (bestMove.board.isGoal()) {
            return bestMove;
        } else {
            Iterable<Board> neighbors = bestMove.board.neighbors();
            for (Board neighbor : neighbors) {
                if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
                    movesPQ.insert(new Move(neighbor, bestMove));
                }
            }
        }
        return null;
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
        return isSolvable() ? this.lastMove.moves : -1;
    }

    /**
     * Sequence of boards in a shortest solution; null if unsolvable
     */
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> solutionStack = new Stack<>();
        if (lastMove != null) {
            solutionStack.push(lastMove.board);
            Move prev = lastMove.previous;

            while (prev != null) {
                solutionStack.push(prev.board);
                prev = prev.previous;
            }
        }

        Collections.reverse(solutionStack);
        return solutionStack;
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