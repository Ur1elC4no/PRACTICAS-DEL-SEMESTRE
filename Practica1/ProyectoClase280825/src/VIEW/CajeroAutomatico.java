package VIEW;

import CONTROLLER.CajeroController;
import CONTROLLER.CajeroController;
import MODEL.CajeroModel;
import MODEL.CajeroModel;


public class CajeroAutomatico {
    public static void main(String[] args) {

        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        CajeroController controller = new CajeroController(model, view);
        controller.iniciarSistema();
    }
}