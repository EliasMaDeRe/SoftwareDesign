package Controllers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.*;

import DTOs.actualizarNombreCuentaDTO;
import DTOs.anadirClienteDTO;
import DTOs.borrarCuentaDTO;
import Models.Banco;
import Models.Cuenta; 

public class Controlador {

    private Banco banco;

    public Controlador(String archivo){

        banco = new Banco(archivo);

    }

    public void imprimirClientes(){

        HashMap<String,ArrayList<Cuenta>> clientes = banco.getClientes();

        for(Entry<String,ArrayList<Cuenta>> entry : clientes.entrySet()){
            if(entry.getValue().isEmpty()) continue;
            System.out.println("Cliente: "+entry.getValue().get(0).getnombreTitular());
            for(int i = 0; i < entry.getValue().size(); ++i){

                System.out.println("    "+entry.getValue().get(i).getnumeroCuenta());

            }
        }

    }

    public void guardar(){

        banco.cerrarBanco();

    }

    public boolean anadirCliente(anadirClienteDTO info){

        try{
            verificarAnadirCliente(info);
            validarAnadirCliente(info);
        }catch(Exception e){

            e.printStackTrace();
            return false;

        }
        Cuenta cuenta = new Cuenta(Double.parseDouble(info.getSaldo()),info.getNumeroCuenta(),info.getNombreTitular());
        banco.anadirCuenta(info.getId(),cuenta);
        return true;


    }

    public boolean borrarCuenta(borrarCuentaDTO info){

        try{

            validarBorrarCuenta(info);
            banco.borrarCuenta(info.getID(), banco.getCuentas().get(info.getNumeroCuenta()));
            return true;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public boolean actualizarNombreCuenta(actualizarNombreCuentaDTO info){

        if(banco.getClientes().containsKey(info.getID())){

            banco.actualizarNombre(info.getID(),info.getNuevoNombre());
            return true;

        }

        return false;
    }

    private void validarBorrarCuenta(borrarCuentaDTO info) throws IOException{

        validarInfoBorrarCuenta(info);
        validarSaldoBorrarCuenta(info);

    }

    private void validarInfoBorrarCuenta(borrarCuentaDTO info) throws IOException{

        if(!(banco.getCuentas().containsKey(info.getNumeroCuenta()) && banco.getCuentas().get(info.getNumeroCuenta()).getnombreTitular().equals(info.getNombreTitular())))
            throw new IOException("Error con la informacion de la cuenta a borrar");

    }

    private void validarSaldoBorrarCuenta(borrarCuentaDTO info) throws IOException{

        if(!(banco.getCuentas().get(info.getNumeroCuenta()).getSaldo() == 0))
            throw new IOException("La cuenta debe estar en ceros antes de borrar");

    }

    private void verificarAnadirCliente(anadirClienteDTO info) throws IOException{

        verificarDato(info.getId(), 0);
        verificarDato(info.getNombreTitular(), 1);
        verificarDato(info.getNumeroCuenta(), 2);
        verificarDato(info.getSaldo(), 3);

    }

    private void verificarDato(String arg, int i) throws IOException{

        switch(i){

            case 0:
                verificarCliente(arg);
                break;
            case 1:
                verificarNombre(arg);
                break;
            case 2:
                verificarCuenta(arg);
                break;
            case 3:
                verificarSaldo(arg);
                break;

        }

    }

    private void verificarCliente(String arg) throws IOException{

        if(!Pattern.matches("[0-9]+",arg))
            throw new IOException("El id del Cliente es incorrecto");

    }

    private void verificarNombre(String arg) throws IOException{

        if(!Pattern.matches("[^0-9]+",arg))
            throw new IOException("El nombre del Cliente es incorrecto");

    }

    private void verificarCuenta(String arg) throws IOException{

        if(!Pattern.matches("[0-9]+",arg) || arg.length() != 16)
            throw new IOException("La cuenta del Cliente es incorrecta");

    }

    private void verificarSaldo(String arg) throws IOException{

        try{
            Float.parseFloat(arg);
        }catch(NumberFormatException e){

            throw new IOException("El saldo de la Cuenta es incorrecto");

        }

    }

    private void validarAnadirCliente(anadirClienteDTO info) throws IOException{

        if(cuentaExiste(info)) 
            throw new IOException("La cuenta ya existe");
        if(idExiste(info) && !nombreEsIgual(info))
            throw new IOException("Ya hay un cliente con ese ID registrado");
    
    }

    private boolean cuentaExiste(anadirClienteDTO info){

        return banco.getCuentas().containsKey(info.getNumeroCuenta());

    }

    private boolean idExiste(anadirClienteDTO info){

        return banco.getClientes().containsKey(info.getId());

    }

    private boolean nombreEsIgual(anadirClienteDTO info){

        return banco.getClientes().get(info.getId()).get(0).getnombreTitular().equalsIgnoreCase(info.getNombreTitular());

    }

    public Banco getBanco(){

        return this.banco;

    }


}
