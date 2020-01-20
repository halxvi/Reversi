import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Reversi {
    private static Scanner stdIn = new Scanner(System.in);
    private static int turn[] = { 11 };
    private static int field[][] = new int[9][9];
    private static List<Integer> xPathList = new ArrayList<Integer>();
    private static List<Integer> yPathList = new ArrayList<Integer>();

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

    static void showTable(final int array[][]) {
        for (int m = 0; m < 9; m++) {
            for (int t = 0; t < 9; t++) {
                if (array[m][t] == 0) {
                    System.out.print("   ");
                } else if (array[m][t] == 10) {
                    System.out.print(" B ");
                } else if (array[m][t] == 11) {
                    System.out.print(" W ");
                } else if (array[m][t] == 20) {
                    System.out.print(" ☆ ");
                } else {
                    System.out.print(" " + array[m][t] + " ");
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }

    private static boolean findVoidPath(final int x, final int y, final int turn[]) {
        if (field[y][x] == 0 || field[y][x] == 20) {
            return true;
        } else {
            return false;
        }
    }

    private static void addPath(int turn[]) {
        int f;
        if (turn[0] == 11) {
            f = 10;
        } else {
            f = 11;
        }
        xPathList.clear();
        yPathList.clear();
        clearFieldPath();
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                if (field[y][x] == turn[0]) {
                    // x右
                    if (x + 2 < 9) {
                        if (!findVoidPath(x, y, turn) && field[y][x + 1] == f) {
                            if (findVoidPath(x + 2, y, turn)) {
                                xPathList.add(x + 2);
                                yPathList.add(y);
                                field[y][x + 2] = 20;
                            }
                        }
                    }
                    // x左
                    if (x - 2 > 0) {
                        if (!findVoidPath(x, y, turn) && field[y][x - 1] == f) {
                            if (findVoidPath(x - 2, y, turn)) {
                                xPathList.add(x - 2);
                                yPathList.add(y);
                                field[y][x - 2] = 20;
                            }
                        }
                    }
                    // y上
                    if (y - 2 > 0) {
                        if (!findVoidPath(x, y, turn) && field[y - 1][x] == f) {
                            if (findVoidPath(x, y - 2, turn)) {
                                xPathList.add(x);
                                yPathList.add(y - 2);
                                field[y - 2][x] = 20;
                            }
                        }
                    }
                    // y下
                    if (y + 2 < 9) {
                        if (!findVoidPath(x, y, turn) && field[y + 1][x] == f) {
                            if (findVoidPath(x, y + 2, turn)) {
                                xPathList.add(x);
                                yPathList.add(y + 2);
                                field[y + 2][x] = 20;
                            }
                        }
                    }
                    // y右上
                    if (y - 2 > 0 && x + 2 < 9) {
                        if (!findVoidPath(x, y, turn) && field[y - 1][x + 1] == f) {
                            if (findVoidPath(x + 2, y - 2, turn)) {
                                xPathList.add(x + 2);
                                yPathList.add(y - 2);
                                field[y - 2][x + 2] = 20;
                            }
                        }
                    }
                    // y左上
                    if (y - 2 > 0 && x - 2 > 0) {
                        if (!findVoidPath(x, y, turn) && field[y - 1][x - 1] == f) {
                            if (findVoidPath(x - 2, y - 2, turn)) {
                                xPathList.add(x - 2);
                                yPathList.add(y - 2);
                                field[y - 2][x - 2] = 20;
                            }
                        }
                    }
                    // y右下
                    if (y + 2 < 9 && x + 2 < 9) {
                        if (!findVoidPath(x, y, turn) && field[y + 1][x + 1] == f) {
                            if (findVoidPath(x + 2, y + 2, turn)) {
                                xPathList.add(x + 2);
                                yPathList.add(y + 2);
                                field[y + 2][x + 2] = 20;
                            }
                        }
                    }
                    // y左下
                    if (y + 2 < 9 && x - 2 > 0) {
                        if (!findVoidPath(x, y, turn) && field[y + 1][x - 1] == f) {
                            if (findVoidPath(x - 2, y + 2, turn)) {
                                xPathList.add(x - 2);
                                yPathList.add(y + 2);
                                field[y + 2][x - 2] = 20;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void insideOut(final int x, final int y, final int turn[]) {
        final int xRight = 8 - x;
        final int xLeft = x - 1;
        final int yUp = y - 1;
        final int yDown = 8 - y;
        // final int cross
        // 該当駒を置く
        field[y][x] = turn[0];
        // x軸のチェック
        if (x == 1 || x == 2) {
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y][x + k] = turn[0];
                    }
                    break;
                }
            }
        } else if (x == 7 || x == 8) {
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y][x - k] = turn[0];
                    }
                    break;
                }
            }
        } else {
            // x軸左
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y][x - k] = turn[0];
                    }
                    break;
                }
            }
            // x軸右
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y][x + k] = turn[0];
                    }
                    break;
                }
            }
        }
        // y軸
        if (y == 1 || y == 2) {
            for (int i = 1; i < yDown + 1; i++) {
                if (field[y + i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y + k][x] = turn[0];
                    }
                    break;
                }
            }
        } else if (y == 7 || y == 8) {
            for (int i = 1; i < yUp + 1; i++) {
                if (field[y - i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y - k][x] = turn[0];
                    }
                    break;
                }
            }
        } else {
            // y軸上
            for (int i = 1; i < yUp + 1; i++) {
                if (field[y - i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y - k][x] = turn[0];
                    }
                    break;
                }
            }
            // y軸下
            for (int i = 1; i < yDown + 1; i++) {
                if (field[y + i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        field[y + k][x] = turn[0];
                    }
                    break;
                }
            }
        }

        // ななめ
        if (y == 1 || y == 2) {
            for (int i = 1; i < yDown; i++) {
                for (int r = 0; r < xRight; r++) {
                    // y右下
                    if (field[y + i][x + r] == turn[0]) {
                        for (int k = 1; k < r; k++) {
                            field[y + k][x + k] = turn[0];
                        }
                        break;
                    }
                }
                for (int n = 0; n < xLeft; n++) {
                    // y左下
                    if (field[y + i][x - n] == turn[0]) {
                        for (int k = 1; k < n; k++) {
                            field[y + k][x - k] = turn[0];
                        }
                        break;
                    }
                }

            }
        } else if (y == 7 || y == 8) {
            for (int i = 1; i < yUp; i++) {
                for (int r = 0; r < xRight; r++) {
                    // y右上
                    if (field[y - i][x + r] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            field[y - k][x + k] = turn[0];
                        }
                        break;
                    }
                }
                for (int n = 0; n < xLeft; n++) {
                    // y左上
                    if (field[y - i][x - n] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            field[y - k][x - k] = turn[0];
                        }
                        break;
                    }
                }
            }
        } else {
            for (int i = 1; i < yUp; i++) {
                for (int r = 0; r < xRight; r++) {
                    // y右上
                    if (field[y - i][x + r] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            field[y - k][x + k] = turn[0];
                        }
                        break;
                    }
                }
                for (int n = 0; n < xLeft; n++) {
                    // y左上
                    if (field[y - i][x - n] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            field[y - k][x - k] = turn[0];
                        }
                        break;
                    }
                }
            }
            for (int i = 1; i < yDown; i++) {
                for (int r = 0; r < xRight; r++) {
                    // y右下
                    if (field[y + i][x + r] == turn[0]) {
                        for (int k = 1; k < r; k++) {
                            field[y + k][x + k] = turn[0];
                        }
                        break;
                    }
                }
                for (int n = 0; n < xLeft; n++) {
                    // y左下
                    if (field[y + i][x - n] == turn[0]) {
                        for (int k = 1; k < n; k++) {
                            field[y + k][x - k] = turn[0];
                        }
                        break;
                    }
                }

            }
        }
    }

    static void clearFieldPath() {
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                if (field[y][x] == 20) {
                    field[y][x] = 0;
                }
            }
        }
    }

    static boolean checkPath(int x, int y) {
        boolean flag = false;
        for (int i = 0; i < xPathList.size(); i++) {
            System.out.println(i + " : " + xPathList.get(i));
            System.out.println(i + " : " + yPathList.get(i));
            if (xPathList.get(i) == x && yPathList.get(i) == y) {
                flag = true;
            }
        }
        return flag;
    }

    static void checkField(final int x, final int y, final int turn[]) {
        if (findVoidPath(x, y, turn) && checkPath(x, y)) {
            insideOut(x, y, turn);
            changeTurn(turn);
            return;
        } else {
            System.out.println("--------------------------");
            System.out.println("正しい場所を指定してください");
            System.out.println("--------------------------");
            return;
        }
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
            if (turn[0] == 11) {
                System.out.println("白の番です");
            } else {
                System.out.println("黒の番です");
            }
            System.out.println("x座標を入力");
            final int x = stdIn.nextInt();
            System.out.println("y座標を入力");
            final int y = stdIn.nextInt();
            checkField(x, y, turn);
        } catch (final ArrayIndexOutOfBoundsException e) {
            System.out.println("--------------------------");
            System.out.println("正しく座標を入力してください" + e);
            System.out.println("--------------------------");
        }
    }

    public static void main(String[] args) {
        init();
        do {
            addPath(turn);
            showTable(field);
            io(turn);
        } while (true);
    }
}