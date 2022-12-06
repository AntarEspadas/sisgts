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
	private Empleado empleado;
	
	public boolean RecuperaCorreo(String correo, String contrasenia) {
		
		agremiado=agremiadoRepository.findByCorreo(correo);

        if (agremiadoRepository.findByCorreo(correo)!=null && contrasenia.equals(agremiado.getContrasenia()) ) {

        	/*empleado=null;
        	agremiado = agremiadoRepository.findByCorreo(correo);
        	
        	*/
        	//agremiadoRepository.findByCorreo(correo);
        	
            return true;

        }else {
            return false;
        }

    }
	
}
