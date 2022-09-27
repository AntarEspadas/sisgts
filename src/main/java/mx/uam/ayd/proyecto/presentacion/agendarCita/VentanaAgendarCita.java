package mx.uam.ayd.proyecto.presentacion.agendarCita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
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

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;

@Slf4j
@Component
public class VentanaAgendarCita extends Pantalla {

	private ControlAgendarCita controlador;
	private Map<LocalDate, Set<LocalTime>> horariosNoDisponibles;
	private List<LocalTime> horarios;
	
	private final CalendarPanel calendarPanel;
	private LocalDate fechaSeleccionada;
	private final JComboBox<LocalTime> comboBox;
	private final JTextArea txtrMotivo;

	public VentanaAgendarCita() {
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{50, 151, 77, 151, 50, 0};
		gridBagLayout.rowHeights = new int[]{42, 17, 26, 63, 17, 97, 3, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
				
				JLabel lblFecha = new JLabel("Fecha");
				GridBagConstraints gbc_lblFecha = new GridBagConstraints();
				gbc_lblFecha.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
				gbc_lblFecha.gridx = 1;
				gbc_lblFecha.gridy = 1;
				add(lblFecha, gbc_lblFecha);
				
				JLabel lblHorario = new JLabel("Horario");
				GridBagConstraints gbc_lblHorario = new GridBagConstraints();
				gbc_lblHorario.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
				gbc_lblHorario.gridx = 3;
				gbc_lblHorario.gridy = 1;
				add(lblHorario, gbc_lblHorario);

		var manana = LocalDate.now().plusDays(1);
		var settings = new DatePickerSettings(new Locale("es", "MX"));
		settings.setAllowEmptyDates(false);
		calendarPanel = new CalendarPanel(settings);
		settings.setDateRangeLimits(manana, null);
		calendarPanel.setSelectedDate(manana);
		calendarPanel.addCalendarListener(new CalendarListener() {
			public void selectedDateChanged(CalendarSelectionEvent event) {
				actualizarHorarios();
			}
			public void yearMonthChanged(YearMonthChangeEvent event) {
			}
		});
		GridBagConstraints gbc_datePanel = new GridBagConstraints();
		gbc_datePanel.anchor = GridBagConstraints.WEST;
		gbc_datePanel.gridwidth = 2;
		gbc_datePanel.gridheight = 2;
		gbc_datePanel.insets = new Insets(0, 0, 5, 5);
		gbc_datePanel.gridx = 1;
		gbc_datePanel.gridy = 2;
		add(calendarPanel, gbc_datePanel);
		
		comboBox = new JComboBox<>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);
		
		JLabel lblMotivo = new JLabel("Motivo");
		GridBagConstraints gbc_lblMotivo = new GridBagConstraints();
		gbc_lblMotivo.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMotivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivo.gridx = 1;
		gbc_lblMotivo.gridy = 4;
		add(lblMotivo, gbc_lblMotivo);
		
		txtrMotivo = new JTextArea();
		GridBagConstraints gbc_txtrMotivo = new GridBagConstraints();
		gbc_txtrMotivo.fill = GridBagConstraints.BOTH;
		gbc_txtrMotivo.insets = new Insets(0, 0, 5, 5);
		gbc_txtrMotivo.gridwidth = 3;
		gbc_txtrMotivo.gridx = 1;
		gbc_txtrMotivo.gridy = 5;
		add(txtrMotivo, gbc_txtrMotivo);
		
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
		add(btnCancelar, gbc_btnCancelar);
		
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
		add(btnAgendar, gbc_btnAgendar);
		
	}
	
	public void muestra(ControlAgendarCita controlador, List<LocalTime> horarios, Map<LocalDate, Set<LocalTime>> horariosNoDisponibles) {
		this.controlador = controlador;
		this.horarios = horarios;
		this.horariosNoDisponibles = horariosNoDisponibles;

		this.txtrMotivo.setText("");
		actualizarHorarios();

		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}
	
	private void actualizarHorarios() {
		var fecha = calendarPanel.getSelectedDate();
		
		fechaSeleccionada = fecha;
		
		List<LocalTime> horariosDisponibles;
		var horariosNoDisponibles = this.horariosNoDisponibles.getOrDefault(fecha, null);
		log.info("Fecha seleccionada: {}", fechaSeleccionada);
		log.info("Horarios no disponibles: {}", horariosNoDisponibles);

		if (fecha.isBefore(LocalDate.now().plusDays(1)))
			horariosDisponibles = new ArrayList<>();
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
