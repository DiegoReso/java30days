package Day1;

public class Equals {
    public static void main(String[] args) {

        System.out.println("=== Dentro do cache (-128 a 127) ===");
        Integer x = 127, y = 127;
        System.out.println("x = 127, y = 127");
        System.out.println("x == y      -> " + (x == y));        // mesmo objeto cacheado
        System.out.println("x.equals(y) -> " + x.equals(y));     // mesmo conteúdo

        System.out.println();
        System.out.println("=== Fora do cache (>= 128) ===");
        Integer p = 128, q = 128;
        System.out.println("p = 128, q = 128");
        System.out.println("p == q      -> " + (p == q));        // objetos diferentes!
        System.out.println("p.equals(q) -> " + p.equals(q));     // conteúdo igual

        System.out.println();
        System.out.println("=== Varrendo a fronteira do cache ===");
        for (int valor : new int[] {126, 127, 128, 129}) {
            Integer a = valor;   // autoboxing
            Integer b = valor;   // autoboxing
            System.out.printf("valor=%-4d  a == b -> %-5b  |  a.equals(b) -> %b%n",
                    valor, (a == b), a.equals(b));
        }

        System.out.println();
        System.out.println("=== A prova: new Integer sempre cria objeto novo ===");
        Integer m = 100;                  // vem do cache
        Integer n = Integer.valueOf(100); // também vem do cache
        Integer o = new Integer(100);     // FORÇA objeto novo na heap
        System.out.println("m == n (ambos do cache)   -> " + (m == n));   // true cache
        System.out.println("m == o (o é objeto novo)  -> " + (m == o));   // false nao tem mesma ref
        System.out.println("m.equals(o)               -> " + m.equals(o));// true  mesmo conteudo
    }
}
