package VIEW;

import java.util.Scanner;

public class CajeroView {
    private Scanner scanner;

    public CajeroView() {
        scanner = new Scanner(System.in);
    }
    public void mostrarBienvenida(){
        System.out.println("==========================================");
        System.out.println("Bienvenido al cajero automatico del banco del bajio");
        System.out.println("==========================================");
    }
    public String solicitarNoCuenta (){
        System.out.println("Ingresa tu numero de cuenta: ");
        return scanner.nextLine();
    }
    public String slicitarPin(){
        System.out.println("Ingresa tu pin: ");
        return scanner.nextLine();
    }
   public void mostrarMenuPrincipal(String titular){
        System.out.println("=======================================");
        System.out.println("Bienvenid@ "+titular);
        System.out.println("=======================================");
        System.out.println("1.- Consulta de saldo ");
        System.out.println("2.- Retirar ");
        System.out.println("3.- Depositar ");
        System.out.println("4.- Transferir ");
        System.out.println("5.- Consulta de movimientos ");
        System.out.println("6.- Tarjeta de crédito (TDC)");
        System.out.println("9.- Salir del sistema ");
        System.out.print("Selecciona una opción: ");
   }
   public int leerOpcion(){
        try{
            String linea = scanner.nextLine();
            if (linea == null) return -1;
            linea = linea.trim();
            if (linea.isEmpty()) return -1;
            return Integer.parseInt(linea);
        }catch(Exception e){
            return -1;
        }
   }
   public void mostrarSaldo(double saldo){
       System.out.println("=======================================");
       System.out.println("Tu saldo actual es: $"+saldo);
       System.out.println("=======================================");
   }
   public double solicitarCantidad(String operacion ){
        System.out.println("Ingresa la cantidad a "+operacion+" :");
        try {
            String linea = scanner.nextLine();
            if (linea == null) return -1;
            linea = linea.trim().replace(',', '.');
            if (linea.isEmpty()) return -1;
            return Double.parseDouble(linea);
        }catch(NumberFormatException e){
            return -1;
        }
   }
   public void mostrarMensaje(String mensaje){
        System.out.println("==== "+mensaje);
   }
   public String solicitarNoCuentaDestino(){
        System.out.println("Ingresa el número de cuenta destino: ");
        return scanner.nextLine();
   }
   public void esperarEnter(){
        System.out.println("Presiona Enter para continuar...");
        try{
            scanner.nextLine();
        }catch(Exception e){
            // ignorar
        }
   }
   public void mostrarMovimientos(java.util.List<String> movimientos){
        System.out.println("=========== Movimientos ===========");
        if (movimientos == null || movimientos.isEmpty()){
            System.out.println("Sin movimientos");
        } else {
            for (String m : movimientos){
                System.out.println("- " + m);
            }
        }
        System.out.println("===================================");
   }
   public void mostrarEstadoTdc(double limite, double deuda, double disponible){
        System.out.println("========== Estado TDC ==========");
        System.out.println("Límite: $"+limite);
        System.out.println("Deuda: $"+deuda);
        System.out.println("Disponible: $"+disponible);
        System.out.println("================================");
   }
   public void mostrarMenuTdc(){
        System.out.println("============= TDC =============");
        System.out.println("1.- Consultar estado TDC");
        System.out.println("2.- Comprar con TDC");
        System.out.println("3.- Pagar TDC desde cuenta");
        System.out.println("9.- Regresar");
        System.out.print("Selecciona una opción: ");
   }
}
