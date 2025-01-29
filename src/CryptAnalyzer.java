import java.util.Scanner;

public class CryptAnalyzer {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.printOptions();

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        menu.printEnterKey();
    }
}