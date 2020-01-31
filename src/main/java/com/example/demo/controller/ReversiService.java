package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class ReversiService {
    private static int turn[] = { 10 };
    private static int field[][] = new int[9][9];
    private static List<Integer> xPathList = new ArrayList<>();
    private static List<Integer> yPathList = new ArrayList<>();
    private static List<String> messageList = new ArrayList<>();

    void init() {
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
        // x0y0
        field[0][0] = 30;
        messageList.clear();
    }

    static void showTable(final int array[][]) {
        for (int m = 0; m < 9; m++) {
            for (int t = 0; t < 9; t++) {
                if (array[m][t] == 0 || array[m][t] == 30) {
                    System.out.print("   ");
                } else if (array[m][t] == 10) {
                    System.out.print(" B ");
                } else if (array[m][t] == 11) {
                    System.out.print(" W ");
                } else if (array[m][t] == 20) {
                    System.out.print(" # ");
                } else {
                    System.out.print(" " + array[m][t] + " ");
                }
            }
        }
    }

    static boolean findVoidPath(final int x, final int y) {
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            if (field[y][x] == 0 || field[y][x] == 20) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    static void addPath(int turn[]) {
        int x = 0;
        int y = 0;
        int f;
        if (turn[0] == 11) {
            f = 10;
        } else {
            f = 11;
        }
        xPathList.clear();
        yPathList.clear();
        clearFieldPath();
        for (y = 1; y < 9; y++) {
            for (x = 1; x < 9; x++) {
                int xRight = 8 - x;
                int xLeft = x - 1;
                int yUp = y - 1;
                int yDown = 8 - y;
                if (field[y][x] == 0) {
                    // x右
                    if (xRight > 1) {
                        if (!findVoidPath(x + 1, y) && field[y][x + 1] == f) {
                            for (int i = 1; i < xRight + 1; i++) {
                                if (!findVoidPath(x + i, y) && field[y][x + i] == turn[0]) {
                                    if (field[y][x] != 20) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    }
                                }
                            }
                        }
                    }
                    // x左
                    if (xLeft > 1) {
                        if (!findVoidPath(x - 1, y) && field[y][x - 1] == f) {
                            for (int i = 1; i < xLeft + 1; i++) {
                                if (!findVoidPath(x - i, y) && field[y][x - i] == turn[0]) {
                                    if (field[y][x] != 20) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    }
                                }
                            }
                        }
                    }
                    // y上
                    if (yUp > 1) {
                        if (!findVoidPath(x, y - 1) && field[y - 1][x] == f) {
                            for (int i = 1; i < yUp + 1; i++) {
                                if (!findVoidPath(x, y - i) && field[y - i][x] == turn[0]) {
                                    if (field[y][x] != 20) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    }
                                }
                            }
                        }
                    }
                    // y下
                    if (yDown > 1) {
                        if (!findVoidPath(x, y + 1) && field[y + 1][x] == f) {
                            for (int i = 1; i < yDown + 1; i++) {
                                if (!findVoidPath(x, y + i) && field[y + i][x] == turn[0]) {
                                    if (field[y][x] != 20) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    }
                                }
                            }
                        }
                    }
                    // y右上
                    if (yUp > 1 && xRight > 1) {
                        if (!findVoidPath(x + 1, y - 1) && field[y - 1][x + 1] == f) {
                            if (xRight > yUp) {
                                for (int i = 1; i < yUp + 1; i++) {
                                    if (!findVoidPath(x + i, y - i) && field[y - i][x + i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else if (xRight < yUp) {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (!findVoidPath(x + i, y - i) && field[y - i][x + i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (!findVoidPath(x + i, y - i) && field[y - i][x + i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // y左上
                    if (yUp > 1 && xLeft > 1) {
                        if (!findVoidPath(x - 1, y - 1) && field[y - 1][x - 1] == f) {
                            if (xLeft > yUp) {
                                for (int i = 1; i < yUp + 1; i++) {
                                    if (!findVoidPath(x - i, y - i) && field[y - i][x - i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else if (xLeft < yUp) {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (!findVoidPath(x - i, y - i) && field[y - i][x - i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (!findVoidPath(x - i, y - i) && field[y - i][x - i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // y右下
                    if (yDown > 1 && xRight > 1) {
                        if (!findVoidPath(x + 1, y + 1) && field[y + 1][x + 1] == f) {
                            if (xRight > yDown) {
                                for (int i = 1; i < yDown + 1; i++) {
                                    if (!findVoidPath(x + i, y + i) && field[y + i][x + i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else if (xRight < yDown) {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (!findVoidPath(x + i, y + i) && field[y + i][x + i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (!findVoidPath(x + i, y + i) && field[y + i][x + i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // y左下
                    if (yDown > 1 && xLeft > 1) {
                        if (!findVoidPath(x - 1, y + 1) && field[y + 1][x - 1] == f) {
                            if (xLeft > yDown) {
                                for (int i = 1; i < yDown + 1; i++) {
                                    if (!findVoidPath(x - i, y + i) && field[y + i][x - i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else if (xLeft < yDown) {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (!findVoidPath(x - i, y + i) && field[y + i][x - i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            } else {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (!findVoidPath(x - i, y + i) && field[y + i][x - i] == turn[0]) {
                                        if (field[y][x] != 20) {
                                            xPathList.add(x);
                                            yPathList.add(y);
                                            field[y][x] = 20;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    static void flip(final int x, final int y, final int turn[]) {
        int f;
        if (turn[0] == 11) {
            f = 10;
        } else {
            f = 11;
        }
        final int xRight = 8 - x;
        final int xLeft = x - 1;
        final int yUp = y - 1;
        final int yDown = 8 - y;
        // 該当駒を置く
        field[y][x] = turn[0];
        // x軸のチェック
        if (x == 1 || x == 2) {
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x + k, y) && field[y][x + k] == f) {
                            field[y][x + k] = turn[0];
                        }
                    }
                    break;
                }
            }
        } else if (x == 7 || x == 8) {
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x - k, y) && field[y][x - k] == f) {
                            field[y][x - k] = turn[0];
                        }
                    }
                    break;
                }
            }
        } else {
            // x軸左
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x - k, y) && field[y][x - k] == f) {
                            field[y][x - k] = turn[0];
                        }
                    }
                    break;
                }
            }
            // x軸右
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x + k, y) && field[y][x + k] == f) {
                            field[y][x + k] = turn[0];
                        }
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
                        if (!findVoidPath(x, y + k) && field[y + k][x] == f) {
                            field[y + k][x] = turn[0];
                        }
                    }
                    break;
                }
            }
        } else if (y == 7 || y == 8) {
            for (int i = 1; i < yUp + 1; i++) {
                if (field[y - i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x, y - k) && field[y - k][x] == f) {
                            field[y - k][x] = turn[0];
                        }
                    }
                    break;
                }
            }
        } else {
            // y軸上
            for (int i = 1; i < yUp + 1; i++) {
                if (field[y - i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x, y - k) && field[y - k][x] == f) {
                            field[y - k][x] = turn[0];
                        }
                    }
                    break;
                }
            }
            // y軸下
            for (int i = 1; i < yDown + 1; i++) {
                if (field[y + i][x] == turn[0]) {
                    for (int k = 1; k < i; k++) {
                        if (!findVoidPath(x, y + k) && field[y + k][x] == f) {
                            field[y + k][x] = turn[0];
                        }
                    }
                    break;
                }
            }
        }

        // ななめ
        if (y == 1 || y == 2) {
            // y左下
            if (xLeft < yDown) {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y + i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y + k) && field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y + k) && field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y + i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y + k) && field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
            // y右下
            if (xRight < yDown) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y + k) && field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y + k) && field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y + k) && field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
        } else if (y == 7 || y == 8) {
            // y右上
            if (xRight < yUp) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y - k) && field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y - k) && field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y - k) && field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
            // y左上
            if (xLeft < yUp) {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y - k) && field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y - k) && field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y - k) && field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
        } else {
            // y左下
            if (xLeft < yDown) {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y + i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y + k) && field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y + k) && field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y + i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y + k) && field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
            // y右下
            if (xRight < yDown) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y + k) && field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y + k) && field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y + k) && field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
            // y右上
            if (xRight < yUp) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y - k) && field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y - k) && field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x + k, y - k) && field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            }
            // y左上
            if (xLeft < yUp) {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y - k) && field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y - k) && field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn[0];
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn[0]) {
                        for (int k = 1; k < i; k++) {
                            if (!findVoidPath(x - k, y - k) && field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn[0];
                            }
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
            if (xPathList.get(i) == x && yPathList.get(i) == y) {
                flag = true;
            }
        }
        return flag;
    }

    static void checkField(final int x, final int y, final int turn[]) {
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            if (findVoidPath(x, y) && checkPath(x, y)) {
                flip(x, y, turn);
                changeTurn(turn);
            }
        } else {
            messageList.add("--------------------------");
            messageList.add("正しく座標を入力してください");
        }
    }

    static void changeTurn(int turn[]) {
        if (turn[0] == 11) {
            turn[0] = 10;
        } else {
            turn[0] = 11;
        }
    }

    static boolean passCheck(int turn[]) {
        boolean flag = false;
        addPath(turn);
        if (xPathList.size() == 0) {
            flag = true;
        }
        return flag;
    }

    static boolean winCheck() {
        boolean flag = false;
        int counter = 0;
        for (int i = 1; i < 9; i++) {
            for (int r = 1; r < 9; r++) {
                if (field[i][r] == 0 || field[i][r] == 20) {
                    counter += 1;
                }
            }
        }
        if (counter == 0) {
            flag = true;
        }
        return flag;
    }

    static void sumField() {
        int w = 0;
        int b = 0;
        for (int i = 1; i < 9; i++) {
            for (int r = 1; r < 9; r++) {
                if (field[i][r] == 10) {
                    b += 1;
                }
                if (field[i][r] == 11) {
                    w += 1;
                }
            }
        }
        if (w > b) {
            messageList.add("--------------------------");
            messageList.add("白" + w + "個");
            messageList.add("黒" + b + "個");
            messageList.add("白の勝利です！");
            messageList.add("--------------------------");
        } else if (w < b) {
            messageList.add("--------------------------");
            messageList.add("白" + w + "個");
            messageList.add("黒" + b + "個");
            messageList.add("黒の勝利です！");
            messageList.add("--------------------------");
        } else {
            messageList.add("--------------------------");
            messageList.add("白" + w + "個");
            messageList.add("黒" + b + "個");
            messageList.add("引き分けです！");
            messageList.add("--------------------------");
        }
    }

    void myDo(int x, int y, int turn[]) {
        try {
            if (findVoidPath(x, y)) {
                checkField(x, y, turn);
                nextTurn();
            } else {
                messageList.add("もう一度やり直してください");
            }
        } catch (final ArrayIndexOutOfBoundsException e) {
            messageList.add("内部エラー");
        }
    }

    public int[] getData() {
        int controllerData[] = new int[64];
        int counter = 0;
        for (int i = 1; i < 9; i++) {
            for (int v = 1; v < 9; v++) {
                controllerData[counter] = field[i][v];
                counter += 1;
            }
        }
        return controllerData;
    }

    public String getMessage() {
        String message = "";
        if (messageList.size() > 10) {
            for (int i = 0; i < 2; i++) {
                messageList.remove(0);
            }
        }
        for (int i = 0; i < messageList.size(); i++) {
            message += messageList.get(i) + "\n";
        }
        return message;
    }

    public void putPiece(int xAxis, int yAxis) {
        myDo(xAxis, yAxis, turn);
        if (winCheck()) {
            sumField();
            return;
        } else {
            nextTurn();
        }
    }

    public void nextTurn() {
        int f[] = {};
        if (turn[0] == 10) {
            f[0] = 11;
        } else {
            f[0] = 10;
        }
        npcDo();
    }

    void npcDo() {
        int f[] = {};
        if (turn[0] == 10) {
            f[0] = 11;
        } else {
            f[0] = 10;
        }
        if (passCheck(f)) {
            messageList.add("コンピュータが置く駒がありません");
            messageList.add("コンピュータのターンをパスします");
            addPath(turn);
            return;
        } else {
            Random rand = new Random();
            int a = rand.nextInt(xPathList.size());
            int NPCx = xPathList.get(a);
            int NPCy = yPathList.get(a);
            try {
                if (findVoidPath(NPCx, NPCy)) {
                    checkField(NPCx, NPCy, f);
                    messageList.add("コンピュータが考え中");
                } else {
                    messageList.add("もう一度やり直してください");
                }
            } catch (final ArrayIndexOutOfBoundsException e) {
                messageList.add("正しく座標を入力してください");
            }
            if (winCheck()) {
                sumField();
                return;
            }
            if (passCheck(turn)) {
                messageList.add("自分が置く駒がありません");
                messageList.add("自分のターンをパスします");
                addPath(f);
                npcDo();
            }
        }
    }

    public void start() {
        init();
        addPath(turn);
        messageList.add("ゲーム開始ィィィィ！！！！！！");
    }
}