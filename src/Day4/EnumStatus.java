package Day4;

public enum EnumStatus {
    ATIVO("A"),
    INATIVO("I");

    private final String sigla;

    EnumStatus(String sigla) {
        this.sigla = sigla;
    }

    public String sigla() {
        return sigla;
    }
}
