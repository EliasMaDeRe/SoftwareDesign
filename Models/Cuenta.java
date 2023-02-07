package Models;
public class Cuenta {

    private double saldo;
    private String numeroCuenta;
    private String nombreTitular;

    public double getSaldo(){

        return saldo;

    }

    public String getnumeroCuenta(){

        return numeroCuenta;

    }

    public String getnombreTitular(){

        return nombreTitular;

    }

    public void setNombre(String nombre){

        this.nombreTitular = nombre;

    }

    public Cuenta(double saldo, String numeroCuenta, String nombreTitular){

        this.saldo = saldo;
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;

    }



}
