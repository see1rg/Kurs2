
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        LocalDate taskForDate;
        System.out.println("Для просмотра задач на день введите дату в формате ДД-MM-ГГГГ");
        String choiceDate = scanner.next();
        taskForDate = checkDateTime(choiceDate);
        System.out.println(ServiceTask.getTasksOnDate(taskForDate));
    }

    private static void deleteTask(Scanner scanner) throws Exception {
        LocalDate taskForDate;
        System.out.println("Для удаления задачи введите дату в формате ДД-MM-ГГГГ");
        String choiceDate = scanner.next();
        taskForDate = checkDateTime(choiceDate);
        ServiceTask.getTasksOnDate(taskForDate);
        System.out.println("Введите id задачи для ее удаления.");
        int choiceId = scanner.nextInt();
        ServiceTask.deleteTask(choiceId);
    }

    private static void checkDate(String dateTime) throws Exception {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", new Locale("ru"));
        DateValidatorUsingDateTimeFormatter validator = new DateValidatorUsingDateTimeFormatter(dateFormatter);

        if (!validator.isValid(dateTime)) {
            throw new Exception("неверно указана дата.");
        }
    }

    private static void inputTask(Scanner scanner) throws Exception {
        System.out.print("Введите название задачи: ");
        String fake = scanner.nextLine();
        String taskName = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String descriptionOfTask = scanner.nextLine();
        System.out.println("Введите дату в формате ДД-MM-ГГГГ");
        String dateTime1 = scanner.next();
        LocalDate timeCreateTask = checkDateTime(dateTime1);
        Repeatability repeatability = null;
        label:
        while (true) {
            printMenuPeriod();
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        repeatability = PeriodTask.ONETIME;
                        break label;
                    case 2:
                        repeatability = PeriodTask.DAILY;
                        break label;
                    case 3:
                        repeatability = PeriodTask.WEEKLY;
                        break label;
                    case 4:
                        repeatability = PeriodTask.MONTHLY;
                        break label;
                    case 5:
                        repeatability = PeriodTask.ANNUAL;
                        break label;
                    case 0:
                        break label;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
        PersonalOrWork personalOrWork;
        label1:
        {
            while (true) {
                System.out.print("Тип задачи: личная \"1\" или рабочая \"2\". ");
                if (scanner.hasNextInt()) {
                    int persOrWork = scanner.nextInt();
                    switch (persOrWork) {
                        case 1:
                            personalOrWork = PersonalOrWork.PERSONAL;
                            break label1;
                        case 2:
                            personalOrWork = PersonalOrWork.WORK;
                            break label1;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
        Task task = new Task(personalOrWork, taskName, descriptionOfTask, timeCreateTask, repeatability);
        Task.adSetOfTasks(task);
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

    public static LocalDate checkDateTime(String dateTime) throws Exception {
        checkDate(dateTime);
        LocalDate dateTime1 = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return dateTime1;
    }
}
