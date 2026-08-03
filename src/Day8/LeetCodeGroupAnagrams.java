package Day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCodeGroupAnagrams {
    public static void main(String[] args) {
        String[] palavras = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(palavras));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> mapa = new HashMap<>();

        for (String palavra : strs) {
            char[] letras = palavra.toCharArray();   // 1. quebra em char[]  (Dia 5)
            Arrays.sort(letras);                      // 2. ordena as letras  (Dia 6)
            String chave = new String(letras);        // 3. vira a "chave" do anagrama
            // 4. joga a palavra no grupo dessa chave (cria a lista se nao existir)
            mapa.computeIfAbsent(chave, k -> new ArrayList<>()).add(palavra);
        }

        return new ArrayList<>(mapa.values());        // 5. os valores sao os grupos
    }
}