package VIEW;

import CONTROLLER.CajeroController;
import MODEL.CajeroModel;
import java.util.ArrayList;
import java.util.List;


public class CajeroAutomatico {
    public static void main(String[] args) {

        CajeroModel model = new CajeroModel();
        CajeroView view = new CajeroView();
        CajeroController controller = new CajeroController(model, view);
        List<String> mensajes = new ArrayList<String>();
        mensajes.add("Iniciando sistema...");
        System.out.println(mensajes.get(0));
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