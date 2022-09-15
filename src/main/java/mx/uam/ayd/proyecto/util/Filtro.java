package mx.uam.ayd.proyecto.util;

import lombok.Data;

@Data
public class Filtro {

    private String atributo;

    private String operador;

    private Object valor;
}
