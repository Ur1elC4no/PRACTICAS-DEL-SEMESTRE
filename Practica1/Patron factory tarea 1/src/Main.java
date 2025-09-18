import java.util.*;

/*
 * DEMO: MVC + Genéricos + Patrón Factory en un solo archivo, simple y entendible.
 * 
 * Estructura (todo en este archivo para compilar fácil):
 * - MODELO: Identifiable, Product, Customer
 * - REPOSITORIO (GENÉRICO): Repository<T, ID>, InMemoryRepository<T, ID>
 * - FACTORY: RepositoryFactory (crea repositorios según el modelo)
 * - VISTA: View<T>, ConsoleView<T>
 * - CONTROLADOR (GENÉRICO): GenericController<T, ID>
 * - MAIN: Demostración con Product y Customer
 */

/* ==========================
 *        MODELO
 * ==========================
 */
interface Identifiable<ID> {
    ID getId();
}

class Product implements Identifiable<Integer> {
    private final Integer id;
    private String name;
    private double price;

    public Product(Integer id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class Customer implements Identifiable<Integer> {
    private final Integer id;
    private String fullName;

    public Customer(Integer id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    @Override
    public Integer getId() { return id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

/* ==========================
 *       REPOSITORIO
 *   (Programación Genérica)
 * ==========================
 */
interface Repository<T extends Identifiable<ID>, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(ID id);
}

class InMemoryRepository<T extends Identifiable<ID>, ID> implements Repository<T, ID> {
    private final Map<ID, T> storage = new HashMap<>();

    @Override
    public T save(T entity) {
        storage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(ID id) {
        storage.remove(id);
    }
}

/* ==========================
 *          FACTORY
 *  (Crea repositorios según el modelo)
 * ==========================
 */
class RepositoryFactory {
    public static <T extends Identifiable<ID>, ID> Repository<T, ID> createRepository(Class<T> modelClass) {
        // Aquí podrías decidir diferentes tipos de repositorio según el modelo o configuración.
        // Para mantenerlo simple y entendible, devolvemos un repositorio en memoria.
        System.out.println("Factory: creando repositorio en memoria para " + modelClass.getSimpleName());
        return new InMemoryRepository<>();
    }
}

/* ==========================
 *           VISTA
 * ==========================
 */
interface View<T> {
    void showList(List<T> items);
    void showItem(T item);
    void showMessage(String message);
}

class ConsoleView<T> implements View<T> {
    private final String title;

    public ConsoleView(String title) {
        this.title = title;
    }

    @Override
    public void showList(List<T> items) {
        System.out.println("==== " + title + " (lista) ====");
        if (items.isEmpty()) {
            System.out.println("(sin elementos)");
        } else {
            items.forEach(System.out::println);
        }
        System.out.println();
    }

    @Override
    public void showItem(T item) {
        System.out.println("==== " + title + " (detalle) ====");
        System.out.println(item);
        System.out.println();
    }

    @Override
    public void showMessage(String message) {
        System.out.println("[" + title + "] " + message);
    }
}

/* ==========================
 *        CONTROLADOR
 *      (Programación Genérica)
 * ==========================
 */
class GenericController<T extends Identifiable<ID>, ID> {
    private final Repository<T, ID> repository;
    private final View<T> view;

    public GenericController(Repository<T, ID> repository, View<T> view) {
        this.repository = repository;
        this.view = view;
    }

    public void crear(T entity) {
        repository.save(entity);
        view.showMessage("Creado: " + entity);
    }

    public void listar() {
        view.showList(repository.findAll());
    }

    public void mostrar(ID id) {
        repository.findById(id)
                  .ifPresentOrElse(view::showItem,
                          () -> view.showMessage("No se encontró el elemento con id=" + id));
    }

    public void eliminar(ID id) {
        repository.delete(id);
        view.showMessage("Eliminado id=" + id);
    }
}

/* ==========================
 *            MAIN
 *    (Demostración de uso)
 * ==========================
 */
public class Main {
    public static void main(String[] args) {
        // PRODUCTOS
        View<Product> productView = new ConsoleView<>("PRODUCTOS");
        Repository<Product, Integer> productRepo = RepositoryFactory.createRepository(Product.class);
        GenericController<Product, Integer> productController = new GenericController<>(productRepo, productView);

        productController.crear(new Product(1, "Teclado", 25.99));
        productController.crear(new Product(2, "Mouse", 15.49));
        productController.listar();
        productController.mostrar(1);
        productController.eliminar(2);
        productController.listar();

        // CLIENTES
        View<Customer> customerView = new ConsoleView<>("CLIENTES");
        Repository<Customer, Integer> customerRepo = RepositoryFactory.createRepository(Customer.class);
        GenericController<Customer, Integer> customerController = new GenericController<>(customerRepo, customerView);

        customerController.crear(new Customer(100, "Ana Pérez"));
        customerController.crear(new Customer(101, "Luis Gómez"));
        customerController.listar();
        customerController.mostrar(101);
    }
}
