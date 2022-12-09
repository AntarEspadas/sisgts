package mx.uam.ayd.proyecto.presentacion.agendar_cita;

import java.util.Comparator;
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
	private transient ControlAgendarCita controlador;

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
		GridBagConstraints gbcLblHorarios = new GridBagConstraints();
		gbcLblHorarios.anchor = GridBagConstraints.NORTH;
		gbcLblHorarios.insets = new Insets(0, 0, 5, 5);
		gbcLblHorarios.gridx = 2;
		gbcLblHorarios.gridy = 1;
		add(lblHorarios, gbcLblHorarios);

		JLabel lblHorarios2 = new JLabel("Lunes a Viernes de 10:00 a 18:00");
		GridBagConstraints gbcLblHorarios2 = new GridBagConstraints();
		gbcLblHorarios2.anchor = GridBagConstraints.NORTH;
		gbcLblHorarios2.insets = new Insets(0, 0, 5, 5);
		gbcLblHorarios2.gridx = 2;
		gbcLblHorarios2.gridy = 3;
		add(lblHorarios2, gbcLblHorarios2);
		
				JLabel lblSusCitas = new JLabel("Sus citas");
				GridBagConstraints gbcLblSusCitas = new GridBagConstraints();
				gbcLblSusCitas.anchor = GridBagConstraints.NORTH;
				gbcLblSusCitas.fill = GridBagConstraints.HORIZONTAL;
				gbcLblSusCitas.insets = new Insets(0, 0, 5, 5);
				gbcLblSusCitas.gridx = 1;
				gbcLblSusCitas.gridy = 4;
				add(lblSusCitas, gbcLblSusCitas);

		listaCitas = new JList<>();
		GridBagConstraints gbcListaCitas = new GridBagConstraints();
		gbcListaCitas.gridwidth = 3;
		gbcListaCitas.fill = GridBagConstraints.BOTH;
		gbcListaCitas.insets = new Insets(0, 0, 5, 5);
		gbcListaCitas.gridx = 1;
		gbcListaCitas.gridy = 5;
		add(listaCitas, gbcListaCitas);

		JButton btnAgendarCita = new JButton("Agendar Cita");
		GridBagConstraints gbcBtnAgendarCita = new GridBagConstraints();
		gbcBtnAgendarCita.insets = new Insets(0, 0, 5, 5);
		gbcBtnAgendarCita.anchor = GridBagConstraints.NORTH;
		gbcBtnAgendarCita.gridx = 1;
		gbcBtnAgendarCita.gridy = 6;
		add(btnAgendarCita, gbcBtnAgendarCita);
		
		btnAgendarCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.agendarCita();
			}
		});

		JButton btnEditarCita = new JButton("Editar Cita");
		GridBagConstraints gbcBtnEditarCita = new GridBagConstraints();
		gbcBtnEditarCita.insets = new Insets(0, 0, 5, 5);
		gbcBtnEditarCita.anchor = GridBagConstraints.NORTH;
		gbcBtnEditarCita.gridx = 2;
		gbcBtnEditarCita.gridy = 6;
		add(btnEditarCita, gbcBtnEditarCita);
		
		btnEditarCita.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listaCitas.getSelectedIndex()==-1){
					controlador.verificar();
				}else {
					int indice = listaCitas.getSelectedIndex();
					var modelo = (DefaultListModel<String>) listaCitas.getModel();
					modelo.remove(indice);
					
					controlador.agendarCita();
					controlador.editarCita();
				}
			}
		});

		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbcBtnEliminar = new GridBagConstraints();
		gbcBtnEliminar.insets = new Insets(0, 0, 5, 5);
		gbcBtnEliminar.anchor = GridBagConstraints.NORTH;
		gbcBtnEliminar.gridx = 3;
		gbcBtnEliminar.gridy = 6;
		add(btnEliminar, gbcBtnEliminar);
		
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listaCitas.getSelectedIndex()==-1){
					controlador.verificar();
				}else {
					
					var modelo = (DefaultListModel<String>) listaCitas.getModel();
					modelo.remove(listaCitas.getSelectedIndex());
					
					controlador.eliminarCita();
				}
			}
		});
	}
	
	public void muestra(ControlAgendarCita controlador, List<Cita> citas) {
		this.controlador = controlador;
		var listModel = new DefaultListModel<String>();
		if (!citas.isEmpty()) {
			
			// Informació para generar formato de las fechas
			var locale = Locale.forLanguageTag("en-Latn");
			var fechaFormatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM", locale);
			var horaFormatter = DateTimeFormatter.ofPattern(", HH:mm", locale);

			var ayer = LocalDate.now().minusDays(1);
			// Selecciona todas las citas con fechas no pasadas, ordena las citas según la fecha
			// y usa los formateadores para conseguir cadenas con la fecha y hora
			listModel.addAll
			(
				citas.stream()
						.filter(cita -> cita.getFecha().isAfter(ayer))
						.sorted(Comparator.comparing(Cita::getFecha))
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
