/********************************************************************************************************
 *  Compilation:  javac -classpath ".;C:\Users\Cutie\Downloads\algs4.jar" DynamicConnecticity.java
 *  Execution:    java -claspath ".;C:\Users\Cutie\Downloads\algs4.jar" DynamicConnecticity
 *
 *  % Get-Content tintUF.txt | java -classpath ".;C:\Users\Cutie\Downloads\algs4.jar" DynamicConnectivity
 *
 *********************************************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class DynamicConnectivity {
    public static void main(String[] args) {
        int n = StdIn.readInt(); // reads int from standard input
        UF uf = new UF(n); // creates a new UF object

        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            // if they are not connected, it's going to connect them and print it out
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}