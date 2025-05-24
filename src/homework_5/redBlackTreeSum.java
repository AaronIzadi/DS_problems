/*Implements standard CLRS Red-Black Tree insert.
Tracks only insertions (no deletes).
After each insert, it traverses the tree to compute the sum of all red nodes.
Outputs that sum immediately.*/

package homework_5;

import java.util.Scanner;

public class redBlackTreeSum {

    static long sumOfRedNodes;

    static class Node {
        int key;
        Node parent;
        Node left;
        Node right;
        int color;
        public static final int RED = 1;
        public static final int BLACK = 0;

        public Node(int key) {
            this.key = key;
            this.color = RED;
        }
    }

    static class RedBlackTree {
        private Node root;
        private final Node TNULL;

        public RedBlackTree() {
            TNULL = new Node(0);
            TNULL.color = Node.BLACK;
            TNULL.left = null;
            TNULL.right = null;
            root = TNULL;
        }

        private void leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != TNULL) {
                y.left.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == TNULL) {
                root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
            y.left = x;
            x.parent = y;
        }

        private void rightRotate(Node z) {
            Node y = z.left;
            z.left = y.right;
            if (y.right != TNULL) {
                y.right.parent = z;
            }
            y.parent = z.parent;
            if (z.parent == TNULL) {
                root = y;
            } else if (z == z.parent.right) {
                z.parent.right = y;
            } else {
                z.parent.left = y;
            }
            y.right = z;
            z.parent = y;
        }

        private void insertFixUp(Node z) {
            while (z.parent.color == Node.RED) {
                if (z.parent == z.parent.parent.left) {
                    Node y = z.parent.parent.right;
                    if (y.color == Node.RED) {
                        z.parent.color = Node.BLACK;
                        sumOfRedNodes -= z.parent.key;
                        y.color = Node.BLACK;
                        sumOfRedNodes -= y.key;
                        z.parent.parent.color = Node.RED;
                        sumOfRedNodes += z.parent.parent.key;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.right) {
                            z = z.parent;
                            leftRotate(z);
                        }
                        z.parent.color = Node.BLACK;
                        sumOfRedNodes -= z.parent.key;
                        z.parent.parent.color = Node.RED;
                        sumOfRedNodes += z.parent.parent.key;
                        rightRotate(z.parent.parent);
                    }
                } else {
                    Node y = z.parent.parent.left;
                    if (y.color == Node.RED) {
                        z.parent.color = Node.BLACK;
                        sumOfRedNodes -= z.parent.key;
                        y.color = Node.BLACK;
                        sumOfRedNodes -= y.key;
                        z.parent.parent.color = Node.RED;
                        sumOfRedNodes += z.parent.parent.key;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.left) {
                            z = z.parent;
                            rightRotate(z);
                        }
                        z.parent.color = Node.BLACK;
                        sumOfRedNodes -= z.parent.key;
                        z.parent.parent.color = Node.RED;
                        sumOfRedNodes += z.parent.parent.key;
                        leftRotate(z.parent.parent);
                    }
                }
            }
            if (root.color == Node.RED) {
                sumOfRedNodes -= root.key;
            }
            root.color = Node.BLACK;
        }

        public void insert(Node z) {
            Node x = root;
            Node y = TNULL;
            while (x != TNULL) {
                y = x;
                if (z.key < x.key) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            z.parent = y;
            if (y == TNULL) {
                root = z;
            } else if (z.key < y.key) {
                y.left = z;
            } else {
                y.right = z;
            }
            z.left = TNULL;
            z.right = TNULL;
            z.color = Node.RED;
            sumOfRedNodes += z.key;
            insertFixUp(z);
            System.out.println(sumOfRedNodes);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }
        RedBlackTree redBlackTree = new RedBlackTree();
        for (int i = 0; i < n; i++) {
            redBlackTree.insert(new Node(array[i]));
        }
    }
}