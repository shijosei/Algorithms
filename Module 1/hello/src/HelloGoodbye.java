/******************************************************************************
 *  Compilation:  javac HelloGoodbye.java
 *  Execution:    java HelloGoodbye Kevin Bob
 *
 *  Prints "Please provide two arguments.", you need to use terminal to execute
 *  the code.
 *
 *  % java HelloGoodbye Kevin Bob
 *  Hello Kevin and Bob.
 *  Goodbye Bob and Kevin.
 *
 ******************************************************************************/
public class HelloGoodbye {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide two arguments.");
        } else {
            System.out.println("Hello " + args[0] + " and " + args[1] + ".");
            System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
        }
    }
}
