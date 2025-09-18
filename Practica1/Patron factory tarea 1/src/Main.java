import controller.FiguraController;
import view.FiguraView;
import util.AlmacenGenerico;

public class Main {
    public static void main(String[] args) {
        FiguraView view = new FiguraView();
        FiguraController controller = new FiguraController(view);

        // Uso de Factory + MVC
        controller.calcularArea("circulo", 5);
        controller.calcularArea("cuadrado", 4);

        // Programación genérica
        AlmacenGenerico<String> log = new AlmacenGenerico<>();
        log.agregar("Ejecución correcta");
        log.agregar("Fin del programa");

        for (String msg : log.obtenerTodos()) {
            System.out.println("Log: " + msg);
        }
    }
}
