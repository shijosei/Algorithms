/******************************************************************************
 *  Compilation:  javac  RandomizedQueue.java Permutation.java
 *  Execution:    Get-Content .\distinct.txt | java Permutation 3
 *  Execution:    Get-Content .\duplicates.txt | java Permutation 8
 * <p>
 *  % Get-Content .\duplicates.txt | java Permutation 8
 *  BB
 *  BB
 *  CC
 *  BB
 *  AA
 *  CC
 *  BB
 *  BB
 ******************************************************************************/
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]); // get the number of items to print
        RandomizedQueue<String> randomizedQueue =  new RandomizedQueue<>();
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8); // specify "UTF-8" encoding explicitly.

        // read strings from standard input and add to the randomized queue
        while (scanner.hasNext()) {
            randomizedQueue.enqueue(scanner.next());
        }
        scanner.close();

        // check if k is greater than the size of the queue
        if (k > randomizedQueue.size()) {
            throw new IllegalArgumentException("k is larger than the number of input elements.");
        }

        // print k items from randomized queues
        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
