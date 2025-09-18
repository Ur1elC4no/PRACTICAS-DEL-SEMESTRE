/*
  Demostración del Patrón Factory en Java
  Este archivo contiene una implementación simple del patrón de diseño Factory.
  Permite crear distintos tipos de vehículos (Coche, Motocicleta, Camion) a través
  de una fábrica (VehiculoFactory) que encapsula la lógica de instanciación
  y validación de parámetros. Además, incluye una clase Bicicleta como ejemplo
  de cómo extender el sistema con nuevos tipos que implementen la misma interfaz.

  Estructura:
  - interface Vehiculo: Contrato común para todos los vehículos.
  - Clases concretas: Coche, Motocicleta, Camion, Bicicleta.
  - enum TipoVehiculo: Tipos soportados por la fábrica.
  - class VehiculoFactory: Punto único de creación de vehículos.
  - class FactoryPatternDemo (con main): Secuencia de demostración del patrón.

  Contrato que define las capacidades básicas que todo vehículo del sistema debe tener.
 */
interface Vehiculo {
    /*
     Simula la acción de acelerar del vehículo.
     */
    void acelerar();

    /*
      Simula la acción de frenar del vehículo.
     */
    void frenar();

    /*
     Muestra la información descriptiva del vehículo (tipo, características principales).
     */
    void mostrarInfo();
}

/*
  Implementación concreta de un vehículo tipo Coche.
  Encapsula la marca y el modelo, y expone comportamientos básicos.
 */
class Coche implements Vehiculo {
    /** Marca del coche (por ejemplo, "Toyota"). */
    private String marca;
    /** Modelo del coche (por ejemplo, "Corolla"). */
    private String modelo;

    /*
      Crea un nuevo coche.
      @param marca  marca del coche
      @param modelo modelo del coche
     */
    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    /*
      Mensaje representando la aceleración específica de un coche.
     */
    @Override
    public void acelerar() {
        System.out.println("El coche " + marca + " " + modelo + " está acelerando por la carretera");
    }

    /*
      Mensaje representando el frenado de un coche.
     */
    @Override
    public void frenar() {
        System.out.println("El coche está frenando con frenos de disco");
    }

    /*
      Muestra una descripción breve del coche.
     */
    @Override
    public void mostrarInfo() {
        System.out.println("🚗 Coche: " + marca + " " + modelo + " - 4 ruedas, motor a gasolina");
    }
}

/*
  Implementación concreta de un vehículo tipo Motocicleta.
  Encapsula marca y cilindrada (en cc).
 */
class Motocicleta implements Vehiculo {
    /* Marca de la motocicleta (por ejemplo, "Yamaha"). */
    private String marca;
    /* Cilindrada en centímetros cúbicos (por ejemplo, 250). */
    private int cilindrada;

    /*
      Crea una nueva motocicleta.
      @param marca       marca de la motocicleta
      @param cilindrada  cilindrada en cc
     */
    public Motocicleta(String marca, int cilindrada) {
        this.marca = marca;
        this.cilindrada = cilindrada;
    }

    /*
      Mensaje representando la aceleración de una motocicleta.
     */
    @Override
    public void acelerar() {
        System.out.println("La motocicleta " + marca + " está acelerando con " + cilindrada + "cc");
    }

    /*
      Mensaje representando el frenado de una motocicleta.
     */
    @Override
    public void frenar() {
        System.out.println("La motocicleta está frenando con precaución");
    }

    /*
      Muestra una descripción breve de la motocicleta.
     */
    @Override
    public void mostrarInfo() {
        System.out.println("🏍️ Motocicleta: " + marca + " " + cilindrada + "cc - 2 ruedas, ágil y rápida");
    }
}

/*
  Implementación concreta de un vehículo tipo Camion.
  Encapsula la marca y la capacidad de carga (en toneladas).
 */
class Camion implements Vehiculo {
    /* Marca del camión (por ejemplo, "Volvo"). */
    private String marca;
    /* Capacidad de carga en toneladas (por ejemplo, 15.0). */
    private double capacidadCarga;

