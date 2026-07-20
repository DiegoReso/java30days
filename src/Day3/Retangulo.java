package Day3;

public class Retangulo implements InterfaceForma {
    private double height;
    private double base;

    public Retangulo(double height, double base) {
        this.height = height;
        this.base = base;
    }

    public Retangulo() {
    }

    @Override
    public double area() {
        return base * height;
    }
}
