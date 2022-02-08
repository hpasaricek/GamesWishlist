import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class GameWishlistSelector {
    private static List<GameWishlist> savedGameWishlists = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    static {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("savedWishlists.dat")))) {
            savedGameWishlists = (List<GameWishlist>) objectInputStream.readObject();
        } catch (IOException e) {
            System.out.println("Error while loading file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error while loading wishlists from file: " + e.getMessage());
        }
    }

    private GameWishlistSelector() {
    }

    private static void displaySelectorOptions() {
        System.out.println("Available options are:");
        System.out.println(
                "\n1. Create a new game wishlist." +
                        "\n2. Choose an existing game wishlist." +
                        "\n3. Remove an existing game wishlist." +
                        "\n4. Display all saved game wishlists." +
                        "\n5. Quit.");
        System.out.println("\nPlease enter a number to choose the corresponding option:");
    }

    public static void startSelector() {
        boolean quit = false;

        while (!quit) {
            displaySelectorOptions();

            switch (getUserInput()) {
                case 1:
                    GameWishlistOptions.startOptions(createWishlist());
                    break;
                case 2:
                    displayWishlists();
                    GameWishlistOptions.startOptions(chooseExistingWishlist());
                    break;
                case 3:
                    System.out.println("Choose the wishlist you want to remove:");
                    displayWishlists();
                    removeWishlist();
                    break;
                case 4:
                    displayWishlists();
                    break;
                case 5:
                    System.out.println("Quitting.");
                    quit = true;
                    saveWishlistList();
                    break;
                default:
                    System.out.println("Wrong option chosen. Please try again!");
            }
            displayDivider();
        }
    }

    private static void displayWishlists() {
        if (savedGameWishlists.isEmpty()) {
            System.out.println("There are no saved wishlists.");
            return;
        }

        System.out.println("Saved wishlists:");
        int orderNum = 1;
        for (GameWishlist wishlist : savedGameWishlists) {
            System.out.println(orderNum + ". " + wishlist);
            orderNum++;
        }
    }

    private static void removeWishlist() {
        int userChoice = getUserInput() - 1;
        if (userChoice > (savedGameWishlists.size() + 1)) {
            System.out.println("Wrong option chosen.");
            return;
        }

        GameWishlist foundWishlist = savedGameWishlists.get(userChoice);
        savedGameWishlists.remove(foundWishlist);
        System.out.println("Successfully removed wishlist: " + foundWishlist.getGamesWishlistName());
    }

    private static GameWishlist createWishlist() {
        System.out.println("Enter wishlist name:");
        String wishlistName = scanner.nextLine();
        GameWishlist wishlist = new GameWishlist(wishlistName);
        System.out.println("Created new wishlist: " + wishlistName);
        savedGameWishlists.add(wishlist);
        return wishlist;
    }

    private static GameWishlist chooseExistingWishlist() {
        int choice = getUserInput() - 1;
        return savedGameWishlists.get(choice);
    }

    private static void saveWishlistList() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("savedWishlists.dat"))) {
            objectOutputStream.writeObject(savedGameWishlists);
        } catch (IOException e) {
            System.out.println("Error while saving wishlists to file: " + e.getMessage());
        }
    }

    private static int getUserInput() {
        int userChoice = scanner.nextInt();
        scanner.nextLine();
        return userChoice;
    }

    private static void displayDivider() {
        System.out.println("\n---------------------------------------------\n");
    }
}
