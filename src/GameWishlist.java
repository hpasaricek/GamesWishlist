import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameWishlist implements Serializable {
    private final String gameWishlistName;
    private final List<VideoGame> wishlistGames = new ArrayList<>();
    private int wishlistGamesCount;

    private static final String NO_GAMES_ERROR_TEXT = "There are no games in the wishlist!";

    public GameWishlist(String gameWishlistName) {
        this.gameWishlistName = gameWishlistName;
        this.wishlistGamesCount = 0;
    }

    public String getGameWishlistName() {
        return gameWishlistName;
    }

    public int getWishlistGamesCount() {
        return wishlistGamesCount;
    }

    public void addGameToWishlist(VideoGame game) {
        if (wishlistGames.contains(game)) {
            System.out.println("Game: " + game.getVideoGameName() + " already exists in the wishlist: " + gameWishlistName);
            return;
        }

        wishlistGames.add(game);
        wishlistGamesCount++;
        System.out.println("Added game: " + game.getVideoGameName() + " to wishlist: " + gameWishlistName);
    }

    public void removeGameFromWishlist() {
        if (wishlistGames.isEmpty()) {
            System.out.println(NO_GAMES_ERROR_TEXT);
            return;
        }

        VideoGame game = findGame();
        if (game == null) {
            System.out.println("The entered game doesn't exist in the wishlist: " + getGameWishlistName());
            return;
        }

        wishlistGames.remove(game);
        System.out.println("Removed game: " + game.getVideoGameName() + " from wishlist: " + gameWishlistName);
        wishlistGamesCount--;
    }

    // Finds a VideoGame by its name and console
    // Returns VideoGame if exists and null if the game doesn't exist
    private VideoGame findGame() {
        displayWishlistGames();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter game name:");
        String gameName = scanner.nextLine();

        System.out.println("Enter game console:");
        String gameConsole = scanner.nextLine();
        VideoGame searchedGame = null;

        for (VideoGame videoGame : wishlistGames) {
            if (videoGame.getVideoGameName().equals(gameName) && videoGame.getVideoGameConsole().equalsIgnoreCase(gameConsole)) {
                searchedGame = videoGame;
            }
        }
        return searchedGame;
    }

    public void displayWishlistGames() {
        if (wishlistGames.isEmpty()) {
            System.out.println(NO_GAMES_ERROR_TEXT);
            return;
        }

        System.out.println(this.gameWishlistName + " has " + wishlistGamesCount + " game(s) in wishlist.\n");
        for (VideoGame game : wishlistGames) {
            System.out.println(game + "\n");
        }
    }

    // Displays a list of games, finds the game according to user input and then modifies the game
    public void modifyGame() {
        if (wishlistGames.isEmpty()) {
            System.out.println(NO_GAMES_ERROR_TEXT);
            return;
        }

        displayWishlistGames();
        VideoGame game = findGame();
        if (game == null) {
            System.out.println("The searched game doesn't exist.");
            return;
        }

        game.modifyVideoGame();
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = result * gameWishlistName.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        GameWishlist wishlist = (GameWishlist) obj;
        return this.gameWishlistName.equalsIgnoreCase(wishlist.gameWishlistName);
    }
}