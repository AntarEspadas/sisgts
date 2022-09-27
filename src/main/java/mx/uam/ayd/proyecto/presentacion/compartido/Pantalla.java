package mx.uam.ayd.proyecto.presentacion.compartido;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.ayd.proyecto.presentacion.principal.VentanaPrincipal;

public class Pantalla extends JPanel {
	
	@Autowired
	private VentanaPrincipal ventana;

	public void setVisible(boolean visible) {
		if (visible)
			ventana.muestraComponente(this);
		else
			ventana.quitaComponente(this);
	}
	
}
