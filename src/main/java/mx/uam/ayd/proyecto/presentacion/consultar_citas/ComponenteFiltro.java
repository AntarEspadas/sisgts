package mx.uam.ayd.proyecto.presentacion.consultar_citas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.util.Filtro;
import mx.uam.ayd.proyecto.util.Operador;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

@Slf4j
public class ComponenteFiltro extends JPanel {
	
	private final ArrayList<CambioFiltroListener> listeners = new ArrayList<>();

	@Getter
	private final JButton btnEliminar;
	
	@Getter
	private final transient Filtro filtro;
	
	private transient FiltroModel opcionSeleccionada;
	
	private final DatePicker campoFecha;
	
	private final JTextField campoTexto;

	GridBagConstraints gbcCampo;
	
	public ComponenteFiltro() {

		filtro = new Filtro("fecha", Operador.FECHA_EXACTA, LocalDate.now());

		setMinimumSize(new Dimension(10, 50));
		setMaximumSize(new Dimension(32767, 50));
		setBounds(new Rectangle(0, 0, 50, 50));
		GridBagLayout gblPanel1 = new GridBagLayout();
		gblPanel1.columnWidths = new int[]{126, 0, 58, 0};
		gblPanel1.rowHeights = new int[]{0, 0};
		gblPanel1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gblPanel1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gblPanel1);

		var fecha = "fecha";

		var comboBoxModel = new DefaultComboBoxModel<FiltroModel>();
		opcionSeleccionada = new FiltroModel(Operador.FECHA_EXACTA, fecha, "Fecha exacta");
		comboBoxModel.addElement(opcionSeleccionada);
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_ANTES, fecha, "Fecha antes"));
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_DESPUES, fecha, "Fecha despues"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.nombre", "Nombre"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.apellidos", "Apellido"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "motivo", "Motivo"));
		
		JComboBox<FiltroModel> comboBox = new JComboBox<>();

		comboBox.setModel(comboBoxModel);
		GridBagConstraints gbcComboBox = new GridBagConstraints();
		gbcComboBox.insets = new Insets(0, 0, 0, 5);
		gbcComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbcComboBox.gridx = 0;
		gbcComboBox.gridy = 0;
		add(comboBox, gbcComboBox);
		
		campoTexto = new JTextField();
		campoTexto.setColumns(10);
		campoTexto.setText("");
		campoTexto.getDocument().addDocumentListener(new DocumentListener() {
			private void actualiza() {
				filtro.setValor(campoTexto.getText());
				ejecutaListeners();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				actualiza();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualiza();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				actualiza();
			}
		});
		
		var datePickerSettings = new DatePickerSettings();
		var locale = Locale.forLanguageTag("es-Latn");
		datePickerSettings.setLocale(locale);
		datePickerSettings.setAllowEmptyDates(false);
		datePickerSettings.setAllowKeyboardEditing(false);

		campoFecha = new DatePicker(datePickerSettings);
		campoFecha.addDateChangeListener(event -> {
			if (event.getOldDate().isEqual(event.getNewDate())) return;

			filtro.setValor(event.getNewDate());

			ejecutaListeners();
		});
		campoFecha.setDateToToday();
		
		gbcCampo = new GridBagConstraints();
		gbcCampo.fill = GridBagConstraints.HORIZONTAL;
		gbcCampo.insets = new Insets(0, 0, 0, 5);
		gbcCampo.gridx = 1;
		gbcCampo.gridy = 0;
		
		add(campoFecha, gbcCampo);

		btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbcBtnEliminar = new GridBagConstraints();
		gbcBtnEliminar.gridx = 2;
		gbcBtnEliminar.gridy = 0;
		add(btnEliminar, gbcBtnEliminar);


		comboBox.addItemListener(e -> {
			var filtroModel = (FiltroModel)e.getItem();
			if (opcionSeleccionada == filtroModel) return;
			opcionSeleccionada = filtroModel;

			filtro.setOperador(filtroModel.getOperador());
			filtro.setAtributo(filtroModel.getAtributo());

			if (opcionSeleccionada.getOperador() == Operador.CONTIENE) {
				remove(campoFecha);
				add(campoTexto, gbcCampo);
				filtro.setValor(campoTexto.getText());
			}
			else {
				remove(campoTexto);
				add(campoFecha, gbcCampo);
				filtro.setValor(campoFecha.getDate());
			}

			revalidate();
			repaint();

			ejecutaListeners();
		});
	}
	
	private void ejecutaListeners() {
		for (var listener : listeners) {
			if (listener == null) continue;
			listener.cambioFiltro(filtro);
		}
	}
	
	public void addCambioFiltroListener(CambioFiltroListener listener) {
		listeners.add(listener);
	}

}

interface CambioFiltroListener{
	void cambioFiltro(Filtro filtro);
}

@Getter
@AllArgsConstructor
class FiltroModel{
	private Operador operador;
	
	private String atributo;
	
	private String etiqueta;
	
	@Override
	public String toString() {
		return etiqueta;
	}
}

