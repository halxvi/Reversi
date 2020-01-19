import java.util.Scanner;

public class Reversi {
    private static int turn = 10;
    private static int field[][] = new int[9][9];

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

    static void checkField(int x, int y, int turn) {
        if (field[y][x] == 0) {
            field[y][x] = turn;
        } else {
            System.out.println("既に置かれています");
            return;
        }
    }

    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        init();
        do {
            switch (turn) {
            case 10:
                try {
                    showTable(field);
                    System.out.println("白の番です");
                    System.out.println("x座標を入力");
                    int x = stdIn.nextInt();
                    System.out.println("y座標を入力");
                    int y = stdIn.nextInt();
                    checkField(x, y, turn);
                    if (turn == 10) {
                        turn = 11;
                    } else {
                        turn = 10;
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("正しく座標を入力してください");
                    break;
                }
            case 11:
                try {
                    showTable(field);
                    System.out.println("黒の番です");
                    System.out.println("x座標を入力");
                    int a = stdIn.nextInt();
                    System.out.println("y座標を入力");
                    int b = stdIn.nextInt();
                    checkField(a, b, turn);
                    if (turn == 10) {
                        turn = 11;
                    } else {
                        turn = 10;
                    }
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("正しく座標を入力してください");
                    break;
                }
            }
        } while (true);
    }
}
