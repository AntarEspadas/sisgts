package mx.uam.ayd.proyecto.datos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.negocio.modelo.Empleado;

public interface AvisoRepository extends CrudRepository<Aviso, Long> {
	

	public List<Aviso> findAll();

	

	

}
