import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameWishlist implements Serializable {
    private final String gamesWishlistName;
    private final List<VideoGame> gamesInWishlist = new ArrayList<>();
    private int gamesCount;


    public GameWishlist(String gamesWishlistName) {
        this.gamesWishlistName = gamesWishlistName;
        this.gamesCount = 0;
    }

    public String getGamesWishlistName() {
        return gamesWishlistName;
    }

    public void addGameToWishlist(VideoGame game) {
        if (gamesInWishlist.contains(game)) {
            System.out.println("This game already exists in the wishlist.");
            return;
        }

        gamesInWishlist.add(game);
        gamesCount++;
        System.out.println("Added " + game.getVideoGameName() + " to " + gamesWishlistName + " wishlist.");
    }

    public void removeGameFromWishlist() {
        VideoGame game = findGame();

        if (game == null) {
            System.out.println("The game doesn't exist!");
            return;
        }

        gamesInWishlist.remove(game);
        System.out.println("Removed " + game.getVideoGameName() + " from " + gamesWishlistName + " list.");
        gamesCount--;
    }

    // Finds a VideoGame object by its name and returns it
    // If not found, returns null
    private VideoGame findGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter game name: ");
        String gameName = scanner.nextLine();

        System.out.println("Enter game console: ");
        String gameConsole = scanner.nextLine();
        VideoGame searchedGame = null;

        for (VideoGame videoGame : gamesInWishlist) {
            if (videoGame.getVideoGameName().equals(gameName) && videoGame.getVideoGameConsole().equalsIgnoreCase(gameConsole)) {
                searchedGame = videoGame;
            }
        }
        return searchedGame;
    }

    public void displayGames() {
        if (gamesInWishlist.isEmpty()) {
            System.out.println("There are no games in the wishlist.");
            return;
        }

        System.out.println(this.gamesWishlistName + " has " + gamesCount + " game(s) in wishlist.");
        for (VideoGame game : gamesInWishlist) {
            System.out.println(game);
        }
    }

    // Displays a list of games, finds the game according to user input and then modifies the game
    public void modifyGame() {
        displayGames();
        VideoGame game = findGame();
        game.modifyVideoGame();
    }

    public void saveWishlist() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("output.dat"))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error while saving GameWishlist.");
        }
    }

    public GameWishlist loadWishlist() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("output.dat"))) {
            return (GameWishlist) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while loading GameWishlist.");
            return null;
        }
    }

    @Override
    public String toString() {
        return this.gamesWishlistName;
    }
}
