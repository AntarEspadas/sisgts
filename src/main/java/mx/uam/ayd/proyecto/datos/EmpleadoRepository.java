package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

public interface EmpleadoRepository extends CrudRepository <Empleado, Long> {
	
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
	public Empleado findBytipoEmpleado(String tipoEmpleado);
	

}
