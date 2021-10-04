package pojo;

public class Facultad {
    
    private Integer idFacultad; 
    private String nombre; 
    
    public Facultad(){
        
    }
    
    public Facultad(Integer idFacultad, String nombre) {
        this.idFacultad = idFacultad;
        this.nombre = nombre;
    }

    public Integer getIdFacultad() {
        return idFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
