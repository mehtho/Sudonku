package com.sudonku;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * A straightforward library to generate and solve Sudoku puzzles
 *
 * @author Matthew Ho
 */
public class Sudonku {
    private final int GRID_SIZE = 9;
    private final int SUBGRID_SIZE = 3;
    private int[][] board = new int[9][9];
    private int fillPercentage;

    /**
     * Create a board filled to a given percentage
     * @param fillPercentage Integer from 0-100
     */
    public Sudonku(int fillPercentage) {
        this.fillPercentage = fillPercentage;
        generateBoard();
    }

    /**
     * Import a board as a 9x9 array of integers from 0-9
     * @param board 9x9 array of integers from 0-9
     */
    public Sudonku(int[][] board) {
        this.board = board;
    }

    /**
     * Create a new, randomised board
     */
    public void generateBoard() {
        board = new int[GRID_SIZE][GRID_SIZE]; //Fills the board with 0's
        threeDiagonals(); //Fills in randomised roots
        recursiveSolve(0, SUBGRID_SIZE); //Recursively solves the board based on created roots
        createGaps(); //Remove numbers to meet the fill percentage requirement
    }

    /**
     * Checks if a given value is valid in coordinates (x,y)
     * @param y The y value of the grid to check
     * @param x The x value of the grid to check
     * @param test The value to check
     * @return Whether the value in the given location is valid
     */
    private boolean isValid(int y, int x, int test) {
        return isValidRow(y, test) && isValidCol(x, test) && isValidBox(y / SUBGRID_SIZE, x / SUBGRID_SIZE, test);
    }

    /**
     * Checks if a value in a given row is valid
     * @param row Position of the row to test
     * @param test Value to test
     * @return Whether the value in the given row is valid
     */
    private boolean isValidRow(int row, int test) {
        for (int n = 0; n < GRID_SIZE; n++) {
            if (board[row][n] == test) return false;
        }
        return true;
    }

    /**
     * Checks if a value in a given column is valid
     * @param col Position of the row to test
     * @param test Value to test
     * @return Whether the value in the given column is valid
     */
    private boolean isValidCol(int col, int test) {
        for (int n = 0; n < GRID_SIZE; n++) {
            if (board[n][col] == test) return false;
        }
        return true;
    }


    /**
     * Checks if a value in a given 3x3 grid (Box) is valid
     * Treat a 9x9 grid as a 3x3 grid of 3x3 grids
     * Therefore the middle 3x3 grid of a normal 9x9 sudoku puzzle will be referred to as
     * (1,1)
     * @param boxRow Row of the box to test
     * @param boxCol Column of the box to test
     * @param test Value to test
     * @return Whether the value in the given column is valid
     */
    private boolean isValidBox(int boxRow, int boxCol, int test) {
        for (int y = 0; y < SUBGRID_SIZE; y++) {
            for (int x = 0; x < SUBGRID_SIZE; x++) {
                if (board[(SUBGRID_SIZE * boxRow) + y][(SUBGRID_SIZE * boxCol) + x] == test) return false;
            }
        }
        return true;
    }

