import java.util.*;

public class QueueWithTwoStack {
    static Stack<Integer> stack1 = new Stack<Integer>();
    static Stack<Integer> stack2 = new Stack<Integer>();

    static void enqueue(int item) {

        // move all elements from stack1 to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        // push item to stack1
        stack1.push(item);

        // push everything back to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    static int dequeue() {
        if (stack1.isEmpty()) {
            return -1;
        }

        // return the top of the item
        int item = stack1.peek();
        stack1.pop();

        return item;
    }

    public static void main(String[] args) {
        QueueWithTwoStack q = new QueueWithTwoStack();

        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
            System.out.println(q.dequeue());
        }
    }
}