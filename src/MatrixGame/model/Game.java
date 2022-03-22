package MatrixGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int MIN_CELL_VALUE = 1;
    private static final int MAX_CELL_VALUE_PLUS_ONE = 9; //
    private static final int NUM_OPTIONS = 4;

    // TODO Ask someone about making these final
    private List<ArrayList<Cell>> board;
    private int score1;
    private int score2;
    private int turnCounter;
    private int boardSize;

    public Game() {
    }

    public List<ArrayList<Cell>> getBoard() {
        return board;
    }

    public void setBoard(List<ArrayList<Cell>> board) {
        this.board = board;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * Creates a 2D ArrayList of Cells of size boardSize x boardSize
     * @param boardSize the dimensions of the board are boardSize x boardSize
     */
    public void makeGame(int boardSize) {
        this.boardSize = boardSize;
        this.board = new ArrayList<ArrayList<Cell>>();
        Random rand = new Random();
        for (int i = 0; i < this.boardSize; i++) {
            board.add(new ArrayList<Cell>());
            for (int j = 0; j < boardSize; j++) {
                int randNum = rand.nextInt(MAX_CELL_VALUE_PLUS_ONE - MIN_CELL_VALUE + 1) + MIN_CELL_VALUE;
                int[] coord = {i, j};
                board.get(i).add(new Cell(randNum, coord));
            }
        }
        this.score1 = 0;
        this.score2 = 0;
        this.turnCounter = this.boardSize * this.boardSize;
    }

    /**
     * Prints the cells in the board to the console
     */
    public void printBoard(int turn) {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < this.boardSize; i++) {
            for (int k = 0; k < Cell.CELL_STRING_SIZE; k++) {
                for (int j = 0; j < this.boardSize; j++) {
                    board.get(i).get(j).setPrintStringArr();
                    boardString.append(this.board.get(i).get(j).getPrintStringArr()[k]);
                }
                boardString.append("\n");
            }
        }
        System.out.println(boardString);
        System.out.println("Player 1: " + this.score1);
        System.out.println("Player 2: " + this.score2 + "\n");
        if (turn % 2 == 0)
            System.out.print("Player 1, ");
        else
            System.out.print("Player 2, ");
        System.out.println("enter A, B, C, or D to choose an option > ");
    }

    /**
     * This function contains the logic for finding which cells should be selectable
     * It does this by using the different nested for loop iteration strategies
     *
     * TODO
     * To make it faster, I could create four different ArrayLists of Cells called
     * A, B, C, and D where the Cells are stored in order. Then I could find the options
     * just by iterating through those separate 1D ArrayLists
     *
     * I don't think it will make much of a difference either way, since this is a small
     * project
     */
    public Cell[] findOptions() {
        Cell[] options = new Cell[NUM_OPTIONS];
        Cell curr;
        boolean breakCase = false;
        // A
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                curr = this.board.get(i).get(j);
                if (!curr.isTaken()) {
                    curr.addOption("a");
                    options[0] = curr;
                    breakCase = true;
                    break;
                }
                else
                    curr.setOption(0, "#");
            }
            if (breakCase) {
                breakCase = false;
                break;
            }
        }
        // B
        for (int j = this.boardSize - 1; j >= 0; j--) {
            for (int i = 0; i < this.boardSize; i++) {
                curr = this.board.get(i).get(j);
                if (!curr.isTaken()) {
                    curr.addOption("b");
                    options[1] = curr;
                    breakCase = true;
                    break;
                }
                else
                    curr.setOption(1, "#");
            }
            if (breakCase) {
                breakCase = false;
                break;
            }
        }
        // C
        for (int i = this.boardSize - 1; i >= 0; i--) {
            for (int j = this.boardSize - 1; j >= 0; j--) {
                curr = this.board.get(i).get(j);
                if (!curr.isTaken()) {
                    curr.addOption("c");
                    options[2] = curr;
                    breakCase = true;
                    break;
                }
                else
                    curr.setOption(3, "#");
            }
            if (breakCase) {
                breakCase = false;
                break;
            }
        }
        // D
        for (int j = 0; j < this.boardSize; j++) {
            for (int i = this.boardSize - 1; i >= 0; i--) {
                curr = this.board.get(i).get(j);
                if (!curr.isTaken()) {
                    curr.addOption("d");
                    options[3] = curr;
                    breakCase = true;
                    break;
                }
                else
                    curr.setOption(2, "#");
            }
            if (breakCase) {
                breakCase = false;
                break;
            }
        }
        return options;
    }

    /**
     *
     * @param opt
     * @param turn
     */
    public void selectOption(String opt, int turn, Cell[] options) {
        int points = 0;
        switch (opt) {
            case "a":
                points += options[0].getValue();
                options[0].setTaken(true);
                break;
            case "b":
                points += options[1].getValue();
                options[1].setTaken(true);
                break;
            case "c":
                points += options[2].getValue();
                options[2].setTaken(true);
                break;
            case "d":
                points += options[3].getValue();
                options[3].setTaken(true);
                break;
        }
        if (turn % 2 == 0)
            this.score1 += points;
        else
            this.score2 += points;
    }

    /**
     *
     * @param scan
     * @return
     */
    public static int welcome(Scanner scan) {
        System.out.println("Welcome to the Matrix Game!");
        System.out.println("Please choose a board size (4x4 or 5x5, enter 4 for 4x4 and 5 for 5x5) > ");
        int bs = scan.nextInt();
        while (bs != 4 && bs != 5) {
            System.out.println("[ERROR] Invalid input. Please enter 4 or 5 > ");
            bs = scan.nextInt();
        }
        return bs;
    }

    /**
     *
     */
    public void gameOver() {
        System.out.println("Game over!");
        if (this.score1 > this.score2)
            System.out.println("Player 1 won with a score of " + this.score1 + " to " + this.score2 + "!");
        else if (this.score2 > this.score1)
            System.out.println("Player 2 won with a score of " + this.score2 + " to " + this.score1 + "!");
        else
            System.out.println("It's a tie with both players scoring " + this.score1 + " points!");
    }
}
