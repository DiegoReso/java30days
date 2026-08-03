package Day8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BenchList {
    public static void main(String[] args) {
        int TAMANHO = 100_000;
        int INSERCOES = 10_000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < TAMANHO; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long inicioArray = System.nanoTime();
        for (int i = 0; i < INSERCOES; i++) {
            arrayList.add(arrayList.size() / 2, i);
        }
        long fimArray = System.nanoTime();
        System.out.println("ArrayList  (add no meio): " + (fimArray - inicioArray) / 1_000_000 + " ms");

        long inicioLinked = System.nanoTime();
        for (int i = 0; i < INSERCOES; i++) {
            linkedList.add(linkedList.size() / 2, i);
        }
        long fimLinked = System.nanoTime();
        System.out.println("LinkedList (add no meio): " + (fimLinked - inicioLinked) / 1_000_000 + " ms");
    }
}
