package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.RepositoryUsuario;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@Slf4j
@Service
public class ServicioUsuario {

	@Autowired 
	private RepositoryUsuario usuarioRepository;
	
	//@Autowired
	//private GrupoRepository grupoRepository;
	
	/**
	 * 
	 * Permite agregar un usuario
	 * 
	 * @param idempleado
	 * @param nombre
	 * @param apellido
	 * @param templeado
	 * @param clave
	 * @param filiacion
	 * @param adscripcion 
	 * @param puesto 
	 * @param domicilio 
	 * @param turno 
	 * @param celular	
	 * @param telefono
	 * @param centrodetrabajo
	 * @param correo
	 * @param contrasenia
	
	 * @return
	 */
	//METODO QUE AGREGA EMPLEADO
	public Usuario RegistraEmpleado(String idempleado, String nombre, String apellido, String templeado, String correo, String contrasenia) {
		
		//BUSCA AL USUARIO QUE SE QUIERE AGREGAR EN LA BASE DE DATOS
		Usuario usuario = usuarioRepository.findByNombreAndApellido(nombre, apellido);
		
		//SI EXISTE MANDA MENSAJE QUE YA EXISTE
		if(usuario != null) {
			throw new IllegalArgumentException("Ese usuario ya existe");
		}//FIN DEL IF
		
		//BUSCA AL GRUPO EN EL REPOSITORY
		//Grupo grupo = grupoRepository.findByNombre(nombreGrupo);
		
		//SI NO EXISTE MANDA MENSAJE QUE NO SE ENCONTRO
		//if(grupo == null) {
			//throw new IllegalArgumentException("No se encontró el grupo");
		//}//FIN DEL IF
		
		//SI EL USUARIO NO EXISTE Y EL GRUPO EXISTE COMIENZA AGREGAR LOS DATOS QUE ESCRIBIO DEL USUARIO
		log.info("Agregando usuario nombre: "+nombre+" apellido:"+apellido);
		
		//AGREGA LOS DATOS DEL USUARIO SI NO EXISTE
		usuario = new Usuario();
		usuario.setIdempleado(idempleado);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setTempleado(templeado);
		usuario.setCorreo(correo);
		usuario.setContrasenia(contrasenia);
			
		//GUARDA AL USUARIO EN LA BASE DE DATOS
		usuarioRepository.save(usuario);
		
		//AGREGA AL USUARIO AL GRUPO
		//grupo.addUsuario(usuario);
		
		//GRUARDA EL GRUPO EN LA BASE DE DATOS
		//grupoRepository.save(grupo);
		
		return usuario;//REGRESA AL USUARIO
		
	}
	
	//METODO QUE AGREGA AGREMIADO
		public Usuario RegistraAgremiado(String nombre, String apellido, String clave, String filiacion, String adscripcion, String puesto, String domicilio, String turno, String celular, String telefono, String centrodetrabajo, String correo, String contrasenia) {
			
			//BUSCA AL USUARIO QUE SE QUIERE AGREGAR EN LA BASE DE DATOS
			Usuario usuario = usuarioRepository.findByNombreAndApellido(nombre,apellido);
			
			//SI EXISTE MANDA MENSAJE QUE YA EXISTE
			if(usuario != null) {
				throw new IllegalArgumentException("Ese usuario ya existe");
			}//FIN DEL IF
			
			//BUSCA AL GRUPO EN EL REPOSITORY
			//Grupo grupo = grupoRepository.findByNombre(nombreGrupo);
			
			//SI NO EXISTE MANDA MENSAJE QUE NO SE ENCONTRO
			//if(grupo == null) {
				//throw new IllegalArgumentException("No se encontró el grupo");
			//}//FIN DEL IF
			
			//SI EL USUARIO NO EXISTE Y EL GRUPO EXISTE COMIENZA AGREGAR LOS DATOS QUE ESCRIBIO DEL USUARIO
			log.info("Agregando usuario nombre: "+nombre+" apellido:"+apellido);
			
			//AGREGA LOS DATOS DEL USUARIO SI NO EXISTE
			usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setTempleado(clave);
			usuario.setTempleado(filiacion);
			usuario.setTempleado(adscripcion);
			usuario.setTempleado(puesto);
			usuario.setTempleado(domicilio);
			usuario.setTempleado(turno);
			usuario.setTempleado(celular);
			usuario.setTempleado(telefono);
			usuario.setTempleado(centrodetrabajo);
			usuario.setCorreo(correo);
			usuario.setContrasenia(contrasenia);
				
			//GUARDA AL USUARIO EN LA BASE DE DATOS
			usuarioRepository.save(usuario);
			
			//AGREGA AL USUARIO AL GRUPO
			//grupo.addUsuario(usuario);
			
			//GRUARDA EL GRUPO EN LA BASE DE DATOS
			//grupoRepository.save(grupo);
			
			return usuario;//REGRESA AL USUARIO
			
		}
	

