import java.nio.file.Path;

public class Constants {
    private static final String RESOURCES_PATH = "resources/";
    public static final Path DATA_PATH = Path.of(RESOURCES_PATH + "data.txt");
    public static final Path ENCRYPTED_PATH = Path.of(RESOURCES_PATH + "encrypted.txt");
    public static final Path DECRYPTED_PATH = Path.of(RESOURCES_PATH + "decrypted.txt");
    public static final Path DICTIONARY_PATH = Path.of(RESOURCES_PATH + "dictionary.txt");
}
