package Day3;

import Day2.ClasseConta;

public class SummaryDay2 {
    public static void main(String[] args) {
        ClasseConta cc = new ClasseConta(500);
        System.out.println(cc.getSaldo());
        //cc.saldo = 30; //'saldo' has private access in 'Day2.ClasseConta' -> veja nao é possível acessar saldo diretamente (encapsulamento na veia) :)

        //falhando miseravelmente ao tentar sacar mais que o saldo -> protecao dos dados, a classe faz as protecoes
        //necessarias
        try {
            cc.sacar(550);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //tentativa deposito valor 0, classe ja protege essa operacao
        try {
            cc.deposito(0);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //leetcode do dia 2 para fixar
        String palindrome = "A man, a plan, a canal: Panama";

        int esquerda = 0;
        int direita = palindrome.length() - 1;
        boolean isPalindrome = true;

        while(esquerda < direita) {
            while(esquerda < direita && !Character.isLetterOrDigit(palindrome.charAt(esquerda))) {
                esquerda++;
            }
            while(esquerda < direita && !Character.isLetterOrDigit(palindrome.charAt(direita))) {
                direita--;
            }
            if(Character.toLowerCase(palindrome.charAt(esquerda)) != Character.toLowerCase(palindrome.charAt(direita))) {
                isPalindrome = false;
                break;
            }
            esquerda++;
            direita--;
        }
        System.out.println(isPalindrome ? "Palindrome" : "Not Palindrome");

    }
}
