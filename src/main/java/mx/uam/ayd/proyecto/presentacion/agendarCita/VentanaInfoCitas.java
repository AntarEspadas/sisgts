package mx.uam.ayd.proyecto.presentacion.agendarCita;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JList;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;

import org.springframework.stereotype.Component;

import lombok.*;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * Ventana que muestra información de las citas agendadas por el usuario
 * 
 * @author Antar Espadas
 */
@Component
public class VentanaInfoCitas extends JFrame {
	
	private ControlAgendarCita controlador;

	private JList<String> listaCitas;

	public VentanaInfoCitas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(new Rectangle(100, 100, 500, 400));
		getContentPane().setLayout(null);
		
		listaCitas = new JList<>();
		listaCitas.setBounds(36, 145, 430, 164);
		getContentPane().add(listaCitas);
		
		JLabel lblSusCitas = new JLabel("Sus citas");
		lblSusCitas.setBounds(36, 116, 60, 17);
		getContentPane().add(lblSusCitas);
		
		JLabel lblHorarios = new JLabel("Horarios");
		lblHorarios.setBounds(217, 37, 60, 17);
		getContentPane().add(lblHorarios);
		
		JLabel lblHorarios2 = new JLabel("Lunes a Viernes de 10:00 a 18:00");
		lblHorarios2.setBounds(145, 84, 197, 17);
		getContentPane().add(lblHorarios2);
		
		JButton btnAgendarCita = new JButton("Agendar Cita");
		btnAgendarCita.setBounds(188, 321, 112, 27);
		getContentPane().add(btnAgendarCita);
		


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
					.sorted((c1, c2) -> c1.getFecha().compareTo(c2.getFecha()))
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
