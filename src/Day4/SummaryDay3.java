package Day4;

import Day3.InterfaceForma;

public class SummaryDay3 {

    public static void main(String[] args) {
        Triangle triangle = new Triangle(10, 30);
        System.out.println(triangle.area());

        //leetcode do dia anterior para fixar
        int precos[] = {2, 1, 7, 5, 9, 1, 3};
        int menorPreco = precos[0];
        int maiorLucro = 0;

        for (int i = 0; i < precos.length; i++) {
            if (precos[i] < menorPreco) {
                menorPreco = precos[i];
            }
            if ((precos[i] - menorPreco) > maiorLucro) {
                maiorLucro = precos[i] - menorPreco;
            }
        }
        System.out.println(maiorLucro);
    }
}

class Triangle implements InterfaceForma {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    public Triangle() {

    }

    @Override
    public double area() {
        return (base * height) / 2;
    }
}


