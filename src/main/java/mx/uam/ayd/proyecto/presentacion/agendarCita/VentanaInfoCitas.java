package mx.uam.ayd.proyecto.presentacion.agendarCita;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.JList;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import org.springframework.stereotype.Component;

import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



/**
 * Ventana que muestra información de las citas agendadas por el usuario
 * 
 * @author Antar Espadas
 */
@Component
public class VentanaInfoCitas extends Pantalla {
	
	private ControlAgendarCita controlador;

	private final JList<String> listaCitas;

	public VentanaInfoCitas() {
		setBounds(new Rectangle(100, 100, 500, 400));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{36, 64, 321, 58, 0, 0};
		gridBagLayout.rowHeights = new int[]{37, 17, 30, 17, 17, 164, 27, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblHorarios = new JLabel("Horarios");
		GridBagConstraints gbc_lblHorarios = new GridBagConstraints();
		gbc_lblHorarios.anchor = GridBagConstraints.NORTH;
		gbc_lblHorarios.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorarios.gridx = 2;
		gbc_lblHorarios.gridy = 1;
		add(lblHorarios, gbc_lblHorarios);

		JLabel lblHorarios2 = new JLabel("Lunes a Viernes de 10:00 a 18:00");
		GridBagConstraints gbc_lblHorarios2 = new GridBagConstraints();
		gbc_lblHorarios2.anchor = GridBagConstraints.NORTH;
		gbc_lblHorarios2.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorarios2.gridx = 2;
		gbc_lblHorarios2.gridy = 3;
		add(lblHorarios2, gbc_lblHorarios2);
		
				JLabel lblSusCitas = new JLabel("Sus citas");
				GridBagConstraints gbc_lblSusCitas = new GridBagConstraints();
				gbc_lblSusCitas.anchor = GridBagConstraints.NORTH;
				gbc_lblSusCitas.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblSusCitas.insets = new Insets(0, 0, 5, 5);
				gbc_lblSusCitas.gridx = 1;
				gbc_lblSusCitas.gridy = 4;
				add(lblSusCitas, gbc_lblSusCitas);

		listaCitas = new JList<>();
		GridBagConstraints gbc_listaCitas = new GridBagConstraints();
		gbc_listaCitas.gridwidth = 3;
		gbc_listaCitas.fill = GridBagConstraints.BOTH;
		gbc_listaCitas.insets = new Insets(0, 0, 5, 5);
		gbc_listaCitas.gridx = 1;
		gbc_listaCitas.gridy = 5;
		add(listaCitas, gbc_listaCitas);

		JButton btnAgendarCita = new JButton("Agendar Cita");
		GridBagConstraints gbc_btnAgendarCita = new GridBagConstraints();
		gbc_btnAgendarCita.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgendarCita.anchor = GridBagConstraints.NORTH;
		gbc_btnAgendarCita.gridx = 2;
		gbc_btnAgendarCita.gridy = 6;
		add(btnAgendarCita, gbc_btnAgendarCita);
		


		btnAgendarCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.agendarCita();
			}
		});
	}
	
	public void muestra(ControlAgendarCita controlador, List<Cita> citas) {
		this.controlador = controlador;
		var listModel = new DefaultListModel<String>();
		if (citas.size() != 0) {
			
			// Informació para generar formato de las fechas
			var locale = new Locale("es", "MX");
			var fechaFormatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM", locale);
			var horaFormatter = DateTimeFormatter.ofPattern(", HH:mm", locale);

			var ayer = LocalDate.now().minusDays(1);
			// Selecciona todas las citas con fechas no pasadas, ordena las citas según la fecha
			// y usa los formateadores para conseguir cadenas con la fecha y hora
			listModel.addAll
			(
				citas.stream()
					.filter(cita -> cita.getFecha().isAfter(ayer))
					.sorted()
					.map(cita -> cita.getFecha().format(fechaFormatter) + cita.getHora().format(horaFormatter))
					.collect(Collectors.toList())
			);
		}
		else
			listModel.addElement("Usted no tiene ninguna cita pendiente");

		listaCitas.setModel(listModel);
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}
}
