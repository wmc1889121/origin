package com.allen.origin.demo;

import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class QuickSort {

    public int[] sort(int[] src) {
        if (src == null || src.length < 2) {
            return src;
        }

        ArrayUtils.shuffle(src);

        sort(src, 0, src.length - 1);

        return src;
    }

    private void sort(int[] src, int lo, int hi) {
        if (lo >= hi) return;

        int h = partition(src, lo, hi);
        sort(src, lo, h - 1);
        sort(src, h + 1, hi);
    }

    private int partition(int[] src, int lo, int hi) {
        int base = src[lo];

        int l = lo, r = hi + 1;
        while (true) {
            while (base >= src[++l]) {
                if (l == hi) break;
            }
            while (base <= src[--r]) {
                if (lo == r) break;
            }

            if (l >= r) break;

            Utils.swap(src, l, r);
        }

        Utils.swap(src, lo, r);

        return r;
    }

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();

        int[] sorted = sort.sort(new int[]{1, 6, 4, 3, 7, 5, 3, 8, 9, 0, 5, 5, 0});

        for (int i=0; i<sorted.length; i++) {
            System.out.println(sorted[i]);
        }
    }
}
