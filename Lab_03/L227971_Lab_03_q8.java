import java.util.*;

public class L227971_Lab_03_q8 {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(8);
        arr.add(2);
        arr.add(3);
        arr.add(7);
        arr.add(5);
        Collections.sort(arr); // ascending order
        System.out.println("Sorted array is: ");
        for (Integer i : arr) {
            System.out.println(i);
        }
    }

}