	/**
	 * Recupera todos los usuarios existentes
	 * 
	 * @return Una lista con los usuarios (o lista vacía)
	 */
	/*
	//METODO QUE REGRESA A LOS USUARIOS EXISTENTES
	public List <Usuario> recuperaUsuarios() {

		System.out.println("usuarioRepository = "+usuarioRepository);
		
		List <Usuario> usuarios = new ArrayList<>();
		
		for(Usuario usuario:usuarioRepository.findAll()) {
			usuarios.add(usuario);
		}
				
		return usuarios;
	}//FIN DEL METODO QUE REGRESA LOS USUARIOS EXISTENTES
	*/
	
	//METODO QUE VERIFICA SI EL NOMBRE Y APELLIDO YA EXISTEN EN LA BASE DE DATOS
     public Usuario Recupera1(String nombre, String apellido) {
		
		// Regla de negocio: No se permite agregar dos usuarios con el mismo nombre y apellido
		
		Usuario usuariorecu = usuarioRepository.findByNombreAndApellido(nombre, apellido);//REVISA EN EL REPOSITORIO SI YA EXISTE EL USUARIO 
		
		//SI USUARIO ES NULL PERMITE CONTINUAR CON EL REGISTRO 
		if(usuariorecu==null) {
			System.out.println("null");
			//throw new IllegalArgumentException("ok");
		}//FIN DEL IF
		
		//SI EL USUARIO ES DIFERENTE DE NULL Y YA EXISTE EN EL REPOSITORIO MANDA MENSAJE QUE YA EXISTE 
		if(usuariorecu != null) {
			System.out.println("Usuario ya existe");
			throw new IllegalArgumentException("Ese usuario ya existe");
		}//FIN DEL IF 

		return usuariorecu;//REGRESA AL USUARIO

	}//FIN DEL METODO DE VERIFICACION 
     
     
     
   //METODO QUE VERIFICA SI LA CLAVE YA EXISTE EN LA BASE DE DATOS
     public Usuario RecuperaClave(String clave) {
		
		// Regla de negocio: No se permite agregar dos usuarios con el mismo nombre y apellido
		
		Usuario usuarioclave = usuarioRepository.findByClave(clave);//REVISA EN EL REPOSITORIO SI YA EXISTE EL USUARIO 
		
		//SI USUARIO ES NULL PERMITE CONTINUAR CON EL REGISTRO 
		if(usuarioclave==null) {
			System.out.println("null");
			//throw new IllegalArgumentException("ok");
		}//FIN DEL IF
		
		//SI EL USUARIO ES DIFERENTE DE NULL Y YA EXISTE EN EL REPOSITORIO MANDA MENSAJE QUE YA EXISTE 
		if(usuarioclave != null) {
			System.out.println("Usuario ya existe");
			throw new IllegalArgumentException("Ese usuario ya existe");
		}//FIN DEL IF 

		return usuarioclave;//REGRESA AL USUARIO

	}//FIN DEL METODO DE VERIFICACION 
     
     
     
   //METODO QUE VERIFICA SI EL NOMBRE Y APELLIDO YA EXISTEN EN LA BASE DE DATOS
     public Usuario Recuperaid(String idempleado) {
		
		// Regla de negocio: No se permite agregar dos usuarios con el mismo nombre y apellido
		
		Usuario usuarioid = usuarioRepository.findByIdempleado(idempleado);//REVISA EN EL REPOSITORIO SI YA EXISTE EL USUARIO 
		
		//SI USUARIO ES NULL PERMITE CONTINUAR CON EL REGISTRO 
		if(usuarioid==null) {
			System.out.println("null");
			//throw new IllegalArgumentException("ok");
		}//FIN DEL IF
		
		//SI EL USUARIO ES DIFERENTE DE NULL Y YA EXISTE EN EL REPOSITORIO MANDA MENSAJE QUE YA EXISTE 
		if(usuarioid != null) {
			System.out.println("Usuario ya existe");
			throw new IllegalArgumentException("Ese usuario ya existe");
		}//FIN DEL IF 

		return usuarioid;//REGRESA AL USUARIO

	}//FIN DEL METODO DE VERIFICACION 
     
   //METODO QUE VERIFICA SI EL CORREO Y CONTRASEÑA YA EXISTEN EN LA BASE DE DATOS
     public Usuario recuperaCorreo(String correo) {
		
		// Regla de negocio: No se permite agregar dos usuarios con el mismo nombre y apellido
		
		Usuario correoverifi = usuarioRepository.findByCorreo(correo);//REVISA EN EL REPOSITORIO SI YA EXISTE EL CORREO 
		
		//SI CORREO ES NULL PERMITE CONTINUAR CON EL REGISTRO 
		
		if(correoverifi==null) {
			System.out.println("null");
			//throw new IllegalArgumentException("ok");
		}//FIN DEL IF
		
		//SI EL USUARIO ES DIFERENTE DE NULL Y YA EXISTE EN EL REPOSITORIO MANDA MENSAJE QUE YA EXISTE 
		if(correoverifi != null) {
			System.out.println("Correo ya existe");
			throw new IllegalArgumentException("El correo ya existe");
		}//FIN DEL IF 

		return correoverifi;//REGRESA AL USUARIO

	}//FIN DEL METODO DE VERIFICACION 

}


