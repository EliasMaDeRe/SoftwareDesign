import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.util.regex.*; 

public class Controlador {

    private Banco banco;

    Controlador(String archivo){

        banco = new Banco(archivo);

    }

    public boolean anadirCliente(String[] args){

        try{
            verificar(args);
            validar(args);
        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

        try{

            File file = new File(this.banco.getArchivo()+".txt");
            BufferedWriter write = new BufferedWriter(new FileWriter(file,true));
            banco.anadirCliente(args, write);

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }
        

        return true;


    }

    private void verificar(String[] args) throws IOException{

        for(int i = 0; i < args.length; ++i){

            verificarDato(args[i],i);

        }

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

    private void validar(String[] args) throws IOException{

        ArrayList<ArrayList<String>> clientes = banco.obtenerClientes();


        if(cuentaExiste(args,clientes)) 
            throw new IOException("La cuenta ya existe");
        if(idExiste(args,clientes) && !nombreEsIgual(args,clientes))
            throw new IOException("Ya hay un cliente con ese ID registrado");

        
    
    }

    private boolean cuentaExiste(String[] args, ArrayList<ArrayList<String>> clientes){

        for(int i = 0; i < clientes.size(); ++i){

            if(args[2].equals(clientes.get(i).get(2)))
                return true;

        }

        return false;

    }

    private boolean idExiste(String[] args, ArrayList<ArrayList<String>> clientes){

        for(int i = 0; i < clientes.size(); ++i){

            if(args[0].equals(clientes.get(i).get(0)))
                return true;

        }

        return false;

    }

    private boolean nombreEsIgual(String[] args, ArrayList<ArrayList<String>> clientes){

        for(int i = 0; i < clientes.size(); ++i){

            if(clientes.get(i).get(0).equals(args[0]) && !(clientes.get(i).get(1).equals(args[1])))
                return false;

        }

        return true;

    }

    public Banco getBanco(){

        return this.banco;

    }
}
