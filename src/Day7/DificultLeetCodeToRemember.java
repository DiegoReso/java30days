package Day7;

import java.util.Arrays;

public class DificultLeetCodeToRemember {
    public static void main(String[] args) {

        String s = "asA man, a plan, a canal: Panamasa";
        System.out.println(isPalindrome(s));

        int nums[] = {3, 5, 9, 11, 90, 92};
        int target = 103;
        System.out.println(Arrays.toString(twoSumII(nums, target)));
    }

    public static int[] twoSumII(int[] nums, int target) {
        int esquerda = 0;
        int direita = nums.length - 1;
        while(esquerda < direita) {
            if(nums[esquerda] + nums[direita] == target) {
                return new int[]{esquerda + 1 , direita + 1};
            }
            if(nums[esquerda] + nums[direita] < target) {
                esquerda++;
            }else{
                direita--;
            }
        };
        return new int[]{};
    }

    public static boolean isPalindrome(String s) {
        int esquerda = 0;
        int direita = s.length() - 1;

        while (esquerda < direita) {
            while (esquerda < direita && !Character.isLetterOrDigit(s.charAt(esquerda))) {
                esquerda++;
            }
            while (esquerda < direita && !Character.isLetterOrDigit(s.charAt(direita))) {
                direita--;
            }
            if (Character.toLowerCase(s.charAt(esquerda)) != Character.toLowerCase(s.charAt(direita))) {
                return false;
            }
            esquerda++;
            direita--;
        }
        return true;
    }

}
