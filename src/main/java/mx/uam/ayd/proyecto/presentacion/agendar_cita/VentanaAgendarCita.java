package mx.uam.ayd.proyecto.presentacion.agendar_cita;

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

	private transient ControlAgendarCita controlador;
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
				GridBagConstraints gbcLblFecha = new GridBagConstraints();
				gbcLblFecha.anchor = GridBagConstraints.NORTHWEST;
				gbcLblFecha.insets = new Insets(0, 0, 5, 5);
				gbcLblFecha.gridx = 1;
				gbcLblFecha.gridy = 1;
				add(lblFecha, gbcLblFecha);
				
				JLabel lblHorario = new JLabel("Horario");
				GridBagConstraints gbcLblHorario = new GridBagConstraints();
				gbcLblHorario.anchor = GridBagConstraints.NORTHWEST;
				gbcLblHorario.insets = new Insets(0, 0, 5, 5);
				gbcLblHorario.gridx = 3;
				gbcLblHorario.gridy = 1;
				add(lblHorario, gbcLblHorario);

		var manana = LocalDate.now().plusDays(1);
		var settings = new DatePickerSettings(Locale.forLanguageTag("es-Latn"));
		settings.setAllowEmptyDates(false);
		calendarPanel = new CalendarPanel(settings);
		settings.setDateRangeLimits(manana, null);
		calendarPanel.setSelectedDate(manana);
		calendarPanel.addCalendarListener(new CalendarListener() {
			public void selectedDateChanged(CalendarSelectionEvent event) {
				actualizarHorarios();
			}
			public void yearMonthChanged(YearMonthChangeEvent event) {
				// No es necesario hacer nada cuando cambia el año o el mes
			}
		});
		GridBagConstraints gbcDatePanel = new GridBagConstraints();
		gbcDatePanel.anchor = GridBagConstraints.WEST;
		gbcDatePanel.gridwidth = 2;
		gbcDatePanel.gridheight = 2;
		gbcDatePanel.insets = new Insets(0, 0, 5, 5);
		gbcDatePanel.gridx = 1;
		gbcDatePanel.gridy = 2;
		add(calendarPanel, gbcDatePanel);
		
		comboBox = new JComboBox<>();
		GridBagConstraints gbcComboBox = new GridBagConstraints();
		gbcComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbcComboBox.anchor = GridBagConstraints.NORTH;
		gbcComboBox.insets = new Insets(0, 0, 5, 5);
		gbcComboBox.gridx = 3;
		gbcComboBox.gridy = 2;
		add(comboBox, gbcComboBox);
		
		JLabel lblMotivo = new JLabel("Motivo");
		GridBagConstraints gbcLblMotivo = new GridBagConstraints();
		gbcLblMotivo.anchor = GridBagConstraints.NORTHWEST;
		gbcLblMotivo.insets = new Insets(0, 0, 5, 5);
		gbcLblMotivo.gridx = 1;
		gbcLblMotivo.gridy = 4;
		add(lblMotivo, gbcLblMotivo);
		
		txtrMotivo = new JTextArea();
		GridBagConstraints gbcTxtrMotivo = new GridBagConstraints();
		gbcTxtrMotivo.fill = GridBagConstraints.BOTH;
		gbcTxtrMotivo.insets = new Insets(0, 0, 5, 5);
		gbcTxtrMotivo.gridwidth = 3;
		gbcTxtrMotivo.gridx = 1;
		gbcTxtrMotivo.gridy = 5;
		add(txtrMotivo, gbcTxtrMotivo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.infoCitas();
			}
		});
		GridBagConstraints gbcBtnCancelar = new GridBagConstraints();
		gbcBtnCancelar.anchor = GridBagConstraints.NORTH;
		gbcBtnCancelar.insets = new Insets(0, 0, 0, 5);
		gbcBtnCancelar.gridx = 1;
		gbcBtnCancelar.gridy = 6;
		add(btnCancelar, gbcBtnCancelar);
		
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
		GridBagConstraints gbcBtnAgendar = new GridBagConstraints();
		gbcBtnAgendar.insets = new Insets(0, 0, 0, 5);
		gbcBtnAgendar.anchor = GridBagConstraints.NORTH;
		gbcBtnAgendar.gridx = 3;
		gbcBtnAgendar.gridy = 6;
		add(btnAgendar, gbcBtnAgendar);
		
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
		var horariosNoDisponiblesParaFecha = this.horariosNoDisponibles.getOrDefault(fecha, null);
		log.info("Fecha seleccionada: {}", fechaSeleccionada);
		log.info("Horarios no disponibles: {}", horariosNoDisponiblesParaFecha);

		if (fecha.isBefore(LocalDate.now().plusDays(1)))
			horariosDisponibles = new ArrayList<>();
		else if (horariosNoDisponiblesParaFecha == null || horariosNoDisponiblesParaFecha.isEmpty())
			horariosDisponibles = horarios;
		else
			horariosDisponibles = horarios
									.stream()
									.filter(hora -> !horariosNoDisponiblesParaFecha.contains(hora))
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
