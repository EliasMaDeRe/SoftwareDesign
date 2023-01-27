public class Main {

    public static void main(String[] args) {
        
        Controlador controlador = new Controlador("clientes");
        controlador.getBanco().imprimirClientes();
        String[] cliente = new String[]{"001","Elias Madera De Regil","1234567890123443","129"};
        System.out.println(controlador.anadirCliente(cliente));
        

    }
    
}