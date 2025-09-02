package MODEL;

public class Cuenta {
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String titular;
    public Cuenta(String numeroCuenta, String pin, double saldoInicial, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.titular = titular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getPin() {
        return pin;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    //Reglas de negocio

    public boolean validarPin(String pinIngresado){
        return this.pin.equals(pinIngresado);
    }
    public boolean retirar(double cantidad){
        if (cantidad > 0 && cantidad <= this.saldo){
            saldo -= cantidad;
            return true;
        }
        return false;
    }
    public void depositar(double cantidad){
        if (cantidad > 0){
            saldo += cantidad;
        }
    }

    //De tarea diseñar los comportamientos restantes transferir, cambiar nip

    public boolean transferir(Cuenta cuentaDestino, double cantidad) {
        if (cantidad <= 0) {
            return false;
        }

        if (this.saldo < cantidad) {
            return false;
        }

        if (cuentaDestino == null) {
            return false;
        }

        if (this.retirar(cantidad)) {
            cuentaDestino.depositar(cantidad);
            return true;
        }

        return false;
    }
    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (!validarPin(pinActual)) {
            return false;
        }

        if (nuevoPin == null || nuevoPin.trim().isEmpty()) {
            return false;
        }

        if (!nuevoPin.matches("\\d{4}")) {
            return false;
        }

        this.pin = nuevoPin;
        return true;
    }
}