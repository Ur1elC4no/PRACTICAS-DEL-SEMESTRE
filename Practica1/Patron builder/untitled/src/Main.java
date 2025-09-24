
// Clase Producto
class Computadora {
    private String procesador;
    private int ram;

    private String tarjetaGrafica;
    private String almacenamiento;

    // Constructor privado (solo Builder puede acceder)
    private Computadora(Builder builder) {
        this.procesador = builder.procesador;
        this.ram = builder.ram;
        this.tarjetaGrafica = builder.tarjetaGrafica;
        this.almacenamiento = builder.almacenamiento;
    }

    @Override
    public String toString() {
        return "Computadora: [Procesador=" + procesador +
                ", RAM=" + ram + "GB, Tarjeta=" + tarjetaGrafica +
                ", Almacenamiento=" + almacenamiento + "]";
    }

    // Clase estática Builder
    public static class Builder {
        private String procesador;
        private int ram;
        private String tarjetaGrafica;
        private String almacenamiento;

        public Builder setProcesador(String procesador) {
            this.procesador = procesador;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setTarjetaGrafica(String tarjetaGrafica) {
            this.tarjetaGrafica = tarjetaGrafica;
            return this;
        }

        public Builder setAlmacenamiento(String almacenamiento) {
            this.almacenamiento = almacenamiento;
            return this;
        }

        public Computadora build() {
            return new Computadora(this);
        }
    }
}

// Ejemplo de uso
public class Main {
    public static void main(String[] args) {
        Computadora gamer = new Computadora.Builder()
                .setProcesador("Intel i9")
                .setRam(32)
                .setTarjetaGrafica("NVIDIA RTX 4080")
                .setAlmacenamiento("1TB SSD")
                .build();

        Computadora oficina = new Computadora.Builder()
                .setProcesador("Intel i5")
                .setRam(8)
                .setAlmacenamiento("512GB SSD")
                .build();

        System.out.println(gamer);
        System.out.println(oficina);
    }
}

/*
 Clase que representa una Pizza con diferentes configuraciones.
 Se utiliza junto con el patrón Builder para construir objetos paso a paso.
 */

//class Pizza {
  //  private final String tamaño;
    //private final String masa;
    //private final boolean extraQueso;
    //private final boolean pepperoni;
    //private final boolean champiñones;

    /*
     Constructor privado que recibe el Builder.
     Solo el Builder puede crear instancias de Pizza.
     Objeto Builder con la configuración de la Pizza.
     */
 /*   private Pizza(Builder builder) {
        this.tamaño = builder.tamaño;
        this.masa = builder.masa;
        this.extraQueso = builder.extraQueso;
        this.pepperoni = builder.pepperoni;
        this.champiñones = builder.champiñones;
    }

    @Override
    public String toString() {
        return "Pizza [Tamaño=" + tamaño + ", Masa=" + masa +
                ", Extra Queso=" + extraQueso +
                ", Pepperoni=" + pepperoni +
                ", Champiñones=" + champiñones + "]";
    }

    /*
     Clase estática Builder que permite construir la Pizza paso a paso.
     */
 /*   public static class Builder {
        private String tamaño;
        private String masa;
        private boolean extraQueso;
        private boolean pepperoni;
        private boolean champiñones;

        /*
         Define el tamaño de la pizza.
         El tamaño de la pizza (chica, mediana, grande).
         @return La instancia del Builder.
         */
  /*      public Builder setTamaño(String tamaño) {
            this.tamaño = tamaño;
            return this;
        }

        /*
         Define el tipo de masa de la pizza.
         @param masa Tipo de masa (delgada, gruesa).
         @return La instancia del Builder.
         */
  /*      public Builder setMasa(String masa) {
            this.masa = masa;
            return this;
        }

        /*
         Indica si la pizza lleva extra queso.
         @param extraQueso true si lleva extra queso, false en caso contrario.
         @return La instancia del Builder.
         */
   /*     public Builder setExtraQueso(boolean extraQueso) {
            this.extraQueso = extraQueso;
            return this;
        }

        /*
          Indica si la pizza lleva pepperoni.

          @param pepperoni true si lleva pepperoni, false en caso contrario.
          @return La instancia del Builder.
         */
  /*      public Builder setPepperoni(boolean pepperoni) {
            this.pepperoni = pepperoni;
            return this;
        }

        /*
         Indica si la pizza lleva champiñones.
         @param champiñones true si lleva champiñones, false en caso contrario.
         @return La instancia del Builder.
         */
  /*      public Builder setChampiñones(boolean champiñones) {
            this.champiñones = champiñones;
            return this;
        }

        /*
         Construye y devuelve la Pizza con la configuración definidos
         @return Objeto Pizza creado.
         */
   /*     public Pizza build() {
            return new Pizza(this);
        }
    }
}

/*
 Clase principal para probar el patrón Builder con Pizza.
 */
/*public class Main {
    public static void main(String[] args) {
        // Pizza personalizada para un cliente
        Pizza pizza1 = new Pizza.Builder()
                .setTamaño("Grande")
                .setMasa("Delgada")
                .setExtraQueso(true)
                .setPepperoni(true)
                .build();

        // Otra pizza con diferente configuración
        Pizza pizza2 = new Pizza.Builder()
                .setTamaño("Mediana")
                .setMasa("Gruesa")
                .setChampiñones(true)
                .build();

        System.out.println(pizza1);
        System.out.println(pizza2);
    }
}
*/