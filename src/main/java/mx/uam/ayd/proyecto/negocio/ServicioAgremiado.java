package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;


@Service
public class ServicioAgremiado {
	@Autowired
    RepositoryAgremiado agremiadoRepository;
	
	private Agremiado agremiado;
	
	@Autowired
	@Lazy
	private ServicioEmpleado servicioempleado;

	/**
	 * Verifica que exista un usuario con el correo dado y que la contraseña coincida
	 * @param correo El correo que se va a busca. Debe ser distinto de null
	 * @param contrasenia Contraseña que se debe coincidir con la contraseña del usuario. Debe ser distinto de null
	 * @return Si el correo existe y la contraseña coincide, regresa true. Si el correo no existe o la contraseña no
	 * coincide, regresa false
	 */
	public boolean verificaCorreoYContrasenia(String correo, String contrasenia) {
		
		agremiado=agremiadoRepository.findByCorreo(correo);

        if (agremiadoRepository.findByCorreo(correo)!=null && contrasenia.equals(agremiado.getContrasenia()) ) {
        	
        	servicioempleado.logOut();
        	
            return true;

        }else {
        	
            return false;
            
        }

    }
	
	
	public boolean verificaClave(String clave) {

		return agremiadoRepository.findByClave(clave)!=null;
		
	}

	public Agremiado recuperaAgremiado(String clave){
		return agremiadoRepository.findByClave(clave);
	}
	
	
	
	//SI HAY UN AGREMIADO CON SESION INICIADA, RECUPERA ESE AGREMIADO, SI NO REGRESA NULL
	public Agremiado getAgremiadoActual() {
		return agremiado;
	}
	
	/*CIERRA LA SESION DEL AGFREMIADO DESPUES DE LLAMAR A ESTE METODO, DE AHORA EN  ADELANTE GETAGREMIADOACTUAL DEBE REGRESAR
	 * NULL HASTA QUE UN AGREMIADO VUELVA A INICIAR SESION*/
	public void logOut() {
		agremiado=null;
	}
	
}
