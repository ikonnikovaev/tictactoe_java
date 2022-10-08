package tictactoe;

public class Game {
    private static final int FIELD_SIZE = 3;
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
        if (checkMainDiag(ch) || checkAuxDiag(ch)) {
            return true;
        }
        return false;
    }

    public boolean isPossible() {
        if (isWinner('X') && isWinner('O')) {
            return false;
        }
        int nX = countChar('X');
        int nO =countChar('O');
        if (Math.abs(nX - nO) > 1) {
            return false;
        }
        return true;
    }

    public String getState() {
        if (!isPossible()) {
            return "Impossible";
        }
        if (isWinner('X')) {
            return "X wins";
        }
        if (isWinner('O')) {
            return "O wins";
        }
        if (hasEmptyCells()) {
            return "Game not finished";
        }
        return "Draw";
    }
}
