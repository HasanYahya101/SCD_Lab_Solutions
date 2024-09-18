import java.util.*;

class Classroom {
    public ArrayList<Student> students = new ArrayList<Student>();

    public boolean search_by_name(String name) {
        for (Student s : this.students) {
            if (s.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean search_by_roll(int roll) {
        for (Student s : this.students) {
            if (s.roll_No == roll) {
                return true;
            }
        }
        return false;
    }

    public void edit_student(int roll, Student s) {
        for (int i = 0; i < this.students.size(); i++) {
            if (students.get(i).roll_No == roll) {
                students.set(i, s);
                System.out.println("Success");
                return;
            }
        }
        System.out.println("Error");
    }

    public boolean del(int roll) {
        for (int i = 0; i < this.students.size(); i++) {
            if (students.get(i).roll_No == roll) {
                students.remove(i);
                System.out.println("success");
                return true;
            }
        }
        return false;
    }
}

class Student {
    public String name;
    public int roll_No; // std_id
    public ArrayList<Subject> subjects = new ArrayList<Subject>();

    public Student(String name, int roll_no) {
        this.name = name;
        this.roll_No = roll_no;
    }

    public void add_course(Subject s) {
        this.subjects.add(s);
    }
}

class Subject {
    public String name;
    public int marks;

    public Subject(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class L227971_Quiz_01_q3 {
    public static void main(String[] args) {
        Classroom gcr = new Classroom();

        Student s1 = new Student("s1", 10);
        Student s2 = new Student("s2", 20);
        Student s3 = new Student("s3", 30);
        Student s4 = new Student("s4", 40);
        Student s5 = new Student("s5", 50);

        Subject math = new Subject("Math", 120);
        Subject math2 = new Subject("Math", 140);
        Subject math3 = new Subject("Math", 170);
        Subject physics = new Subject("Physics ", 110);
        Subject algebra = new Subject("Algebra ", 170);
        s1.add_course(physics);
        s2.add_course(algebra);
        s3.add_course(math);
        s4.add_course(math2);
        s5.add_course(math3);

        gcr.students.add(s1);
        gcr.students.add(s2);
        gcr.students.add(s3);
        gcr.students.add(s4);
        gcr.students.add(s5);

        System.out.println(gcr.search_by_name("s1"));
        System.out.println(gcr.search_by_roll(20));
        System.out.println(gcr.search_by_roll(30));

        Student s6 = new Student("s6", 60);
        gcr.edit_student(20, s6);
        gcr.edit_student(30, s6);

        gcr.del(10);
    }
}
