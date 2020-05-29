package com.allen.origin.demo;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class MergeSort {
    private int[] aux;

    public int[] sort(int[] src) {
        if (src == null || src.length < 2) {
            return src;
        }

        aux = new int[src.length];

        for (int sz = 1; sz < src.length; sz *= 2) {
            for (int i = 0; i < src.length; i += 2 * sz) {
                int hi = Math.min(src.length - 1, i + 2 * sz - 1);
                sort(src, i, hi, i + sz - 1);
            }
            System.out.println();
        }

        return src;
    }

    private void sort(int[] src, int lo, int hi, int mid) {
        if (lo >= hi) return;

        for (int i = lo; i <= hi; i++) {
            aux[i] = src[i];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                src[k] = aux[j++];
            } else if (j > hi) {
                src[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                src[k] = aux[i++];
            } else {
                src[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();

        int[] sorted = sort.sort(new int[]{1, 6, 4, 3, 7, 5, 3, 8, 9, 0, 5, 5, 0});

        for (int i = 0; i < sorted.length; i++) {
            System.out.println(sorted[i]);
        }
    }
}
