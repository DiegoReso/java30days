package Day8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        System.out.println("=== LIST BASICO (programa contra a interface) ===");
        List<String> nomes = new ArrayList<>();
        nomes.add("Pedro");
        nomes.add("Acawer");
        nomes.add("Ana");
        System.out.println(nomes.get(0)); //acesso por indice
        nomes.set(1, "Camares");
        System.out.println("apos set:   " + nomes);
        nomes.remove("Ana");                                  // remove por valor
        System.out.println("apos remove:" + nomes);
        System.out.println("size():     " + nomes.size());

        System.out.println();
        System.out.println("=== FOR-EACH ===");
        for (String n : nomes){
            System.out.println(" - " + n);
        }

        System.out.println();
        System.out.println("=== ACEITA DUPLICADOS E MANTEM ORDEM ===");
        List<Integer> nums = new ArrayList<>();
        nums.add(10);
        nums.add(10);       // duplicado OK (diferente do Set)
        nums.add(5);
        System.out.println("nums: " + nums);

        System.out.println();
        System.out.println("=== ARRAYLIST vs LINKEDLIST (mesma interface) ===");
        List<Integer> arr = new ArrayList<>();
        List<Integer> lnk = new LinkedList<>();
        arr.add(1); arr.add(2);
        lnk.add(1); lnk.add(2);
        System.out.println("ArrayList:  " + arr);
        System.out.println("LinkedList: " + lnk);


    }
}
