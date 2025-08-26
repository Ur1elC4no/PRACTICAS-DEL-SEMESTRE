import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CajeroService cajero = new CajeroService();

        int intentos = 0;
        boolean salir = false;
        int usuarioActual = -1;

        System.out.println("=== Bienvenido al Cajero ===");

        while (intentos < 3 && usuarioActual == -1) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();
            usuarioActual = cajero.autenticar(pin);
            if (usuarioActual == -1) {
                System.out.println("PIN incorrecto.");
                intentos++;
            }
        }

        if (usuarioActual == -1) {
            System.out.println("Demasiados intentos fallidos. Adiós.");
            scanner.close();
            return;
        }

        System.out.println("Bienvenido, " + cajero.getNombre(usuarioActual));

        while (!salir) {
            System.out.println("\n1. Ver saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
                continue;
            }

            if (opcion == 1) {
                System.out.println("Su saldo es: $" + cajero.consultarSaldo(usuarioActual));
            } else if (opcion == 2) {
                System.out.print("Ingrese cantidad a retirar: ");
                double retiro;
                try {
                    retiro = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Monto inválido.");
                    continue;
                }
                if (cajero.retirar(usuarioActual, retiro)) {
                    System.out.println("Retiro exitoso. Nuevo saldo: $" + cajero.consultarSaldo(usuarioActual));
                } else {
                    System.out.println("Fondos insuficientes.");
                }
            } else if (opcion == 3) {
                System.out.print("Ingrese cantidad a depositar: ");
                double deposito;
                try {
                    deposito = Double.parseDouble(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Monto inválido.");
                    continue;
                }
                cajero.depositar(usuarioActual, deposito);
                System.out.println("Depósito exitoso. Nuevo saldo: $" + cajero.consultarSaldo(usuarioActual));
            } else if (opcion == 4) {
                salir = true;
                System.out.println("Gracias por usar el cajero.");
            } else {
                System.out.println("Opción inválida.");
            }
        }

        scanner.close();
    }
}