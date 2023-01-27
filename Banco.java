import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Banco {

    private String archivo = "";

    Banco(String archivo){

        this.archivo = archivo;

    }

    public String getArchivo(){

        return this.archivo;

    }

    public void imprimirClientes(){

        ArrayList<ArrayList<String>> clientes = obtenerClientes();

        for(int i = 0; i < clientes.size(); ++i){

            for(int j = 0; j < clientes.get(i).size(); ++j)
                System.out.print(clientes.get(i).get(j)+" ");
            System.out.println();
        }

    }

    public ArrayList<ArrayList<String>> obtenerClientes(){

        ArrayList<ArrayList<String>> clientes = new ArrayList<>();
        try{
            File file = new File(archivo+".txt");
            Scanner scanner = new Scanner(file);
            
            String linea;
            while(scanner.hasNextLine()){
                linea = scanner.nextLine();
                ArrayList<String> datos =  new ArrayList<String>(Arrays.asList(linea.split(",")));
                clientes.add(datos);
            }
            scanner.close();
        }catch(Exception e){

            System.out.println("No se pudo abrir el archivo");

        }       
        return clientes;
    }

    public void anadirCliente(String[] args, BufferedWriter write) throws IOException{
        
        String linea = "";
        write.newLine();
        for(int i = 0; i < args.length; ++i){

            linea+=args[i];
            if(i != args.length-1)
                linea+=",";

        }
        write.write(linea);
        write.close();

    }
}
