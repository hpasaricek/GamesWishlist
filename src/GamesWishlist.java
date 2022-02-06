import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GamesWishlist implements Serializable {
    private final String gamesWishlistName;
    private final List<VideoGame> gamesInWishlist = new ArrayList<>();
    private int gamesCount;


    public GamesWishlist(String gamesWishlistName) {
        this.gamesWishlistName = gamesWishlistName;
        this.gamesCount = 0;
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
            gameWishlistOptions.displayDivider();
        }
    }

    // Displays a list of games, finds the game according to user input and then modifies the game
    public void modifyGame() {
        displayGames();
        VideoGame game = findGame();
        game.modifyVideoGame();
    }
}
