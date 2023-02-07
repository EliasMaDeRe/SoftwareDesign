package Models;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Banco {

    private String archivo = "";
    private HashMap<String,ArrayList<Cuenta>> clientes;
    private HashMap<String,Cuenta> cuentas;

    public Banco(String archivo){

        this.archivo = archivo;
        clientes = new HashMap<>();
        cuentas = new HashMap<>();
        cargarClientes();

    }

    public String getArchivo(){

        return this.archivo;

    }

    public HashMap<String,ArrayList<Cuenta>> getClientes(){

        return this.clientes;

    }

    public HashMap<String,Cuenta> getCuentas(){

        return this.cuentas;

    }

    private void cargarClientes(){

        try{
            File file = new File(archivo+".txt");
            Scanner scanner = new Scanner(file);
            
            String linea;
            while(scanner.hasNextLine()){

                linea = scanner.nextLine();
                ArrayList<String> datos =  new ArrayList<>(Arrays.asList(linea.split(",")));
                String id = datos.remove(0);
                Cuenta cuenta = new Cuenta(Double.parseDouble(datos.get(2)), datos.get(1), datos.get(0));
                anadirCuenta(id,cuenta);
                
            }
            scanner.close();
        }catch(Exception e){

            e.printStackTrace();
            System.out.println("No se pudo abrir el archivo");

        }       
        
    }

    public void cerrarBanco(){

        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(archivo+".txt"));
            for(String cliente : clientes.keySet()){

                for(Cuenta cuenta : clientes.get(cliente)){
    
                    myWriter.write(cliente+","+cuenta.getnombreTitular()+","+cuenta.getnumeroCuenta()+","+cuenta.getSaldo());
                    myWriter.newLine();
                }
    
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error guardando cambios");
            e.printStackTrace();
        }



    }

    public void anadirCuenta(String id, Cuenta cuenta){

        if(!clientes.containsKey(id)){
            clientes.put(id, new ArrayList<>());
        }
        clientes.get(id).add(cuenta);
        cuentas.put(cuenta.getnumeroCuenta(),cuenta);

    }

    public void borrarCuenta(String id, Cuenta cuenta){

        clientes.get(id).remove(cuenta);
        cuentas.remove(cuenta.getnumeroCuenta());
 
    }

    public void actualizarNombre(String id, String NuevoNombre){

        for(Cuenta cuenta : getClientes().get(id)){

            cuenta.setNombre(NuevoNombre);

        }

    }

}
