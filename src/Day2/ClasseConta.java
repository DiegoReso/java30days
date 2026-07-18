package Day2;

public class ClasseConta {
    //A regra prática: começar tudo private e só abra (public) o que realmente precisa ser usado por fora.
    // É mais fácil abrir depois do que fechar (fechar algo que já é público pode quebrar quem dependia dele).
    private double saldo;

//    final num campo
//    final torna o campo imutável depois de inicializado — você atribui uma vez (na declaração ou no construtor) e
//    nunca mais. exemplo:
//    private final String titular;   // definido no construtor, nunca muda depois

    public ClasseConta(double saldo, String titular) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void deposito(double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("valor invalido");
        }
        this.saldo += valor;
    }

}
