package Day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Dia 7 — Revisao geral (sem LeetCode).
 *
 * Poucos exemplos de CADA dia anterior (1 a 6), tudo numa classe so pra rodar
 * e ver a saida.
 *
 *  Dia 1 — Tipos, memoria, autoboxing, ==, cache do Integer, null
 *  Dia 2 — Classes, construtor, this, encapsulamento
 *  Dia 3 — Heranca, interface, polimorfismo
 *  Dia 4 — record, enum, imutabilidade
 *  Dia 5 — String, StringBuilder, char como numero
 *  Dia 6 — Arrays e a classe java.util.Arrays
 */
public class SummaryDay1To6 {

    public static void main(String[] args) {
        dia1();
        dia2();
        dia3();
        dia4();
        dia5();
        dia6();
    }

    // ============================================================
    // DIA 1 — Tipos, memoria e o modelo mental
    // ============================================================
    static void dia1() {
        System.out.println("========== DIA 1: tipos e memoria ==========");

        // primitivo guarda valor; wrapper guarda referencia (autoboxing escondido)
        int a = 5;          // valor direto na stack
        Integer b = 5;      // Integer.valueOf(5) por baixo dos panos

        // == em objeto compara REFERENCIA; .equals() compara CONTEUDO
        Integer x = 500, y = 500;
        System.out.println("x == y      (fora do cache) -> " + (x == y));       // false
        System.out.println("x.equals(y) (conteudo)      -> " + x.equals(y));    // true

        // cache do Integer: -128 a 127 -> mesmo objeto, == funciona "por acidente"
        Integer p = 127, q = 127;
        System.out.println("127 == 127  (dentro cache)  -> " + (p == q));       // true

        // null em wrapper -> NPE no auto-unboxing (aqui tratado com seguranca)
        Integer talvez = null;
        int seguro = (talvez != null) ? talvez : -1;
        System.out.println("null tratado com seguranca  -> " + seguro);         // -1

        System.out.println("a=" + a + ", b=" + b + "\n");
    }

