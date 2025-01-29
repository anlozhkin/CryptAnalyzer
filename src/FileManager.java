import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public List<Character> readFileAsCharacters(Path path) throws IOException {
        List<Character> characters = new ArrayList<>();
        Files.readAllLines(path)
                .forEach(str -> characters.addAll(str.chars().mapToObj(c -> (char) c).toList()));
        return characters;
    }

    public void writeFile(Path path, String data) throws IOException {
        Files.write(path, data.getBytes());
    }
}
