package mx.uam.ayd.proyecto.presentacion.Notificaciones;

import java.awt.GridBagLayout;
import java.awt.Rectangle;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

@Slf4j
@Component
public class VentanaNotificaciones extends Pantalla {
	
	public VentanaNotificaciones() {
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 151, 77, 151, 50, 0};
		gridBagLayout.rowHeights = new int[]{42, 17, 26, 63, 17, 97, 3, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
	}

	public void cierra() {
		// TODO Auto-generated method stub
		setVisible(false);
	}

    
}
