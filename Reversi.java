import java.util.Scanner;

public class Reversi {
    static int turn[] = { 11 };
    static int field[][] = new int[9][9];
    static Scanner stdIn = new Scanner(System.in);

    static void init() {
        for (int i = 0; i < 9; i++) {
            for (int n = 0; n < 9; n++) {
                field[i][n] = 0;
            }
        }
        field[4][4] = 11;
        field[5][5] = 11;
        field[4][5] = 10;
        field[5][4] = 10;
        for (int i = 1; i < 9; i++) {
            field[0][i] = i;
            field[i][0] = i;
        }

    }

    static void showTable(int array[][]) {
        for (int m = 0; m < 9; m++) {
            for (int t = 0; t < 9; t++) {
                if (array[m][t] == 0) {
                    System.out.print("   ");
                } else if (array[m][t] == 10) {
                    System.out.print(" B ");
                } else if (array[m][t] == 11) {
                    System.out.print(" W ");
                } else {
                    System.out.print(" " + array[m][t] + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    private static void findVoidPath(int x, int y, int turn[]) {
        if (field[y][x] == 0) {
            field[y][x] = turn[0];
        } else {
            System.out.println("既に置かれています");
        }
    }

    private static void insideOut(int x, int y, int turn[]) {
        int xRight = 8 - x;
        int xLeft = x - 1;
        int yUp = y - 1;
        int yDown = 8 - y;
        // x軸のチェック
        if (x == 1 || x == 2) {

        } else if (x == 7 || x == 8) {
        } else {
            // x軸左
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn[0]) {
                    for (int l = 1; l < i; l++) {
                        field[y][x - l] = turn[0];
                    }
                    break;
                }
                System.out.println("fjejfiejf");
            }
            // x軸右
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn[0]) {
                    for (int l = 1; l < i; l++) {
                        field[y][x + l] = turn[0];
                    }
                    break;
                }
            }
        }
    }

    static void checkField(int x, int y, int turn[]) {
        findVoidPath(x, y, turn);
        insideOut(x, y, turn);
    }

    static void changeTurn(int turn[]) {
        if (turn[0] == 11) {
            turn[0] = 10;
        } else {
            turn[0] = 11;
        }
    }

    static void io(int turn[]) {
        try {
            showTable(field);
            if (turn[0] == 11) {
                System.out.println("白の番です");
            } else {
                System.out.println("黒の番です");
            }
            System.out.println("x座標を入力");
            int x = stdIn.nextInt();
            System.out.println("y座標を入力");
            int y = stdIn.nextInt();
            checkField(x, y, turn);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("正しく座標を入力してください");
        }
    }

    public static void main(String[] args) {
        init();
        do {
            io(turn);
            changeTurn(turn);
        } while (true);
    }
}
