/*
 Demostración del Patrón Factory en Java.
 
 Este archivo define y demuestra:
 - interface Vehiculo: contrato común para distintos tipos de vehículos.
 - clases concretas Coche, Motocicleta, Camion y Bicicleta: implementaciones de Vehiculo.
 - enum TipoVehiculo: tipos soportados por la fábrica.
 - clase VehiculoFactory: encapsula la lógica de creación (Factory Method estático).
 - clase FactoryPatternDemo: punto de entrada que ejecuta una demostración paso a paso.
 
 Todas las explicaciones están documentadas usando comentarios de bloque (/* */).
*/

interface Vehiculo {
    /* Método de comportamiento: acelera el vehículo (incrementa su velocidad o simula el inicio del movimiento). */
    void acelerar();
    /* Método de comportamiento: desacelera o detiene gradualmente el vehículo. */
    void frenar();
    /* Método utilitario: imprime información relevante del vehículo (identidad y características clave). */
    void mostrarInfo();
}

/* Implementación de Vehiculo que representa un coche (automóvil de 4 ruedas). */
class Coche implements Vehiculo {
    /* Marca del coche (fabricante). */
    private String marca;
    /* Modelo del coche (línea o versión específica). */
    private String modelo;

    /* Constructor: inicializa el coche con su marca y modelo. */
    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    /* Implementación específica de acelerar para un coche. */
    @Override
    public void acelerar() {
        System.out.println("El coche " + marca + " " + modelo + " está acelerando por la carretera");
    }

    /* Implementación específica de frenar para un coche. */
    @Override
    public void frenar() {
        System.out.println("El coche está frenando con frenos de disco");
    }

    /* Muestra la información descriptiva del coche. */
    @Override
    public void mostrarInfo() {
        System.out.println("🚗 Coche: " + marca + " " + modelo + " - 4 ruedas, motor a gasolina");
    }
}

/* Implementación de Vehiculo que representa una motocicleta (2 ruedas). */
class Motocicleta implements Vehiculo {
    /* Marca del fabricante de la motocicleta. */
    private String marca;
    /* Capacidad de cilindrada del motor en centímetros cúbicos (cc). */
    private int cilindrada;

    /* Constructor: inicializa la motocicleta con marca y cilindrada. */
    public Motocicleta(String marca, int cilindrada) {
        this.marca = marca;
        this.cilindrada = cilindrada;
    }

    /* Implementación específica de acelerar para una motocicleta. */
    @Override
    public void acelerar() {
        System.out.println("La motocicleta " + marca + " está acelerando con " + cilindrada + "cc");
    }

    /* Implementación específica de frenar para una motocicleta. */
    @Override
    public void frenar() {
        System.out.println("La motocicleta está frenando con precaución");
    }

    /* Muestra la información descriptiva de la motocicleta. */
    @Override
    public void mostrarInfo() {
        System.out.println("🏍️ Motocicleta: " + marca + " " + cilindrada + "cc - 2 ruedas, ágil y rápida");
    }
}

/* Implementación de Vehiculo que representa un camión (vehículo de carga). */
class Camion implements Vehiculo {
    /* Marca del camión. */
    private String marca;
    /* Capacidad de carga en toneladas. */
    private double capacidadCarga;

    /* Constructor: inicializa el camión con marca y capacidad de carga. */
    public Camion(String marca, double capacidadCarga) {
        this.marca = marca;
        this.capacidadCarga = capacidadCarga;
    }

    /* Implementación específica de acelerar para un camión (más lenta por su peso). */
    @Override
    public void acelerar() {
        System.out.println("El camión " + marca + " está acelerando lentamente con " + capacidadCarga + " toneladas");
    }

    /* Implementación específica de frenar para un camión (sistema neumático). */
    @Override
    public void frenar() {
        System.out.println("El camión está frenando con sistema de frenos neumático");
    }

    /* Muestra la información descriptiva del camión. */
    @Override
    public void mostrarInfo() {
        System.out.println("🚛 Camión: " + marca + " - Capacidad: " + capacidadCarga + " toneladas");
    }
}

/* Enumeración que define los tipos de vehículos soportados por la fábrica. */
enum TipoVehiculo {
    COCHE, MOTOCICLETA, CAMION
}

/* Clase fábrica que centraliza la creación de objetos Vehiculo según el tipo solicitado. */
class VehiculoFactory {

    /*
     Método de fábrica principal: crea instancias de Vehiculo en función de 'tipo'.
     - parametros es un arreglo variable (varargs) que contiene los datos necesarios para construir cada tipo:
       * COCHE: [marca, modelo]
       * MOTOCICLETA: [marca, cilindradaComoString]
       * CAMION: [marca, capacidadComoString]
     - Si faltan parámetros o hay errores de formato numérico, se aplican valores por defecto seguros.
    */
    public static Vehiculo crearVehiculo(TipoVehiculo tipo, String... parametros) {
        switch (tipo) {
            case COCHE:
                /* Crea un Coche con marca y modelo; usa valores por defecto si faltan. */
                if (parametros.length >= 2) {
                    return new Coche(parametros[0], parametros[1]);
                } else {
                    return new Coche("Toyota", "Corolla");
                }

            case MOTOCICLETA:
                /* Crea una Motocicleta con marca y cilindrada; intenta parsear la cilindrada. */
                if (parametros.length >= 2) {
                    try {
                        int cilindrada = Integer.parseInt(parametros[1]);
                        return new Motocicleta(parametros[0], cilindrada);
                    } catch (NumberFormatException e) {
                        /* Si la cilindrada no es un número válido, usa 250cc por defecto. */
                        return new Motocicleta(parametros[0], 250);
                    }
                } else {
                    return new Motocicleta("Yamaha", 250);
                }

            case CAMION:
                /* Crea un Camión con marca y capacidad; intenta parsear la capacidad (toneladas). */
                if (parametros.length >= 2) {
                    try {
                        double capacidad = Double.parseDouble(parametros[1]);
                        return new Camion(parametros[0], capacidad);
                    } catch (NumberFormatException e) {
                        /* Si la capacidad no es válida, usa 10.0 toneladas por defecto. */
                        return new Camion(parametros[0], 10.0);
                    }
                } else {
                    return new Camion("Volvo", 15.0);
                }

            default:
                /* Protección ante tipos no soportados. */
                throw new IllegalArgumentException("Tipo de vehículo no soportado: " + tipo);
        }
    }

