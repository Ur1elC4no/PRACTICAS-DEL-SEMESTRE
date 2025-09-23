package MODEL;

public class Cuenta {
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String titular;

    public Cuenta(String numeroCuenta, String pin, double saldoInicial, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin != null ? pin : ""; // Evita null
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // Validar PIN de forma segura
    public boolean validarPin(String pinIngresado) {
        if (pinIngresado == null) return false; // Nunca null
        return pinIngresado.equals(this.pin);  // Llamamos equals sobre la variable segura
    }

    // Retiro de saldo
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    // Depósito de saldo
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    // Transferencia a otra cuenta
    public boolean transferir(Cuenta destino, double cantidad) {
        if (destino != null && cantidad > 0 && saldo >= cantidad) {
            saldo -= cantidad;
            destino.depositar(cantidad);
            return true;
        }
        return false;
    }

    // Cambiar PIN de forma segura
    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (validarPin(pinActual) && nuevoPin != null && !nuevoPin.isEmpty()) {
            this.pin = nuevoPin;
            return true;
        }
        return false;
    }
}
