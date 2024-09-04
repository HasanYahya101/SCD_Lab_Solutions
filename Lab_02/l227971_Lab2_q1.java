import java.util.*; // java is the worst language ever made

class Calculator {

    public int Add(int num1, int num2) {
        return num1 + num2;
    }

    public int Add(float num1, float num2) {
        Float result = num1 + num2;
        Integer rInt = result.intValue();
        return rInt;
    }

    public int Add(String num1, String num2) {
        Integer n1 = Integer.parseInt(num1);
        Integer n2 = Integer.parseInt(num2);
        return n1 + n2;
    }

    public int Add(int arr[]) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = result + arr[i];
        }
        return result;
    }

    public int Add(String arr[]) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = result + Integer.parseInt(arr[i]);
        }
        return result;
    }

}

public class l227971_Lab2_q1 {

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.Add(1, 2));
        System.out.println(calc.Add(1.0f, 2.4f));
        System.out.println(calc.Add("1", "2"));
        int arr[] = { 1, 2, 3, 4 };
        System.out.println(calc.Add(arr));
        String arr_str[] = { "1", "2", "3" };
        System.out.println(calc.Add(arr_str));
    }

}