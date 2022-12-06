package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.RepositoryAgremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;

@Service
public class ServicioAgremiado {
	@Autowired
    RepositoryAgremiado agremiadoRepository;
	
	public boolean RecuperaCorreo(String correo) {

        if (agremiadoRepository.findByCorreo(correo)!=null) {

        //if(agremiadoRepository.findByCorreo(correo)!=null && agremiadoRepository.findByContrasenia(contrasenia)!=null ){
            
            return true;

        }else {
            return false;
        }

    }
	
}
