import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    public List<Character> readFileAsCharacters(Path path) {
        List<Character> characters = new ArrayList<>();
        readFileAsStrings(path)
                .forEach(str -> characters.addAll(str.chars().mapToObj(c -> (char) c).toList()));
        return characters;
    }

    public List<String> readFileAsStrings(Path path) {
        try {
            List<String> result = new ArrayList<>();
            List<String> stringsFromFile = Files.readAllLines(path);
            stringsFromFile.stream()
                    .map(str -> str.split(" "))
                    .forEach(arr -> result.addAll(Arrays.asList(arr)));
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(Path path, String data) {
        try {
            Files.write(path, data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
