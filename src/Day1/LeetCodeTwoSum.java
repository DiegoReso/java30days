package Day1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeTwoSum {
    public static void main(String[] args) {
        //pensamento para resolucao
        //preciso iterar sobre o array verificar se temos o valor que resulta em target -  valor atual no map
        //se tiver temos a soma para o target...

        int nums[] = {2,7,11,15};
        int target = 9;

        int[] result = twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public static int[] twoSum(int[] num, int target){
        Map<Integer, Integer> maper = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            if(maper.containsKey(target-num[i])){
                return new int[]{maper.get(target - num[i]), i};
            }
            maper.put(num[i], i);
        }
        return new int[]{};
    }
}
