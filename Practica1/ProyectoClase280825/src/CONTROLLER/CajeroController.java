package CONTROLLER;

import MODEL.CajeroModel;
import VIEW.CajeroView;

public class CajeroController {
    private CajeroModel model;
    private CajeroView view;
    private boolean sistemaActivo;

    public CajeroController(CajeroModel model, CajeroView view){
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }
    public void iniciarSistema(){
        view.mostrarBienvenida();
        while (sistemaActivo){
            if(autenticarUsuario()){
                ejecutarMenuPrincipal();
            }else{
                view.mostrarMensaje("Credenciales incorrectas");
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }
    private boolean autenticarUsuario(){
        String numeroCuenta = view.solicitarNumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticacion(numeroCuenta, pin);
    }
    private void ejecutarMenuPrincipal(){
        boolean sessionActive = true;
        while(sessionActive){
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion){
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarRetiro();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    realizarTransferencia();
                    break;
                case 5:
                    cambiarPin();
                    break;
                case 9:
                    view.mostrarMensaje("Saliendo del sistema...");
                    view.cerrarScanner();
                    sessionActive = false;
                    sistemaActivo = false;
                    break;
                default:
                    view.mostrarMensaje("Opción inválida");
                    break;
            }
        }
    }
    public void consultarSaldo(){
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }
    private void realizarRetiro(){
        double cantidad = view.solicitarCantidad("Retirar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensaje("Retiro exitoso de "+cantidad);
        }else{
            view.mostrarMensaje("Fondos insuficientes");
        }
    }
    public void realizarDeposito(){
        double cantidad = view.solicitarCantidad("Depositar");
        if (cantidad <= 0) {
            view.mostrarMensaje("Error en la cantidad");
            return;
        }
        if (model.realizarDeposito(cantidad)) {
            view.mostrarMensaje("Depósito exitoso de "+cantidad);
        }else{
            view.mostrarMensaje("Error al procesar el deposito");
        }
    }
    private void realizarTransferencia(){
        String cuentaDestino = view.solicitarCuentaDestino();
        double cantidad = view.solicitarCantidad("Transferir");

        if (cantidad <= 0) {
            view.mostrarMensaje("Cantidad inválida");
            return;
        }

        if (model.getCuentaActual() != null && cuentaDestino.equals(model.getCuentaActual().getNumeroCuenta())) {
            view.mostrarMensaje("No puedes transferir a la misma cuenta");
            return;
        }

        if (!model.cuentaExistente(cuentaDestino)) {
            view.mostrarMensaje("La cuenta destino no existe");
            return;
        }

        if (model.consultarSaldo() < cantidad) {
            view.mostrarMensaje("Fondos insuficientes");
            return;
        }

        if (model.realizarTransferencia(cuentaDestino, cantidad)) {
            view.mostrarMensaje("Transferencia exitosa de " + cantidad + " a la cuenta " + cuentaDestino);
        } else {
            view.mostrarMensaje("No fue posible realizar la transferencia");
        }
    }

    private void cambiarPin(){
        String pinActual = view.solicitarPinActual();
        String nuevoPin = view.solicitarNuevoPin();
        if (model.cambiarNip(pinActual, nuevoPin)){
            view.mostrarMensaje("PIN actualizado correctamente");
        }else{
            view.mostrarMensaje("No fue posible cambiar el PIN");
        }
    }
}