package mx.uam.ayd.proyecto.presentacion.publicaciones;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.*;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.presentacion.publicaciones.consultar_avisos.Componente;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Brandon Villada
 *
 */
@Slf4j
@Component
public class VentanaAvisos extends Pantalla{
	private transient ControlAvisos controlador;
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
	GridBagConstraints gbcPanelAdmin = new GridBagConstraints();
	gbcPanelAdmin.gridwidth = 3;
	gbcPanelAdmin.insets = new Insets(0, 0, 5, 5);
	gbcPanelAdmin.fill = GridBagConstraints.BOTH;
	gbcPanelAdmin.gridx = 0;
	gbcPanelAdmin.gridy = 0;
	add(panelAdmin, gbcPanelAdmin);
	GridBagLayout gblPanelAdmin = new GridBagLayout();
	gblPanelAdmin.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
	gblPanelAdmin.rowHeights = new int[]{0, 0, 17, 0};
	gblPanelAdmin.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gblPanelAdmin.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	panelAdmin.setLayout(gblPanelAdmin);
	
	JButton btnNuevo = new JButton("Nueva publicación");
	btnNuevo.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			controlador.crear();
		}
	});
	GridBagConstraints gbcBtnNuevo = new GridBagConstraints();
	gbcBtnNuevo.insets = new Insets(0, 0, 5, 5);
	gbcBtnNuevo.gridx = 1;
	gbcBtnNuevo.gridy = 1;
	panelAdmin.add(btnNuevo, gbcBtnNuevo);
	
	JButton btnEditar = new JButton("Editar");
	btnEditar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			var componente = (Componente) paneles.getSelectedComponent();
			var aviso = componente.getAviso();
			controlador.editar(aviso);
		}
	});
	GridBagConstraints gbcBtnEditar = new GridBagConstraints();
	gbcBtnEditar.insets = new Insets(0, 0, 5, 5);
	gbcBtnEditar.gridx = 3;
	gbcBtnEditar.gridy = 1;
	panelAdmin.add(btnEditar, gbcBtnEditar);
	
	JButton btnEliminar = new JButton("Eliminar");
	btnEliminar.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			var componente = (Componente) paneles.getSelectedComponent();
			var aviso = componente.getAviso();
			controlador.eliminar(aviso);
		}
	});
	GridBagConstraints gbcBtnEliminar = new GridBagConstraints();
	gbcBtnEliminar.insets = new Insets(0, 0, 5, 5);
	gbcBtnEliminar.gridx = 5;
	gbcBtnEliminar.gridy = 1;
	panelAdmin.add(btnEliminar, gbcBtnEliminar);
	
	
	JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.RIGHT);
	paneles = tabbedPane;
	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	GridBagConstraints gbcTabbedPane = new GridBagConstraints();
	gbcTabbedPane.insets = new Insets(0, 0, 5, 5);
	gbcTabbedPane.fill = GridBagConstraints.BOTH;
	gbcTabbedPane.gridx = 1;
	gbcTabbedPane.gridy = 1;
	add(tabbedPane, gbcTabbedPane);
	
	
	
	
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

	public boolean muestraConfirmacionEliminar(){
		return JOptionPane.showConfirmDialog(this, "¿Está seguro de querer eliminar esta publicación?") == 0;
	}

	public void setAvisos(List<Aviso> avisos){
		paneles.removeAll();
		log.info("avisos = {}", avisos);
		for (Aviso aviso : avisos) {
			Componente aux = new Componente();
			aux.setAviso(aviso);
			paneles.add(aux);
		}
	}
}