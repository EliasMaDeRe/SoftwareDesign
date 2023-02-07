import Controllers.Controlador;
import DTOs.actualizarNombreCuentaDTO;
import DTOs.anadirClienteDTO;
import DTOs.borrarCuentaDTO;

public class Main {

    public static void main(String[] args) {
        
        Controlador controlador = new Controlador("clientes");
        controlador.imprimirClientes();
        controlador.anadirCliente(new anadirClienteDTO("004", "Maky", "1234567890123400", "666.0"));
        controlador.imprimirClientes();
        controlador.guardar();
        

    }
    
}