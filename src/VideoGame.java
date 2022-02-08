import java.io.Serializable;

public abstract class VideoGame implements Serializable {
    private String videoGameName;
    private String videoGameConsole;
    private int videoGameReleaseDate;

    public VideoGame(String videoGameName, String videoGameConsole, int videoGameReleaseDate) {
        this.videoGameName = videoGameName;
        this.videoGameConsole = videoGameConsole;
        this.videoGameReleaseDate = videoGameReleaseDate;
    }

    public String getVideoGameName() {
        return videoGameName;
    }

    public void setVideoGameName(String videoGameName) {
        this.videoGameName = videoGameName;
    }

    public String getVideoGameConsole() {
        return videoGameConsole;
    }

    public void setVideoGameConsole(String videoGameConsole) {
        this.videoGameConsole = videoGameConsole;
    }

    public int getVideoGameReleaseDate() {
        return videoGameReleaseDate;
    }

    public void setVideoGameReleaseDate(int videoGameReleaseDate) {
        this.videoGameReleaseDate = videoGameReleaseDate;
    }

    public void modifyOptions() {
        System.out.println("Select an option you want to change:");
        System.out.println("1. Video game name\n" +
                "2. Video game console\n" +
                "3. Video game release date");
    }

    public abstract void modifyVideoGame();

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + ((videoGameName == null) ? 0 : videoGameName.hashCode()) + ((videoGameConsole == null) ? 0 : videoGameConsole.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        VideoGame videoGame = (VideoGame) obj;
        return (this.videoGameName.equalsIgnoreCase(videoGame.videoGameName)) && (this.videoGameConsole.equalsIgnoreCase(videoGame.videoGameConsole));
    }
}
