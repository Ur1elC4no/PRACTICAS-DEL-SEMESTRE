package Controller;

import Model.*;
import java.util.ArrayList;
import java.util.List;

public class sistemaController {
    private final List<Producto> productos = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
    private int nextClienteId = 1;

    public sistemaController() {}

    // PRODUCTOS (métodos simples con bucles básicos)
    public boolean agregarProducto(Producto p) {
        // validar duplicado por código
        if (buscarProductoPorCodigo(p.getCodigo()) != null) {
            return false;
        }
        productos.add(p);
        return true;
    }

    public boolean modificarProducto(Producto p) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(p.getCodigo())) {
                productos.set(i, p);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(codigo)) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public Producto buscarProductoPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) return p;
        }
        return null;
    }

    public List<Producto> buscarProductoPorNombre(String nombre) {
        List<Producto> resultado = new ArrayList<>();
        String n = nombre.toLowerCase();
        for (Producto p : productos) {
            if (p.getNombre() != null && p.getNombre().toLowerCase().contains(n)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // CLIENTES (IDs sencillos, sin ordenamientos ni streams)
    public Cliente crearCliente(String nombre, String email, String telefono, double saldo) {
        Cliente c = new Cliente(nextClienteId, nombre, email, telefono, saldo);
        clientes.add(c);
        nextClienteId++;
        return c;
    }

    public boolean agregarCliente(Cliente c) {
        if (c.getIdValue() == null) {
            c.setId(nextClienteId);
        }
        // verificar duplicado por ID
        if (buscarClientePorId(c.getIdValue()) != null) {
            return false;
        }
        clientes.add(c);
        if (c.getIdValue() >= nextClienteId) {
            nextClienteId = c.getIdValue() + 1;
        }
        return true;
    }

    public boolean modificarCliente(Cliente c) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdValue().equals(c.getIdValue())) {
                clientes.set(i, c);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarCliente(Integer id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdValue().equals(id)) {
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }

    public Cliente buscarClientePorId(Integer id) {
        for (Cliente c : clientes) {
            if (c.getIdValue().equals(id)) return c;
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public List<Cliente> buscarClientePorNombre(String nombre) {
        List<Cliente> resultado = new ArrayList<>();
        String n = nombre.toLowerCase();
        for (Cliente c : clientes) {
            if (c.getNombre() != null && c.getNombre().toLowerCase().contains(n)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cliente> buscarClientePorEmail(String email) {
        List<Cliente> resultado = new ArrayList<>();
        String e = email.toLowerCase();
        for (Cliente c : clientes) {
            if (c.getEmail() != null && c.getEmail().toLowerCase().contains(e)) {
                resultado.add(c);
            }
        }
        return resultado;
    }
}