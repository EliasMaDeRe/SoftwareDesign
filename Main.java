import Controllers.Controlador;
import Controllers.RSADataCypher;
import DTOs.actualizarNombreCuentaDTO;
import DTOs.anadirClienteDTO;
import DTOs.borrarCuentaDTO;
import Interfaces.Cypher;

public class Main {

    public static void main(String[] args) {

        Controlador controlador = new Controlador("clientes","RSA");
        controlador.actualizarNombreCuenta(new actualizarNombreCuentaDTO("004", "Makyyyy"));
        controlador.imprimirClientesPDF();
        controlador.guardar();
              

    }
    
}