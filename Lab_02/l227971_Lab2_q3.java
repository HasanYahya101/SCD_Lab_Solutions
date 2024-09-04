class Maze_Game {
    char[][] maze = { { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' },
            { 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o' } };

    public void print() {
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out
                    .println("" + maze[i][0] + ' ' + maze[i][1] + ' ' + maze[i][2] + ' ' + maze[i][3] + ' ' + maze[i][4]
                            + ' ' + maze[i][5] + ' ' + maze[i][6] + ' ' + maze[i][7] + ' ' + maze[i][8] + ' '
                            + maze[i][9] + "");
        }
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("");
    }

    public int rand() {
        int num = (int) (Math.random() * 7);
        return num;
    }

    public int getRow(int num) {
        if (num >= 90 && num <= 100) {
            return 10;
        }
        int v = num % 10;
        if (v < 1) {
            return 1;
        }
        return v;
    }

    public int getCol(int num) {
        int val = num / 10;
        if (val < 1) {
            val = 1;
        }
        return val;
    }

    public void clear_maze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                this.maze[i][j] = 'o';
            }
        }
    }

    public void set_Marker(int r, int c) {
        if (r > 9) {
            r = 9;
        }
        if (c > 9) {
            c = 9;
        }
        this.maze[r][c] = 'x';
    }

    public void play() {
        int position = 0;

        while (position < 100) {
            int rand = rand();
            System.out.println("\n\nRandom number is: " + rand);
            position = position + rand;
            if (position > 100) {
                position = 100;
            }
            System.out.println("Position is: " + position);
            clear_maze();
            set_Marker(getRow(position) - 1, getCol(position) - 1);
            print();
        }

    }
}

public class l227971_Lab2_q3 { // i hate java, but atleast it shows the exceptions
    public static void main(String[] args) {
        Maze_Game game = new Maze_Game();
        game.play();
    }
}
