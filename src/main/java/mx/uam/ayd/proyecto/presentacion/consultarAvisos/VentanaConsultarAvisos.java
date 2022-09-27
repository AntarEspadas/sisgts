package mx.uam.ayd.proyecto.presentacion.consultarAvisos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

/**
 * @author Brandon Villada
 *
 */
@Component
public class VentanaConsultarAvisos extends Pantalla{
	private ControlConsultarAvisos controlador;
	private JTabbedPane paneles;

	public VentanaConsultarAvisos() {
	setBounds(new Rectangle(100, 100, 500, 500));
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{40, 300, 40, 0};
	gridBagLayout.rowHeights = new int[]{30, 48, 147, 40, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
	setLayout(gridBagLayout);
	
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
	paneles = tabbedPane;
	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
	gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
	gbc_tabbedPane.fill = GridBagConstraints.BOTH;
	gbc_tabbedPane.gridx = 1;
	gbc_tabbedPane.gridy = 2;
	add(tabbedPane, gbc_tabbedPane);
	
	
	
	
	}
	
	public void muestra(ControlConsultarAvisos controlador) {
		// TODO Auto-generated method stub
		this.controlador = controlador;
		setVisible(true);
	}

	public void muestra(List<Aviso> avisos) {
		paneles.removeAll();
		for (Aviso aviso : avisos) {
			Componente aux = new Componente();
            String contenido = (aviso.getContenido());
            String fecha =aviso.getFecha();
            String imagen=aviso.getImagen();
            if (imagen != "null") {
            	ImageIcon imagenico = new ImageIcon(imagen);
            	aux.setParams(contenido, fecha,imagenico);
            }else {
            aux.setParams(contenido, fecha);
            }
            paneles.add(aux);
           
		}
	}
}
