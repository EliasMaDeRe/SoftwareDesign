package DTOs;

public class anadirClienteDTO {
    
    private String id;
    private String nombreTitular;
    private String numeroCuenta;
    private String saldo;

    public anadirClienteDTO(String id, String nombreTitular, String numeroCuenta, String saldo){

        this.id = id;
        this.nombreTitular = nombreTitular;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;

    }

    public String getId() {
        return id;
    }
    public String getNombreTitular() {
        return nombreTitular;
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public String getSaldo() {
        return saldo;
    }

}
