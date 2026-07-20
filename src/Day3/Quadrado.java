package Day3;

public class Quadrado implements InterfaceForma {
    private double side;

    public Quadrado(double face) {
        this.side = face;
    }

    public Quadrado() {
    }

    @Override
    public double area() {
        return side * side;
    }
}
