package Day6;

public class SummaryDay5 {
    public static void main(String[] args) {
        // 1) String e imutavel: "modificar" cria uma nova
        System.out.println("=== 1. String imutavel ===");
        String s = "java";
        String antes = s;
        s = s + " 21";
        System.out.println("s     = " + s);       // java 21
        System.out.println("antes = " + antes);   // java (intacto)

        // 2) StringBuilder: mutavel, vai crescendo
        System.out.println("\n=== 2. StringBuilder ===");
        StringBuilder sb = new StringBuilder();
        sb.append("Diego").append(" tem ").append(30).append(" anos");
        System.out.println(sb.toString());

        // 3) length: METODO na String (com parenteses)
        System.out.println("\n=== 3. length na String ===");
        String texto = "Programando";
        System.out.println("length() = " + texto.length());
        System.out.println("charAt(0) = " + texto.charAt(0));
        System.out.println("substring(0,4) = " + texto.substring(0, 4));

        // 4) char e numero: c - 'a' vira indice 0..25
        System.out.println("\n=== 4. char como numero ===");
        char c = 'j';
        System.out.println("'j' como int  = " + (int) c);        // 106
        System.out.println("'j' - 'a'     = " + (c - 'a'));       // 9
        System.out.println("(char)('A'+1) = " + (char) ('A' + 1)); // B

        // 5) mini Valid Anagram pra relembrar
        System.out.println("\n=== 5. Valid Anagram ===");
        System.out.println("anagram/nagaram -> " + isAnagram("anagram", "nagaram")); // true
        System.out.println("rat/car         -> " + isAnagram("rat", "car"));         // false
    }

    static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        int[] c = new int[26];
        for (int i = 0; i < a.length(); i++) {
            c[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < b.length(); i++) {
            c[b.charAt(i) - 'a']--;
        }
        for (int cont : c) {
            if (cont != 0) return false;
        }
        return true;
    }
}

