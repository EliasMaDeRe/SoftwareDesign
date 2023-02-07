import Controllers.Controlador;
import Controllers.EncryptAES;
import DTOs.actualizarNombreCuentaDTO;
import DTOs.anadirClienteDTO;
import DTOs.borrarCuentaDTO;
import Interfaces.Cypher;

public class Main {

    public static void main(String[] args) {
        
        Controlador controlador = new Controlador("clientes");
        controlador.actualizarNombreCuenta(new actualizarNombreCuentaDTO("004", "Fiora"));
        controlador.imprimirClientesPDF();
        controlador.guardar();
              

    }
    
}