import java.lang.reflect.Array;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_to_WIN = 4;
    public static final char EMPTY_DOTS = '.';
    public static final char X_DOTS = 'X';
    public static final char O_DOTS = 'O';
    public static Scanner scan = new Scanner(System.in);
    public static Random Rnd = new Random();
    public static int Xhuman;
    public static int Yhuman;

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanMove();
            printMap();
            if (checkWin(X_DOTS)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiMove();
            printMap();

            if (checkWin(O_DOTS)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = EMPTY_DOTS;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + "  ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanMove() {
        int x;
        int y;
        do {
            System.out.println("Введитее координаты в формате Х и У");
            x = (scan.nextInt()-1);
            y = (scan.nextInt()-1);
        }  while(cellIsOk(x, y) == false);
        map [y][x] = X_DOTS;
    }
    public static void aiMove() {
        int x;
        int y;
        do {
            x = Rnd.nextInt(SIZE);
            y = Rnd.nextInt(SIZE);
        }  while(cellIsOk(x, y) == false);
        System.out.println("Компьютер походил на координаты Х="+ (x+1)+ " У="+(y+1));
        map [y][x] = O_DOTS;
    }
    public static boolean cellIsOk(int x, int y)
    {if ((x<0) || x>=(SIZE)|| (y<0) || y>=(SIZE)) return false;
        return map[y][x] == EMPTY_DOTS;
    }
    public static boolean checkWin(char symb) {
        for (int col=0; col<2; col++) {
            for (int row=0; row<2; row++) {
                if ((checkDiagonal(symb, col, row))||(checkHorVert(symb, col, row))) return true;}
        }
        return false;
    }

    public static  boolean checkDiagonal(char symb, int offsetX, int offsetY) {
        boolean rightDiag, leftDiag;
        rightDiag = true;
        leftDiag = true;

        for (int cols = offsetX; cols < DOTS_to_WIN + offsetX; cols++) {
            for (int row = offsetY; row < DOTS_to_WIN + offsetY; row++) {
                for (int i = 0; i < DOTS_to_WIN; i++) {
                    rightDiag &= (map[i+offsetX][i+offsetY] == symb);
                    leftDiag &= (map[(DOTS_to_WIN-1 + offsetX) - i][i+offsetY] == symb);}
                if (rightDiag || leftDiag) return true;}}
        return false;}


    public static boolean checkHorVert(char symb, int offsetX, int offsetY) {
        boolean cols, rows;
        for (int col=offsetX; col<DOTS_to_WIN+offsetX; col++) {
            cols = true;
            rows = true;
            for (int row=offsetY; row<DOTS_to_WIN+offsetY; row++) {
                cols &= (map[col][row] == symb);
                rows &= (map[row][col] == symb);
            }
            if (cols || rows) return true;
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i<SIZE; i++) {
            for (int j = 0; j<SIZE; j++) {
                if (map[j][i] == EMPTY_DOTS) return false;
            }
        }
        return true;
    }
}

