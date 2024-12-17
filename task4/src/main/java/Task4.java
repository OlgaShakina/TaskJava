import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static int minMovesToEqualElements(int[] args) {
        Arrays.sort(args);

        int median = args[args.length / 2];

        int moves = 0;
        for (int num : args) {
            moves += Math.abs(num - median);
        }
        return moves;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("/Users/olgabuinova/IdeaProjects/TaskJava/task4/src/main/java/nums.txt");
            return;
        }

        String fileName = args[0];
        int[] nums;

        try {
            Scanner scanner = new Scanner(new File(fileName));
            String[] numberStrings = scanner.nextLine().split(" ");
            nums = new int[numberStrings.length];

            for (int i = 0; i < numberStrings.length; i++) {
                nums[i] = Integer.parseInt(numberStrings[i]);
            }
            scanner.close();

            int result = minMovesToEqualElements(nums);
            System.out.println("Минимальное количество ходов: " + result);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + fileName);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в формате числа в файле: " + e.getMessage());
        }
    }
}

