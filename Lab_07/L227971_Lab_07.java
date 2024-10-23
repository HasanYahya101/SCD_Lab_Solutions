import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

class Employee implements Serializable
{
    public String ssn;
    public int age;
    public String first_name;
    public String last_name;
    
    public Employee(String ssn, int age, String first_name, String last_name)
    {
        this.ssn = ssn;
        this.age = age;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    
}

class Record
{
    public ArrayList<Employee> list = new ArrayList<>();
    
    public void search_ssn(String ssn)
    {
        int count = 0;
        for (Employee employee : this.list)
        {
            if (employee.ssn.equals(ssn))
            {
                System.out.println(employee.ssn + ", " + employee.age + ", " + employee.first_name + ", " + employee.last_name);
                count++;
            }
        }
        
        if (count == 0)
        {
            System.out.println("Error: No data found");
        }
    }
    
    public void search_first_name(String f_name)
    {
        int count = 0;
        for (Employee employee : this.list)
        {
            if (employee.first_name.equals(f_name))
            {
                System.out.println(employee.ssn + ", " + employee.age + ", " + employee.first_name + ", " + employee.last_name);
                count++;
            }
        }
        
        if (count == 0)
        {
            System.out.println("Error: No data found");
        }
    }
    
    public void search_last_name(String l_name)
    {
        int count = 0;
        for (Employee employee : this.list)
        {
            if (employee.last_name.equals(l_name))
            {
                System.out.println(employee.ssn + ", " + employee.age + ", " + employee.first_name + ", " + employee.last_name);
                count++;
            }
        }
        
        if (count == 0)
        {
            System.out.println("Error: No data found");
        }
    }
    
    public void search_age(int age)
    {
        int count = 0;
        for (Employee employee : this.list)
        {
            if (employee.age == age)
            {
                System.out.println(employee.ssn + ", " + employee.age + ", " + employee.first_name + ", " + employee.last_name);
                count++;
            }
        }
        
        if (count == 0)
        {
            System.out.println("Error: No data found");
        }
    }
    
    public void show_all()
    {
        if (this.list.size() == 0)
        {
            System.out.println("Error: List is empty"); 
            return;  
        }
        
        for (Employee employee : this.list)
        {
                System.out.println(employee.ssn + ", " + employee.age + ", " + employee.first_name + ", " + employee.last_name);
        }
    }
}

public class L227971_Lab_07 {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("123-45-6789", 30, "Hasan", "Yahya"));
        employees.add(new Employee("987-65-4321", 25, "Wasee", "Rehman"));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("employees.ser"))) {
            oos.writeObject(employees);
            System.out.println("Employees have been serialized to employees.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Employee> deserializedEmployees = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("employees.ser"))) {
            deserializedEmployees = (ArrayList<Employee>) ois.readObject();
            System.out.println("Employees have been deserialized from employees.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Record rec = new Record();
        rec.list = deserializedEmployees;
        
        rec.search_age(30);
        rec.search_first_name("Wasee");
        rec.search_last_name("Ali");
        rec.show_all();
    }
    
}
