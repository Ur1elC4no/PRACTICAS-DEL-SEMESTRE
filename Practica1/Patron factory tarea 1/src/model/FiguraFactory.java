package model;

public class FiguraFactory {
    public static Figura crearFigura(String tipo, double valor) {
        switch (tipo.toLowerCase()) {
            case "circulo": return new Circulo(valor);
            case "cuadrado": return new Cuadrado(valor);
            default: throw new IllegalArgumentException("Tipo de figura no soportado");
        }
    }
}
