import java.util.Scanner;

public class PS5Game extends PlayStationGame {
    private boolean isPS5Exclusive;

    public PS5Game(String videoGameName, int videoGameReleaseDate, boolean hasPlatinumTrophy, boolean isPS5Exclusive) {
        super(videoGameName, "PS5", videoGameReleaseDate, hasPlatinumTrophy);
        this.isPS5Exclusive = isPS5Exclusive;
    }

    @Override
    public String toString() {
        return super.toString() +
                "PS5 Exclusive: " + this.isPS5Exclusive;
    }

    public static PS5Game createPS5game() {
        Scanner scanner = new Scanner(System.in);
        String name;
        int releaseDate;
        boolean hasPlatinumTrophy;
        boolean isPS5Exclusive;

        System.out.println("Enter PS5 game name:");
        name = scanner.nextLine();

        System.out.println("Enter release date:");
        releaseDate = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Does the game have a platinum trophy?");
        hasPlatinumTrophy = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.println("Is the game a PS5 exclusive?");
        isPS5Exclusive = scanner.nextLine().equalsIgnoreCase("yes");

        return new PS5Game(name, releaseDate, hasPlatinumTrophy, isPS5Exclusive);
    }

    @Override
    public void modifyOptions() {
        super.modifyOptions();
        System.out.println("4. PS5 exclusive");
    }

    @Override
    public void modifyVideoGame() {
        modifyOptions();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("New game name:");
                this.setVideoGameName(scanner.nextLine());
                break;
            case 2:
                System.out.println("New game console:");
                this.setVideoGameConsole(scanner.nextLine());
                break;
            case 3:
                System.out.println("New release date:");
                this.setVideoGameReleaseDate(scanner.nextInt());
                break;
            case 4:
                System.out.println("PS5 exclusive:");
                this.isPS5Exclusive = scanner.nextLine().equalsIgnoreCase("yes");
                break;
            default:
                System.out.println("Wrong option chosen.");
        }
        System.out.println(this.getVideoGameName() + " modified successfully.");
    }
}
