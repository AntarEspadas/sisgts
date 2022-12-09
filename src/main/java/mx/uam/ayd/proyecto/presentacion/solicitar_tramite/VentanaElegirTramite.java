package mx.uam.ayd.proyecto.presentacion.solicitar_tramite;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana que permite al agremiado elegir el trámite que quiere solicitar
 * 
 * @author Antar Espadas
 *
 */
@Component
public class VentanaElegirTramite extends Pantalla {
	
	private transient ControlSolicitarTramite controlador;

	private JComboBox<VistaTipoTramite> comboBox;
	private JList<String> list;
	private JButton btnSiguiente;
	public VentanaElegirTramite() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 218, 151, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 99, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTrmites = new JLabel("Trámites");
		GridBagConstraints gbcLblTrmites = new GridBagConstraints();
		gbcLblTrmites.anchor = GridBagConstraints.WEST;
		gbcLblTrmites.insets = new Insets(0, 0, 5, 5);
		gbcLblTrmites.gridx = 1;
		gbcLblTrmites.gridy = 1;
		add(lblTrmites, gbcLblTrmites);
		
		
		comboBox = new JComboBox<>();
		comboBox.addItemListener(e -> {
			var item = (VistaTipoTramite)e.getItem();
			var requerimientos = item.getTipoTramite().getRequerimientos();
			var model = new DefaultListModel<String>();
			model.addAll(Arrays.asList(requerimientos));
			list.setModel(model);
			btnSiguiente.setEnabled(true);
		});
		GridBagConstraints gbcComboBox = new GridBagConstraints();
		gbcComboBox.insets = new Insets(0, 0, 5, 5);
		gbcComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbcComboBox.gridx = 1;
		gbcComboBox.gridy = 2;
		add(comboBox, gbcComboBox);
		
		JLabel lblRequisitos = new JLabel("Documentos requeridos");
		GridBagConstraints gbcLblRequisitos = new GridBagConstraints();
		gbcLblRequisitos.anchor = GridBagConstraints.WEST;
		gbcLblRequisitos.insets = new Insets(0, 0, 5, 5);
		gbcLblRequisitos.gridx = 1;
		gbcLblRequisitos.gridy = 3;
		add(lblRequisitos, gbcLblRequisitos);
		
		list = new JList<>();
		GridBagConstraints gbcList = new GridBagConstraints();
		gbcList.gridwidth = 2;
		gbcList.insets = new Insets(0, 0, 5, 5);
		gbcList.fill = GridBagConstraints.BOTH;
		gbcList.gridx = 1;
		gbcList.gridy = 4;
		add(list, gbcList);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setEnabled(false);
		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnSiguiente.isEnabled()) return;
				var seleccion = (VistaTipoTramite)comboBox.getSelectedItem();
				controlador.subirArchivos(seleccion.getTipoTramite());
			}
		});
		GridBagConstraints gbcBtnSiguiente = new GridBagConstraints();
		gbcBtnSiguiente.gridwidth = 2;
		gbcBtnSiguiente.insets = new Insets(0, 0, 5, 5);
		gbcBtnSiguiente.gridx = 1;
		gbcBtnSiguiente.gridy = 6;
		add(btnSiguiente, gbcBtnSiguiente);
	}
	
	/**
	 * Muestra la ventana
	 * 
	 * @param controlador El controlador responsable de esta ventana
	 * @param tipos Los tipos de trámites que se pueden solicitar
	 */
	public void muestra(ControlSolicitarTramite controlador, List<TipoTramite> tipos) {
		
		this.controlador = controlador;
		
		list.setModel(new DefaultComboBoxModel<>());

		var model = new DefaultComboBoxModel<VistaTipoTramite>();
		var vistaTipos = tipos
				.stream()
				.map(VistaTipoTramite::new)
				.collect(Collectors.toList());
		model.addAll(vistaTipos);
		comboBox.setModel(model);
		setVisible(true);
	}

	public void muestraDialogoError(){
		JOptionPane.showMessageDialog(this, "No puede solicitar un trámite en este momento porque ya tiene un trámite en curso");
	}
}

@Getter
@AllArgsConstructor
class VistaTipoTramite{
	private TipoTramite tipoTramite;
	
	@Override
	public String toString() {
		return tipoTramite.getNombreTramite();
	}
}