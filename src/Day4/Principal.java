package Day4;

public class Principal {
    public static void main(String[] args) {
        System.out.println("=========Record========");
        RecordPonto r1 = new RecordPonto(3, 4);
        System.out.println("r1.x() = " + r1.x());
        System.out.println("r1.y() = " + r1.y());
        System.out.println("r1     = " + r1 );
        System.out.println("equals = " + r1.equals(new RecordPonto(3, 4)));

        System.out.println();
        System.out.println("=== ENUM ===");
        for (EnumStatus s : EnumStatus.values()) {
            System.out.println(s + " -> sigla " + s.sigla());
        }
        EnumStatus atual = EnumStatus.ATIVO;
        System.out.println("compara com == : " + (atual == EnumStatus.ATIVO));

        System.out.println();
        System.out.println("=== IMUTABILIDADE ===");
        //r1.x = 99;//NEM COMPILA (record nao tem setter)
        RecordPonto movido = new RecordPonto(r1.x() + 1, r1.y());// pra "mudar", criar um novo
        System.out.println("original: " + r1 + "  |  novo: " + movido);

    }
}
