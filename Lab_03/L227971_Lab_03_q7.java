import java.util.LinkedList;

class Queue {
    public LinkedList<Integer> ll = new LinkedList<Integer>();

    public boolean Enque(Integer val) {
        ll.addLast(val);
        return true;
    }

    public Integer Dequeue() {
        if (ll.size() < 1) {
            System.out.println("Error: The  list is empty");
            return null;
        }
        return ll.removeFirst();
    }

    public boolean isEmpty() {
        if (ll.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getSize() {
        return ll.size();
    }
}

public class L227971_Lab_03_q7 {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.Enque(1);
        queue.Enque(2);
        queue.Enque(3);
        queue.Enque(4);
        System.out.println("Deque: " + queue.Dequeue());
        System.out.println("Empty: " + (queue.isEmpty() ? "True" : "False"));
        System.out.println("Size: " + queue.getSize());
    }
}
