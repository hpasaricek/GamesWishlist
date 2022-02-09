import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface OptionsLoaderUtils {
    static StringBuilder loadOptionsFromFile(String FILE_NAME) {
        StringBuilder options = new StringBuilder();
        String input;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))) {
            options.append("Available options are:\n");
            while ((input = bufferedReader.readLine()) != null) {
                options.append(input).append("\n");
            }
            options.append("Please enter a number to choose the corresponding option:");
        } catch (IOException e) {
            System.out.println("Error while loading file!");
        }

        return options;
    }

}
