import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        System.out.print("Enter the interval length: ");
        int m = sc.nextInt();

        int[] arr = new int[n];
        Arrays.setAll(arr, i -> i + 1);

        int current = 0;
        System.out.print("Path: ");

        do {
            System.out.print(arr[current] + " ");
            current = (current + m - 1) % n;
        } while (current != 0);

        sc.close();
    }
}
