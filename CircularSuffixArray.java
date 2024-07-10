/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {

    private Integer[] index;
    private int length;
    private String[] orginalSuffixes;
    private String input;

    private class circularCompare implements Comparator<Integer> {
        public int compare(Integer u, Integer v) {
            for (int i = 0; i < length; i++) {
                int check = input.charAt((u + i) % length) - input.charAt((v + i) % length);
                if (check < 0) {
                    return -1;
                }
                if (check > 0) {
                    return 1;
                }
            }
            return 0;
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        this.length = s.length();
        input = s;
        index = new Integer[length];
        for (int i = 0; i < length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, new circularCompare());
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length - 1) {
            throw new IllegalArgumentException();
        }
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray check = new CircularSuffixArray("ABRACADABRA!");
        System.out.println(check.length);
        System.out.println(check.index(11));
        System.out.println(check.index(4));
    }

}
