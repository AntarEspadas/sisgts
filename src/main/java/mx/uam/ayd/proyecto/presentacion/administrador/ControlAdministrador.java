package mx.uam.ayd.proyecto.presentacion.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.presentacion.editaragremiado.ControlEditaragremiado;
import mx.uam.ayd.proyecto.presentacion.editarempleado.ControlEditarempleado;

@Component
public class ControlAdministrador {

	    @Autowired
	    private ControlEditarempleado controlEditarempleado;
	    
	    @Autowired
	    private ControlEditaragremiado controlEditaragremiado; 
	    
	    @Autowired
		private VentanaAdministrador ventanaAdministrador;

	    public void inicia(){
	       ventanaAdministrador.muestra(this);
	    }

	    public void editaEmpleado(){
	        controlEditarempleado.inicia();
	    }

	    public void editaAgremiado(){
	        controlEditaragremiado.inicia();
	    }  
}
