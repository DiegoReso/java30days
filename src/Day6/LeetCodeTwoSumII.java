package Day6;

import java.util.Arrays;

public class LeetCodeTwoSumII {
    public static void main(String[] args) {
        int numbers[] = {1, 2, 3, 4, 10, 16, 28, 63};
        int target = 64;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    public static int[] twoSum(int[] nums, int target) {

        int esquerda = 0;
        int direita = nums.length - 1;
        while (esquerda < direita) {
            if (nums[direita] + nums[esquerda] == target) {
                return new int[]{esquerda + 1 , direita + 1};
            }
            if (nums[direita] + nums[esquerda] > target) {
                direita--;
            } else {
                esquerda++;
            }
        }
        return new int[]{};
    }
}
