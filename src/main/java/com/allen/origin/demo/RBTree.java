package com.allen.origin.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class RBTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public V put(K key, V val) {
        Entry<Node<K, V>, V> putResult = put(root, new Node<>(key, val));
        root = putResult.getKey();
        root.color = Node.BLACK;
        return putResult.getVal();
    }

    private Entry<Node<K, V>, V> put(Node<K, V> root, Node<K, V> node) {
        if (root == null) {
            return new Entry<>(node, null);
        }

        Entry<Node<K, V>, V> rootAndOldVal;
        int cmp = root.compareTo(node);
        if (cmp < 0) {
            rootAndOldVal = put(root.right, node);
            root.right = rootAndOldVal.key;
        } else if (cmp > 0) {
            rootAndOldVal = put(root.left, node);
            root.left = rootAndOldVal.key;
        } else {
            V oldVal = root.v;
            root.v = node.v;
            return new Entry<>(root, oldVal);
        }

        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (!isRed(root.left) && isRed(root.right)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            root.color = Node.RED;
            root.left.color = Node.BLACK;
            root.right.color = Node.BLACK;
        }

        root.count = 1 + size(root.left) + size(root.right);
        rootAndOldVal.setKey(root);

        return rootAndOldVal;
    }

    private Node<K, V> rotateLeft(Node<K, V> root) {
        Node<K, V> newRoot = root.right;
        root.right = root.right.left;
        newRoot.left = root;

        newRoot.color = root.color;
        root.color = Node.RED;

        newRoot.count = size(root);
        root.count = 1 + size(root.left) + size(root.right);

        return newRoot;
    }

    private Node<K, V> rotateRight(Node<K, V> root) {
        Node<K, V> newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;

        newRoot.color = root.color;
        root.color = Node.RED;

        newRoot.count = size(root);
        root.count = 1 + size(root.left) + size(root.right);

        return newRoot;
    }

    private int size(Node node) {
        return node == null ? 0 : node.count;
    }

    private boolean isRed(Node n) {
        return n != null && n.color == Node.RED;
    }

    private static class Node<K extends Comparable<K>, V> implements Comparable<Node<K, V>> {
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private K k;
        private V v;
        private Node<K, V> left;
        private Node<K, V> right;
        private boolean color = RED;
        private int count = 1;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return k.compareTo(o.k);
        }

        @Override
        public String toString() {
            return MessageFormat.format("[{0},{1},{2}]", k, v, color == RED ? "red" : "black");
        }

    }

    @Data
    @AllArgsConstructor
    private static class Entry<K, V> {
        private K key;
        private V val;
    }

    public static void main(String[] args) {
        RBTree<Integer, Integer> tree = new RBTree<>();

        tree.put(3, 3);
        tree.put(2, 2);
        tree.put(1, 1);
        tree.put(4, 4);
        tree.put(7, 7);
        tree.put(9, 9);
        tree.put(5, 5);
        tree.put(0, 0);

        System.out.println(tree.root);
    }
}
