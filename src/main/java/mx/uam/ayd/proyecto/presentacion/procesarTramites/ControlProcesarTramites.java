package mx.uam.ayd.proyecto.presentacion.procesarTramites;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioSolicitudTramite;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

@Component
public class ControlProcesarTramites {

    @Autowired
    private ServicioSolicitudTramite servicioSolicitudTramite;

    @Autowired
    private VentanaProcesarTramites ventana;

    public void inicia() {

        List<SolicitudTramite> solicitudes = servicioSolicitudTramite.findByEstadoNotFinalizado();

        ventana.muestra(solicitudes, this);

    }

    public void tramitePendiente(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramitePendiente(solicitudSeleccionada);
    }

    public void tramiteEnProgreso(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteEnProgreso(solicitudSeleccionada);
    }

    public void tramiteRechazado(SolicitudTramite solicitudSeleccionada) {
        ventana.ventanaTramiteRechazado(solicitudSeleccionada);
    }
    
}
