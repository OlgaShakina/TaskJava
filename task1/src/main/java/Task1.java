import java.util.Arrays;

public class Task1 {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите количество элементов в массиве и длину интервала.");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] arr = new int[n];
        Arrays.setAll(arr, i -> i + 1);

        int current = 0;
        System.out.print("Path: ");

        do {
            System.out.print(arr[current] + " ");
            current = (current + m - 1) % n;
        } while (current != 0);
    }
}
