package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;

import org.springframework.data.repository.CrudRepository;

public interface RepositoryAgremiado extends CrudRepository<Agremiado, Long> {
	
	  public Agremiado findByCorreo(String correo);
	  
	  public Agremiado findByContrasenia(String contrasenia);
}
