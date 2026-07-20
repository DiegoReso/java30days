package Day4;

import java.util.HashSet;
import java.util.Set;

//ideia para resolver, colocar num set que so aceita itens unicos, e o metodo add retorna false quando o item
//ja existe, ai fica faci, na propria adicao conseguimod validar :)
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
