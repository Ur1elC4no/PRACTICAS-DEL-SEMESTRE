package MODEL;

import java.util.HashMap;
import java.util.Map;

public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;
    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas ();
    }
    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000.0, "Juan Perez"));
        cuentas.put("54321", new Cuenta("54321", "0000", 5000.0, "Maria Garcia"));
    }
    public boolean autenticar (String noCuenta, String pin) {
        Cuenta cuenta = cuentas.get(noCuenta);
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
        return this.cuentaActual !=null ? cuentaActual.getSaldo():0;
    }
    public java.util.List<String> consultarMovimientos(){
        return this.cuentaActual != null ? this.cuentaActual.getMovimientos() : java.util.Collections.emptyList();
    }
    public boolean realizarRetiro(double cantidad){
        return cuentaActual != null && cuentaActual.retirar(cantidad);
    }
    public boolean realizarDeposito (Double cantidad) {
        if (cuentaActual !=null && cantidad>0) {
            this.cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }
    public boolean cuentaExistente (String noCuenta) {
        return this.cuentas.containsKey(noCuenta);
    }
    public boolean realizarTransferencia(String noCuentaDestino, double cantidad) {
        if (cuentaActual == null) return false;
        if (noCuentaDestino == null) return false;
        Cuenta destino = cuentas.get(noCuentaDestino);
        if (destino == null) return false;
        if (destino == cuentaActual) return false;
        if (cantidad <= 0) return false;
        return cuentaActual.transferirA(destino, cantidad);
    }
    // TDC operations
    public double tdcLimite(){ return cuentaActual != null ? cuentaActual.getTdcLimite() : 0; }
    public double tdcDeuda(){ return cuentaActual != null ? cuentaActual.getTdcDeuda() : 0; }
    public double tdcDisponible(){ return cuentaActual != null ? cuentaActual.getTdcDisponible() : 0; }
    public boolean tdcComprar(double monto){ return cuentaActual != null && cuentaActual.tdcComprar(monto); }
    public boolean tdcPagarDesdeCuenta(double monto){ return cuentaActual != null && cuentaActual.tdcPagarDesdeCuenta(monto); }
}
