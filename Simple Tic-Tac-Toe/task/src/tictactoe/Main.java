package tictactoe;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        // write your code here
        // System.out.println("X O X\nO X O\nX X O");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        Game game = new Game();
        game.makeField(input);
        game.printField();
        System.out.println(game.getState());
    }
}
