import java.io.File;
import java.util.Scanner;

public class Banco {

    public void imprimirClientes(String archivo){

        try{
            File file = new File(archivo+".txt");
            Scanner scanner = new Scanner(file);
            String linea = new String();
            while(scanner.hasNextLine()){
                linea = scanner.nextLine();
                String[] datos = linea.split(",");
                for(int i = 0; i < datos.length; ++i){
                    System.out.print(datos[i]+" ");
                    
                }
                System.out.println();
            }
        }catch(Exception e){

            System.out.println("No se pudo abrir el archivo");

        }

    }

    
}
