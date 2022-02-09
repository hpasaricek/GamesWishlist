import java.util.Scanner;

public final class GameWishlistOptions {
    private static final String OPTIONS_FILENAME = "GameWishlistOptions.txt";
    private static final Scanner scanner = new Scanner(System.in);

    private GameWishlistOptions() {
    }

    // Available options used for startOptions() are loaded from file using OptionsLoaderUtils interface
    private static void displayOptions() {
        System.out.println(OptionsLoaderUtils.loadOptionsFromFile(OPTIONS_FILENAME));
    }

    // Starting method for the class
    // Switch cases are corresponding to loaded options from displayOptions()
    public static void startOptions() {
        int userChoice;
        boolean quit = false;
        GameWishlist wishlist = GameWishlistSelector.startSelector();

        while (!quit) {
            displayOptions();
            userChoice = getUserChoice();
            displayDivider();
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
                    wishlist.displayWishlistGames();
                    break;
                case 6:
                    wishlist = GameWishlistSelector.startSelector();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    quit = true;
                    GameWishlistSelector.saveGameWishlistToFile();
                    break;
                default:
                    System.out.println("Wrong number selected. Please try again!");
            }
            displayDivider();
        }
    }

    private static int getUserChoice() {
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        return userChoice;
    }

    private static void displayDivider() {
        System.out.println("\n---------------------------------------------\n");
    }
}