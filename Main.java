import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторная работа №4. Вариант 3.");

        int boxValue = 3;

        Integer nullableNumber = null;
        Integer number = 99;
        String nullableText = null;
        String text = "hello";

        int x = 1;
        int y = -2;
        int z = 7;

        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        List<Integer> numbers = Arrays.asList(1, -3, 7);

        List<int[]> arrays = Arrays.asList(
                new int[]{1, 2, -3},
                new int[]{-4, -2, 0},
                new int[]{5, 7, 9}
        );

        List<List<Integer>> groups = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        List<String> stringsByLength =
                Arrays.asList("qwerty", "asdfg", "zx", "qw");

        List<String> stringsWithDuplicates =
                Arrays.asList("qwerty", "asdfg", "qwerty", "qw");

        task1_1(boxValue);
        task1_2(nullableNumber, number, nullableText, text);
        task2_3(x, y, z);
        task3_1(strings, numbers, arrays);
        task3_2(strings, numbers, arrays);
        task3_3(strings, numbers, groups);
        task3_4(numbers, stringsByLength, stringsWithDuplicates);
    }

    // Задание 1.1. Обобщенная коробка
    private static void task1_1(int value) {
        System.out.println("\nЗАДАНИЕ 1.1 - обобщенная коробка");
        Box<Integer> box = new Box<>();
        box.put(value);
        System.out.println("Коробка заполнена: " + box.isFull());
        extractFromBox(box);
        System.out.println("Коробка после извлечения: " + box);
        System.out.println("Коробка заполнена: " + box.isFull());
    }

    private static <T> void extractFromBox(Box<T> box) {
        T value = box.get();
        System.out.println("Извлеченное значение: " + value);
    }

    // Задание 1.2. Хранилище
    private static void task1_2(
            Integer nullableNumber, Integer number,
            String nullableText, String text) {
        System.out.println("\nЗАДАНИЕ 1.2 - хранилище");
        printStorage(new Storage<>(nullableNumber), 0);
        printStorage(new Storage<>(number), -1);
        printStorage(new Storage<>(nullableText), "default");
        printStorage(new Storage<>(text), "hello world");
    }

    private static <T> void printStorage(Storage<T> storage, T alternative) {
        System.out.println(storage + " -> результат: "
                + storage.getOrElse(alternative));
    }

    // Задание 2.3. Начало отсчета
    private static void task2_3(int x, int y, int z) {
        System.out.println("\nЗАДАНИЕ 2.3 - начало отсчета");

        Box<Point3D> point3DBox = new Box<>();
        putOrigin(point3DBox, x, y, z);
        System.out.println("Box<Point3D>: " + point3DBox.get());

        Box<Point> pointBox = new Box<>();
        putOrigin(pointBox, x, y, z);
        System.out.println("Box<Point>: " + pointBox.get());

        Box<Object> objectBox = new Box<>();
        putOrigin(objectBox, x, y, z);
        System.out.println("Box<Object>: " + objectBox.get());
    }

    private static void putOrigin(
            Box<? super Point3D> box, int x, int y, int z) {
        box.put(new Point3D(x, y, z));
    }

    // Задание 3.1. Функция
    private static void task3_1(
            List<String> strings, List<Integer> numbers, List<int[]> arrays) {
        System.out.println("\nЗАДАНИЕ 3.1 - функция");

        List<Integer> lengths = transform(strings, value -> value.length());
        List<Integer> absoluteValues =
                transform(numbers, value -> Math.abs(value));
        List<Integer> maximums = transform(arrays, array -> findMaximum(array));

        System.out.println("Длины строк: " + lengths);
        System.out.println("Модули чисел: " + absoluteValues);
        System.out.println("Максимумы массивов: " + maximums);
    }

    private static <T, P> List<P> transform(
            List<T> source, Apply<T, P> action) {
        List<P> result = new ArrayList<>();
        for (T value : source) {
            result.add(action.apply(value));
        }
        return result;
    }

    private static int findMaximum(int[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException(
                    "Нельзя найти максимум пустого массива");
        }
        int maximum = values[0];
        for (int value : values) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

    // Задание 3.2. Фильтр
    private static void task3_2(
            List<String> strings, List<Integer> numbers, List<int[]> arrays) {
        System.out.println("\nЗАДАНИЕ 3.2 - фильтр");

        List<String> longStrings =
                filter(strings, value -> value.length() >= 3);
        List<Integer> nonPositiveNumbers =
                filter(numbers, value -> value <= 0);

        List<int[]> arraysWithoutPositive = filter(arrays, values -> {
            for (int value : values) {
                if (value > 0) {
                    return false;
                }
            }
            return true;
        });

        System.out.println("Строки длиной не менее 3: " + longStrings);
        System.out.println("Числа после удаления положительных: "
                + nonPositiveNumbers);
        System.out.println("Массивы без положительных элементов: "
                + formatArrays(arraysWithoutPositive));
    }

    private static <T> List<T> filter(
            List<T> source, Test<T> condition) {
        List<T> result = new ArrayList<>();
        for (T value : source) {
            if (condition.test(value)) {
                result.add(value);
            }
        }
        return result;
    }

    private static String formatArrays(List<int[]> arrays) {
        List<String> result = new ArrayList<>();
        for (int[] array : arrays) {
            result.add(Arrays.toString(array));
        }
        return result.toString();
    }

    // Задание 3.3. Сокращение
    private static void task3_3(
            List<String> strings, List<Integer> numbers,
            List<List<Integer>> groups) {
        System.out.println("\nЗАДАНИЕ 3.3 - сокращение");

        String joined = reduce(strings, "", (left, right) -> left + right);
        Integer sum = reduce(numbers, 0, (a, b) -> a + b);
        List<Integer> groupSizes = transform(groups, group -> group.size());
        Integer totalElements = reduce(groupSizes, 0, (a, b) -> a + b);

        System.out.println("Объединенная строка: " + joined);
        System.out.println("Сумма чисел: " + sum);
        System.out.println("Общее количество элементов во вложенных списках: "
                + totalElements);

        System.out.println("Результат для пустого списка строк: '"
                + reduce(Collections.<String>emptyList(), "",
                (left, right) -> left + right) + "'");
        System.out.println("Результат для пустого списка чисел: "
                + reduce(Collections.<Integer>emptyList(), 0, (a, b) -> a + b));
    }

    private static <T> T reduce(
            List<T> source, T identity, Combiner<T> combiner) {
        T result = identity;
        for (T value : source) {
            result = combiner.combine(result, value);
        }
        return result;
    }

    // Задание 3.4. Коллекционирование
    private static void task3_4(
            List<Integer> numbers, List<String> stringsByLength,
            List<String> stringsWithDuplicates) {
        System.out.println("\nЗАДАНИЕ 3.4 - коллекционирование");

        ResultFactory<List<List<Integer>>> signFactory = () -> {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            result.add(new ArrayList<>());
            return result;
        };

        ResultWriter<List<List<Integer>>, Integer> signWriter =
                (result, value) -> {
                    if (value > 0) {
                        result.get(0).add(value);
                    } else if (value < 0) {
                        result.get(1).add(value);
                    }
                };

        List<List<Integer>> signGroups =
                collect(numbers, signFactory, signWriter);
        System.out.println("Положительные и отрицательные числа: "
                + signGroups);

        ResultFactory<Map<Integer, List<String>>> mapFactory =
                () -> new LinkedHashMap<>();

        ResultWriter<Map<Integer, List<String>>, String> mapWriter =
                (result, value) -> result.computeIfAbsent(
                        value.length(), key -> new ArrayList<>()).add(value);

        Map<Integer, List<String>> groupedStrings =
                collect(stringsByLength, mapFactory, mapWriter);
        System.out.println("Строки, сгруппированные по длине: "
                + groupedStrings);

        ResultFactory<Set<String>> setFactory = () -> new LinkedHashSet<>();
        ResultWriter<Set<String>, String> setWriter =
                (result, value) -> result.add(value);

        Set<String> uniqueStrings =
                collect(stringsWithDuplicates, setFactory, setWriter);
        System.out.println("Строки без повторений: " + uniqueStrings);
    }

    private static <T, R> R collect(
            List<T> source, ResultFactory<R> factory,
            ResultWriter<R, T> writer) {
        R result = factory.create();
        for (T value : source) {
            writer.add(result, value);
        }
        return result;
    }
}
