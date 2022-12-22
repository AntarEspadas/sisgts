package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;

public interface AvisoRepository extends CrudRepository<Aviso, Long> {
	

	List<Aviso> findAllByOrderByDestacadoDesc();

}
