package Day2;

import java.util.*;

public class SummaryDay1 {
    public static void main(String[] args) {

        // 1) Somar int[] e depois Integer[]
        int[] a = {1, 2, 3, 4};
        int somaA = 0;
        for (int v : a) somaA += v;            // sem boxing
        System.out.println("soma int[]     = " + somaA);

        Integer[] b = {1, 2, 3, 4};
        int somaB = 0;
        for (Integer v : b) somaB += v;        // unboxing a cada volta
        System.out.println("soma Integer[] = " + somaB);

        // 2) int dentro de List -> autoboxing
        List<Integer> lista = new ArrayList<>();
        lista.add(10);                         // AUTOBOXING: int 10 -> Integer
        int primeiro = lista.get(0);           // AUTO-UNBOXING: Integer -> int
        System.out.println("lista.get(0)   = " + primeiro);

        // 3) Bonus: NPE no unboxing de um Integer null
        Integer nulo = null;
        int vaiEstourar = nulo;                // desembrulha null -> NPE
        System.out.println(vaiEstourar);       // nunca chega aqui

        //refazendo leet code para fixar
        int nums[] = {2, 7, 11, 15};
        int target = 26;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}
