package Day2;

public class ClasseConta {
    //A regra prática: começar tudo private e só abra (public) o que realmente precisa ser usado por fora.
    // É mais fácil abrir depois do que fechar (fechar algo que já é público pode quebrar quem dependia dele).
    private double saldo;

    public ClasseConta(double saldo) {
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
