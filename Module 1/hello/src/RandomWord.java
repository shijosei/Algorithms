/******************************************************************************
 *  Compilation:  javac -classpath ".;algs4.jar" RandomWord.java
 *  Execution:    java -claspath ".;algs4.jar" RandomWord
 *
 *  % java -claspath ".;algs4.jar" RandomWord
 *  tails heads
 *  -->press enter then (ctrl + z)
 *  tails
 *  % Get-Content animals8.txt | java -classpath ".;C:\Users\Cutie\Downloads\algs4.jar" RandomWord
 *  emu
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = null;
        int i = 1;

        while (!StdIn.isEmpty()) {
            String word = StdIn.readString(); // read the next word

            // With probability 1/i replace the current champion
            if (StdRandom.bernoulli(1.0 / i)) {
                champion = word;
            }
            i++;
        }

        StdOut.println(champion);
    }
}
