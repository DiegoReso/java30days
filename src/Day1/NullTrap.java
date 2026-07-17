package Day1;

import java.util.HashMap;
import java.util.Map;

public class NullTrap {
    public static void main(String[] args) {
//        int a = null;        // ERRO DE COMPILAÇÃO — nem compila
        Integer b = null;    // OK, wrappers aceitam null

        int c = b;           // COMPILA, mas explode em runtime:
        // NullPointerException no auto-unboxing!

//      O código acima compila lindamente e estoura só quando roda. É por isso que a JVM "leva o tipo a sério"
//       — mas o null num wrapper é uma porta lateral para o NPE.

        Map<String, Integer> pontos = new HashMap<>();
        int p = pontos.get("joao");   // "joao" não existe → get devolve null → NPE no unboxing


        Integer total = null;
        int novo = total + 10;   // desembrulha total (null) pra somar → NPE

        Integer valor = null;
        int x = (1 > 0) ? valor : 0;   // pode estourar mesmo se você "acha" que pegou o 0
    }
}