    /*
      Crea un nuevo camión.
      @param marca           marca del camión
      @param capacidadCarga  capacidad en toneladas
     */
    public Camion(String marca, double capacidadCarga) {
        this.marca = marca;
        this.capacidadCarga = capacidadCarga;
    }

    /*
      Mensaje representando la aceleración de un camión (más lenta y pesada).
     */
    @Override
    public void acelerar() {
        System.out.println("El camión " + marca + " está acelerando lentamente con " + capacidadCarga + " toneladas");
    }

    /*
      Mensaje representando el frenado de un camión.
     */
    @Override
    public void frenar() {
        System.out.println("El camión está frenando con sistema de frenos neumático");
    }

    /*
      Muestra una descripción breve del camión.
     */
    @Override
    public void mostrarInfo() {
        System.out.println("🚛 Camión: " + marca + " - Capacidad: " + capacidadCarga + " toneladas");
    }
}

/*
  Enumeración con los tipos de vehículos soportados por la fábrica.
 */
enum TipoVehiculo {
    /** Coche de 4 ruedas. */
    COCHE,
    /** Motocicleta de 2 ruedas. */
    MOTOCICLETA,
    /** Camión de carga. */
    CAMION
}

/*
  Fábrica centralizada responsable de crear instancias de {@link Vehiculo} según su tipo.
  Encapsula la lógica de parseo/validación de parámetros y provee valores por defecto.
 */
class VehiculoFactory {

    /*
      Crea un vehículo del tipo indicado, a partir de parámetros variables (varargs).

      Convención de parámetros por tipo:
      - COCHE:        [0]=marca, [1]=modelo
      - MOTOCICLETA:  [0]=marca, [1]=cilindrada (int como String)
      - CAMION:       [0]=marca, [1]=capacidad (double como String)
      Si faltan parámetros o su formato no es válido, se usan valores por defecto razonables.
      @param tipo        tipo de vehículo a crear
      @param parametros  parámetros específicos del tipo (ver convención)
      @return instancia concreta de Vehiculo
      @throws IllegalArgumentException si el tipo no es soportado
     */
    public static Vehiculo crearVehiculo(TipoVehiculo tipo, String... parametros) {
        switch (tipo) {
            case COCHE:
                if (parametros.length >= 2) {
                    return new Coche(parametros[0], parametros[1]);
                } else {
                    // Valor por defecto
                    return new Coche("Toyota", "Corolla");
                }

            case MOTOCICLETA:
                if (parametros.length >= 2) {
                    try {
                        int cilindrada = Integer.parseInt(parametros[1]);
                        return new Motocicleta(parametros[0], cilindrada);
                    } catch (NumberFormatException e) {
                        // Si no se puede parsear, usar cilindrada por defecto
                        return new Motocicleta(parametros[0], 250);
                    }
                } else {
                    return new Motocicleta("Yamaha", 250);
                }

            case CAMION:
                if (parametros.length >= 2) {
                    try {
                        double capacidad = Double.parseDouble(parametros[1]);
                        return new Camion(parametros[0], capacidad);
                    } catch (NumberFormatException e) {
                        // Si no se puede parsear, usar capacidad por defecto
                        return new Camion(parametros[0], 10.0);
                    }
                } else {
                    return new Camion("Volvo", 15.0);
                }

            default:
                throw new IllegalArgumentException("Tipo de vehículo no soportado: " + tipo);
        }
    }

    /*
      Crea un vehículo del tipo indicado usando valores por defecto.
      @param tipo tipo de vehículo a crear
      @return instancia concreta con parámetros por defecto
     */
    public static Vehiculo crearVehiculoPorDefecto(TipoVehiculo tipo) {
        return crearVehiculo(tipo);
    }
}

/*
  Clase de arranque que demuestra el uso del patrón Factory para crear distintos vehículos.
 */
public class FactoryPatternDemo {

