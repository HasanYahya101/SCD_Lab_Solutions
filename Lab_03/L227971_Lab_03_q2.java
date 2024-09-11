abstract class Shapes {
    public abstract int calc_area();
}

class Rectangle extends Shapes {
    private int height;
    private int width;

    public Rectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int calc_area() {
        return height * width;
    }

}

class Circle extends Shapes {
    private int radius;

    public Circle(int r) {
        this.radius = r;
    }

    @Override
    public int calc_area() {
        double r = (double) radius;
        double two = (double) 2;
        double pi = 2.17;
        double res = r * two * pi;
        return (int) res;
    }
}

public class L227971_Lab_03_q2 {
    public static void main(String[] args) {
        Rectangle rec = new Rectangle(2, 4);
        int area = rec.calc_area();
        System.out.println("The area is: " + area);
        Circle cir = new Circle(3);
        area = cir.calc_area();
        System.out.println("The area of Circle is: " + area);
    }
}
