import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину массива: ");
        int n = scanner.nextInt(); // Размер массива
        int[] array = new int[n];

        System.out.println("Введите " + n + " элементов массива:");
        for (int i = 0; i < n; i++) {
            System.out.print("Элемент " + (i + 1) + ": ");
            array[i] = scanner.nextInt(); // Пользователь вводит элементы массива
        }

        System.out.println("Исходный массив: " + Arrays.toString(array));

        System.out.print("Введите количество корзин: ");
        int bucketCount = scanner.nextInt(); // Количество корзин

        bucketSort(array, bucketCount); // Вызов блочной сортировки

        System.out.println("Отсортированный массив: " + Arrays.toString(array));
        scanner.close();
    }

    // Метод для блочной сортировки
    public static void bucketSort(int[] array, int bucketCount) {
        if (array.length == 0) return;

        // Находим максимальное значение для определения диапазонов корзин
        int maxValue = array[0];
        for (int num : array) {
            if (num > maxValue) maxValue = num;
        }

        // Создаем корзины
        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Распределяем элементы по корзинам
        for (int num : array) {
            int bucketIndex = (num * bucketCount) / (maxValue + 1);
            buckets[bucketIndex].add(num);
        }

        // Сортируем каждую корзину и объединяем их
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket); // Сортировка элементов внутри корзины
            for (int num : bucket) {
                array[index++] = num; // Перемещаем элементы обратно в массив
            }
        }
    }
}
