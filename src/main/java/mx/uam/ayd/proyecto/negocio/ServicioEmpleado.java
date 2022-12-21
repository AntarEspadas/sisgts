package mx.uam.ayd.proyecto.negocio;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;


import mx.uam.ayd.proyecto.datos.RepositoryEmpleado;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
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
		
		for(Empleado _empleado:empleadoRepository.findAll()) {
			empleados.add(_empleado);
		}
				
		return empleados;
	}
	
	public boolean verificaCorreoYContrasenia(String correo, String contrasenia) {
		
		empleado=empleadoRepository.findByCorreo(correo);

        if(empleadoRepository.findByCorreo(correo)!=null && contrasenia.equals(empleado.getContrasenia())) {
        	
        	servicioagremiado.logOut();
        	
            return true;
            
        }else {
        	
            return false;
            
        }

    }
	
	//Metodo que verifica si el Id del empleado existe 
	public boolean verificaIdEmpleado(long idEmpleado) {

		return empleadoRepository.findById(idEmpleado)!=null;
		
	}//Fin del metodo verifica Id empleado

	//Metodo recupera empleado recupera a un empleado exitente en la base de datos
	public Empleado recuperaEmpleado(long idEmpleado){
		return empleadoRepository.findById(idEmpleado);
	}//Fin del metodo recupera empelado
	
	//SI HAY UN EMPLEADO CON SESION INICIADA, RECUPERA ESE AGREMIADO, SI NO REGRESA NULL
	public Empleado getEmpleadoActual() {
		return empleado;
	}
		
	/*CIERRA LA SESION DEL EMPLEADO DESPUES DE LLAMAR A ESTE METODO, DE AHORA EN  ADELANTE GETEMPLEADOACTUAL DEBE REGRESAR
	 * NULL HASTA QUE UN EMPLEADO VUELVA A INICIAR SESION*/
	public void logOut() {
		
		empleado=null;
	}
	
	//Metodo edita empleado perimte editar los datos de un empelado existente regresando true 
	public boolean editaEmpleado(long id, String nombre, String apelidos, String correo, String contrasenia, String tipoempleado, @Nullable Empleado empleado) {
		empleado=empleadoRepository.findById(id);
		empleado.setId(id);
		empleado.setNombre(nombre);		
		empleado.setApellidos(apelidos);
		empleado.setCorreo(correo);
		empleado.setContrasenia(contrasenia);
		empleado.setTipoEmpleado(tipoempleado);
		empleado=empleadoRepository.save(empleado);
		return true;
		
	}//Fin del metodo edita empleado
	
}
