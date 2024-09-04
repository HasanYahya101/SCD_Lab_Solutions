class King {
    public int array[][] = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public void converttoK() {
        int j = 4;
        boolean decreasing = true;
        for (int i = 0; i < 10; i++) {
            array[i][0] = 1;
            array[i][j] = 1;
            if (j == 0) {
                decreasing = false;
                j++;
            } else if (decreasing) {
                j--;
            } else if (decreasing == false) { // K on the kleft half
                j++;
            }
        }
    }

    public void converttoI() {
        for (int i = 0; i < 10; i++) {
            array[i][4] = 1;
            array[i][5] = 1;
        }
        for (int i = 0; i < 10; i++) {
            array[0][i] = 1;
        }
        for (int i = 0; i < 10; i++) {
            array[9][i] = 1;
        }
    }

    public void converttoN() {
        for (int i = 0; i < 10; i++) {
            array[i][0] = 1;
            array[i][9] = 1;
            for (int j = 0; j < 10; j++) {
                if (i == j) {
                    array[i][j] = 1;
                }
            }
        }
    }

    public void converttoG() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || i == 9) {
                    array[i][j] = 1;
                } else if (j == 0 || j == 9) {
                    array[i][j] = 1;
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            array[i][9] = 0;
        }

        for (int i = 5; i < 10; i++) {
            array[5][i] = 1;
        }
    }

    public void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void clear() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = 0;
            }
        }
    }
}

public class l227971_Lab2_q4 {
    public static void main(String args[]) {
        King king = new King();
        king.converttoK(); // K kind of looks weird
        king.print();
        king.clear();
        king.converttoI();
        king.print();
        king.clear();
        king.converttoN();
        king.print();
        king.clear();
        king.converttoG();
        king.print();
    }
}