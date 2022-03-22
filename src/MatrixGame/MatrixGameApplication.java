package MatrixGame;

import MatrixGame.model.Cell;
import MatrixGame.model.Game;

import java.util.Scanner;

public class MatrixGameApplication {
    /**
     *
     * @param game
     * @param scan
     * @param turn
     * @param options
     */
    public static void scanOption(Game game, Scanner scan, int turn, Cell[] options) {
        String opt = scan.next();
        boolean optCorrect = false;
        while (!optCorrect) {
            switch (opt) {
                case "a":
                case "A":
                case "b":
                case "B":
                case "c":
                case "C":
                case "d":
                case "D":
                    game.selectOption(opt.toLowerCase(), turn, options);
                    optCorrect = true;
                    break;
                case "q":
                case "Q":
                case "quit":
                case "Quit":
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("[ERROR] Invalid input. Please enter A, B, C, or D > ");
                    opt = scan.next();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Game game = new Game();

        int boardSize = game.welcome(scan);
        game.makeGame(boardSize);

        int turn = 0;
        Cell[] options;
        while (turn < game.getTurnCounter()) {
            options = game.findOptions();
            game.printBoard(turn);
            scanOption(game, scan, turn, options);
            turn++;
        }

        game.gameOver();
    }
}
