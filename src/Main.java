import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final int AMOUNT_OF_DATA = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию имя отчество датаРождения номерТелефона пол");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            scanner.close();
            throw new RuntimeException("пустые строки вводить нельзя");
        }
        String[] contentInput = input.split(" ");

        if (contentInput.length < AMOUNT_OF_DATA) {
            scanner.close();
            throw new RuntimeException("Данные введены некорректно, неверное количество(данных меньше)");
        }

        if (contentInput.length > AMOUNT_OF_DATA) {
            scanner.close();
            throw new RuntimeException("Данные введены некорректно, неверное количество(данных больше)");
        }

        for (int i = 0; i < contentInput.length; i++) {
            if (contentInput[i].isEmpty()) {
                scanner.close();
                throw new RuntimeException("Данные введены некорректно; null");
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!contentInput[i].matches("[A-Z][a-z]*")) {
                scanner.close();
                throw new RuntimeException("Данные введены некорректно: неправильно введено Фамилия || Имя || отчество");
            }
        }

        if (!contentInput[3].matches("((0[1-9])|([1-2][0-9])|(3[0,1]))[.]((0[1-9])|(1[0-2]))[.][1-2][0-9][0-9][0-9]")) {
            //думаю основное задание не поиск в интернете регулярных выражений, поэтому я написал его сам,
            // здесь нет исключений, на месяц февраль, или если в месяце 30 дней, а не 31 и на то, если введут дату рождения больше текущей даты
            // и др., если это критическая ошибка, то исправлю
            scanner.close();
            throw new RuntimeException("Данные введены некорректно: неправильно введена дата рождения");
        }

        if (!contentInput[4].matches("8[0-9]{10}")) {
            scanner.close();
            throw new RuntimeException("Данные введены некорректно: неправильно введен номер телефона");
        }

        if (!contentInput[5].matches("f|m")) {
            scanner.close();
            throw new RuntimeException("Данные введены некорректно: неправильно введен пол");
        }

        File log = new File(contentInput[0]);

        try {
            if (!log.exists()) {
                System.out.println("Людей с такой фамилией ещё не было");
                log.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(log, true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("<" + contentInput[0] + ">" + "<" + contentInput[1] + ">" +
                    "<" + contentInput[2] + ">" + "<" + contentInput[3] + ">" + "<" + contentInput[4] + ">" +
                    "<" + contentInput[5] + ">\n");
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать в файл", e);
        }

        System.out.println("Copy successfully");
    }
}
//Ivanov Ivan Ivanovich 22.12.2234 80000000000 f