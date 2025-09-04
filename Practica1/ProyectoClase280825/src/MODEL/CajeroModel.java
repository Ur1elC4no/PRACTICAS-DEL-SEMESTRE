package MODEL;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public CajeroModel() {
        cuentas = new HashMap<String, Cuenta>();
        inicializarCuentas();
    }

    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000, "Juan Pérez"));
        cuentas.put("67890", new Cuenta("67890", "2222", 3000, "María García"));
        cuentas.put("34567", new Cuenta("34567", "3333", 2000, "Carlos Sánchez"));
        cuentas.put("89012", new Cuenta("89012", "4444", 1000, "Ana Rodríguez"));
    }

    public boolean autenticacion(String numeroCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    public double consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getSaldo() : 0;
    }

    public boolean realizarRetiro(double cantidad) {
        return cuentaActual != null && cuentaActual.retirar(cantidad);
    }

    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    public boolean cuentaExistente(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }

    //Definir el metodo para transferir
    public boolean realizarTransferencia(String numeroCuentaDestino, double cantidad) {

        if (cuentaActual == null) {
            return false;
        }

        if (cantidad <= 0) {
            return false;
        }

        if (numeroCuentaDestino.equals(cuentaActual.getNumeroCuenta())) {
            return false;
        }

        Cuenta cuentaDestino = cuentas.get(numeroCuentaDestino);
        if (cuentaDestino == null) {
            return false;
        }
        return cuentaActual.transferir(cuentaDestino, cantidad);
    }
    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (cuentaActual == null) {
            return false;
        }

        return cuentaActual.cambiarNip(pinActual, nuevoPin);
    }
}