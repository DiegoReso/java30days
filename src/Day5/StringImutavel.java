package Day5;

public class StringImutavel {
    public static void main(String[] args) {
        System.out.println("=== STRING E IMUTAVEL ===");
        String s = "java";
        String antes = s;
        s = s + " 21"; // NÃO alterou "java" — criou "java 21" e apontou s pra ela
        System.out.println("s     = " + s);       // java 21
        System.out.println("antes = " + antes);   // java  (o original ficou intacto)
        System.out.println("mesmo objeto? " + (antes == "java"));

        System.out.println();
        System.out.println("=== STRINGBUILDER (mutavel) ===");
        StringBuilder sb = new StringBuilder();
        sb.append("java");
        sb.append(" ");
        sb.append(21);
        System.out.println("sb = " + sb.toString());

        System.out.println();
        System.out.println("=== METODOS UTEIS DE STRING ===");
        String texto = "Programando";
        System.out.println("lenght() = " + texto.length());//metodo com ()
        System.out.println("charAt() = " + texto.charAt(0));
        System.out.println("substring(0,4) = " + texto.substring(0, 4));
        System.out.println("indexOf('g') = " + texto.indexOf('g'));
        System.out.println("lastIndexOf('o') = " + texto.lastIndexOf('o'));
        System.out.println("toUpperCase() = " + texto.toUpperCase());

        System.out.println();
        System.out.println("=== CHAR E UM NUMERO (16 bits) ===");
        char c = 'A';
        int codigo = c;  // char vira numero sem esforco
        System.out.println("'A' como int = " + codigo);        // 65
        System.out.println("'A' + 1 vira = " + (char)(c + 1));  // B

    }
}
