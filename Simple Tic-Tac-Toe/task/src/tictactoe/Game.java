package tictactoe;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Game {
    private static final int FIELD_SIZE = 3;
    private static final String GAME_NOT_FINISHED = "Game not finished";
    private static final String X_WINS = "X wins";
    private static final String O_WINS = "O wins";
    private static final String DRAW = "Draw";

    private static final String IMPOSSIBLE = "Impossible";
    private static char[][] field;

    public void makeField(String input) {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        for (int r = 0; r < FIELD_SIZE; ++r) {
            for (int c = 0; c < FIELD_SIZE; ++c) {
                field[r][c] = input.charAt(r * FIELD_SIZE + c);
            }
        }
    }

    public void printField() {
        System.out.println("---------");
        for (int r = 0; r < FIELD_SIZE; ++r) {
            System.out.print("| ");
            for (int c = 0; c < FIELD_SIZE; ++c) {
                System.out.print(field[r][c] + " ");
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }

    private boolean checkRow(int r, char ch) {
        for (int c = 0; c < FIELD_SIZE; ++c) {
            if (field[r][c] != ch) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int c, char ch) {
        for (int r = 0; r < FIELD_SIZE; ++r) {
            if (field[r][c] != ch) {
                return false;
            }
        }
        return true;
    }

    private boolean checkMainDiag(char ch) {
        for (int r = 0; r < FIELD_SIZE; ++r) {
            if (field[r][r] != ch) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAuxDiag(char ch) {
        for (int r = 0; r < FIELD_SIZE; ++r) {
            if (field[r][FIELD_SIZE - r - 1] != ch) {
                return false;
            }
        }
        return true;
    }

    private int countChar(char ch) {
        int k = 0;
        for (int r = 0; r < FIELD_SIZE; ++r) {
            for (int c = 0; c < FIELD_SIZE; ++c) {
                if (field[r][c] == ch) {
                    k += 1;
                }
            }
        }
        return k;
    }

    private boolean hasEmptyCells() {
        for (int r = 0; r < FIELD_SIZE; ++r) {
            for (int c = 0; c < FIELD_SIZE; ++c) {
                if (field[r][c] == '_') {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isWinner(char ch) {
        for (int r = 0; r < FIELD_SIZE; ++r) {
            if (checkRow(r, ch)) {
                return true;
            }
        }
        for (int c = 0; c < FIELD_SIZE; ++c) {
            if (checkColumn(c, ch)) {
                return true;
            }
        }
        return checkMainDiag(ch) || checkAuxDiag(ch);

    }

    public boolean isPossible() {
        if (isWinner('X') && isWinner('O')) {
            return false;
        }
        int nX = countChar('X');
        int nO =countChar('O');
        return !(Math.abs(nX - nO) > 1);
    }

    public String getState() {
        if (!isPossible()) {
            return IMPOSSIBLE;
        }
        if (isWinner('X')) {
            return X_WINS;
        }
        if (isWinner('O')) {
            return O_WINS;
        }
        if (hasEmptyCells()) {
            return GAME_NOT_FINISHED;
        }
        return DRAW;
    }

    public void makeUserMove(char ch) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int r = scanner.nextInt() - 1;
                int c = scanner.nextInt() - 1;
                if (field[r][c] == '_') {
                    field[r][c] = ch;
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }

    public void play() {
        makeField("_".repeat(FIELD_SIZE * FIELD_SIZE));
        printField();
        int i = 0;
        String state = getState();
        while (state.equals(GAME_NOT_FINISHED)) {
            char ch = 'X';
            if (i % 2 == 1) {
                ch = 'O';
            }
            makeUserMove(ch);
            printField();
            i += 1;
            state = getState();
        }
        System.out.println(state);
    }
}
