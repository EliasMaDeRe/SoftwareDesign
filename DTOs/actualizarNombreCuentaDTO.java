package DTOs;

public class actualizarNombreCuentaDTO {
    
    private String id;
    private String nuevoNombre;

    public actualizarNombreCuentaDTO(String id, String nuevoNombre){

        this.id = id;
        this.nuevoNombre = nuevoNombre;

    }

    public String getID(){

        return id;

    }

    public String getNuevoNombre(){

        return nuevoNombre;

    }



}
