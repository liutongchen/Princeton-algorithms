/**
 * Created by Liutong Chen on 08/17/2018
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    /**
     * A client program takes an integer k as a command-line argument;
     * reads in a sequence of strings and prints exactly k of them, uniformly at random;
     * print each item from the sequence at most once
     */
    public static void main(String[] args) {
        int numberOfOutput = Integer.parseInt(args[0]);
        RandomizedQueue<String> stringInput = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            stringInput.enqueue(StdIn.readString());
        }

        for (int i = 0; i < numberOfOutput; i++) {
            StdOut.println(stringInput.dequeue());
        }
    }
}
