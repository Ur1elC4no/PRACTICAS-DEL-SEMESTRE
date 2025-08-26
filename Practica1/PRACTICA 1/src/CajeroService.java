public class CajeroService {
    private final Cliente[] clientes;

    public CajeroService() {
        this.clientes = new Cliente[] {
            new Cliente("1234", "Juan", 1000.0),
            new Cliente("5678", "Maria", 2500.0)
        };
    }

    public int autenticar(String pin) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i].getPin().equals(pin)) {
                return i;
            }
        }
        return -1;
    }

    public String getNombre(int index) {
        return clientes[index].getNombre();
    }

    public double consultarSaldo(int index) {
        return clientes[index].getSaldo();
    }

    public boolean retirar(int index, double monto) {
        if (monto <= consultarSaldo(index)) {
            clientes[index].setSaldo(consultarSaldo(index) - monto);
            return true;
        }
        return false;
    }

    public void depositar(int index, double monto) {
        clientes[index].setSaldo(consultarSaldo(index) + monto);
    }
}