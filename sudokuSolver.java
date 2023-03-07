import java.util.Scanner;

public class sudokuSolver{

        static final int GRID_SIZE = 9;

    public static void main (String[] args){

        int[][] testboard = {
            {3,8,5,2,6,4,7,1,0},
            {1,0,7,9,0,5,0,2,3},
            {9,2,4,7,1,3,6,0,0},
            {0,0,8,6,9,0,5,3,0},
            {0,0,3,4,7,0,2,9,6},
            {0,9,0,0,0,0,0,4,7},
            {0,5,9,1,2,6,0,0,8},
            {0,0,0,0,0,9,0,5,2},
            {2,0,1,8,0,0,9,6,0},
        };

        // int[][] board = new int[9][9];


        // System.out.println("Enter the integers from the sudoku board you want to solve. Enter all values from left to right," + 
        // "with 0 representing the blank squares.");

        // Scanner scan = new Scanner (System.in);

        // for(int i = 0; i < GRID_SIZE; i++) {
        //     for(int j = 0; j < GRID_SIZE; j++) {
        //         board[i][j] = scan.nextInt();
        //     }
        // }

        // if (solve(board)) {
        //     System.out.println("Solved successfully.");
        //     printBoard(board);
        // } else
        //     System.out.println("Board was unsolvable.");



        if(solve(testboard)){
            System.out.println("Solved successfully.");
            printBoard(testboard);
        }
        else
            System.out.println("Board was unsolvable.");



    }

    /**
     * Checks if the target integer doesn't have a duplicate in the working row
     * @param board  The sudoku board
     * @param row    The row of the entry point
     * @param target The integer we want to place in the entry point
     * @return true if the entry point is a valid option for target
     */
    public static boolean checkRow(int[][] board, int row, int target){

        //Check if the number we want to place is already within the specified row of the board.
        for(int i = 0; i < GRID_SIZE; i++) {
            if(board[row][i] == target)
                return false; //The number already occupies the row.
        }

        return true;
    }   

    /**
     * Checks if the target integer doesn't have a duplicate in the working column
     * @param board  The sudoku board
     * @param col    The column of the entry point
     * @param target The integer we want to place in the entry point
     * @return true if the entry point is a valid option for the target
     */
    public static boolean checkCol(int[][] board, int col, int target) {

        // Check if the number we want to place is already within the specified column of
        // the board.
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == target)
                return false; // The number already occupies the column.
        }

        return true;
    }

    /**
     * Checks if the target integer doesn't have a duplicate within the working square
     * @param board The sudoku board
     * @param row The row of the entry point
     * @param col The column of the entry point
     * @param target The integer to be placed in the entry point
     * @return true if the entry point is a valid option for the target
     */
    public static boolean checkSquare(int[][] board, int row, int col, int target){

        for(int i = row - row % 3; i < (row - row % 3) + 3; i++){
            for(int j = col - col % 3; j < (col - col % 3) + 3; j++){
                if(board[i][j] == target){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the target integer is valid for the entry point, with respect to all sudoku rules
     * @param board The sudoku board
     * @param row The row of the entry point
     * @param col The column of the entry point
     * @param target The integer to be placed in the entry point
     * @return true if the target is a valid option for the target
     */
    public static boolean entryCheck(int[][] board, int row, int col, int target){

        if((checkRow(board, row, target) && checkCol(board, col, target) && checkSquare(board, row, col, target)))
            return true;

        else
            return false;

    }

    /**
     * Solves the sudoku board
     * @param board The sudoku board
     * @return true if the board has been solved
     */
    public static boolean solve(int[][] board){
        int[] nums = {1,2,3,4,5,6,7,8,9};

        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++){

                //if a blank space is discovered
                if(board[i][j] == 0){
                    for(int n = 1; n <= GRID_SIZE; n++){
                        if(entryCheck(board, i, j, n)){
                            board[i][j] = n;

                            if(solve(board))
                                return true;

                            else
                                board[i][j] = 0;
                        }

                    }
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Prints the sudoku board
     * @param board The sudoku board
     */
    public static void printBoard(int[][] board){

        for(int i = 0; i < GRID_SIZE; i++){
            for(int j = 0; j < GRID_SIZE; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}