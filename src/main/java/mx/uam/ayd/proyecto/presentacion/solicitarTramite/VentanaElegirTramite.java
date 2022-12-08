package mx.uam.ayd.proyecto.presentacion.solicitarTramite;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
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
	
	private ControlSolicitarTramite controlador;

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
		GridBagConstraints gbc_lblTrmites = new GridBagConstraints();
		gbc_lblTrmites.anchor = GridBagConstraints.WEST;
		gbc_lblTrmites.insets = new Insets(0, 0, 5, 5);
		gbc_lblTrmites.gridx = 1;
		gbc_lblTrmites.gridy = 1;
		add(lblTrmites, gbc_lblTrmites);
		
		
		comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				var item = (VistaTipoTramite)e.getItem();
				var requerimientos = item.getTipoTramite().getRequerimientos();
				var model = new DefaultListModel<String>();
				model.addAll(Arrays.asList(requerimientos));
				list.setModel(model);
				btnSiguiente.setEnabled(true);
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);
		
		JLabel lblRequisitos = new JLabel("Documentos requeridos");
		GridBagConstraints gbc_lblRequisitos = new GridBagConstraints();
		gbc_lblRequisitos.anchor = GridBagConstraints.WEST;
		gbc_lblRequisitos.insets = new Insets(0, 0, 5, 5);
		gbc_lblRequisitos.gridx = 1;
		gbc_lblRequisitos.gridy = 3;
		add(lblRequisitos, gbc_lblRequisitos);
		
		list = new JList<>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 4;
		add(list, gbc_list);
		
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
		GridBagConstraints gbc_btnSiguiente = new GridBagConstraints();
		gbc_btnSiguiente.gridwidth = 2;
		gbc_btnSiguiente.insets = new Insets(0, 0, 5, 5);
		gbc_btnSiguiente.gridx = 1;
		gbc_btnSiguiente.gridy = 6;
		add(btnSiguiente, gbc_btnSiguiente);
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