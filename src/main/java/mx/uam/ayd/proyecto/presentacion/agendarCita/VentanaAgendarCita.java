package mx.uam.ayd.proyecto.presentacion.agendarCita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.UtilDateModel;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Slf4j
@Component
public class VentanaAgendarCita extends JFrame {

	private ControlAgendarCita controlador;
	private Map<LocalDate, Set<LocalTime>> horariosNoDisponibles;
	private List<LocalTime> horarios;
	
	private JDatePanel datePanel;
	private LocalDate fechaSeleccionada;
	private JComboBox<LocalTime> comboBox;
	private JTextArea txtrMotivo;

	public VentanaAgendarCita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 151, 77, 151, 50, 0};
		gridBagLayout.rowHeights = new int[]{42, 17, 26, 63, 17, 97, 3, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
				
				JLabel lblFecha = new JLabel("Fecha");
				GridBagConstraints gbc_lblFecha = new GridBagConstraints();
				gbc_lblFecha.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
				gbc_lblFecha.gridx = 1;
				gbc_lblFecha.gridy = 1;
				getContentPane().add(lblFecha, gbc_lblFecha);
				
				JLabel lblHorario = new JLabel("Horario");
				GridBagConstraints gbc_lblHorario = new GridBagConstraints();
				gbc_lblHorario.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
				gbc_lblHorario.gridx = 3;
				gbc_lblHorario.gridy = 1;
				getContentPane().add(lblHorario, gbc_lblHorario);

		UtilDateModel model = new UtilDateModel();
		datePanel = new JDatePanel(model);
		datePanel.getModel().addChangeListener(e -> actualizarHorarios());
		GridBagConstraints gbc_datePanel = new GridBagConstraints();
		gbc_datePanel.anchor = GridBagConstraints.WEST;
		gbc_datePanel.gridwidth = 2;
		gbc_datePanel.gridheight = 2;
		gbc_datePanel.insets = new Insets(0, 0, 5, 5);
		gbc_datePanel.gridx = 1;
		gbc_datePanel.gridy = 2;
		getContentPane().add(datePanel, gbc_datePanel);
		
		comboBox = new JComboBox<LocalTime>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBox, gbc_comboBox);
		
		JLabel lblMotivo = new JLabel("Motivo");
		GridBagConstraints gbc_lblMotivo = new GridBagConstraints();
		gbc_lblMotivo.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMotivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivo.gridx = 1;
		gbc_lblMotivo.gridy = 4;
		getContentPane().add(lblMotivo, gbc_lblMotivo);
		
		txtrMotivo = new JTextArea();
		GridBagConstraints gbc_txtrMotivo = new GridBagConstraints();
		gbc_txtrMotivo.fill = GridBagConstraints.BOTH;
		gbc_txtrMotivo.insets = new Insets(0, 0, 5, 5);
		gbc_txtrMotivo.gridwidth = 3;
		gbc_txtrMotivo.gridx = 1;
		gbc_txtrMotivo.gridy = 5;
		getContentPane().add(txtrMotivo, gbc_txtrMotivo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.infoCitas();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.NORTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		JButton btnAgendar = new JButton("Agendar");
		var ventana = this;
		btnAgendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var hora = (LocalTime)comboBox.getSelectedItem();
				var motivo = txtrMotivo.getText();
				
				var mensaje = validaCampos(fechaSeleccionada, hora, motivo);
				if (mensaje != null) {
					JOptionPane.showMessageDialog(ventana, mensaje);
					return;
				}
				controlador.agendarCita(fechaSeleccionada, hora, txtrMotivo.getText());
			}
		});
		GridBagConstraints gbc_btnAgendar = new GridBagConstraints();
		gbc_btnAgendar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAgendar.anchor = GridBagConstraints.NORTH;
		gbc_btnAgendar.gridx = 3;
		gbc_btnAgendar.gridy = 6;
		getContentPane().add(btnAgendar, gbc_btnAgendar);
		
	}
	
	public void muestra(ControlAgendarCita controlador, List<LocalTime> horarios, Map<LocalDate, Set<LocalTime>> horariosNoDisponibles) {
		this.controlador = controlador;
		this.horarios = horarios;
		this.horariosNoDisponibles = horariosNoDisponibles;
		
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}
	
	private void actualizarHorarios() {
		var dateModel = datePanel.getModel();
		var nuevaFecha = LocalDate.of(dateModel.getYear(), dateModel.getMonth() + 1, dateModel.getDay());
		
		if (fechaSeleccionada != null && nuevaFecha.isEqual(fechaSeleccionada)) return;
		fechaSeleccionada = nuevaFecha;
		
		List<LocalTime> horariosDisponibles;
		var horariosNoDisponibles = this.horariosNoDisponibles.getOrDefault(nuevaFecha, null);
		log.info("Fecha seleccionada: {}", fechaSeleccionada);
		log.info("Horarios no disponibles: {}", horariosNoDisponibles);

		if (nuevaFecha.isBefore(LocalDate.now().plusDays(1)))
			horariosDisponibles = new ArrayList<LocalTime>();
		else if (horariosNoDisponibles == null || horariosNoDisponibles.size() == 0)
			horariosDisponibles = horarios;
		else
			horariosDisponibles = horarios
									.stream()
									.filter(hora -> !horariosNoDisponibles.contains(hora))
									.collect(Collectors.toList());
		
		var comboBoxModel = new DefaultComboBoxModel<LocalTime>();
		comboBoxModel.addAll(horariosDisponibles);
		
		comboBox.setModel(comboBoxModel);
	}
	
	private String validaCampos(LocalDate fecha, LocalTime hora, String motivo) {
		if (fecha == null) return "Favor de escoger una fecha";
		if (fecha.isBefore(LocalDate.now().plusDays(1))) return "Favor de escoger una fecha con al menos un día de anticipación";
		if (hora == null) return "Favor de escoger una hora";
		if (motivo == null || motivo.length() == 0) return "Favor de especificar un motivo";
		
		return null;
	}
}