    /**
     * Consider a Sudoku board as a 3x3 board of 3x3 grids
     * 3x3 Grids diagonal of each other are independent
     * Fill these with valid, randomised values to serve as randomised roots of the board
     */
    private void threeDiagonals() {
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            int[] toAdd = getShuffledArray(1, GRID_SIZE);
            for (int y = 0; y < SUBGRID_SIZE; y++) {
                for (int x = 0; x < SUBGRID_SIZE; x++) {
                    board[(SUBGRID_SIZE * i) + y][((SUBGRID_SIZE * i) + x)] = toAdd[(SUBGRID_SIZE * y) + x];
                }
            }
        }
    }

    /**
     * Returns an array of numbers from start to finish in a randomised order
     * @param start Lowest value of the randomly arranged array
     * @param finish Highest value of the randomly arranged array
     * @return The randomly arranged array with values ranging from start to finish
     */
    private int[] getShuffledArray(int start, int finish) {
        Random rand = new Random();
        int[] toReturn = IntStream.iterate(start, n -> n + 1).limit(finish).toArray();
        for (int i = 0; i < toReturn.length; i++) {
            int randomIndexToSwap = rand.nextInt(toReturn.length);
            int temp = toReturn[randomIndexToSwap];
            toReturn[randomIndexToSwap] = toReturn[i];
            toReturn[i] = temp;
        }

        return toReturn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < GRID_SIZE; y++) {
            for (int x = 0; x < GRID_SIZE; x++) {
                if ((y) % SUBGRID_SIZE == 0 && x == 0 && y != 0) sb.append("————————— ————————— —————————\n");
                sb.append(" " + board[y][x] + " ");
                if (x < (GRID_SIZE - 1) && (x + 1) % SUBGRID_SIZE == 0) sb.append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Randomly removes values to meet the fill percentage requirement
     */
    private void createGaps() {
        int target = 81 - (int) (81 * ((double)fillPercentage / 100));
        while (target > 0) {
            int rand = getRandomInt(80);
            int x = rand / GRID_SIZE;
            int y = rand % GRID_SIZE;
            if (board[y][x] != 0) {
                board[y][x] = 0;
                target--;
            }
        }
    }


    /**
     * Begins the recursive solving process
     */
    public void solve() {
        recursiveSolve(0, 0);
    }

    /**
     * A recursive function to iteratively solve a 9x9 sudoku grid
     * Starts from coordinates (x,y)
     * Assigns an experimental value and tests it
     * @param y y coordinate to begin solving from
     * @param x x coordinate to begin solving from
     * @return Whether the experimental value lead to a valid solution
     */
    private boolean recursiveSolve(int y, int x) {
        while (x < GRID_SIZE || y < GRID_SIZE) {                        //While within the board
            if (x >= GRID_SIZE && y <= GRID_SIZE) {                     //If the line is filled, continue at the beginning of the next line
                x = 0;
                y++;
            }
            while (y < GRID_SIZE && x < GRID_SIZE && board[y][x] != 0) {  //Iterate through the board until a non-zero value is found
                x++;

                //Row done
                if (x >= GRID_SIZE && y <= GRID_SIZE) {                 //Continue on the next line if necessary
                    x = 0;
                    y++;
                }

            }

            if (y > 8) return true;                 //If done with the last row, the board is completed successfully
            for (int test = 1; test <= GRID_SIZE; test++) { //Test numbers 1-9 to find a valid solution
                if (isValid(y, x, test)) {
                    board[y][x] = test;           //If value is valid, assign said value to coordinates
                    if (recursiveSolve(y, x + 1))
                        return true;   //Based on this assumption, solve next grid with 0 value

                    board[y][x] = 0;              //If unsuccessful, reset grid to 0
                }
            }
            return false;
        }

        return true;
    }

    /**
     * Returns a random integer from 0 to cap
     * @param cap The highest possible value of the random integer to return
     * @return The randomly generated integer
     */
    private int getRandomInt(int cap) {
        return (int) Math.floor((Math.random() * cap + 1));
    }

    /**
     * Getter for Fill Percentage
     * @return Fill Percentage
     */
    public double getFillPercentage() {
        return fillPercentage;
    }

    /**
     * Setter for Fill Percentage
     * @param fillPercentage Integer from 0-100
     */
    public void setFillPercentage(int fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    /**
     * Returns the board in a 9x9 array of integers with values ranging from 0-9
     * @return Getter for the board
     */
    public int[][] getBoard() {
        return this.board;
    }

    /**
     * Import a board as a 9x9 array of integers with values ranging from 0-9
     * @param board 9x9 array of integers with values ranging from 0-9
     */
    public void setBoard(int[][] board) {
        this.board = board;
    }
}
