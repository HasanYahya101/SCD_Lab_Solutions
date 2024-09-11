class BankAccount {
    private int acc_num;
    private String name;
    private double balance;

    public BankAccount(int acc, String name, double bal) {
        if (bal < 0) {
            bal = 0;
        }
        this.acc_num = acc;
        this.name = name;
        this.balance = bal;
    }

    public boolean deposit(double am) {
        this.balance = this.balance + am;
        return true;
    }

    public boolean withdraw(double am) {
        if (this.balance - am < 1) {
            System.out.println("Error: not enough balance");
            return false;
        }
        this.balance = this.balance - am;
        return true;
    }

    public void display() {
        System.out.println(
                "Account No: " + this.acc_num + ", " + "Name: " + this.name + ", " + "Balance: " + this.balance);
    }
}

public class L227971_Lab_03_q1 {
    public static void main(String[] args) {
        BankAccount bank = new BankAccount(1, "Hasan", 0);
        bank.deposit(100);
        bank.withdraw(50);
        bank.display();
    }
}