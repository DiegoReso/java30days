package Day3;

import java.awt.*;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Quadrado q = new Quadrado(3);
        Circulo c = new Circulo(2);
        Retangulo r = new Retangulo(3, 5);

        //polimorfismo em acao
        List<InterfaceForma> formas = List.of(q, c, r);
        for (InterfaceForma f : formas) {
            System.out.println(f.area());
        }

        System.out.println("Soma das formas");
        double soma = 0;
        for(InterfaceForma f : formas) {
            soma = soma +  f.area();
        }
        System.out.println(soma);

//        Classe abstrata (o meio - termo)
//        Fica entre a classe normal e a interface. Ela pode ter estado e métodos concretos (prontos), mas também métodos
//        abstratos (sem corpo, que as filhas precisam implementar). E o principal: não pode ser instanciada — você não faz
//        new nela, só nas filhas.
//        abstract class FormaBase {
//            abstract double area();                    // filha precisa implementar
//            void descrever() {                         // já vem pronto pra todas
//                System.out.println("Área: " + area());
//            }
//        }
    }
}
