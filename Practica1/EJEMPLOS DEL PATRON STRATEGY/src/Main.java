// Estrategia
/*interface MetodoPago { void pagar(double monto); }

// Estrategias concretas
class PagoTarjeta implements MetodoPago {
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " con Tarjeta");
    }
}
class PagoPayPal implements MetodoPago {
    public void pagar(double monto) {
        System.out.println("Pagando $" + monto + " con PayPal");
    }
}

// Contexto
class Carrito {
    private MetodoPago metodo;
    public void setMetodo(MetodoPago m) { metodo = m; }
    public void checkout(double monto) { metodo.pagar(monto); }
}

// Uso
public class Main {
    public static void main(String[] args) {
        Carrito c = new Carrito();
        c.setMetodo(new PagoTarjeta()); c.checkout(500);
        c.setMetodo(new PagoPayPal());  c.checkout(300);
    }
}
*/
// Estrategia
interface Descuento {
    double aplicar(double precio);
}

// Estrategias concretas
class DescuentoNavidad implements Descuento {
    public double aplicar(double precio) {
        return precio * 0.8; // 20% de descuento
    }
}

class DescuentoAniversario implements Descuento {
    public double aplicar(double precio) {
        return precio - 50; // $50 de descuento
    }
}

// Contexto
class Venta {
    private Descuento descuento;
    public void setDescuento(Descuento d) { descuento = d; }
    public void mostrarPrecio(double precio) {
        System.out.println("Precio final: $" + descuento.aplicar(precio));
    }
}

// Uso
public class Main {
    public static void main(String[] args) {
        Venta v = new Venta();
        v.setDescuento(new DescuentoNavidad());
        v.mostrarPrecio(500); // Precio con descuento navideño

        v.setDescuento(new DescuentoAniversario());
        v.mostrarPrecio(500); // Precio con descuento de aniversario
    }
}
