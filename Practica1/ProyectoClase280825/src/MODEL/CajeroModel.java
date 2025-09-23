package MODEL;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000, "Juan Pérez"));
        cuentas.put("67890", new Cuenta("67890", "2222", 3000, "María García"));
        cuentas.put("34567", new Cuenta("34567", "3333", 2000, "Carlos Sánchez"));
        cuentas.put("89012", new Cuenta("89012", "4444", 1000, "Ana Rodríguez"));
    }

    // Autenticación de usuario
    public boolean autenticacion(String numeroCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    public Cuenta getCuentaActual() {
        return cuentaActual;
    }

    // Consultar saldo
    public double consultarSaldo() {
        if (cuentaActual != null) {
            return cuentaActual.getSaldo();
        }
        return 0;
    }

    // Retiro de dinero
    public boolean realizarRetiro(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            return cuentaActual.retirar(cantidad);
        }
        return false;
    }

    // Depósito de dinero
    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    // Verifica si una cuenta existe
    public boolean cuentaExistente(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }

    // Transferencia entre cuentas
    public boolean realizarTransferencia(String numeroCuentaDestino, double cantidad) {
        if (cuentaActual == null || cantidad <= 0 || numeroCuentaDestino.equals(cuentaActual.getNumeroCuenta())) {
            return false;
        }

        Cuenta cuentaDestino = cuentas.get(numeroCuentaDestino);
        if (cuentaDestino == null) {
            return false;
        }

        return cuentaActual.transferir(cuentaDestino, cantidad);
    }

    // Cambiar PIN
    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (cuentaActual == null) {
            return false;
        }
        return cuentaActual.cambiarNip(pinActual, nuevoPin);
    }
}