    /* Método de conveniencia: crea un vehículo del tipo dado con parámetros por defecto. */
    public static Vehiculo crearVehiculoPorDefecto(TipoVehiculo tipo) {
        return crearVehiculo(tipo);
    }
}

/* Clase principal con el método main: ejecuta la demostración del patrón Factory. */
public class FactoryPatternDemo {

    /* Punto de entrada del programa: orquesta la creación y uso de vehículos. */
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN FACTORY ===\n");

        /* Creación explícita de instancias usando la fábrica con parámetros específicos. */
        Vehiculo coche1 = VehiculoFactory.crearVehiculo(TipoVehiculo.COCHE, "Honda", "Civic");
        Vehiculo moto1 = VehiculoFactory.crearVehiculo(TipoVehiculo.MOTOCICLETA, "Kawasaki", "600");
        Vehiculo camion1 = VehiculoFactory.crearVehiculo(TipoVehiculo.CAMION, "Mercedes", "12.5");

        /* Creación usando valores por defecto para cada tipo. */
        Vehiculo coche2 = VehiculoFactory.crearVehiculoPorDefecto(TipoVehiculo.COCHE);
        Vehiculo moto2 = VehiculoFactory.crearVehiculoPorDefecto(TipoVehiculo.MOTOCICLETA);

        /* Colección heterogénea que demuestra el polimorfismo de la interfaz Vehiculo. */
        Vehiculo[] vehiculos = {coche1, moto1, camion1, coche2, moto2};

        /* Sección 1: Mostrar información de cada vehículo. */
        System.out.println("1. INFORMACIÓN DE VEHÍCULOS:");
        System.out.println("----------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
        }

        /* Sección 2: Acelerar todos los vehículos. */
        System.out.println("\n2. ACELERANDO TODOS LOS VEHÍCULOS:");
        System.out.println("----------------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.acelerar();
        }

        /* Sección 3: Frenar todos los vehículos. */
        System.out.println("\n3. FRENANDO TODOS LOS VEHÍCULOS:");
        System.out.println("--------------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.frenar();
        }

        /* Sección 4: Ejemplo de creación dinámica basada en entrada de texto. */
        System.out.println("\n4. CREACIÓN DINÁMICA BASADA EN INPUT:");
        System.out.println("-------------------------------------");

        /* Entradas de ejemplo que simulan valores capturados desde el exterior (e.g., UI/CLI). */
        String[] tiposInput = {"COCHE", "MOTOCICLETA", "CAMION"};

        for (String tipoStr : tiposInput) {
            try {
                /* Convertir el texto al valor del enum y crear una instancia por defecto. */
                TipoVehiculo tipo = TipoVehiculo.valueOf(tipoStr);
                Vehiculo vehiculo = VehiculoFactory.crearVehiculoPorDefecto(tipo);
                System.out.print("Creado dinámicamente: ");
                vehiculo.mostrarInfo();
            } catch (IllegalArgumentException e) {
                /* Captura entradas inválidas que no correspondan al enum. */
                System.out.println("Tipo de vehículo no válido: " + tipoStr);
            }
        }

        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
}

/* Implementación adicional de Vehiculo que representa una bicicleta (propulsión humana).
   Nota: No se utiliza en la demo anterior, pero muestra cómo añadir nuevos tipos sin
   modificar la interfaz ni el código cliente, manteniendo abierto/cerrado (OCP). */
class Bicicleta implements Vehiculo {
    /* Tipo de bicicleta (por ejemplo, montaña, ruta, urbana). */
    private String tipo;
    /* Número de velocidades o relaciones del sistema de cambios. */
    private int numVelocidades;

    /* Constructor: inicializa la bicicleta con su tipo y número de velocidades. */
    public Bicicleta(String tipo, int numVelocidades) {
        this.tipo = tipo;
        this.numVelocidades = numVelocidades;
    }

    /* Implementación de acelerar: simula el pedaleo. */
    @Override
    public void acelerar() {
        System.out.println("La bicicleta " + tipo + " está siendo pedaleada");
    }

    /* Implementación de frenar: simula el uso de frenos de mano. */
    @Override
    public void frenar() {
        System.out.println("La bicicleta está frenando con frenos de mano");
    }

    /* Muestra la información descriptiva de la bicicleta. */
    @Override
    public void mostrarInfo() {
        System.out.println("🚲 Bicicleta: " + tipo + " - " + numVelocidades + " velocidades, ecológica");
    }
}
