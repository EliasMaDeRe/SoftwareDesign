package DTOs;
public class borrarCuentaDTO {

    private String nombreTitular;
    private String numeroCuenta;
    private String id;

    public borrarCuentaDTO(String nombreTitular, String numeroCuenta, String id){

        this.nombreTitular = nombreTitular;
        this.numeroCuenta = numeroCuenta;
        this.id = id;

    }

    public String getNombreTitular(){

        return nombreTitular;

    }

    public String getNumeroCuenta(){

        return numeroCuenta;

    }

    public String getID(){

        return id;

    }



}