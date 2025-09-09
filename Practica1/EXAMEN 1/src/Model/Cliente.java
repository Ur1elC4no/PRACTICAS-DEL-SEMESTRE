package Model;

public class Cliente {
    private Integer id; // ID simple
    private String nombre;
    private String email;
    private String telefono;
    private double saldo;

    public Cliente(Integer id, String nombre, String email, String telefono, double saldo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public Integer getIdValue() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }
}