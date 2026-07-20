package Day4;

import java.util.HashSet;
import java.util.Set;

public class LeetCodeContainsDuplicate {
    public static void main(String[] args) {

        int numeros[] = {1, 2, 3, 4, 5};
        System.out.println(containsDuplicate(numeros));

    }

    public static boolean containsDuplicate(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
}
