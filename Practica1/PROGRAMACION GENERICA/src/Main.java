// CLASE GENERICA CON UN PARAMETRO
//class Caja <T> {
//    private T contenido ;
//    public Caja(T contenido){
//        this.contenido = contenido;
//    }
//    public T getContenido() {
//        return contenido;
//    }
//    public void setContenido(T contenido) {
//        this.contenido = contenido;
//    }
//}

//USO DE LA CLASE GENERICA
//public class Main{
//    public static void main(String[] args) {
//        Caja <String> cajaTexto = new Caja<>("Hola Mundo");
//        System.out.println(cajaTexto.getContenido());

//        Caja <Integer> cajaNumero = new Caja <> (42);
//        System.out.println(cajaNumero.getContenido());
//    }
//}

/*class Utilidad{
    public static <T> void imprimir (T elemento){
        System.out.println(elemento);
    }
}
public class Main {
    public static void main(String[] args) {
        Utilidad.imprimir("Texto Generico");
        Utilidad.imprimir(123);
        Utilidad.imprimir(3.14);
    }
}*/
//Interface Generica
/*interface Operation<T>{
    T ejecutar (T a, T b);
}
//Implementacion de la interfaz con enteros
class Suma implements Operation<Integer>{
    @Override
    public Integer ejecutar (Integer a, Integer b) {
        return a + b;
    }
}
public class Main {
    public static void main(String[] args) {
        Suma suma = new Suma();
        System.out.println(suma.ejecutar(10, 5)); //15

    }
}*/
/*class Calculadora < T extends Number>{
    private T numero;

    public Calculadora(T numero) {
        this.numero = numero;
    }
    public double getDoble() {
        return numero.doubleValue() * 2;
    }
}
public class Main {
    public static void main(String[] args) {
        Calculadora<Integer> calc1 = new Calculadora<>(10);
        System.out.println(calc1.getDoble()); // 20.0

        Calculadora<Double> calc2 = new Calculadora<>(5.5);
        System.out.println(calc2.getDoble()); // 11.0
    }
}*/
/*import java.util.ArrayList;
import java.util.List;
class Util {
    public static void imprimirLista (List<?> lista){
        for (Object elemento : lista){
            System.out.println(elemento);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3);
        List<String> palabras = List.of("Java", "Genericos");

        Util.imprimirLista(numeros);
        Util.imprimirLista(palabras);
    }
}*/

/*class Par <K, V> {
    private K clave;
    private V valor;
    public Par(K clave, V valor) {
        this.clave = clave;
        this.valor = valor;
    }
    public K getClave() {
        return clave;
    }
    public V getValor() {
        return valor;
    }
    public class GenericE1{
        public static void main(String[] args) {
            Par<String, Integer> edadPersona = new Par <>("Carlos", 25);
            System.out.println(edadPersona.getClave() + " tiene "+edadPersona.getValor()+" años ");
        }
    }
}*/
// Método genérico simple
public class Main {
    public static <T> void imprimir(T dato) {
        System.out.println(dato);
    }

    public static void main(String[] args) {
        imprimir("Hola Genéricos"); // imprime un texto
        imprimir(100);              // imprime un número
        imprimir(3.14);             // imprime un decimal
    }
}

