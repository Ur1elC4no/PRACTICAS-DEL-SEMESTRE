package VIEW;

import java.util.Scanner;

public class CajeroView {
    private Scanner scanner;

    public CajeroView(){
        scanner = new Scanner(System.in);
    }

    public void mostrarBienvenida(){
        System.out.println("======================================");
        System.out.println("Bienvenido al cajero automatico de BBVA");
        System.out.println("======================================");
    }

    public String solicitarNumeroCuenta(){
        System.out.println("Ingresa tu número de cuenta: ");
        return scanner.nextLine().trim();
    }

    public String solicitarPin(){
        System.out.println("Ingresa tu PIN: ");
        return scanner.nextLine().trim();
    }

    public void mostrarMenuPrincipal(String titular){
        System.out.println("======================================");
        System.out.println("Bienvenido usuario: " + titular);
        System.out.println("======================================");
        System.out.println("1.- Consultar Saldo");
        System.out.println("2.- Retirar");
        System.out.println("3.- Depositar");
        System.out.println("4.- Transferir");
        System.out.println("5.- Cambiar PIN");
        System.out.println("6w.- Salir");
    }

    public int leerOpcion(){
        try{
            return Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            return -1;
        }
    }

    public void mostrarSaldo(double saldo){
        System.out.println("======================================");
        System.out.println("Tu saldo es $:" + saldo);
        System.out.println("======================================");
    }

    public double solicitarCantidad(String operacion){
        System.out.println("Ingresa la cantidad a " + operacion + ": ");
        try {
            return Double.parseDouble(scanner.nextLine());
        }catch (NumberFormatException e){
            return -1;
        }
    }

    public void mostrarMensaje(String mensaje){
        System.out.println("==== "+mensaje);
    }

    public String solicitarCuentaDestino(){
        System.out.print("Cuenta destino: ");
        return scanner.nextLine().trim();
    }

    public String solicitarPinActual(){
        System.out.print("PIN actual: ");
        return scanner.nextLine();
    }

    public String solicitarNuevoPin(){
        System.out.print("Nuevo PIN: ");
        return scanner.nextLine();
    }

    public void mostrarError(String error){
        System.out.println("Error: " + error);
    }

    public void cerrarScanner(){
        scanner.close();
    }

    // ===== Patrón Builder =====
    public static class Builder {
        private Scanner scanner;
        private String bienvenida = "Bienvenido al cajero automatico";

        public Builder setScanner(Scanner scanner){
            this.scanner = scanner;
            return this;
        }

        public Builder setBienvenida(String mensaje){
            this.bienvenida = mensaje;
            return this;
        }

        public CajeroView build(){
            CajeroView view = new CajeroView();
            if(this.scanner != null){
                view.scanner = this.scanner;
            }
            // Podemos personalizar la bienvenida usando el builder
            view.mostrarBienvenida = () -> System.out.println("======================================\n" +
                    this.bienvenida + "\n======================================");
            return view;
        }
    }

    // Para personalizar la bienvenida desde el Builder
    private Runnable mostrarBienvenida = this::mostrarBienvenidaOriginal;

    private void mostrarBienvenidaOriginal(){
        System.out.println("======================================");
        System.out.println("Bienvenido al cajero automatico de BBVA");
        System.out.println("======================================");
    }

    public void mostrarBienvenidaPersonalizada(){
        mostrarBienvenida.run();
    }
}
