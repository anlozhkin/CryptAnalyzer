import java.util.ArrayList;
import java.util.List;

public class Alphabets {
    public static final int RU_ALPHABET_SIZE = 33;
    public static final int ENG_ALPHABET_SIZE = 26;

    private List<Character> mergedAlphabet = new ArrayList<>();
    private List<Character> rusAlphabet = new ArrayList<>();
    private List<Character> engAlphabet = new ArrayList<>();
    private List<Character> symbols;

    public Alphabets() {
        initialize();
    }

    private void initialize() {
        initializeAlphabet(rusAlphabet, 'а', RU_ALPHABET_SIZE);
        initializeAlphabet(engAlphabet, 'a', ENG_ALPHABET_SIZE);
        symbols = List.of('.', ',', '-', ':', ';', '!');
        mergeLists();
    }

    private void initializeAlphabet(List<Character> alphabet, char startCharacter, int size) {
        for (int i = startCharacter, k = 0; k < size; i++, k++) {
            alphabet.add((char) i);
        }
    }

    private void mergeLists() {
        mergedAlphabet.addAll(rusAlphabet);
        mergedAlphabet.addAll(rusAlphabet.stream().map(Character::toUpperCase).toList());
        mergedAlphabet.addAll(engAlphabet);
        mergedAlphabet.addAll(engAlphabet.stream().map(Character::toUpperCase).toList());
        mergedAlphabet.addAll(symbols);
    }

    public List<Character> getAlphabet() {
        return mergedAlphabet;
    }
}
