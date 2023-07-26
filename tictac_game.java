import java.util.Scanner;

public class tictac_game{
    private static final int SIZE = 3;
    private static final char EMPTY = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private char[][] board;
    private char currentPlayer;

    public tictac_game() {
        board = new char[SIZE][SIZE];
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    public void play() {
        boolean gameFinished = false;
        int movesCount = 0;

        while (!gameFinished) {
            displayBoard();
            int[] move = getPlayerMove();

            if (isValidMove(move[0], move[1])) {
                makeMove(move[0], move[1]);

                if (isWinningMove(move[0], move[1])) {
                    displayBoard();
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameFinished = true;
                } else if (movesCount == SIZE * SIZE - 1) {
                    displayBoard();
                    System.out.println("It's a draw!");
                    gameFinished = true;
                }

                currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                movesCount++;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = EMPTY;
            }
        }
    }

    private void displayBoard() {
        System.out.println("-------------");

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print("| " + board[row][col] + " ");
            }

            System.out.println("|\n-------------");
        }
    }

    private int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Player " + currentPlayer + ", enter your move (row column): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        return new int[]{row, col};
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY;
    }

    private void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private boolean isWinningMove(int row, int col) {
        // Check row
        if (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) {
            return true;
        }

        // Check column
        if (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) {
            return true;
        }

        // Check diagonals
        if (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (row + col == SIZE - 1 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        tictac_game game= new tictac_game();
        game.play();
    }
}

