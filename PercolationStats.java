/**
 * Created by Liutong Chen on 8/7/2018
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result; // each element represents the estimation of each trial

    /**
     * Initialize results and run experiments
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        assertPositive(n);
        assertPositive(trials);
        result = new double[trials];
        // perform trials independent experiments on an n-by-n grid
        runExperiments(n, trials);
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return StdStats.mean(result);
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(result);
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return mean() - confidence();
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return mean() + confidence();
    }

    /**
     * Create PercolationStats class and test client
     * @param args
     */
    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(size, trials);
        System.out.println("mean                     = " + stats.mean());
        System.out.println("stddev                   = " + stats.stddev());
        System.out.println("95% confidence interval  = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

    /**
     * Throw IllegalArgumentException if parameter is not positive
     * @param n
     */
    private void assertPositive(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return confidence level
     */
    private double confidence() {
        return (1.96 * stddev() / Math.sqrt(result.length));
    }

    /**
     * Randomly choose an integer from [1, size+1)
     * @param size
     * @return
     */
    private int random(int size) {
        return StdRandom.uniform(1, size + 1);
    }

    /**
     * Run experiments (trial) times
     * @param size
     * @param trials
     */
    private void runExperiments(int size, int trials) {
        for (int i = 0; i < trials; i++) {
            result[i] = runEachExperiment(size);
        }
    }

    /**
     * Run experiment with Percolation instance until percolates
     * Calculate the estimation for each experiment
     * @param size
     * @return
     */
    private double runEachExperiment(int size) {
        Percolation perc = new Percolation(size);
        int openSites = 0;
        do {
            int row = random(size);
            int col = random(size);
            if (!perc.isOpen(row, col)) {
                perc.open(row, col);
                openSites++;
            }
        } while(!perc.percolates());

        return (double) openSites / ((double) size * size);
    }
}
