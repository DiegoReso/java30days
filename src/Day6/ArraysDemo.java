package Day6;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        System.out.println("=== DECLARACAO ===");
        int[] a = new int[5];          // tamanho fixo, tudo zero
        int[] b = {10, 20, 30};        // literal
        System.out.println("a = " + Arrays.toString(a));
        System.out.println("b = " + Arrays.toString(b));

        System.out.println();
        System.out.println("=== length e CAMPO (sem parenteses!) ===");
        System.out.println("b.length = " + b.length);   // CAMPO, sem ()

        System.out.println();
        System.out.println("=== FOR-EACH ===");
        for (int x : b) System.out.print(x + " ");
        System.out.println();

        System.out.println();
        System.out.println("=== ARRAY 2D ===");
        int[][] matriz = new int[2][3];
        matriz[0][1] = 7;
        System.out.println("matriz = " + Arrays.deepToString(matriz));

        System.out.println();
        System.out.println("=== CLASSE UTILITARIA Arrays ===");
        int[] c = {5, 2, 8, 1, 9};
        Arrays.sort(c);                               // ordena in-place
        System.out.println("sort:         " + Arrays.toString(c));
        int[] copia = Arrays.copyOf(c, 7);            // copia com novo tamanho
        System.out.println("copyOf(7):    " + Arrays.toString(copia));
        int[] d = new int[4];
        Arrays.fill(d, 3);                            // preenche tudo com 3
        System.out.println("fill(3):      " + Arrays.toString(d));
        int pos = Arrays.binarySearch(c, 8);          // busca em array ORDENADO
        System.out.println("binarySearch(8): indice " + pos);

    }
}
