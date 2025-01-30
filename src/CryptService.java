import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CryptService {
    private Alphabets alphabets = new Alphabets();
    private FileManager fileManager = new FileManager();

    private List<Character> encryptedChars = new ArrayList<>();
    private List<Character> decryptedChars = new ArrayList<>();
    private List<Character> dataChars = new ArrayList<>();

    /**
     * Зашифровывает файл data.txt
     * @param key
     */
    public void encrypt(int key) {
        dataChars = fileManager.readFileAsCharacters(Constants.DATA_PATH);
        String encryptedCharsStr = shiftChars(key, dataChars, encryptedChars);
        fileManager.writeFile(Constants.ENCRYPTED_PATH, encryptedCharsStr);
    }

    /**
     * Расшифровывает файл encrypted.txt
     * @param key - ключ сдвига
     */
    public void decrypt(int key) {
        encryptedChars = fileManager.readFileAsCharacters(Constants.ENCRYPTED_PATH);
        String decryptedCharsStr = shiftChars(-key, encryptedChars, decryptedChars);
        fileManager.writeFile(Constants.DECRYPTED_PATH, decryptedCharsStr);
    }

    /**
     * Расшифровывает одно слово
     * @param key - ключ сдвига
     * @param word - слово для расшифровки
     */
    public String decrypt(int key, String word) {
        List<Character> wordChars = word.chars().mapToObj(c -> (char) c).toList();
        List<Character> resultChars = new ArrayList<>();
        return shiftChars(-key, wordChars, resultChars);
    }

    /**
     * Сдвигает символы на переданный ключ
     * @param key - ключ, по которому двигать символы (вправо или лево)
     * @param srcList - список источник символов
     * @param dstList - список получатель символов
     * @return возвращает зашифровонные символы в виде строки
     */
    private String shiftChars(int key, List<Character> srcList, List<Character> dstList) {
        List<Character> alphabet = alphabets.getAlphabet();

        for (Character ch : srcList) {
            if (alphabet.contains(ch)) {
                int indexInAlphabet = alphabet.indexOf(ch);
                int shiftedIndexInAlphabet = Math.abs((indexInAlphabet + key) % alphabet.size());
                dstList.add(alphabet.get(shiftedIndexInAlphabet));
            } else {
                dstList.add(ch);
            }
        }

        return dstList.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    // Для отладки
//    public static void main(String[] args) throws IOException {
//        CryptService cryptService = new CryptService();
//        cryptService.decrypt(1, "Боупо");
//    }
}