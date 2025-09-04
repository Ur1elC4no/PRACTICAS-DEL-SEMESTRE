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


//TAREA
//- Personalizar mensajes de error y de exito
//- Metodo para salir, cerrar el scanner



// Para subir el repositorio
// git add .
// git commit /am "Mensaje"
// git pull
//git push