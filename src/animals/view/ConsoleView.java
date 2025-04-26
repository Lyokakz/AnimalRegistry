package animals.view;

import animals.model.*;
import animals.presenter.AnimalPresenter;
import animals.service.AnimalService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final AnimalPresenter presenter = new AnimalPresenter(new AnimalService());

    public void start() {
        try (Scanner scanner = new Scanner(System.in); Count counter = new Count()) {
            boolean running = true;

            while (running) {
                System.out.println("\n====== Реестр Животных ======");
                System.out.println("1. Добавить новое животное");
                System.out.println("2. Показать команды животного");
                System.out.println("3. Обучить животное новой команде");
                System.out.println("4. Показать всех животных по дате рождения");
                System.out.println("5. Показать общее количество животных");
                System.out.println("6. Показать всех домашних животных");
                System.out.println("7. Показать всех вьючных животных");
                System.out.println("0. Выход");
                System.out.print("Выберите пункт меню: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        addAnimal(scanner, counter);
                    }
                    case "2" -> {
                        showAnimalCommands(scanner);
                    }
                    case "3" -> {
                        teachAnimalCommand(scanner);
                    }
                    case "4" -> {
                        showAnimalsByBirthDate();
                    }
                    case "5" -> {
                        System.out.println("Общее количество животных: " + counter.getAnimalCount());
                    }
                    case "6" -> {
                        showPets();
                    }
                    case "7" -> {
                        showPackAnimals();
                    }
                    case "0" -> {
                        running = false;
                    }
                    default -> {
                        System.out.println("Неверный ввод, попробуйте снова.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void addAnimal(Scanner scanner, Count counter) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        System.out.print("Введите дату рождения (ГГГГ-ММ-ДД): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Категория животного: ");
        System.out.println("1 - Домашнее животное");
        System.out.println("2 - Вьючное животное");
        System.out.print("Выберите категорию: ");
        String category = scanner.nextLine();

        Animals newAnimal = null;

        if (category.equals("1")) {
            System.out.println("Введите тип животного: ");
            System.out.println("1 - Dog");
            System.out.println("2 - Cat");
            System.out.println("3 - Hamster");
            System.out.print("Выберите тип: ");
            String type = scanner.nextLine();
            switch (type) {
                case "1" -> newAnimal = new Dog(name, birthDate);
                case "2" -> newAnimal = new Cat(name, birthDate);
                case "3" -> newAnimal = new Hamster(name, birthDate);
                default -> {
                    System.out.println("Неизвестный тип домашнего животного.");
                    return;
                }
            }
        } else if (category.equals("2")) {
            System.out.println("Введите тип животного: ");
            System.out.println("1 - Horse");
            System.out.println("2 - Camel");
            System.out.println("3 - Donkey");
            System.out.print("Выберите тип: ");
            String type = scanner.nextLine();
            switch (type) {
                case "1" -> newAnimal = new Horse(name, birthDate);
                case "2" -> newAnimal = new Camel(name, birthDate);
                case "3" -> newAnimal = new Donkey(name, birthDate);
                default -> {
                    System.out.println("Неизвестный тип вьючного животного.");
                    return;
                }
            }
        } else {
            System.out.println("Неверная категория.");
            return;
        }

        presenter.addAnimal(newAnimal); // передаем объект Animal
        counter.add();
        System.out.println("Животное добавлено!");
    }

    private void showAnimalCommands(Scanner scanner) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine().trim(); // Убираем лишние пробелы

        // Поиск животного в списке по имени
        Animals found = presenter.getAll().stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(name)) // Игнорируем регистр
                .findFirst().orElse(null); // Если животное не найдено, возвращаем null

        if (found != null) {
            // Выводим команды животного
            List<String> commands = found.getCommands();
            if (commands.isEmpty()) {
                System.out.println(name + " пока не знает ни одной команды.");
            } else {
                System.out.println("Команды " + name + ":");
                commands.forEach(cmd -> System.out.println("- " + cmd));
            }
        } else {
            System.out.println("Животное с таким именем не найдено.");
        }
    }

    private void teachAnimalCommand(Scanner scanner) {
        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();
        Animals found = presenter.getAnimal(name);

        if (found != null) {
            System.out.print("Введите новую команду: ");
            String command = scanner.nextLine();
            found.addCommand(command);
            System.out.println(name + " обучено команде \"" + command + "\"");
        } else {
            System.out.println("Животное не найдено.");
        }
    }

    private void showAnimalsByBirthDate() {
        presenter.getAnimalsSorted()
                .forEach(a -> System.out.println(a.getName() + " — " + a.getBirthDate()));
    }

    private void showPets() {
        System.out.println("\nДомашние животные:");
        presenter.getByCategory(Pets.class)
                .forEach(a -> System.out.println(a.getName() + " — " + a.getClass().getSimpleName()));
    }

    private void showPackAnimals() {
        System.out.println("\nВьючные животные:");
        presenter.getByCategory(PackAnimals.class)
                .forEach(a -> System.out.println(a.getName() + " — " + a.getClass().getSimpleName()));
    }
}
