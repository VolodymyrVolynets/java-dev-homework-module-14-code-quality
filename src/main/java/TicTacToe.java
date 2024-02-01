import java.util.Scanner;

public class TicTacToe {
    private byte winner = 0;
    private char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Scanner scan = new Scanner(System.in);

    public void startGame() {
        System.out.println("Enter box number to select. Enjoy!\n");
        boolean boxEmpty = false;

        while (true) {
            loggerField();
            boxEmpty = clearGameField(boxEmpty);

            if (winner != 0 || !boxEmpty) {
                announceGameResult(winner);
                break;
            }

            fillField();
            catcherCheckBox();
            checkIfDraw(boxEmpty);

            makeComputerMove();
            winner = defineWinnerOfTheGame('O') == 1 ? 2 : defineWinnerOfTheGame('X');
        }
    }

    private void catcherCheckBox() {
        for (int i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                break;
            }
        }
    }

    private byte defineWinnerOfTheGame(char sign) {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows of field
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns of field
                {0, 4, 8}, {2, 4, 6}             // Diagonal of field
        };

        for (int[] condition : winConditions) {
            if (box[condition[0]] == sign && box[condition[1]] == sign && box[condition[2]] == sign) {
                return 1;
            }
        }
        return 0;
    }

    private void loggerField() {
        for (int i = 0; i < box.length; i += 3) {
            System.out.printf(" %s | %s | %s ", box[i], box[i + 1], box[i + 2]);
            if (i + 3 < box.length) {
                System.out.println("\n-----------");
            }
        }
        System.out.println("\n\n");
    }

    private boolean clearGameField(boolean boxEmpty) {
        if (!boxEmpty) {
            for (int i = 0; i < 9; i++) {
                box[i] = ' ';
            }
        }
        return true;
    }

    private void fillField() {
        byte input;
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    System.out.println("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                System.err.println("Invalid input. Enter again.");
            }
        }
    }

    private void makeComputerMove() {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                break;
            }
        }
    }

    private void announceGameResult(byte winner) {
        String creator = "Created by Shreyas Saha. Thanks for playing!";
        if (winner == 1) {
            System.out.println("You won the game!\n" + creator);
        } else if (winner == 2) {
            System.out.println("You lost the game!\n" + creator);
        } else if (winner == 3) {
            System.out.println("It's a draw!\n" + creator);
        }
    }

    private void checkIfDraw(boolean boxAvailable) {
        if (!boxAvailable) {
            winner = 3;
        }
    }
}
