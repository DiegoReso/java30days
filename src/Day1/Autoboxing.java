package Day1;

import java.util.ArrayList;
import java.util.List;

public class Autoboxing {
    public static void main(String[] args) {

        Integer boxed = 42;        // autoboxing: int 42 → Integer.valueOf(42)
        int unboxed = boxed;       // auto-unboxing: Integer → int

        List<Integer> lista = new ArrayList<>();
        lista.add(10);             // autoboxing acontece aqui! (List só aceita objetos)
        int primeiro = lista.get(0); // auto-unboxing aqui

        Long soma = 0L;                    // ARMADILHA: Long (objeto), não long
        for (long i = 0; i < 1_000_000; i++) {
            soma += i;                     // desempacota, soma, reempacota → 1 milhão de objetos!
        }
        // Corrigido: usar 'long soma = 0L' primitivo → zero objetos


    }
}
