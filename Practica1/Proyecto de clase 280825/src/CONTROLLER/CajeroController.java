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
        while(sistemaActivo){
            if (autenticarUsuario()){
                ejecutarMenuPrincipal ();
            } else {
                view.mostrarMensaje("Credenciales incorrectes");
                // si no autentica, preguntamos si desea salir o reintentar
                // para simplicidad, reintentamos; podría agregarse un conteo de intentos
            }
        }
        view.mostrarMensaje("Gracias por usar nuestro cajero");
    }
    private boolean autenticarUsuario(){
        String noCuenta = view.solicitarNoCuenta();
        String pin = view.slicitarPin();
        return model.autenticar(noCuenta, pin);
    }
    private void ejecutarMenuPrincipal(){
        boolean sesionActiva = true;
        while(sesionActiva){
            String titular = model.getCuentaActual() != null ? model.getCuentaActual().getTitular() : "";
            view.mostrarMenuPrincipal(titular);
            int opcion = view.leerOpcion();
            switch (opcion){
                case 1:
                    double saldo = model.consultarSaldo();
                    view.mostrarSaldo(saldo);
                    view.esperarEnter();
                    break;
                case 2:
                    double cantRetiro = view.solicitarCantidad("retirar");
                    if (cantRetiro <= 0){
                        view.mostrarMensaje("Cantidad inválida");
                        break;
                    }
                    if (model.realizarRetiro(cantRetiro)){
                        view.mostrarMensaje("Retiro exitoso");
                    } else {
                        view.mostrarMensaje("No se pudo realizar el retiro (fondos insuficientes o sesión inválida)");
                    }
                    view.esperarEnter();
                    break;
                case 3:
                    double cantDeposito = view.solicitarCantidad("depositar");
                    if (cantDeposito <= 0){
                        view.mostrarMensaje("Cantidad inválida");
                        break;
                    }
                    if (model.realizarDeposito(cantDeposito)){
                        view.mostrarMensaje("Depósito exitoso");
                    } else {
                        view.mostrarMensaje("No se pudo realizar el depósito");
                    }
                    view.esperarEnter();
                    break;
                case 4:
                    String destino = view.solicitarNoCuentaDestino();
                    double cantTransfer = view.solicitarCantidad("transferir");
                    if (cantTransfer <= 0){
                        view.mostrarMensaje("Cantidad inválida");
                        break;
                    }
                    if (!model.cuentaExistente(destino)){
                        view.mostrarMensaje("La cuenta destino no existe");
                        break;
                    }
                    if (model.realizarTransferencia(destino, cantTransfer)){
                        view.mostrarMensaje("Transferencia exitosa");
                    } else {
                        view.mostrarMensaje("No se pudo realizar la transferencia");
                    }
                    view.esperarEnter();
                    break;
                case 5:
                    java.util.List<String> movs = model.consultarMovimientos();
                    if (movs == null || movs.isEmpty()){
                        view.mostrarMensaje("Sin movimientos");
                    } else {
                        view.mostrarMensaje("=========== Movimientos ===========");
                        for (String m : movs){
                            view.mostrarMensaje("- " + m);
                        }
                        view.mostrarMensaje("===================================");
                    }
                    view.esperarEnter();
                    break;
                case 6:
                    ejecutarMenuTdc();
                    break;
                case 9:
                    sesionActiva = false;
                    sistemaActivo = false; // salir del sistema completo
                    break;
                default:
                    view.mostrarMensaje("Opción inválida");
                    view.esperarEnter();
                    break;
            }
        }
    }
    private void ejecutarMenuTdc(){
        boolean tdcActiva = true;
        while(tdcActiva){
            view.mostrarMenuTdc();
            int op = view.leerOpcion();
            switch(op){
                case 1:
                    view.mostrarEstadoTdc(model.tdcLimite(), model.tdcDeuda(), model.tdcDisponible());
                    view.esperarEnter();
                    break;
                case 2:
                    double compra = view.solicitarCantidad("comprar con TDC");
                    if (compra <= 0){
                        view.mostrarMensaje("Monto inválido");
                        break;
                    }
                    if (model.tdcComprar(compra)){
                        view.mostrarMensaje("Compra TDC exitosa");
                    } else {
                        view.mostrarMensaje("Compra TDC rechazada (límite insuficiente)");
                    }
                    view.esperarEnter();
                    break;
                case 3:
                    double pago = view.solicitarCantidad("pagar a TDC desde cuenta");
                    if (pago <= 0){
                        view.mostrarMensaje("Monto inválido");
                        break;
                    }
                    if (model.tdcPagarDesdeCuenta(pago)){
                        view.mostrarMensaje("Pago TDC realizado");
                    } else {
                        view.mostrarMensaje("Pago TDC rechazado (saldo/deuda insuficiente)");
                    }
                    view.esperarEnter();
                    break;
                case 9:
                    tdcActiva = false;
                    break;
                default:
                    view.mostrarMensaje("Opción inválida");
                    view.esperarEnter();
            }
        }
    }
}
