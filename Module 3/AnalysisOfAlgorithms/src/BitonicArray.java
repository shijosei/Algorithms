/**
 * Coursera - Algorithms Part I
 * Module 3 - Interview Questions - Analysis of Algorithms
 *
 * Question 2: Search in a bitonic array
 *
 * An array is bitonic if it is comprised of an increasing sequence of integers
 * followed immediately by a decreasing sequence of integers. Write a program
 * that, given a bitonic array of N distinct integer values, determines whether
 * a given integer is in the array.
 *
 * Standard version: Use ~3lgN compares in the worst case.
 * Signing bonus: Use ~2lgN compares in the worst case (and prove that no
 * algorithm can guarantee to perform fewer than ~2lgN compares in the worst case).
 */
public class BitonicArray {
    // Step 1: function to find the peak element in the bitonic array
    public static int findPeak(int[] array, int start, int end) {
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;

            // check if the mid is the peak
            if ((mid == 0 || array[mid - 1] <= array[mid]) && (mid == array.length - 1
                    || array[mid + 1] <= array[mid])) {
                return mid;
            }
            // if the left neighbor is greater, the peak is on the left
            else if (mid > 0 && array[mid - 1] > array[mid]) {
                end = mid - 1;
            }
            // otherwise, the peak is on the right
            else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Step 2: Binary search in ascending order
    public static int binarySearchAscending(int[] array, int start, int end, int key) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == key) {
                return mid;
            }
            else if (array[mid] < key) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }

    // Step 2: Binary search in descending order
    public static int binarySearchDescending(int[] array, int start, int end, int key) {
        while (start <= end) {
            int mid = (start + end) / 2;

            if (array[mid] == key) {
                return mid;
            }
            else if (array[mid] < key) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }

    // Step 3: main func to search for a key in bitonic array
    public static int searchBitonic(int[] array, int key) {
        int peak = findPeak(array, 0, array.length - 1);

        // search increasing part
        int result = binarySearchAscending(array, 0, peak, key);
        if (result != -1) {
            return result;
        }

        // search decreasing part
        return binarySearchDescending(array, peak + 1, array.length - 1, key);

    }

    public static void main(String[] args) {
        int[] bitonicArray = {1, 3, 8, 12, 4, 2};
        int key = 8; // number we are looking for
        int result = searchBitonic(bitonicArray, key);

        if (result == -1) {
            System.out.println("No bitonic found");
        }
        else {
            System.out.println("Found bitonic " + result);
        }
    }
}
