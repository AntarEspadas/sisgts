package mx.uam.ayd.proyecto.datos;

import lombok.AllArgsConstructor;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.util.Filtro;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;

@AllArgsConstructor
public class CitaSpecification implements Specification<Cita> {

    private Filtro filtro;


    @Override
    public Predicate toPredicate(Root<Cita> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        var atributos = filtro.getAtributo().split("\\.");
        From<Cita, Cita> tabla;
        String atributo;
        if (atributos.length == 1){
            atributo = atributos[0];
            tabla = root;
        }
        else {
            atributo = atributos[1];
            tabla = root.join(atributos[0]);
        }

        var valor = filtro.getValor();
        switch (filtro.getOperador()){
            case FECHA_ANTES:
                return builder.lessThan(tabla.get(atributo), (LocalDate) valor);
            case FECHA_DESPUES:
                return builder.greaterThan(tabla.get(atributo), (LocalDate) valor);
            case FECHA_EXACTA:
                return builder.equal(tabla.get(atributo), valor);
            case CONTIENE:
                return builder.like(tabla.get(atributo), "%"+valor+"%");
            default:
                throw new IllegalArgumentException();
        }
    }
}
