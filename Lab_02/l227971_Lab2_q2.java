import java.util.*;

enum Currency {
    PKR, INR, Pound, Dirham, BDT, JPY
}

class Conversion_Rate {
    public double value = 0;
    public Currency cur = Currency.PKR;

    public Conversion_Rate(double val, Currency cur) {
        this.cur = cur;
        this.value = val;
    }
}

class Converter {
    public ArrayList<Conversion_Rate> rates = new ArrayList<Conversion_Rate>();

    public Converter() {
        Conversion_Rate pkr = new Conversion_Rate(236, Currency.PKR);
        Conversion_Rate pound = new Conversion_Rate(0.88, Currency.Pound);
        Conversion_Rate dirham = new Conversion_Rate(3.67, Currency.Dirham);
        Conversion_Rate inr = new Conversion_Rate(79, Currency.INR);
        Conversion_Rate bdt = new Conversion_Rate(104, Currency.BDT);
        Conversion_Rate jpy = new Conversion_Rate(142, Currency.JPY);

        rates.add(pkr);
        rates.add(pound);
        rates.add(dirham);
        rates.add(inr);
        rates.add(bdt);
        rates.add(jpy);
    }

    private double getVal(Currency cur) {
        for (int i = 0; i < this.rates.size(); i++) {
            if (this.rates.get(i).cur == cur) {
                return this.rates.get(i).value;
            }
        }

        return -1;
    }

    public double convert(Currency from, double val, Currency to) {
        double result = 0;
        try {
            double val1 = getVal(to);
            val = val / val1;
            double val2 = getVal(from);
            result = val * val2;
            return result;
        } catch (ArithmeticException e) {
            System.out.println("Can't divide by zero");
        } finally {
            return result;
        }
    }
}

public class l227971_Lab2_q2 {
    public static void main(String[] args) {
        Converter conv_Converter = new Converter();
        double res = conv_Converter.convert(Currency.PKR, 100, Currency.INR);
        System.out.println(res);
    }

}
