package mx.uam.ayd.proyecto.negocio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

@Service
public class ServicioEmpleado {
	
	@Autowired 
	RepositoryEmpleado empleadoRepository;
	
	private Empleado empleado;
	
	@Autowired 
	private ServicioAgremiado servicioagremiado;

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
	
	public boolean RecuperaCorreo(String correo, String contrasenia) {
		
		empleado=empleadoRepository.findByCorreo(correo);

        if(empleadoRepository.findByCorreo(correo)!=null && contrasenia.equals(empleado.getContrasenia())) {
        	
        	servicioagremiado.logOut();
        	
            return true;
            
        }else {
        	
            return false;
            
        }

    }
	
	//SI HAY UN EMPLEADO CON SESION INICIADA, RECUPERA ESE AGREMIADO, SI NO REGRESA NULL
	public Empleado getEmpleadoActual() {
		return empleado;
	}
		
	/*CIERRA LA SESION DEL EMPLEADO DESPUES DE LLAMAR A ESTE METODO, DE AHORA EN  ADELANTE GETEMPLEADOACTUAL DEBE REGRESAR
	 * NULL HASTA QUE UN EMPLEADO VUELVA A INICIAR SESION*/
	public void logOut() {
		
		empleado=null;
	}
	
}
