package View;

import Controller.sistemaController;
import Model.*;

import java.util.List;
import java.util.Scanner;

public class SistemaInventario {

    private static final Scanner scanner = new Scanner(System.in);
    private static sistemaController controller;

    public static void main(String[] args) {
        // Inicializar Controller (sin repositorios)
        controller = new sistemaController();

        cargarDatos();

        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== SISTEMA DE INVENTARIO =====");
            System.out.println("1. Gestionar Productos");
            System.out.println("2. Gestionar Clientes");
            System.out.println("5. Guardar y Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    menuProductos();
                    break;
                case 2:
                    menuClientes();
                    break;
                case 5:
                    guardarDatos();
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static int leerEntero() {
        int numero;
        try {
            numero = Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
        return numero;
    }

    private static double leerDouble() {
        double numero;
        try {
            numero = Double.parseDouble(scanner.nextLine().trim());
        } catch (Exception e) {
            return Double.NaN;
        }
        return numero;
    }

    private static void guardarDatos() {
        // Punto de extensión para persistencia futura (archivo/BD)
    }

    private static void cargarDatos() {
        // Punto de extensión para carga de datos
    }

    static void menuProductos() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n----- GESTIÓN DE PRODUCTOS -----");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Listar Productos");
            System.out.println("5. Buscar Producto");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    modificarProducto();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    listarProductos();
                    break;
                case 5:
                    buscarProducto();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    static void agregarProducto() {
        System.out.println("\n--- AGREGAR NUEVO PRODUCTO ---");

        System.out.print("Código: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Precio: ");
        double precio = leerDouble();

        System.out.print("Cantidad: ");
        int cantidad = leerEntero();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine().trim();

        System.out.print("Fecha de Vencimiento (DD/MM/YYYY) o N/A: ");
        String fechaVencimiento = scanner.nextLine().trim();

        Producto p = new Producto(codigo, nombre, precio, cantidad, categoria, fechaVencimiento);
        boolean ok = controller.agregarProducto(p);
        if (ok) {
            System.out.println("Producto agregado con éxito.");
        } else {
            System.out.println("Error: El código ya existe. No se puede agregar el producto.");
        }
    }

    static void modificarProducto() {
        System.out.println("\n--- MODIFICAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a modificar: ");
        String codigo = scanner.nextLine().trim();

        Producto prod = controller.buscarProductoPorCodigo(codigo);
        if (prod == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.println("Producto encontrado: " + prod.getNombre());
        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");

        System.out.print("Nombre [" + prod.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) prod.setNombre(nombre);

        System.out.print("Precio [" + prod.getPrecio() + "]: ");
        String precioStr = scanner.nextLine();
        if (!precioStr.isEmpty()) {
            try { prod.setPrecio(Double.parseDouble(precioStr)); } catch (NumberFormatException ignored) {}
        }

        System.out.print("Cantidad [" + prod.getCantidad() + "]: ");
        String cantidadStr = scanner.nextLine();
        if (!cantidadStr.isEmpty()) {
            try { prod.setCantidad(Integer.parseInt(cantidadStr)); } catch (NumberFormatException ignored) {}
        }

        System.out.print("Categoría [" + prod.getCategoria() + "]: ");
        String categoria = scanner.nextLine();
        if (!categoria.isEmpty()) prod.setCategoria(categoria);

        System.out.print("Fecha de Vencimiento [" + prod.getFechaVencimiento() + "]: ");
        String fechaVencimiento = scanner.nextLine();
        if (!fechaVencimiento.isEmpty()) prod.setFechaVencimiento(fechaVencimiento);

        boolean ok = controller.modificarProducto(prod);
        if (ok) System.out.println("Producto modificado con éxito.");
        else System.out.println("No fue posible modificar el producto.");
    }

    static void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine().trim();

        Producto prod = controller.buscarProductoPorCodigo(codigo);
        if (prod == null) {
            System.out.println("Producto no encontrado.");
            return;
        }
        System.out.println("Producto encontrado: " + prod.getNombre());
        System.out.print("¿Está seguro que desea eliminar este producto? (S/N): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean ok = controller.eliminarProducto(codigo);
            if (ok) System.out.println("Producto eliminado con éxito.");
            else System.out.println("No se pudo eliminar el producto.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    static void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        System.out.println("Código\tNombre\t\t\tPrecio\tCantidad\tCategoría\tVencimiento");
        System.out.println("--------------------------------------------------------------------------------");

        List<Producto> productos = controller.listarProductos();
        for (Producto p : productos) {
            System.out.printf("%-7s %-20s %.2f\t%-10d %-15s %s\n",
                    p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCantidad(), p.getCategoria(), p.getFechaVencimiento());
        }

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        }
    }

    static void buscarProducto() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.println("1. Buscar por Código");
        System.out.println("2. Buscar por Nombre");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el código a buscar: ");
                String codigo = scanner.nextLine().trim();

                Producto prod = controller.buscarProductoPorCodigo(codigo);
                if (prod != null) {
                    mostrarDetalleProducto(prod);
                } else {
                    System.out.println("Producto no encontrado.");
                }
                break;

            case 2:
                System.out.print("Ingrese el nombre a buscar: ");
                String nombre = scanner.nextLine().toLowerCase();

                List<Producto> encontrados = controller.buscarProductoPorNombre(nombre);
                if (encontrados.isEmpty()) {
                    System.out.println("No se encontraron productos con ese nombre.");
                } else {
                    for (Producto p : encontrados) {
                        mostrarDetalleProducto(p);
                    }
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    static void mostrarDetalleProducto(Producto p) {
        System.out.println("\nDetalle del Producto:");
        System.out.println("Código: " + p.getCodigo());
        System.out.println("Nombre: " + p.getNombre());
        System.out.println("Precio: $" + p.getPrecio());
        System.out.println("Cantidad en stock: " + p.getCantidad());
        System.out.println("Categoría: " + p.getCategoria());
        System.out.println("Fecha de Vencimiento: " + p.getFechaVencimiento());
    }

    static void menuClientes() {
        boolean volver = false;

        while (!volver) {
            System.out.println("\n----- GESTIÓN DE CLIENTES -----");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Eliminar Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Buscar Cliente");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerEntero();

            switch (opcion) {
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    modificarCliente();
                    break;
                case 3:
                    eliminarCliente();
                    break;
                case 4:
                    listarClientes();
                    break;
                case 5:
                    buscarCliente();
                    break;
                case 6:
                    volver = true;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    static void agregarCliente() {
        System.out.println("\n--- AGREGAR NUEVO CLIENTE ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine().trim();

        System.out.print("Saldo inicial: ");
        double saldo = leerDouble();

        Cliente c = controller.crearCliente(nombre, email, telefono, saldo);
        System.out.println("Cliente agregado con éxito. ID asignado: " + c.getIdValue());
    }

    static void modificarCliente() {
        System.out.println("\n--- MODIFICAR CLIENTE ---");
        System.out.print("Ingrese el ID de cliente a modificar: ");
        int id = leerEntero();

        Cliente cliente = controller.buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        System.out.println("Cliente encontrado: " + cliente.getNombre());
        System.out.println("Ingrese los nuevos datos (deje en blanco para mantener el valor actual):");

        System.out.print("Nombre [" + cliente.getNombre() + "]: ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) cliente.setNombre(nombre);

        System.out.print("Email [" + cliente.getEmail() + "]: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) cliente.setEmail(email);

        System.out.print("Teléfono [" + cliente.getTelefono() + "]: ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) cliente.setTelefono(telefono);

        System.out.print("Saldo [" + cliente.getSaldo() + "]: ");
        String saldoStr = scanner.nextLine();
        if (!saldoStr.isEmpty()) {
            try { cliente.setSaldo(Double.parseDouble(saldoStr)); } catch (NumberFormatException ignored) {}
        }

        boolean ok = controller.modificarCliente(cliente);
        if (ok) System.out.println("Cliente modificado con éxito.");
        else System.out.println("No fue posible modificar el cliente.");
    }

    static void eliminarCliente() {
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        System.out.print("Ingrese el ID de cliente a eliminar: ");
        int id = leerEntero();

        Cliente cliente = controller.buscarClientePorId(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        System.out.println("Cliente encontrado: " + cliente.getNombre());
        System.out.print("¿Está seguro que desea eliminar este cliente? (S/N): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S")) {
            boolean ok = controller.eliminarCliente(id);
            if (ok) System.out.println("Cliente eliminado con éxito.");
            else System.out.println("No se pudo eliminar el cliente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        System.out.println("ID\tNombre\t\t\tEmail\t\t\tTeléfono\tSaldo");
        System.out.println("--------------------------------------------------------------------------------");

        List<Cliente> clientes = controller.listarClientes();
        for (Cliente c : clientes) {
            System.out.printf("%-3d %-20s %-20s %-15s $%.2f\n",
                    c.getIdValue(), c.getNombre(), c.getEmail(), c.getTelefono(), c.getSaldo());
        }

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        }
    }

    static void buscarCliente() {
        System.out.println("\n--- BUSCAR CLIENTE ---");
        System.out.println("1. Buscar por Nombre");
        System.out.println("2. Buscar por Email");
        System.out.print("Seleccione una opción: ");

        int opcion = leerEntero();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese el nombre a buscar: ");
                String nombre = scanner.nextLine().toLowerCase();

                List<Cliente> byNombre = controller.buscarClientePorNombre(nombre);
                if (byNombre.isEmpty()) {
                    System.out.println("No se encontraron clientes con ese nombre.");
                } else {
                    for (Cliente c : byNombre) {
                        mostrarDetalleCliente(c);
                    }
                }
                break;

            case 2:
                System.out.print("Ingrese el email a buscar: ");
                String email = scanner.nextLine().toLowerCase();

                List<Cliente> byEmail = controller.buscarClientePorEmail(email);
                if (byEmail.isEmpty()) {
                    System.out.println("No se encontraron clientes con ese email.");
                } else {
                    for (Cliente c : byEmail) {
                        mostrarDetalleCliente(c);
                    }
                }
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    static void mostrarDetalleCliente(Cliente c) {
        System.out.println("\nDetalle del Cliente:");
        System.out.println("ID: " + c.getIdValue());
        System.out.println("Nombre: " + c.getNombre());
        System.out.println("Email: " + c.getEmail());
        System.out.println("Teléfono: " + c.getTelefono());
        System.out.println("Saldo: $" + c.getSaldo());
    }

}