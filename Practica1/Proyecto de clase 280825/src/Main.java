import CONTROLLER.CajeroController;
import MODEL.CajeroModel;
import VIEW.CajeroView;

public class Main {
    public static void main(String[] args) {
        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        CajeroController controller = new CajeroController(model, view);
        controller.iniciarSistema();
    }
}
