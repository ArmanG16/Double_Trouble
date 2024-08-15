/*Arman Gevorgyan
  20 March 2024
  Double Trouble Game
 */
import java.util.Scanner;

public class DoubleTrouble {
    private int yellow = 7, orange = 5, green = 3; //initialize number of markers and colors

    public static void main(String[] args) {
        DoubleTrouble nim = new DoubleTrouble();
        nim.play();
    }

    private boolean endGame() {
        return yellow == 0 && orange == 0 && green == 0;
    }

    public void play() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose who goes first: 0 for Player(You) and 1 for Computer");
        int isItPlayersTurn = sc.nextInt();

        //QUICK DESCRIPTION OF GAME
        System.out.println("This is Double Trouble!\n" +
                "You are allowed to remove however many markers of any color you want.\n" +
                "The player that removes the last marker wins.\n");

        while(true) { //UPDATES STATUS OF MARKERS PER COLOR TO MAKE GAME EASIER TO PLAY
            System.out.println("\nStatus of Markers:");
            System.out.println("Yellow Markers: " + yellow);
            System.out.println("Orange Markers: " + orange);
            System.out.println("Green Markers: " + green);

            if (isItPlayersTurn == 0) {
                System.out.println("\nIt is the player's turn. Enter the color and the number of markers to remove (EX: green 1):");
                String color = sc.next(); //ENSURES PROPER INPUTTING IN TERMS OF (green 1) instead of 1 or 2 green or any other case
                int numMarkers = sc.nextInt();
                removeMarkers(color, numMarkers);
                isItPlayersTurn = 1;
            } else {
                System.out.println("\nIt is the computer's turn.");
                isItComputersTurn();
                isItPlayersTurn = 0;
            }

            if (endGame()) { //TERMINATES GAME WHEN SOMEONE HAS WON
                if (isItPlayersTurn == 1)
                    System.out.println("\nYou won the game!");
                else
                    System.out.println("\nThe computer won the game, shake my head...");
                break;
            }

        }
        sc.close();
        System.out.println("\nThe game is officially over :(");
    }
    private void removeMarkers(String color, int numMarkers) {
        switch (color.toLowerCase()) { //ensures lower case for color input
            case "yellow":
                yellow -= numMarkers;
                break;
            case "orange":
                orange -= numMarkers;
                break;
            case "green":
                green -= numMarkers;
                break;
        }
    }
    private void isItComputersTurn() {
        int gameNim = yellow ^ orange ^ green;

        if (gameNim != 0) {
            computerSmartMove(gameNim);
        } else {
            makeRandomMove(); //random move unless strategic is available
        }
    }

    private void makeRandomMove() {
        String color = "";
        int numMarkers = 0;

        while (color.isEmpty() || numMarkers <= 0) {
            int randomComputerColor = (int) (Math.random() * 3) + 1;
            int counter = 0;

            switch (randomComputerColor) {
                case 1:
                    color = "yellow";
                    counter = yellow;
                    break;
                case 2:
                    color = "orange";
                    counter = orange;
                    break;
                case 3:
                    color = "green";
                    counter = green;
                    break;
            }

            // This ensures that the computer does not choose a color with 0 markers
            if (counter > 0) {
                numMarkers = (int) (Math.random() * counter) + 1;
            } else {
                color = ""; // Resets and allows for another choosing
            }
        }

        System.out.println("The computer chose to remove " + numMarkers + " " + color + " marker(s).");
        removeMarkers(color, numMarkers);
    }
    private void computerSmartMove(int gameNim) {
        String color = "";
        int numMarkersToRemove = 0;

        if ((yellow ^ gameNim) < yellow) {
            color = "yellow";
            numMarkersToRemove = yellow - (yellow ^ gameNim);
        } else if ((orange ^ gameNim) < orange) {
            color = "orange";
            numMarkersToRemove = orange - (orange ^ gameNim);
        } else if ((green ^ gameNim) < green) {
            color = "green";
            numMarkersToRemove = green - (green ^ gameNim);
        }

        // Makes the move
        System.out.println("The computer chose to remove " + numMarkersToRemove + " " + color + " marker(s).");
        removeMarkers(color, numMarkersToRemove);
    }
}
