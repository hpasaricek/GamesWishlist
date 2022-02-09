import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class GameWishlistSelector {
    private static List<GameWishlist> savedGameWishlists = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final String OPTIONS_FILENAME = "GameWishlistSelectorOptions.txt";
    private static final String SAVED_WISHLISTS_FILENAME = "SavedGameWishlists.dat";
    private static final String EMPTY_WISHLIST_ERROR_TEXT = "There are no saved wishlists!";

    // List containing saved GameWishlists is loaded from file and placed into savedGameWishlists
    static {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVED_WISHLISTS_FILENAME)))) {
            savedGameWishlists = (List<GameWishlist>) objectInputStream.readObject();
            if (savedGameWishlists.isEmpty()) {
                System.out.println("No saved wishlists found.");
            } else {
                System.out.println("Found saved wishlists.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error! " + e.getMessage());
        } finally {
            displayDivider();
        }
    }

    private GameWishlistSelector() {
    }

    // Available options used for startSelector() are loaded from file using OptionsLoaderUtils interface
    private static void displaySelectorOptions() {
        System.out.println(OptionsLoaderUtils.loadOptionsFromFile(OPTIONS_FILENAME));
    }

    // Starting method for the class
    // Switch cases are corresponding to loaded options from displaySelectorOptions()
    // Returns selected GameWishlist if found, if not loops again
    public static GameWishlist startSelector() {
        boolean quit = false;
        int userChoice;
        GameWishlist wishlist = null;

        while (!quit) {
            displaySelectorOptions();
            userChoice = getUserChoice();
            displayDivider();
            switch (userChoice) {
                case 1:
                    wishlist = getExistingGameWishlist();
                    if (wishlist != null) {
                        quit = true;
                    }
                    break;
                case 2:
                    createNewGameWishlist();
                    break;
                case 3:
                    removeExistingGameWishlist();
                    break;
                case 4:
                    displayExistingGameWishlists();
                    break;
                default:
                    System.out.println("Wrong option chosen. Please try again!");
            }
            displayDivider();
        }
        return wishlist;
    }

    private static void displayExistingGameWishlists() {
        if (savedGameWishlists.isEmpty()) {
            System.out.println(EMPTY_WISHLIST_ERROR_TEXT);
            return;
        }

        int orderNum = 1;
        System.out.println("Num.\tWishlist name\tTotal games in wishlist");
        for (GameWishlist wishlist : savedGameWishlists) {
            System.out.println(orderNum + ".\t\t" + wishlist.getGameWishlistName() + "\t\t\t" + wishlist.getWishlistGamesCount());
            orderNum++;
        }
    }

    private static void removeExistingGameWishlist() {
        GameWishlist foundWishlist;

        if (savedGameWishlists.isEmpty()) {
            System.out.println(EMPTY_WISHLIST_ERROR_TEXT);
            return;
        }

        foundWishlist = getExistingGameWishlist();
        if (foundWishlist == null) {
            System.out.println("Chosen wishlist doesn't exist.");
            return;
        }

        savedGameWishlists.remove(foundWishlist);
        System.out.println("Successfully removed wishlist: " + foundWishlist.getGameWishlistName());
    }

    private static void createNewGameWishlist() {
        System.out.println("Enter wishlist name:");
        String wishlistName = scanner.nextLine();
        GameWishlist wishlist = new GameWishlist(wishlistName);

        if (savedGameWishlists.contains(wishlist)) {
            System.out.println("Wishlist with the name: " + wishlist.getGameWishlistName() + " already exists! Wishlist is not saved.");
            return;
        }

        System.out.println("Created new wishlist: " + wishlistName);
        savedGameWishlists.add(wishlist);
    }

    private static GameWishlist getExistingGameWishlist() {
        GameWishlist foundWishlist;

        if (savedGameWishlists.isEmpty()) {
            System.out.println(EMPTY_WISHLIST_ERROR_TEXT);
            return null;
        }

        displayExistingGameWishlists();
        System.out.println("Choose the wishlist:");
        int wishlistIndex = getUserChoice() - 1;
        try {
            foundWishlist = savedGameWishlists.get(wishlistIndex);
        } catch (IndexOutOfBoundsException e) {
            foundWishlist = null;
        }
        return foundWishlist;
    }

    public static void saveGameWishlistToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SAVED_WISHLISTS_FILENAME))) {
            objectOutputStream.writeObject(savedGameWishlists);
            System.out.println("Saved wishlists to file.");
        } catch (IOException e) {
            System.out.println("Error - " + e.getMessage());
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