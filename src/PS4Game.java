import java.util.Scanner;

public class PS4Game extends PlayStationGame {
    private boolean hasPS5Update;

    private PS4Game(String videoGameName, int videoGameReleaseDate, boolean hasPlatinumTrophy, boolean hasPS5Update) {
        super(videoGameName, "PS4", videoGameReleaseDate, hasPlatinumTrophy);
        this.hasPS5Update = hasPS5Update;
    }

    @Override
    public String toString() {
        return super.toString() +
                "PS5 Update: " + this.hasPS5Update;
    }

    public static PS4Game createPS4game() {
        Scanner scanner = new Scanner(System.in);
        String name;
        int releaseDate;
        boolean hasPlatinumTrophy;
        boolean hasPS5Update;

        System.out.println("Enter PS4 game name:");
        name = scanner.nextLine();

        System.out.println("Enter release date:");
        releaseDate = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Does the game have a platinum trophy?");
        hasPlatinumTrophy = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.println("Does the game have a PS5 update?");
        hasPS5Update = scanner.nextLine().equalsIgnoreCase("yes");

        return new PS4Game(name, releaseDate, hasPlatinumTrophy, hasPS5Update);
    }

    @Override
    public void modifyOptions() {
        super.modifyOptions();
        System.out.println("4. PS5 update available");
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
                System.out.println("PS5 update available:");
                this.hasPS5Update = scanner.nextLine().equalsIgnoreCase("yes");
            default:
                System.out.println("Wrong option chosen.");
        }
        System.out.println(this.getVideoGameName() + " modified successfully.");
    }
}
