package controller;

import model.Figura;
import model.FiguraFactory;
import view.FiguraView;

public class FiguraController {
    private FiguraView view;

    public FiguraController(FiguraView view) {
        this.view = view;
    }

    public void calcularArea(String tipo, double valor) {
        Figura figura = FiguraFactory.crearFigura(tipo, valor);
        double area = figura.area();
        view.mostrarResultado("El área del " + tipo + " es: " + area);
    }
}
