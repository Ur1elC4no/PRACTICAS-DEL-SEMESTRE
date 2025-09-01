package MODEL;

public class Cuenta {
    private String noCuenta;
    private String pin;
    private Double saldo;
    private String titular;
    private java.util.List<String> movimientos;
    private Double tdcLimite;
    private Double tdcDeuda;
    public Cuenta(String noCuenta, String pin, Double saldoInicial, String titular) {
        this.noCuenta = noCuenta;
        this.pin = pin;
        this.saldo = saldoInicial;
        this.titular = titular;
        this.movimientos = new java.util.ArrayList<>();
        this.movimientos.add("Cuenta creada con saldo inicial $" + saldoInicial);
        this.tdcLimite = 300000.0;
        this.tdcDeuda = 0.0;
        this.movimientos.add("TDC asignada con límite $" + this.tdcLimite);
    }

    public String getNoCuenta() {
        return noCuenta;
    }
    public String getPin() {
        return pin;
    }
    public Double getSaldo() {
        return saldo;
    }
    public String getTitular() {
        return titular;
    }
    public boolean validarPin  (String pinIngresado){
        return pin.equals(pinIngresado);
    }
    public boolean retirar(double cantidad){
        if (cantidad > 0 && cantidad <= saldo ){
            saldo -= cantidad;
            if (movimientos != null) {
                movimientos.add("Retiro: $" + cantidad + " | Saldo: $" + saldo);
            }
            return true;
        }
        return false;
    }
    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            if (movimientos != null) {
                movimientos.add("Depósito: $" + cantidad + " | Saldo: $" + saldo);
            }
            return true;
        }
        return false;
    }
    public boolean transferirA(Cuenta destino, double cantidad) {
        if (destino == null || destino == this) return false;
        if (cantidad <= 0) return false;
        if (!retirar(cantidad)) {
            return false;
        }
        boolean depositado = destino.depositar(cantidad);
        if (!depositado) {
            // rollback si el depósito falla
            depositar(cantidad);
            return false;
        }
        if (movimientos != null) {
            movimientos.add("Transferencia enviada a " + destino.noCuenta + ": $" + cantidad + " | Saldo: $" + this.saldo);
        }
        if (destino.movimientos != null) {
            destino.movimientos.add("Transferencia recibida de " + this.noCuenta + ": $" + cantidad + " | Saldo: $" + destino.saldo);
        }
        return true;
    }
    public Double getTdcLimite() {
        return this.tdcLimite;
    }
    public Double getTdcDeuda() {
        return this.tdcDeuda;
    }
    public Double getTdcDisponible() {
        return this.tdcLimite - this.tdcDeuda;
    }
    public boolean tdcComprar(double monto) {
        if (monto > 0 && monto <= getTdcDisponible()) {
            this.tdcDeuda += monto;
            if (movimientos != null) {
                movimientos.add("TDC compra: $" + monto + " | Deuda TDC: $" + this.tdcDeuda + " | Disponible: $" + getTdcDisponible());
            }
            return true;
        }
        return false;
    }
    public boolean tdcPagarDesdeCuenta(double monto) {
        if (monto > 0 && monto <= this.tdcDeuda && monto <= this.saldo) {
            this.saldo -= monto;
            this.tdcDeuda -= monto;
            if (movimientos != null) {
                movimientos.add("Pago TDC desde cuenta: $" + monto + " | Saldo cuenta: $" + this.saldo + " | Deuda TDC: $" + this.tdcDeuda + " | Disponible: $" + getTdcDisponible());
            }
            return true;
        }
        return false;
    }
    public java.util.List<String> getMovimientos() {
        return java.util.Collections.unmodifiableList(this.movimientos);
    }
    //TAREA: diseñar los comportamientos restantes
}
