package mx.uam.ayd.proyecto.negocio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Service
public class ServicioEmpleado {
	
	@Autowired 
	RepositoryEmpleado empleadoRepository;

	/**
	 * 
	 * Recupera todos los empleados
	 * 
	 * @return
	 */
	public List <Empleado> recuperaEmpleados() {

		
		List <Empleado> empleados = new ArrayList<>();
		
		for(Empleado empleado:empleadoRepository.findAll()) {
			empleados.add(empleado);
		}
				
		return empleados;
	}
}
