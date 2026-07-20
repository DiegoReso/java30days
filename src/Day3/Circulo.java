package Day3;

public class Circulo implements InterfaceForma {

    private double radius;

    public Circulo(double radius) {
        this.radius = radius;
    }

    public Circulo() {
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}
