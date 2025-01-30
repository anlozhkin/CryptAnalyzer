public class Menu {
    public static final String ENCRYPT_OPTION = "1 - Зашифровать текст";
    public static final String DECRYPT_OPTION = "2 - Расшифровать текст";
    public static final String ENTER_KEY_MESSAGE = "Введите ключ шифра";
    public static final String BRUTEFORCE_MESSAGE = "3 - Попробовать взломать ключ";

    public void printOptions() {
        System.out.println(ENCRYPT_OPTION);
        System.out.println(DECRYPT_OPTION);
        System.out.println(BRUTEFORCE_MESSAGE);
    }

    public void printEnterKey() {
        System.out.println(ENTER_KEY_MESSAGE);
    }
}
