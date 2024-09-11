import java.util.HashMap;

class map {
    public HashMap<Integer, String> map = new HashMap<Integer, String>();

    public boolean add(Integer i, String s) {
        this.map.put(i, s);
        return true;
    }

    public String getVal(Integer i) {
        return this.map.get(i);
    }

    public boolean exists(String value) {
        /*
         * if (this.map.containsValue(value)) {
         * return true;
         * } else {
         * return false;
         * }
         */
        for (Integer i : this.map.keySet()) {
            if (getVal(i).equals(value)) {
                return true;
            }
        }

        return false;
    }

    public void print() {
        System.out.println("Values are as under: ");
        for (Integer i : this.map.keySet()) {
            System.out.println(getVal(i));
        }
    }
}

public class L227971_Lab_03_q4 {
    public static void main(String[] args) {
        map m = new map();
        m.add(1, "Temp 1");
        m.add(4, "temp 4");
        m.add(8, "temp 3");
        m.add(2, "temp 9");

        System.out.println("Value: " + m.getVal(4));
        System.out.println("");

        boolean flag = m.exists("temp 9");
        if (flag == true) {
            System.out.println("Value exists");
        } else {
            System.out.println("Value doesn't exist");
        }

        System.out.println("");
        m.print();
    }
}
