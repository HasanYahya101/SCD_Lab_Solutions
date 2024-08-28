package l227971_lab1_q1;

import java.util.*;

public class L227971_Lab1_q1 {
    public static void main(String[] args) {
        Scanner src = new Scanner(System.in);
        
        System.out.println("Please enter 2 numbers");
        
        System.out.println("Enter first number: ");
        Integer num1 = src.nextInt();
        System.out.println("Enter second number: ");
        Integer num2 = src.nextInt();
        
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus");
        
        Integer choice;
        System.out.println("Enter your choice: ");
        choice = src.nextInt();
        while (choice < 1 || choice > 5)
        {
            System.out.println("Invalid choice, please enter a valid value!!!");
            System.out.println("Enter your choice: ");
            choice = src.nextInt();
        }
        
        Integer result = 0;
        
        if (choice == 1)
        {
            result = num1 + num2;
        }
        else if (choice == 2)
        {
            result = num1 - num2;
        }
        else if (choice == 3)
        {
            result = num1 * num2;
        }
        else if (choice == 4)
        {
            try{ // try the division by zero and if exception catch it
            result = num1 / num2;
            }
            catch(ArithmeticException e) // perform the error function
            {
                System.out.print("Division by zero is not allowed. Please try again");
                System.out.println("Enter a new second number: ");
                num2 = src.nextInt();
                while (num2 == 0)
                {
                    System.out.println("Num 2 can't be zero. Enter new num: ");
                    num2 = src.nextInt();
                }
                result = num1 / num2;
            }
        }
        else if (choice == 5)
        {
            result = num1 % num2;
        }
        else
        {
            System.out.println("Invalid Value");
        }
        
        System.out.println("The result is: " + result); // answer
            
        src.close(); // closing the scanner
        
    }
    
}
