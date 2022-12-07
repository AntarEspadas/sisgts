package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Service
public class ServicioAgremiado {
	@Autowired
    RepositoryAgremiado agremiadoRepository;
	
	private Agremiado agremiado;
	
	@Autowired
	private ServicioEmpleado servicioempleado=new ServicioEmpleado();
	
	public boolean RecuperaCorreo(String correo, String contrasenia) {
		
		agremiado=agremiadoRepository.findByCorreo(correo);

        if (agremiadoRepository.findByCorreo(correo)!=null && contrasenia.equals(agremiado.getContrasenia()) ) {
        	
        	servicioempleado.logOut();
        	
        	Agremiado();
        	
            return true;

        }else {
        	
            return false;
            
        }

    }
	
	//SI HAY UN AGREMIADO CON SESION INICIADA, RECUPERA ESE AGREMIADO, SI NO REGRESA NULL
	public void Agremiado() {
		getAgremiadoActual();
		
	}
	
	public Agremiado getAgremiadoActual() {
		return agremiado;
	}
	
	/*CIERRA LA SESION DEL AGFREMIADO DESPUES DE LLAMAR A ESTE METODO, DE AHORA EN  ADELANTE GETAGREMIADOACTUAL DEBE REGRESAR
	 * NULL HASTA QUE UN AGREMIADO VUELVA A INICIAR SESION*/
	public void logOut() {
		agremiado=null;
	}
	
}
