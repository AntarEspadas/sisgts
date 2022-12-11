package mx.uam.ayd.proyecto.negocio.modelo;

public enum Estado {
    PENDIENTE,
    EN_PROGRESO,
    RECHAZADO,
    FINALIZADO;

    public String dislay(){
        return this.toString().toLowerCase().replace('_', ' ');
    }
}
