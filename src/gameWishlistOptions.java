import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class gameWishlistOptions {
    private static final StringBuilder options = new StringBuilder();

    // Loads available options from "options.txt" file and writes it into variable options
    static {
        String input;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("options.txt"))) {
            while ((input = bufferedReader.readLine()) != null) {
                options.append(input).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error while loading \"options.txt\" file!");
        }
    }

    private gameWishlistOptions() {
    }

    // Entry method for gameWishlistOptions class
    // Loops if the user doesn't choose "Quit" from available options
    public static void startOptions(GamesWishlist wishlist) {
        boolean quit;

        do {
            displayOptions();
            quit = selectOptions(wishlist);
            displayDivider();
        } while (!quit);
    }

    // Displays a text menu with available options that were loaded from "options.txt"
    private static void displayOptions() {
        System.out.println("Available options:" +
                "\n" + options +
                "Please enter a number to choose the corresponding option:");
    }

    // Accepts user input and chooses the corresponding option
    // Returns true if user chooses "Quit" or false otherwise
    private static boolean selectOptions(GamesWishlist wishlist) {
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        boolean quit = false;

        switch (userChoice) {
            case 1:
                wishlist.addGameToWishlist(PS4Game.createPS4game());
                break;
            case 2:
                wishlist.addGameToWishlist(PS5Game.createPS5game());
                break;
            case 3:
                wishlist.removeGameFromWishlist();
                break;
            case 4:
                wishlist.modifyGame();
                break;
            case 5:
                wishlist.displayGames();
                break;
            case 6:
                System.out.println("Exiting program.");
                quit = true;
                break;
            default:
                System.out.println("Wrong number selected. Please try again!");
        }
        return quit;
    }

    public static void displayDivider() {
        System.out.println("\n****************************************\n");
    }
}
