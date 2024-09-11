import java.util.*;

class Stack {
    public ArrayList<Integer> stack = new ArrayList<Integer>();

    public boolean Push(Integer i) {
        stack.add(i);
        return true;
    }

    public Integer Pop() {
        if (stack.size() < 1) {
            return null;
        }
        Integer val = stack.get(stack.size() - 1); // get the last index
        stack.remove(stack.size() - 1);
        return val;
    }

    public boolean isEmpty() {
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }

    public Integer peek() {
        return stack.get(stack.size() - 1);
    }
}

public class L227971_Lab_03_q6 {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.Push(0);
        stack.Push(1);
        stack.Push(2);
        System.out.println("Pop: " + stack.Pop());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Empty: " + (stack.isEmpty() ? "True" : "False"));
    }
}
