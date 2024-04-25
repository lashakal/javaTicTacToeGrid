import java.util.Scanner;

public class TicTacToe {
    private static int size;
    private static char[][] board;
    private static char currentPlayer = 'X';


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isInputValid = false;
        while (!isInputValid) {
            System.out.print("Enter the size of the grid (0-10): ");
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= 0 && size <= 10) {
                    isInputValid = true;
                } else {
                    System.out.println("Invalid input. Try again!");
                }
            } else {
                System.out.println("Invalid input. Try again!");
                scanner.next();
            }
        }

        initializeBoard();
        char winner = '\0';

        while (winner == '\0') {
            displayBoard();
            getPlayerMove();
            winner = checkWinner();
            switchPlayer();
        }

        displayBoard();
        if (winner == 'D') {
            System.out.println("It's a draw!");
        } else {
            System.out.println("Player " + winner + " wins!");
        }
    }

    private static void initializeBoard() {
        board = new char[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        // System.out.println("  0 1 2");
        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                if (j < (size - 1)) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < (size - 1)) {
                System.out.print("  -");
                for (int x = 0; x < (size - 1); x++) {
                    System.out.print("+-");
                }
                System.out.println();
                // System.out.println("  -+-+-");
            }
        }
        System.out.println();
    }

    private static void getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        do {
            System.out.print("Player " + currentPlayer + ", enter row: ");
            row = scanner.nextInt();
            System.out.print("Player " + currentPlayer + ", enter column: ");
            col = scanner.nextInt();
        } while (!isValidMove(row, col));

        board[row][col] = currentPlayer;
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size || board[row][col] != ' ') {
            System.out.println("Invalid move! Try again.");
            return false;
        }
        return true;
    }

    private static char checkWinner() {
        // Check for row wins
        for (int i = 0; i < size; i++) {
            boolean currentPlayerRowWin = true;
            for (int j = 0; j < size; j++) {
                if (board[i][j] != currentPlayer) {
                    currentPlayerRowWin = false;
                    break;
                }
            }
            // After going through one row, if currentPlayerRowWin is still true, return currentPlayer as a winner
            if (currentPlayerRowWin == true) {
                return currentPlayer;
            }
        }

        // Check for column wins
        for (int i = 0; i < size; i++) {
            boolean currentPlayerColumnWin = true;
            for (int j = 0; j < size; j++) {
                if (board[j][i] != currentPlayer) {
                    currentPlayerColumnWin = false;
                    break;
                }
            }
            // After going through one column, if currentPlayerColumnWin is still true, return currentPlayer as a winner
            if (currentPlayerColumnWin == true) {
                return currentPlayer;
            }
        }


        // Check for diagonal wins
        boolean currentPlayerDiagonalWin1 = true;
        boolean currentPlayerDiagonalWin2 = true;

        for (int i = 0; i < size; i++) {
            if (board[i][i] != currentPlayer) {
                currentPlayerDiagonalWin1 = false;
            }

            if (board[i][size - 1 - i] != currentPlayer) {
                currentPlayerDiagonalWin2 = false;
            }
        }

        if(currentPlayerDiagonalWin1 || currentPlayerDiagonalWin2) {
            return currentPlayer;
        }


        // Check for a draw
        boolean isBoardFull = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    isBoardFull = false;
                    break;
                }
            }
        }

        return isBoardFull ? 'D' : '\0'; // 'D' for Draw, '\0' for no winner yet
    }

    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}

