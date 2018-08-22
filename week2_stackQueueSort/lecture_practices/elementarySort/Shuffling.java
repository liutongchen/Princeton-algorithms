/**
 * Created by Liutong Chen on 08/22/2018
 */

package elementarySort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This class implements Knuth's shuffling method that takes linear time
 */
public class Shuffling
{
    public static void shuffle(Comparable[] a)
    {
        int N = a.length;

        for (int i = 0; i < N; i++)
        {
            int r = StdRandom.uniform(0, i+1);
            exch(a, r, i);
        }
    }

    public static void exch(Comparable[] a, int i, int j)
    {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
