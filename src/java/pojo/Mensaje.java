package pojo;

public class Mensaje {
    
    private String mensaje; 
    private boolean error; 
    
    public Mensaje(){
        
    }

    public Mensaje(String mensaje, boolean error) {
        this.mensaje = mensaje;
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public boolean isError() {
        return error;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
}
