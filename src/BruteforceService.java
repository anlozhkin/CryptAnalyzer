import java.util.ArrayList;
import java.util.List;

public class BruteforceService {
    private static final int DUMMY_KEYS_COUNT = 100;

    private FileManager fileManager = new FileManager();
    private CryptService cryptService = new CryptService();

    private List<String> encryptedStrings;
    private List<String> dictionaryStrings;
    private List<Integer> keys = new ArrayList<>();

    public BruteforceService() {
        encryptedStrings = fileManager.readFileAsStrings(Constants.ENCRYPTED_PATH);
        dictionaryStrings = fileManager.readFileAsStrings(Constants.DICTIONARY_PATH);
    }

    public int bruteforce() {
        if (encryptedStrings.isEmpty()) {
            throw new IllegalArgumentException("Файл с зашифрованными словами не может быть пустым");
        }
        // Сначала проходимся по наиболее вероятным ключам
        int possibleKey = fillKeysAndBruteforce(1, DUMMY_KEYS_COUNT);
        if (checkPossibleKey(possibleKey)) {
            return possibleKey;
        }
        // Если среди наиболее подходящих ключей не было совпадений, то проходимся по всему положительному Integer
        possibleKey = fillKeysAndBruteforce(31, Integer.MAX_VALUE);
        if (checkPossibleKey(possibleKey)) {
            return possibleKey;
        }
        // Если среди всех положительных ключей не было совпадений, то проходимся по всему отрицательному Integer
        possibleKey = fillKeysAndBruteforce(Integer.MIN_VALUE, -1);
        if (checkPossibleKey(possibleKey)) {
            return possibleKey;
        }
        return 0;
    }

    private int fillKeysAndBruteforce(int startValue, int endValue) {
        for (int i = startValue; i <= endValue; i++) {
            keys.add(i);
        }
        return bruteforce(keys);
    }

    private int bruteforce(List<Integer> keys) {
        int possibleKey = 0;
        for (Integer key : keys) {
            List<String> decryptedWords = new ArrayList<>();
            // Расшифровываем все слова по каждому ключу
            for (String encStr : encryptedStrings) {
                decryptedWords.add(cryptService.decrypt(key, encStr));
            }
            int countMatchingWords = 0;
            for (String dword : decryptedWords) {
                if (dictionaryStrings.contains(dword)) {
                    countMatchingWords++;
                }
            }
            if (countMatchingWords >= decryptedWords.size() / 2) {
                possibleKey = key;
                break;
            }
        }
        return possibleKey;
    }

    private boolean checkPossibleKey(int possibleKey) {
        return possibleKey != 0;
    }
}
