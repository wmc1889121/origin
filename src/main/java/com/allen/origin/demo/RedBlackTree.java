package com.allen.origin.demo;

import lombok.Data;
import lombok.Getter;

import java.util.function.Consumer;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class RedBlackTree {
    @Getter
    private Node root;

    public void put(int key) {
        root = put(root, key);
        root.color = false; // true - red; false - black. 红黑树根一定是黑节点
    }

    public void forEach(Consumer consumer) {

    }

    /**
     * @param p   当前子树的根节点
     * @param key 要插入的node.key
     * @return 插入新的节点后，新子树的根节点
     */
    private Node put(Node p, int key) {
        if (p == null) {
            return new Node(key, 1, true);
        }

        if (key < p.key) {
            p.left = put(p.left, key);
        } else {
            p.right = put(p.right, key);
        }

        if (isRed(p.left) && isRed(p.left)) {
            p = rotateRight(p);
        }
        if (!isRed(p.left) && isRed(p.right)) {
            p = rotateLeft(p);
        }
        if (isRed(p.left) && isRed(p.right)) {
            p = flipColor(p);
        }

        p.N = statCount(p);

        return p;
    }

    private int statCount(Node p) {
        return 1 + size(p.left) + size(p.right);
    }

    private Node flipColor(Node p) {
        p.color = true;
        p.left.color = false;
        p.right.color = false;

        return p;
    }

    private boolean isRed(Node node) {
        return node != null && node.isRed();
    }

    private Node rotateLeft(Node p) {
        Node right = p.right;
        p.right = right.left;
        right.left = p;

        right.color = p.color;
        p.color = true;

        right.N = p.N;
        p.N = statCount(p);

        return right;
    }

    private Node rotateRight(Node p) {
        Node left = p.left;
        p.left = left.right;
        left.right = p;

        left.color = p.color;
        p.color = true;

        left.N = p.N;
        p.N = statCount(p);

        return left;
    }

    private int size(Node node) {
        return node == null ? 0 : node.N;
    }

    @Data
    public static class Node {
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private int key;
        private Node left;
        private Node right;
        private int N;
        private boolean color;

        public Node(int key, int n, boolean color) {
            this.key = key;
            N = n;
            this.color = color;
        }

        public boolean isRed() {
            return color;
        }
    }

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.put(3);
        tree.put(2);
        tree.put(1);
        tree.put(4);
        tree.put(7);
        tree.put(9);
        tree.put(5);
        tree.put(0);

        System.out.println(tree.getRoot());
    }
}
