package com.example;

public class CGPACalculator {

    public double calculateCGPA(double[] grades) {
        if (grades == null || grades.length == 0) {
            return 0.0;
        }

        double total = 0.0;
        for (double grade : grades) {
            if (grade < 0.0 || grade > 4.0) {
                throw new IllegalArgumentException("Grades should be between 0.0 and 4.0");
            }
            total += grade;
        }

        return total / grades.length;
    }

    public static void main(String[] args) {
        CGPACalculator calculator = new CGPACalculator();
        double[] grades = { 3.0, 3.5, 4.0, 2.5 };
        System.out.println("CGPA: " + calculator.calculateCGPA(grades));
    }
}