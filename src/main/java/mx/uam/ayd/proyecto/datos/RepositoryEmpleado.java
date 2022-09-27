package mx.uam.ayd.proyecto.datos;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;
import org.springframework.data.repository.CrudRepository;




/**
 * Repositorio para Empleado
 *
 * @author Brandon Villada
 */

public interface RepositoryEmpleado extends CrudRepository<Empleado, Long> {
	/**
	 * Encuentra un Empleado a partir de un nombre
	 * 
	 * @param nombre
	 * @return
	 */
	public Empleado findByNombre(String nombre);
	/**
	 * Encuentra un Empleado a partir de su jerarquia
	 * 
	 * @param tipoEmpleado
	 * @return
	 */
	public Empleado findByTipoEmpleado(String tipoEmpleado);

}
