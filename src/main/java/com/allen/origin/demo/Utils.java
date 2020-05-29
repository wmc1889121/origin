package com.allen.origin.demo;

/**
 * @author Allen Wan
 * @version 1.0
 */
public class Utils {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
