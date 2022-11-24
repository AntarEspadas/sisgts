package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

/**
 * 
 * Repositorio para usuarios
 * 
 * @author humbertocervantes
 *
 */

public interface RepositoryUsuario extends CrudRepository <Usuario, Long> {

    public Usuario findByNombreAndApellido(String nombre, String apellido);
	
	public Usuario findByCorreo(String correo);
	
	public Usuario findByClave(String clave);
	
	public Usuario findByIdempleado(String idempleado);
	
	//public List <Usuario> findByEdadBetween(int edad1, int edad2);
	
}
