package mx.uam.ayd.proyecto.util;

import lombok.Data;

/**
 * Clase de utilidad que ayuda al repositorio de citas a filtrar la lista de todas las citas
 * según una lista dinámica de filtros
 *
 * @author Antar Espadas
 */
@Data
public class Filtro {

    private String atributo;

    private String operador;

    private Object valor;
}
