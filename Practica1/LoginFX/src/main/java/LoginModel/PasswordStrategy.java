package LoginModel;


public interface PasswordStrategy{
    String hash(String password);
    boolean verificar(String password, String hashAlmacenado);
}