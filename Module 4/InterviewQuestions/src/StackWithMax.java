import java.util.*;

public class StackWithMax {
    // main stack
    static Stack<Integer> mainStack = new Stack<>();

    // stack to keep track of max element
    static Stack<Integer> maxStack = new Stack<>();

    public void push(int x) {
        mainStack.push(x);

        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
        else {
            maxStack.push(maxStack.peek());
        }
    }

    public double pop() {
        if (mainStack.isEmpty()) throw new RuntimeException("Stack is empty");

        // pop from both stacks
        maxStack.pop();
        return mainStack.pop();
    }

    public double getMax() {
        if (maxStack.isEmpty()) throw new RuntimeException("Stack is empty");
        return maxStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax stack = new StackWithMax();
        stack.push(30);
        System.out.println("Max: " + stack.getMax());

        stack.push(50);
        System.out.println("Max: " + stack.getMax());

        stack.push(20);
        System.out.println("Max: " + stack.getMax());

        stack.push(50);
        System.out.println("Max: " + stack.getMax());

        System.out.println("Popped: " + stack.pop());
        System.out.println("Max: " + stack.getMax());

        System.out.println("Popped: " + stack.pop());
        System.out.println("Max: " + stack.getMax());

        System.out.println("Popped: " + stack.pop());
        System.out.println("Max: " + stack.getMax());

        System.out.println("Popped: " + stack.pop());
        // stack is empty
    }
}
