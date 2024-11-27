package details;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final List<Detail> details = new ArrayList<>(); // Список деталей
    private static final Scanner scanner = new Scanner(System.in); // Сканнер для ввода

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Меню ===");
            System.out.println("1. Добавить деталь");
            System.out.println("2. Показать список деталей");
            System.out.println("3. Подсчитать общий вес деталей с одинаковой формой");
            System.out.println("4. Найти уникальную форму");
            System.out.println("5. Выйти");
            System.out.print("Выберите пункт меню: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считать оставшийся символ новой строки

            switch (choice) {
                case 1 -> addDetail();
                case 2 -> showDetails();
                case 3 -> calculateTotalWeight();
                case 4 -> findUniqueShape();
                case 5 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void addDetail() {
        System.out.print("Введите форму детали: ");
        String shape = scanner.nextLine();

        System.out.print("Введите материал детали: ");
        String material = scanner.nextLine();

        double weight = 0.0;
        while (true) {
            System.out.print("Введите вес детали (например, '2.6'): ");
            try {
                weight = Double.parseDouble(scanner.nextLine().replace(",", ".")); // Заменяем запятую на точку
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: вес должен быть числом. Попробуйте снова.");
            }
        }

        double size = 0.0;
        while (true) {
            System.out.print("Введите размер детали (например, '10.0'): ");
            try {
                size = Double.parseDouble(scanner.nextLine().replace(",", ".")); // Заменяем запятую на точку
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: размер должен быть числом. Попробуйте снова.");
            }
        }

        details.add(new SpecificDetail(shape, material, weight, size));
        System.out.println("Деталь успешно добавлена!");
    }

    private static void showDetails() {
        if (details.isEmpty()) {
            System.out.println("Список деталей пуст.");
        } else {
            System.out.println("Список деталей:");
            for (int i = 0; i < details.size(); i++) {
                System.out.println((i + 1) + ". " + details.get(i));
            }
        }
    }

    private static void calculateTotalWeight() {
        if (details.isEmpty()) {
            System.out.println("Список деталей пуст. Добавьте детали.");
            return;
        }

        Map<String, Double> totalWeightByShape = details.stream()
                .collect(Collectors.groupingBy(
                        Detail::getShape,
                        Collectors.summingDouble(Detail::getWeight)
                ));
        System.out.println("Общий вес деталей по форме:");
        totalWeightByShape.forEach((shape, weight) ->
                System.out.println(shape + ": " + weight + " кг"));
    }

    private static void findUniqueShape() {
        if (details.isEmpty()) {
            System.out.println("Список деталей пуст. Добавьте детали.");
            return;
        }

        Map<String, Long> shapeCounts = details.stream()
                .collect(Collectors.groupingBy(
                        Detail::getShape,
                        Collectors.counting()
                ));
        String uniqueShape = shapeCounts.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("Нет уникальной формы");
        System.out.println("Уникальная форма: " + uniqueShape);
    }
}
