package Day5;

public class StringVersusStringBuilder {
    public static void main(String[] args) {
        String string = "";
        long inicio = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            string += i;
        }
        long fim = System.nanoTime();
        long duracao = fim - inicio;
        System.out.println(duracao / 1_000_000 + " ms");

        StringBuilder string2 = new StringBuilder();
        long inicio2 = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            string2.append(i);
        }
        long fim2 = System.nanoTime();
        long duracao2 = fim2 - inicio2;
        System.out.println(duracao2 / 1_000_000 + " ms");
    }
}
