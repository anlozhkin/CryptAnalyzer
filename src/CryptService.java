import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CryptService {
    private Alphabets alphabets = new Alphabets();
    private FileManager fileManager = new FileManager();

    private Path dataPath = Path.of("resources/data.txt");
    private Path encryptedPath = Path.of("resources/encrypted.txt");
    private Path decryptedPath = Path.of("resources/decrypted.txt");

    private List<Character> encryptedChars = new ArrayList<>();
    private List<Character> decryptedChars = new ArrayList<>();
    private List<Character> dataChars = new ArrayList<>();

    public void encrypt(int key) throws IOException {
        dataChars = fileManager.readFileAsCharacters(dataPath);
        String encryptedCharsStr = shiftChars(key, dataChars, encryptedChars);
        fileManager.writeFile(encryptedPath, encryptedCharsStr);
    }

    public void decrypt(int key) throws IOException {
        encryptedChars = fileManager.readFileAsCharacters(encryptedPath);
        String decryptedCharsStr = shiftChars(-key, encryptedChars, decryptedChars);
        fileManager.writeFile(decryptedPath, decryptedCharsStr);
    }

    private String shiftChars(int key, List<Character> srcList, List<Character> dstList) throws IOException {
        List<Character> alphabet = alphabets.getAlphabet();

        srcList.forEach(ch -> {
            if (alphabet.contains(ch)) {
                int indexInAlphabet = alphabet.indexOf(ch);
                int shiftedIndexInAlphabet = (indexInAlphabet + key) % alphabet.size();
                dstList.add(alphabet.get(shiftedIndexInAlphabet));
            } else {
                dstList.add(ch);
            }
        });

        return dstList.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    // Для отладки
//    public static void main(String[] args) throws IOException {
//        CryptService cryptService = new CryptService();
//        cryptService.decrypt(1);
//    }
}