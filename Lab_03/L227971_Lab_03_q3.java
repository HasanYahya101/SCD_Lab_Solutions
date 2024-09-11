import java.util.*;

class Array {
    public ArrayList<String> list = new ArrayList<String>();

    public boolean add(String s) {
        list.add(s);
        return true;
    }

    public boolean remove(int i) {
        list.remove(i);
        return true;
    }

    public boolean exists(String element) {
        boolean flag = false;
        for (String s : list) {
            if (s.equals(element)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public int size() {
        if (list.size() == 0) {
            return 0;
        }

        int size = 0;
        for (String s : list) {
            size++;
        }

        return size;
    }

    public void print() {
        Iterator<String> si = this.list.iterator();
        while (si.hasNext()) {
            System.out.println("Value: " + si.next());
        }
    }
}

public class L227971_Lab_03_q3 {
    public static void main(String[] args) {
        Array list = new Array();
        // adding element
        list.add("Element 1");
        list.add("Element 2");
        list.add("Element 3");
        list.remove(2);
        boolean exists = list.exists("Element 1");
        if (exists == true) {
            System.out.println("Yes, the element exists");
        } else {
            System.out.println("No it doesn't exist");
        }
        System.out.println("Size: " + list.size());
        list.print();
    }
}
