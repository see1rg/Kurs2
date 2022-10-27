
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            deleteTask(scanner);
                            break;
                        case 3:
                            viewTaskADay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void viewTaskADay(Scanner scanner) throws Exception {
        System.out.println("Введите дату в формате ДД-MM-ГГГГ");
        String dateTime = scanner.next();
        checkDate(dateTime);
        for (var entry: Task.getSetOfTasks().entrySet()){
            System.out.println(entry);
        }

        }
            



    private static void deleteTask(Scanner scanner) throws Exception {
        String choiceDate;
        label:
        while (true) {
            System.out.println("Для удаления задачи введите дату в формате ДД-MM-ГГГГ");
            choiceDate = scanner.next();
            checkDate(choiceDate); //добавить метод сравнения строк и парсинг
        }
    }

    private static void checkDate(String dateTime) throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("ru"));
        DateValidator validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        if (!validator.isValid(dateTime)){
            throw new Exception ("неверно указана дата.");
        }
    }

    private static void inputTask(Scanner scanner) throws Exception {
        System.out.print("Введите название задачи: ");
        String  fake = scanner.nextLine();
        String  taskName = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String descriptionOfTask = scanner.nextLine();
        System.out.println("Введите дату в формате ДД-MM-ГГГГ");
        String dateTime = scanner.next();
        PeriodTask periodTask = null;
        label:
        while (true) {
            printMenuPeriod();
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        periodTask = PeriodTask.ONETIME;
                        break label;
                    case 2:
                        periodTask = PeriodTask.DAILY;
                        break label;
                    case 3:
                        periodTask = PeriodTask.WEEKLY;
                        break label;
                    case 4:
                        periodTask = PeriodTask.MONTHLY;
                        break label;
                    case 5:
                        periodTask = PeriodTask.ANNUAL;
                        break label;
                    case 0:
                        break label;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }

        }
        boolean personalOrWork;
        label1:
        {
            while (true) {
                System.out.print("Тип задачи: личная \"1\" или рабочая \"2\". ");
                if (scanner.hasNextInt()) {
                    int persOrWork = scanner.nextInt();
                    switch (persOrWork) {
                        case 1:
                            personalOrWork = true;
                            break label1;
                        case 2:
                            personalOrWork = false;
                            break label1;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

        Task task = new Task(personalOrWork, taskName, descriptionOfTask, dateTime, periodTask);
        Task.adSetOfTasks(task,periodTask);
//        Task.setAnnual(task);
    }

    private static void printMenuPeriod() {
        System.out.println(
                """
                        Выберите повторяемость задачи:
                        1. однократная
                        2. ежедневная
                        3. еженедельная
                        4. ежемесячная
                        5. ежегодная
                        0. Выход
                        """);
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        Выберите пункт меню: 
                        """
        );
    }

}
