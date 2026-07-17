package Day1;

import java.util.Arrays;

public class IntVsInteger {
    public static void main(String[] args) {
        System.out.println("=== 1. Valor inicial ===");
        int[] primitivos = new int[3];
        Integer[] objetos = new Integer[3];
        System.out.println("int[]     inicia como -> " + Arrays.toString(primitivos));
        System.out.println("Integer[] inicia como -> " + Arrays.toString(objetos));

        System.out.println();
        System.out.println("=== 2. Preenchendo (autoboxing acontece no Integer[]) ===");
        primitivos[0] = 10;                 // guarda o valor 10 direto
        objetos[0]    = 10;                 // AUTOBOXING: int 10 -> Integer na heap
        System.out.println("int[]     -> " + Arrays.toString(primitivos));
        System.out.println("Integer[] -> " + Arrays.toString(objetos));

        System.out.println();
        System.out.println("=== 3. Somando (unboxing e o perigo do null) ===");
        int soma = 0;
        for (Integer n : objetos) {
            if (n != null){
                soma += n;       // AUTO-UNBOXING a cada iteracao; guarda do null
            }
        }
        System.out.println("soma segura (pulando null) -> " + soma);

        System.out.println();
        System.out.println("=== 4. O que acontece SEM a guarda de null ===");
        try {
            int somaRuim = 0;
            for (Integer n : objetos) {
                somaRuim += n;              // estoura em objetos[1], que é null
            }
        } catch (NullPointerException e) {
            System.out.println("NPE! desembrulhou um null: " + e.getMessage());
        }

        System.out.println();
        //soma 10 milhões de elementos em cada versão e cronometra
        int N = 10_000_000;

        int[] a = new int[N];
        Integer[] b = new Integer[N];
        for (int i = 0; i < N; i++) { a[i] = i; b[i] = i; }

        long t1 = System.nanoTime();
        long somaA = 0;
        for (int v : a) somaA += v;                 // zero boxing
        long t2 = System.nanoTime();
        long somaB = 0;
        for (Integer v : b) somaB += v;             // unboxing a cada elemento
        long t3 = System.nanoTime();

        System.out.println("Calculando zero unboxing vs unboxing");
        System.out.printf("somaA = %d, somaB = %d\n", somaA, somaB);
        System.out.println("int[]     : " + (t2 - t1) / 1_000_000 + " ms");
        System.out.println("Integer[] : " + (t3 - t2) / 1_000_000 + " ms");
    }
}
