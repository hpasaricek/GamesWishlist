public abstract class PlayStationGame extends VideoGame {
    private final boolean hasPlatinumTrophy;

    public PlayStationGame(String videoGameName, String videoGameConsole, int videoGameReleaseDate, boolean hasPlatinumTrophy) {
        super(videoGameName, videoGameConsole, videoGameReleaseDate);
        this.hasPlatinumTrophy = hasPlatinumTrophy;
    }

    @Override
    public String toString() {
        return "Console: " + this.getVideoGameConsole() + "\n" +
                "Name: " + this.getVideoGameName() + "\n" +
                "Release date: " + this.getVideoGameReleaseDate() + "\n" +
                "Has platinum trophy: " + this.hasPlatinumTrophy + "\n";
    }

}
