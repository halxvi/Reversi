package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
class ReversiService {
    private int turn[] = { 11 };
    private int field[][] = new int[9][9];
    private List<Integer> xPathList = new ArrayList<>();
    private List<Integer> yPathList = new ArrayList<>();
    private List<String> messageList = new ArrayList<>();

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

    boolean findVoidPath(int x, int y) {
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

    void addPath(int turn) {
        int f;
        if (turn == 11) {
            f = 10;
        } else {
            f = 11;
        }
        xPathList.clear();
        yPathList.clear();
        clearFieldPath();
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                int xRight = 8 - x;
                int xLeft = x - 1;
                int yUp = y - 1;
                int yDown = 8 - y;
                if (findVoidPath(x, y)) {
                    // x右
                    if (xRight > 1) {
                        if (field[y][x + 1] == f) {
                            for (int i = 1; i < xRight + 1; i++) {
                                if (field[y][x + i] == turn) {
                                    xPathList.add(x);
                                    yPathList.add(y);
                                    field[y][x] = 20;
                                } else if (findVoidPath(x + i, y)) {
                                    break;
                                }
                            }
                        }
                    }
                    // x左
                    if (xLeft > 1) {
                        if (field[y][x - 1] == f) {
                            for (int i = 1; i < xLeft + 1; i++) {
                                if (field[y][x - i] == turn) {
                                    xPathList.add(x);
                                    yPathList.add(y);
                                    field[y][x] = 20;
                                } else if (findVoidPath(x - i, y)) {
                                    break;
                                }
                            }
                        }
                    }
                    // y上
                    if (yUp > 1) {
                        if (field[y - 1][x] == f) {
                            for (int i = 1; i < yUp + 1; i++) {
                                if (field[y - i][x] == turn) {
                                    xPathList.add(x);
                                    yPathList.add(y);
                                    field[y][x] = 20;
                                } else if (findVoidPath(x, y - i)) {
                                    break;
                                }
                            }
                        }
                    }
                    // y下
                    if (yDown > 1) {
                        if (field[y + 1][x] == f) {
                            for (int i = 1; i < yDown + 1; i++) {
                                if (field[y + i][x] == turn) {
                                    xPathList.add(x);
                                    yPathList.add(y);
                                    field[y][x] = 20;
                                } else if (findVoidPath(x, y + i)) {
                                    break;
                                }
                            }
                        }
                    }
                    // y右上
                    if (yUp > 1 && xRight > 1) {
                        if (field[y - 1][x + 1] == f) {
                            if (xRight > yUp) {
                                for (int i = 1; i < yUp + 1; i++) {
                                    if (field[y - i][x + i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x + i, y - i)) {
                                        break;
                                    }
                                }
                            } else if (xRight < yUp) {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (field[y - i][x + i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x + i, y - i)) {
                                        break;
                                    }
                                }
                            } else {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (field[y - i][x + i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x + i, y - i)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // y左上
                    if (yUp > 1 && xLeft > 1) {
                        if (field[y - 1][x - 1] == f) {
                            if (xLeft > yUp) {
                                for (int i = 1; i < yUp + 1; i++) {
                                    if (field[y - i][x - i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x - i, y - i)) {
                                        break;
                                    }
                                }
                            } else if (xLeft < yUp) {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (field[y - i][x - i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x - i, y - i)) {
                                        break;
                                    }
                                }
                            } else {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (field[y - i][x - i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x - i, y - i)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // y右下
                    if (yDown > 1 && xRight > 1) {
                        if (field[y + 1][x + 1] == f) {
                            if (xRight > yDown) {
                                for (int i = 1; i < yDown + 1; i++) {
                                    if (field[y + i][x + i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x + i, y + i)) {
                                        break;
                                    }
                                }
                            } else if (xRight < yDown) {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (field[y + i][x + i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x + i, y + i)) {
                                        break;
                                    }
                                }
                            } else {
                                for (int i = 1; i < xRight + 1; i++) {
                                    if (field[y + i][x + i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x + i, y + i)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    // y左下
                    if (yDown > 1 && xLeft > 1) {
                        if (field[y + 1][x - 1] == f) {
                            if (xLeft > yDown) {
                                for (int i = 1; i < yDown + 1; i++) {
                                    if (field[y + i][x - i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x - i, y + i)) {
                                        break;
                                    }
                                }
                            } else if (xLeft < yDown) {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (field[y + i][x - i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x - i, y + i)) {
                                        break;
                                    }
                                }
                            } else {
                                for (int i = 1; i < xLeft + 1; i++) {
                                    if (field[y + i][x - i] == turn) {
                                        xPathList.add(x);
                                        yPathList.add(y);
                                        field[y][x] = 20;
                                    } else if (findVoidPath(x - i, y + i)) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    void flip(final int x, final int y, final int turn) {
        int f;
        if (turn == 11) {
            f = 10;
        } else {
            f = 11;
        }
        final int xRight = 8 - x;
        final int xLeft = x - 1;
        final int yUp = y - 1;
        final int yDown = 8 - y;
        // 該当駒を置く
        field[y][x] = turn;
        // x軸のチェック
        if (x == 1 || x == 2) {
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y][x + k] == f) {
                            field[y][x + k] = turn;
                        }
                    }
                    break;
                }
            }
        } else if (x == 7 || x == 8) {
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y][x - k] == f) {
                            field[y][x - k] = turn;
                        }
                    }
                    break;
                }
            }
        } else {
            // x軸左
            for (int i = 1; i < xLeft + 1; i++) {
                if (field[y][x - i] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y][x - k] == f) {
                            field[y][x - k] = turn;
                        }
                    }
                    break;
                }
            }
            // x軸右
            for (int i = 1; i < xRight + 1; i++) {
                if (field[y][x + i] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y][x + k] == f) {
                            field[y][x + k] = turn;
                        }
                    }
                    break;
                }
            }
        }
        // y軸
        if (y == 1 || y == 2) {
            for (int i = 1; i < yDown + 1; i++) {
                if (field[y + i][x] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y + k][x] == f) {
                            field[y + k][x] = turn;
                        }
                    }
                    break;
                }
            }
        } else if (y == 7 || y == 8) {
            for (int i = 1; i < yUp + 1; i++) {
                if (field[y - i][x] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y - k][x] == f) {
                            field[y - k][x] = turn;
                        }
                    }
                    break;
                }
            }
        } else {
            // y軸上
            for (int i = 1; i < yUp + 1; i++) {
                if (field[y - i][x] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y - k][x] == f) {
                            field[y - k][x] = turn;
                        }
                    }
                    break;
                }
            }
            // y軸下
            for (int i = 1; i < yDown + 1; i++) {
                if (field[y + i][x] == turn) {
                    for (int k = 1; k < i; k++) {
                        if (field[y + k][x] == f) {
                            field[y + k][x] = turn;
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
                    if (field[y + i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y + i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            }
            // y右下
            if (xRight < yDown) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn;
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
                    if (field[y - i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            }
            // y左上
            if (xLeft < yUp) {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn;
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
                    if (field[y + i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y + i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x - k] == f) {
                                field[y + k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            }
            // y右下
            if (xRight < yDown) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yDown) {
                for (int i = 1; i < yDown + 1; i++) {
                    if (field[y + i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y + i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y + k][x + k] == f) {
                                field[y + k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            }
            // y右上
            if (xRight < yUp) {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xRight > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xRight + 1; i++) {
                    if (field[y - i][x + i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x + k] == f) {
                                field[y - k][x + k] = turn;
                            }
                        }
                        break;
                    }
                }
            }
            // y左上
            if (xLeft < yUp) {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else if (xLeft > yUp) {
                for (int i = 1; i < yUp + 1; i++) {
                    if (field[y - i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            } else {
                for (int i = 1; i < xLeft + 1; i++) {
                    if (field[y - i][x - i] == turn) {
                        for (int k = 1; k < i; k++) {
                            if (field[y - k][x - k] == f) {
                                field[y - k][x - k] = turn;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    void clearFieldPath() {
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                if (field[y][x] == 20) {
                    field[y][x] = 0;
                }
            }
        }
    }

    boolean checkPath(int x, int y) {
        boolean flag = false;
        for (int i = 0; i < xPathList.size(); i++) {
            if (xPathList.get(i) == x && yPathList.get(i) == y) {
                flag = true;
            }
        }
        return flag;
    }

    void checkField(final int x, final int y, final int turn) {
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            if (findVoidPath(x, y) && checkPath(x, y)) {
                flip(x, y, turn);
            }
        } else {
            messageList.add("正しく座標を入力してください");
        }
    }

    boolean passCheck(int turn) {
        boolean flag = false;
        addPath(turn);
        if (xPathList.size() == 0) {
            flag = true;
        }
        return flag;
    }

    boolean winCheck() {
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

    void sumField() {
        int w = 0;
        int b = 0;
        for (int i = 1; i < 9; i++) {
            for (int r = 1; r < 9; r++) {
                if (field[i][r] == 11) {
                    b += 1;
                }
                if (field[i][r] == 10) {
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

    int[] getData() {
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

    String getMessage() {
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

    void putPiece(int xAxis, int yAxis) {
        myDo(xAxis, yAxis);
        if (winCheck()) {
            sumField();
        }
    }

    void myDo(int x, int y) {
        int t = turn[0];
        try {
            if (findVoidPath(x, y)) {
                checkField(x, y, t);
                npcDo();
            } else {
                messageList.add("正しい場所を選択してください");
                messageList.add("x" + x + "y" + y);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            messageList.add("座標外です");
            messageList.add("x" + x + "y" + y);
        }
    }

    void npcDo() {
        int t = turn[0];
        int f;
        if (turn[0] == 10) {
            f = 11;
        } else {
            f = 10;
        }
        if (passCheck(f)) {
            messageList.add("コンピュータが置く駒がありません");
            messageList.add("コンピュータのターンをパスします");
        } else {
            addPath(f);
            Random rand = new Random();
            int a = rand.nextInt(xPathList.size());
            int NPCx = xPathList.get(a);
            int NPCy = yPathList.get(a);
            messageList.add(NPCx + ":" + NPCy);
            try {
                if (findVoidPath(NPCx, NPCy)) {
                    messageList.add("コンピュータのターン");
                    checkField(NPCx, NPCy, f);
                } else {
                    messageList.add("コンピュータ:もう一度やり直してください");
                }
            } catch (final ArrayIndexOutOfBoundsException e) {
                messageList.add("コンピュータ：正しく座標を入力できませんでした");
            }
        }
        if (passCheck(t)) {
            messageList.add("自分が置く駒がありません");
            messageList.add("自分のターンをパスします");
            npcDo();
        } else {
            addPath(t);
            messageList.add("あなたのターンです");
        }
    }

    void start() {
        init();
        int t = turn[0];
        addPath(t);
        messageList.add("ゲーム開始ィィィィ！！！！！！");
    }
}