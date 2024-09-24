import java.util.Arrays;

/**
 * Coursera - Algorithms Part I
 * Module 3 - Interview Questions - Analysis of Algorithms
 *
 * Question 1: 3-SUM in quadratic time
 *
 * Design an algorithm for the 3-SUM problem that takes time proportional to N2
 * in the worst case. You may assume that you can sort the N integers in time
 * proportional to N2 or better.
 * @author Aytan Abdullayeva
 */
public class ThreeSum {
    public static void findThreeSum(int[] array) {
        Arrays.sort(array); // Step 1: sort the array

        // Step 2: loop through each number
        for (int i = 0; i < array.length - 2; i++) {
            // if the current number is the same as prev, skip to avoid duplicates
            if (i == 0 || array[i] != array[i - 1]) {
                int left = i + 1; // Start pointer
                int right = array.length - 1; // End pointer

                // Step 3: Use two pointers to find the other two numbers
                while (left < right) {
                    int sum = array[i] + array[left] + array[right];

                    if (sum == 0) {
                        // Found a valid triplet
                        System.out.println("Triplet: " + array[i] + ", " + array[left] + ", " + array[right]);
                        left++;
                        right--;
                    }
                    else if (sum < 0) {
                        // If the sum is too small, move the left pointer to the right
                        left++;
                    }
                    else {
                        // If the sum is too large, move the right pointer to the left
                        right--;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Example
        int[] array = {-1, 0, 1, 2, -2, -5};
        findThreeSum(array);
    }
}