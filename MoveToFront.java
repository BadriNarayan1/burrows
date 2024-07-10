/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    private static class Node {
        private Node next;
        private char val;

        Node(char val) {
            this.val = val;
        }
    }

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        Node front = null;
        Node end = null;
        for (int i = 0; i < 256; i++) {
            Node helper = new Node((char) i);
            if (front == null) {
                front = helper;
                end = helper;
                continue;
            }
            end.next = helper;
            end = end.next;
        }
        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            int count = 0;
            Node current = front;
            Node prev = null;
            while (current != null) {
                if (current.val == c) {
                    BinaryStdOut.write(count, 8);
                    if (prev == null) {
                        break;
                    }
                    prev.next = current.next;
                    current.next = front;
                    front = current;
                    break;
                }
                prev = current;
                current = current.next;
                count++;
            }
        }
        BinaryStdOut.close();

    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        Node front = null;
        Node end = null;
        for (int i = 0; i < 256; i++) {
            Node helper = new Node((char) i);
            if (front == null) {
                front = helper;
                end = helper;
                continue;
            }
            end.next = helper;
            end = end.next;
        }
        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readInt(8);
            int count = 0;
            Node current = front;
            Node prev = null;
            while (count != i) {
                prev = current;
                current = current.next;
                count++;
            }
            BinaryStdOut.write(current.val);
            if (count != 0) {
                prev.next = current.next;
                current.next = front;
                front = current;
            }
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        }
        if (args[0].equals("+")) {
            decode();
        }
    }

}
