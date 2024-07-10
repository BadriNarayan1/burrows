/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import java.util.Arrays;
import java.util.Comparator;

public class BurrowsWheeler {

    private static String t;

    // private static class Node {
    //     private Node next;
    //     private int val;
    //
    //     Node(int val) {
    //         this.val = val;
    //     }
    // }

    private static class inverseComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return t.charAt(o1) - t.charAt(o2);
        }
    }

    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output
    public static void transform() {
        String input = BinaryStdIn.readString();
        CircularSuffixArray helper = new CircularSuffixArray(input);
        StringBuilder sb = new StringBuilder();
        int required = 0;
        for (int i = 0, k = input.length(); i < k; i++) {
            int help = helper.index(i);
            if (help == 0) {
                required = i;
                sb.append(input.charAt(k - 1));
                continue;
            }
            sb.append(input.charAt(help - 1));
        }
        BinaryStdOut.write(required);
        BinaryStdOut.write(sb.toString());
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int index = BinaryStdIn.readInt();
        String output = BinaryStdIn.readString();
        t = output;
        Integer[] next = new Integer[output.length()];
        for (int i = 0, k = output.length(); i < k; i++) {
            next[i] = i;
        }
        Arrays.sort(next, new inverseComparator());
        char[] sorted = output.toCharArray();
        Arrays.sort(sorted);
        StringBuilder sb = new StringBuilder();
        sb.append(sorted[index]);
        // for (int i = 0, k = output.length(); i < k; i++) {
        //     Node helper = indices[output.charAt(i)];
        //     if (helper == null) {
        //         indices[output.charAt(i)] = new Node(i);
        //         continue;
        //     }
        //     while (helper.next != null) {
        //         helper = helper.next;
        //     }
        //     helper.next = new Node(i);
        // }
        // for (int i = 0, k = output.length(); i < k; i++) {
        //     Node helper = indices[sorted[i]];
        //     while (marked[helper.val]) {
        //         helper = helper.next;
        //     }
        //     next[i] = helper.val;
        //     marked[helper.val] = true;
        // }
        for (int i = 0, k = output.length(); i < k - 1; i++) {
            sb.append(sorted[next[index]]);
            index = next[index];
        }
        BinaryStdOut.write(sb.toString());
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        }
        if (args[0].equals("+")) {
            inverseTransform();
        }
    }

}
