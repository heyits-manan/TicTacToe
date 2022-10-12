import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTac {

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();


    //main function starts!
    public static void main(String[] args) {
        char[][] gameBoard = {
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
                { '-', '+', '-', '+', '-' },
                { ' ', '|', ' ', '|', ' ' },
        };

        printGameBoard(gameBoard);

        while (true) {

            Scanner sc = new Scanner(System.in);

            //position the player chooses!
            System.out.println("Entert the position (1 - 9): ");
            int position = sc.nextInt();

            //checking the winner!
            String result = checkWinner(); 
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            //making sure the player position does not take the place of cpu position and vice versa!
            while (playerPosition.contains(position) || cpuPosition.contains(playerPosition)
                    || cpuPosition.contains(position)) {
                System.out.println("Position taken! Please select a new position.");
                position = sc.nextInt();
            }

            //Function to put the user input inside individual boxes!
            placePiece(position, gameBoard, "player");

            
            Random ran = new Random();  //Random input by CPU!
            int cpuPos = ran.nextInt(9) + 1;

            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {    //making sure the player position does not take the place of cpu position and vice versa!
                cpuPos = ran.nextInt(9) + 1;
            }

            placePiece(cpuPos, gameBoard, "cpu");

            printGameBoard(gameBoard);
            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

        }

    };

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(int position, char[][] gameBoard, String user) {

        char symbol = 'X';
        if (user.equals("player")) {
            symbol = 'X';
            playerPosition.add(position);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPosition.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;

        }

    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(bottomRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Congratulations u have won";
            } else if (cpuPosition.containsAll(l)) {
                return "CPU has won";
            }

        }
        if (playerPosition.size() + cpuPosition.size() == 9) {
            return "CAT!";
        }
        return "";
    }

}
