package mx.uam.ayd.proyecto.presentacion.publicaciones;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import mx.uam.ayd.proyecto.presentacion.publicaciones.consultarAvisos.Componente;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Brandon Villada
 *
 */
@Component
public class VentanaAvisos extends Pantalla{
	private ControlAvisos controlador;
	private JTabbedPane paneles;
	private JPanel panelAdmin;

	public VentanaAvisos() {
	setBounds(new Rectangle(100, 100, 500, 500));
	GridBagLayout gridBagLayout = new GridBagLayout();
	gridBagLayout.columnWidths = new int[]{40, 300, 40, 0};
	gridBagLayout.rowHeights = new int[]{65, 147, 40, 0};
	gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
	gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
	setLayout(gridBagLayout);
	
	panelAdmin = new JPanel();
	GridBagConstraints gbc_panelAdmin = new GridBagConstraints();
	gbc_panelAdmin.gridwidth = 3;
	gbc_panelAdmin.insets = new Insets(0, 0, 5, 5);
	gbc_panelAdmin.fill = GridBagConstraints.BOTH;
	gbc_panelAdmin.gridx = 0;
	gbc_panelAdmin.gridy = 0;
	add(panelAdmin, gbc_panelAdmin);
	GridBagLayout gbl_panelAdmin = new GridBagLayout();
	gbl_panelAdmin.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
	gbl_panelAdmin.rowHeights = new int[]{0, 0, 17, 0};
	gbl_panelAdmin.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_panelAdmin.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	panelAdmin.setLayout(gbl_panelAdmin);
	
	JButton btnNuevo = new JButton("Nueva publicación");
	btnNuevo.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			controlador.crear();
		}
	});
	GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
	gbc_btnNuevo.insets = new Insets(0, 0, 5, 5);
	gbc_btnNuevo.gridx = 1;
	gbc_btnNuevo.gridy = 1;
	panelAdmin.add(btnNuevo, gbc_btnNuevo);
	
	JButton btnEditar = new JButton("Editar");
	btnEditar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			var componente = (Componente) paneles.getSelectedComponent();
			var aviso = componente.getAviso();
			controlador.editar(aviso);
		}
	});
	GridBagConstraints gbc_btnEditar = new GridBagConstraints();
	gbc_btnEditar.insets = new Insets(0, 0, 5, 5);
	gbc_btnEditar.gridx = 3;
	gbc_btnEditar.gridy = 1;
	panelAdmin.add(btnEditar, gbc_btnEditar);
	
	JButton btnEliminar = new JButton("Eliminar");
	btnEliminar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			var componente = (Componente) paneles.getSelectedComponent();
			var aviso = componente.getAviso();
			controlador.eliminar(aviso);
		}
	});
	GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
	gbc_btnEliminar.insets = new Insets(0, 0, 5, 5);
	gbc_btnEliminar.gridx = 5;
	gbc_btnEliminar.gridy = 1;
	panelAdmin.add(btnEliminar, gbc_btnEliminar);
	
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
	paneles = tabbedPane;
	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
	gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
	gbc_tabbedPane.fill = GridBagConstraints.BOTH;
	gbc_tabbedPane.gridx = 1;
	gbc_tabbedPane.gridy = 1;
	add(tabbedPane, gbc_tabbedPane);
	
	
	
	
	}

	public void muestra(ControlAvisos controlador, List<Aviso> avisos) {
		this.controlador = controlador;
		panelAdmin.setVisible(false);
		setAvisos(avisos);
		setVisible(true);
	}

	public void muestraAdmin(ControlAvisos controlador, List<Aviso> avisos){
		this.controlador = controlador;
		panelAdmin.setVisible(true);
		setAvisos(avisos);
		setVisible(true);
	}

	public void setAvisos(List<Aviso> avisos){
		paneles.removeAll();
		System.out.println(avisos);
		for (Aviso aviso : avisos) {
			Componente aux = new Componente();
			aux.setAviso(aviso);
			paneles.add(aux);
		}
	}
}