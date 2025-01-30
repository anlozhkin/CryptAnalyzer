import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    public List<Character> readFileAsCharacters(Path path) {
        List<Character> characters = new ArrayList<>();
        try {
            for (String str : Files.readAllLines(path)) {
                characters.addAll(str.chars().mapToObj(c -> (char) c).toList());
                characters.add('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
