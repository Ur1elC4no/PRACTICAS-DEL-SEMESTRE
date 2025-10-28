package MODEL;

public class Cuenta {
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String titular;

    public Cuenta(String numeroCuenta, String pin, double saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = (pin != null) ? pin : "";
        this.saldo = Math.max(0, saldo);
        this.titular = (titular != null) ? titular : "";
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

    public boolean validarPin(String pinIngresado) {
        return pinIngresado != null && pinIngresado.equals(this.pin);
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && this.saldo >= cantidad) {
            this.saldo -= cantidad;
            return true;
        }
        return false;
    }

    public void depositar(double cantidad) {
        if (cantidad > 0) {
            this.saldo += cantidad;
        }
    }

    public boolean transferir(Cuenta destino, double cantidad) {
        if (destino == null || cantidad <= 0) {
            return false;
        }
        if (!retirar(cantidad)) {
            return false;
        }
        destino.depositar(cantidad);
        return true;
    }

    public boolean cambiarNip(String pinActual, String nuevoPin) {
        if (validarPin(pinActual) && nuevoPin != null && !nuevoPin.isEmpty()) {
            this.pin = nuevoPin;
            return true;
        }
        return false;
    }
}