    /*
      Punto de entrada. Ejecuta una secuencia de pasos para demostrar:
      1) Mostrar información de diferentes vehículos
      2) Acelerar todos los vehículos
      3) Frenar todos los vehículos
      4) Crear vehículos dinámicamente a partir de una entrada de texto (simulada)
      @param args argumentos de línea de comandos (no usados)
     */
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DEL PATRÓN FACTORY ===\n");

        // 1) Creación explícita con parámetros
        Vehiculo coche1 = VehiculoFactory.crearVehiculo(TipoVehiculo.COCHE, "Honda", "Civic");
        Vehiculo moto1 = VehiculoFactory.crearVehiculo(TipoVehiculo.MOTOCICLETA, "Kawasaki", "600");
        Vehiculo camion1 = VehiculoFactory.crearVehiculo(TipoVehiculo.CAMION, "Mercedes", "12.5");

        // 2) Creación usando valores por defecto
        Vehiculo coche2 = VehiculoFactory.crearVehiculoPorDefecto(TipoVehiculo.COCHE);
        Vehiculo moto2 = VehiculoFactory.crearVehiculoPorDefecto(TipoVehiculo.MOTOCICLETA);

        // Conjunto de vehículos a operar
        Vehiculo[] vehiculos = {coche1, moto1, camion1, coche2, moto2};

        // Mostrar información
        System.out.println("1. INFORMACIÓN DE VEHÍCULOS:");
        System.out.println("----------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
        }

        // Acelerar
        System.out.println("\n2. ACELERANDO TODOS LOS VEHÍCULOS:");
        System.out.println("----------------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.acelerar();
        }

        // Frenar
        System.out.println("\n3. FRENANDO TODOS LOS VEHÍCULOS:");
        System.out.println("--------------------------------");
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.frenar();
        }

        // Demostración de creación dinámica a partir de texto (p.ej., input de usuario)
        System.out.println("\n4. CREACIÓN DINÁMICA BASADA EN INPUT:");
        System.out.println("-------------------------------------");

        String[] tiposInput = {"COCHE", "MOTOCICLETA", "CAMION"};

        for (String tipoStr : tiposInput) {
            try {
                TipoVehiculo tipo = TipoVehiculo.valueOf(tipoStr);
                Vehiculo vehiculo = VehiculoFactory.crearVehiculoPorDefecto(tipo);
                System.out.print("Creado dinámicamente: ");
                vehiculo.mostrarInfo();
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de vehículo no válido: " + tipoStr);
            }
        }

        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
}

/*
  Implementación concreta de un vehículo tipo Bicicleta.
  NOTA: Esta clase no está integrada en la fábrica ni en el enum.
  Se incluye como ejemplo de cómo añadir un nuevo tipo de vehículo
  que cumple el contrato sin modificar las clases existentes.
 */
class Bicicleta implements Vehiculo {
    /* Tipo de bicicleta (por ejemplo, "montaña", "urbana"). */
    private String tipo;
    /* Número de velocidades del sistema de cambios. */
    private int numVelocidades;

    /*
      Crea una nueva bicicleta.
      @param tipo            tipo de bicicleta
      @param numVelocidades  número de velocidades
     */
    public Bicicleta(String tipo, int numVelocidades) {
        this.tipo = tipo;
        this.numVelocidades = numVelocidades;
    }

    /*
      Mensaje representando el esfuerzo de pedaleo (aceleración humana).
     */
    @Override
    public void acelerar() {
        System.out.println("La bicicleta " + tipo + " está siendo pedaleada");
    }

    /*
      Mensaje representando el frenado de una bicicleta.
     */
    @Override
    public void frenar() {
        System.out.println("La bicicleta está frenando con frenos de mano");
    }

    /*
      Muestra una descripción breve de la bicicleta.
     */
    @Override
    public void mostrarInfo() {
        System.out.println("🚲 Bicicleta: " + tipo + " - " + numVelocidades + " velocidades, ecológica");
    }
}