    // ============================================================
    // DIA 2 — Classes, construtor, this, encapsulamento
    // ============================================================
    static void dia2() {
        System.out.println("========== DIA 2: encapsulamento ==========");

        Conta minha = new Conta(100.0);
        minha.depositar(50.0);
        System.out.println("saldo apos depositar 50 -> " + minha.getSaldo());   // 150.0

        // a regra protege a invariante: valor invalido e' bloqueado
        try {
            minha.depositar(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("deposito -10 bloqueado  -> " + e.getMessage());
        }
        System.out.println("saldo intacto           -> " + minha.getSaldo() + "\n"); // 150.0
    }

    /** Classe do Dia 2: campos private + metodos que validam. */
    static class Conta {
        private double saldo;                 // estado escondido

        Conta(double saldoInicial) {          // construtor inicializa no new
            this.saldo = saldoInicial;        // this. desambigua campo x parametro
        }

        void depositar(double valor) {
            if (valor <= 0) throw new IllegalArgumentException("valor invalido");
            this.saldo += valor;
        }

        double getSaldo() {
            return saldo;
        }
    }

    // ============================================================
    // DIA 3 — Heranca, interface e polimorfismo
    // ============================================================
    static void dia3() {
        System.out.println("========== DIA 3: polimorfismo ==========");

        List<Forma> formas = List.of(new Circulo(2), new Quadrado(3));

        double soma = 0;
        for (Forma f : formas) {
            System.out.println("area -> " + f.area());  // cada objeto roda a SUA area
            soma += f.area();                            // sem NENHUM if de tipo
        }
        System.out.println("soma das areas -> " + soma + "\n");
    }

    interface Forma { double area(); }

    static class Circulo implements Forma {
        private final double r;
        Circulo(double r) { this.r = r; }
        @Override public double area() { return Math.PI * r * r; }
    }

    static class Quadrado implements Forma {
        private final double l;
        Quadrado(double l) { this.l = l; }
        @Override public double area() { return l * l; }
    }

    // ============================================================
    // DIA 4 — record, enum e imutabilidade
    // ============================================================
    static void dia4() {
        System.out.println("========== DIA 4: record e enum ==========");

        // record: imutavel, equals por conteudo e toString de graca
        Ponto p1 = new Ponto(3, 4);
        Ponto p2 = new Ponto(3, 4);
        System.out.println("toString gerado       -> " + p1);            // Ponto[x=3, y=4]
        System.out.println("equals por conteudo   -> " + p1.equals(p2)); // true
        System.out.println("== por referencia     -> " + (p1 == p2));    // false

        // pra "mudar" um imutavel eu crio um novo
        Ponto movido = new Ponto(p1.x() + 1, p1.y());
        System.out.println("original " + p1 + " | novo " + movido);

        // enum: conjunto fixo, com campo e metodo; compara com ==
        for (Status s : Status.values()) {
            System.out.println("status " + s + " -> sigla " + s.sigla());
        }
        System.out.println("ATIVO == ATIVO -> " + (Status.ATIVO == Status.ATIVO) + "\n");
    }

    record Ponto(int x, int y) {}

    enum Status {
        ATIVO("A"),
        INATIVO("I");

        private final String sigla;
        Status(String sigla) { this.sigla = sigla; }
        String sigla() { return sigla; }
    }

    // ============================================================
    // DIA 5 — String, StringBuilder e char como numero
    // ============================================================
    static void dia5() {
        System.out.println("========== DIA 5: String e StringBuilder ==========");

        // String e imutavel: "modificar" cria uma nova
        String s = "java";
        String antes = s;
        s = s + " 21";
        System.out.println("s     = " + s);        // java 21
        System.out.println("antes = " + antes);    // java (intacto)

        // StringBuilder: mutavel, encadeia append e vira String no fim
        StringBuilder sb = new StringBuilder();
        sb.append("Diego").append(" tem ").append(30).append(" anos");
        System.out.println("builder -> " + sb);

        // char e numero: c - 'a' vira indice de 0 a 25
        char c = 'j';
        System.out.println("'j' como int  -> " + (int) c);          // 106
        System.out.println("'j' - 'a'     -> " + (c - 'a'));        // 9
        System.out.println("(char)('A'+1) -> " + (char) ('A' + 1)); // B

        // length: METODO na String (com parenteses)
        System.out.println("length()      -> " + "Programando".length() + "\n"); // 11
    }

    // ============================================================
    // DIA 6 — Arrays e a classe java.util.Arrays
    // ============================================================
    static void dia6() {
        System.out.println("========== DIA 6: arrays ==========");

        // int[] nasce com zeros; length e' CAMPO (sem parenteses)
        int[] a = {5, 3, 1, 4, 2};
        System.out.println("length (campo) -> " + a.length);

        // classe utilitaria Arrays: sort no lugar + toString legivel
        Arrays.sort(a);
        System.out.println("ordenado       -> " + Arrays.toString(a)); // [1, 2, 3, 4, 5]

        // busca binaria (so em array ordenado)
        System.out.println("binarySearch 4 -> indice " + Arrays.binarySearch(a, 4));

        // array 2D com deepToString
        int[][] matriz = new int[2][3];
        matriz[0][1] = 7;
        System.out.println("matriz 2D      -> " + Arrays.deepToString(matriz));

        // quando preciso crescer, uso ArrayList (Dia 8 chegando)
        List<Integer> lista = new ArrayList<>();
        lista.add(10);
        lista.add(20);
        System.out.println("ArrayList      -> " + lista);

        // Set pra "ja vi esse valor?" (conexao com o Contains Duplicate do Dia 4)
        Set<Integer> vistos = new HashSet<>();
        System.out.println("add 1a vez     -> " + vistos.add(1));  // true
        System.out.println("add repetido   -> " + vistos.add(1));  // false
    }
}
