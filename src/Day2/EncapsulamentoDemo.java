package Day2;

public class EncapsulamentoDemo {
    public static void main(String[] args) {
        ClasseConta conta = new ClasseConta(100.0);      // construtor inicializa o saldo
        System.out.println("saldo inicial: " + conta.getSaldo());

        conta.deposito(50.0);               // regra passa
        System.out.println("apos depositar 50: " + conta.getSaldo());

        // caminho de erro: a regra protege a invariante
        try {
            conta.deposito(-10.0);          // valor invalido
        } catch (IllegalArgumentException e) {
            System.out.println("bloqueado: " + e.getMessage());
        }
        System.out.println("saldo continua intacto: " + conta.getSaldo());

        //caminho para sacar
        try{
            conta.sacar(-50.0);
        }catch (IllegalArgumentException e) {
            System.out.println("bloqueado: " + e.getMessage());
        }
        System.out.println("saldo continua  intacto: " + conta.getSaldo());

        //caminho para sacar
        try{
            conta.sacar(0.0);
        }catch (IllegalArgumentException e) {
            System.out.println("bloqueado: " + e.getMessage());
        }
        System.out.println("saldo continua  intacto: " + conta.getSaldo());

        //caminho para sacar
        try{
            conta.sacar(30.0);
        }catch (IllegalArgumentException e) {
            System.out.println("bloqueado: " + e.getMessage());
        }
        System.out.println("saldo atualizado, saque foi possivel: " + conta.getSaldo());
        //conta.saldo = -999.0;   // nao compila
    }
}
