class Node {
    public Integer val;
    public Node next;

    Node(Integer d) {
        val = d;
    }
}

class Linked_List {
    public Node head = null;
    public Node tail = null;

    /**
     * Insert given values (val) at the end of linked list
     * 
     * @param val
     */
    public void insertEnd(Integer val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Insert given values (val) at the start of linked list
     * 
     * @param val
     */
    public void insertStart(Integer val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * Delete an element from list
     * 
     * @param val
     */
    public boolean delete(Integer val) {
        if (head == null) {
            return false;
        }

        Node prev = null;
        Node curr = head;
        while (curr != null) {
            if (curr.val == val) {
                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                if (curr == tail) {
                    tail = prev;
                }
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    /**
     * Search for a node with given value and return it
     * 
     * @param val
     * @return
     */
    public Node search(Integer val) {
        Node temp = head;
        while (temp != null) {
            if (temp.val == val) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}

public class L227971_Lab_03_q5 {
    public static void main(String[] args) {
        Linked_List ll = new Linked_List();
        ll.insertStart(1);
        ll.insertStart(2);
        ll.insertStart(3);
        ll.insertEnd(4);
        ll.insertEnd(5);
        ll.delete(4); // deleted 4
        Node temp = ll.search(4);
        if (temp == null) {
            System.out.println("Node doen't exist, success");
        }
        Node val = ll.search(5);
        System.out.println("Value: " + val.val);
    }
}
