package mx.uam.ayd.proyecto.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase de utilidad que ayuda al repositorio de citas a filtrar la lista de todas las citas
 * según una lista dinámica de filtros
 *
 * @author Antar Espadas
 */
@Data
@AllArgsConstructor
public class Filtro {

    private String atributo;

    private Operador operador;

    private Object valor;
}
