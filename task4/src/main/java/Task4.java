import java.util.Arrays;

public class Task4 {
    public static int minMovesToEqualElements(int[] nums) {
        Arrays.sort(nums);

        int median = nums[nums.length / 2];

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

    public static void main(String[] args) {
        int[] nums = {1, 10, 2, 9};
        int result = minMovesToEqualElements(nums);
        System.out.println("Минимальное количество ходов: " + result);
    }
}
