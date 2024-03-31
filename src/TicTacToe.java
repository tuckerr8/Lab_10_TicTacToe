import java.util.Scanner;

public class TicTacToe {
    //Initialize rows, columns and the board
    private static final int ROW = 3;
    private static final int COL = 3;
    private static final String[][] board = new String [ROW][COL];

    //Main program
    public static void main(String[] args) {
        //Initialize scanner, booleans, and ints
        Scanner scan = new Scanner(System.in);
        boolean again;
        boolean end;
        boolean move;
        boolean move2;
        int row;
        int col;

        //Game start
        do {
            clearBoard();
            again = false;
            System.out.println("Welcome to TicTacToe. \nPlayer 1 is X \nPlayer 2 is O");
            clearBoard();
            displayBoard();
            do {
                end = false;

                //Display prompt and get player 1's move and check if it's a valid move or not
                do {
                    move = false;
                    row = SafeInput.getRangedInt(scan,"Player 1, what row would you like to put your X in [Top = 1, Middle = 2, Bottom = 3]", 1,3);
                    row--;
                    col = SafeInput.getRangedInt(scan,"Player 1, what column would you like to put your X in [Top = 1, Middle = 2, Bottom = 3]", 1,3);
                    col--;
                    if (isValidMove(row, col)) {
                        board[row][col] = "X";
                        displayBoard();
                        move = true;
                    } else {
                        System.out.println("Invalid move. Please Try Again");
                    }

                    //Winning move check or tying move check for player 1
                } while (!move);
                if (isWin("X")) {
                    System.out.println("Player 1 Wins!");
                    end = true;
                } else if (isTie("X")) {
                    System.out.println("It is a tie");
                    end = true;
                } else {
                    do {
                        //Display prompt and get player 2's move and check if it's a valid move or not

                        move2 = false;
                        row = SafeInput.getRangedInt(scan,"Player 2, what row would you like to put your X in [Top = 1, Middle = 2, Bottom = 3]", 1,3);
                        row--;
                        col = SafeInput.getRangedInt(scan,"Player 2, what column would you like to put your X in [Top = 1, Middle = 2, Bottom = 3]", 1,3);
                        col--;
                        if (isValidMove(row, col)) {
                            board[row][col] = "O";
                            displayBoard();
                            move2 = true;
                        } else {
                            System.out.println("Invalid move. Please Try Again");
                        }

                        //Winning move check or tying move check for player 2
                    } while (!move2);
                    if (isWin("O")) {
                        System.out.println("Player 2 Wins!");
                        end = true;
                    } else if (isTie("X")) {
                        System.out.println("It is a tie");
                        end = true;
                    }
                }

                //Endgame prompt
            } while (!end);
            System.out.println("Would you like to play again? [Y/N]");
            if(scan.nextLine().equalsIgnoreCase("N")){
                again = true;
            }
        }while(!again);
    }

    //Reset and create the board.
    private static void clearBoard () {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = "-";
            }
        }
    }

    //Display the current board.
    private static void displayBoard () {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }

    //Checks to see if the move the player made is valid or not.
    private static boolean isValidMove(int row, int col) {
        boolean check = false;
        if (board[row][col] == "-") {
            check = true;
        }
        return check;
    }

    //Checks to see if there are any winning combinations in the column
    private static boolean isColWin(String player){
        boolean check = false;
        if (board[0][0].equals(player) && board[1][0].equals(player)  && board[2][0].equals(player)) {
            check = true;
        } else if (board[0][1].equals(player) && board[1][1].equals(player)  && board[2][1].equals(player)){
            check = true;
        } else if (board[0][2].equals(player) && board[1][2].equals(player)  && board[2][2].equals(player)){
            check = true;
        }
        return check;
    }

    //Checks to see if there are any winning combinations in the row
    private static boolean isRowWin(String player){
        boolean check = false;
        if (board[0][0].equals(player) && board[0][1].equals(player)  && board[0][2].equals(player)) {
            check = true;
        } else if (board[1][0].equals(player) && board[1][1].equals(player)  && board[1][2].equals(player)){
            check = true;
        } else if (board[2][0].equals(player) && board[2][1].equals(player)  && board[2][2].equals(player)){
            check = true;
        }
        return check;
    }

    //Checks to see if there are any winning combinations diagonally
    private static boolean isDiagonalWin(String player){
        boolean check = false;
        if (board[0][0].equals(player) && board[1][1].equals(player)  && board[2][2].equals(player)) {
            check = true;
        } else if (board[0][2].equals(player) && board[1][1].equals(player)  && board[2][0].equals(player)){
            check = true;
        }
        return check;
    }

    //Declare a winner if any of the three winning combinations above (by row, column, or diagonally) are true.
    private static boolean isWin (String player){
        boolean check = false;
        if (isDiagonalWin(player) || isRowWin(player) || isColWin(player)){
            check = true;
        }
        return check;
    }

    //Checks to see if there is a tie (no winning combinations by row, column, or diagonally).
    private static boolean isTie (String player){
        boolean check = true;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].equals("-")){
                    check = false;
                }
            }
        }
        if(isWin(player)){
            check = false;
        }
        return check;
    }

}