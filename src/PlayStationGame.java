public abstract class PlayStationGame extends VideoGame {
    private final boolean hasPlatinumTrophy;

    public PlayStationGame(String videoGameName, String videoGameConsole, int videoGameReleaseDate, boolean hasPlatinumTrophy) {
        super(videoGameName, videoGameConsole, videoGameReleaseDate);
        this.hasPlatinumTrophy = hasPlatinumTrophy;
    }

    @Override
    public String toString() {
        return "Name: " + this.getVideoGameName() + "\n" +
                "Console: " + this.getVideoGameConsole() + "\n" +
                "Release year: " + this.getVideoGameReleaseYear() + "\n" +
                "Has platinum trophy: " + (this.hasPlatinumTrophy ? "Yes" : "No") + "\n";
    }

}
