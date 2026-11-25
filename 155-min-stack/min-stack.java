// LeetCode 155. Min Stack (Medium)
//
// Pattern: stack design — keep a second stack of running minimums
// alongside the value stack, so getMin() is O(1).
//
// Time: O(1) per operation, Space: O(n)

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {

    private final Deque<Integer> values = new ArrayDeque<>();
    private final Deque<Integer> mins = new ArrayDeque<>();

    public MinStack() {
    }

    public void push(int val) {
        values.push(val);
        if (mins.isEmpty() || val <= mins.peek()) {
            mins.push(val);
        }
    }

    public void pop() {
        int val = values.pop();
        if (val == mins.peek()) {
            mins.pop();
        }
    }

    public int top() {
        return values.peek();
    }

    public int getMin() {
        return mins.peek();
    }

}
