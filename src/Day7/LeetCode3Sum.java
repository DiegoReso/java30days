package Day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode3Sum {
    public static void main(String[] args) {

        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultado = new ArrayList<>();
        Arrays.sort(nums);                                  // 1. ordena

        for (int i = 0; i < nums.length - 2; i++) {         // 2. fixa um numero
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;                                   // 3a. pula i repetido
            }
            int esquerda = i + 1;
            int direita = nums.length - 1;
            while (esquerda < direita) {                    // 4. dois ponteiros (Two Sum II!)
                int soma = nums[i] + nums[esquerda] + nums[direita];
                if (soma == 0) {
                    resultado.add(Arrays.asList(nums[i], nums[esquerda], nums[direita]));
                    // 3b. pula duplicados apos achar
                    while (esquerda < direita && nums[esquerda] == nums[esquerda + 1]) esquerda++;
                    while (esquerda < direita && nums[direita] == nums[direita - 1]) direita--;
                    esquerda++;
                    direita--;
                } else if (soma < 0) {
                    esquerda++;                             // soma pequena -> aumenta
                } else {
                    direita--;                              // soma grande -> diminui
                }
            }
        }
        return resultado;
    }
}
