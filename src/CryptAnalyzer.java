import java.util.Scanner;

public class CryptAnalyzer {

    public static void main(String[] args) {
        Menu menu = new Menu();
        CryptService cryptService = new CryptService();
        BruteforceService bruteforceService = new BruteforceService();

        menu.printOptions();

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1 -> {
                menu.printEnterKey();
                int key = scanner.nextInt();
                cryptService.encrypt(key);
            }
            case 2 -> {
                menu.printEnterKey();
                int key = scanner.nextInt();
                cryptService.decrypt(key);
            }
            case 3 -> {
                int probableKey = bruteforceService.bruteforce();
                if (probableKey != 0) {
                    System.out.println("Вероятный ключ: " + probableKey);
                } else {
                    System.out.println("Ключ не был подобран");
                }
            }
        }
    }
}