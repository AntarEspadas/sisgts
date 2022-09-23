package mx.uam.ayd.proyecto.presentacion.consultarCitas;

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
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

@Slf4j
public class ComponenteFiltro extends JPanel {
	
	private final ArrayList<CambioFiltroListener> listeners = new ArrayList<>();

	@Getter
	private final JButton btnEliminar;
	
	@Getter
	private final Filtro filtro;
	
	private FiltroModel opcionSeleccionada;
	
	private final DatePicker campoFecha;
	
	private final JTextField campoTexto;

	GridBagConstraints gbc_campo;
	
	public ComponenteFiltro() {
					
		setMinimumSize(new Dimension(10, 50));
		setMaximumSize(new Dimension(32767, 50));
		setBounds(new Rectangle(0, 0, 50, 50));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{126, 0, 58, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gbl_panel_1);
		
		var comboBoxModel = new DefaultComboBoxModel<FiltroModel>();
		opcionSeleccionada = new FiltroModel(Operador.FECHA_EXACTA, "fecha", "Fecha exacta");
		comboBoxModel.addElement(opcionSeleccionada);
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_ANTES, "fecha", "Fecha antes"));
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_DESPUES, "fecha", "Fecha despues"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.nombre", "Nombre"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.apellidos", "Apellido"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "motivo", "Motivo"));
		
		JComboBox<FiltroModel> comboBox = new JComboBox<>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				var filtroModel = (FiltroModel)e.getItem();
				if (opcionSeleccionada == filtroModel) return;
				opcionSeleccionada = filtroModel;
				
				filtro.setOperador(filtroModel.getOperador());
				filtro.setAtributo(filtroModel.getAtributo());
				
				if (opcionSeleccionada.getOperador() == Operador.CONTIENE) {
					remove(campoFecha);
					add(campoTexto, gbc_campo);
					filtro.setValor(campoTexto.getText());
				}
				else {
					remove(campoTexto);
					add(campoFecha, gbc_campo);
					filtro.setValor(campoFecha.getDate());
				}
				
				revalidate();
				repaint();
				
				ejecutaListeners();
			}
		});
		comboBox.setModel(comboBoxModel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
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
		datePickerSettings.setLocale(new Locale("es", "MX"));
		datePickerSettings.setAllowEmptyDates(false);
		datePickerSettings.setAllowKeyboardEditing(false);

		campoFecha = new DatePicker(datePickerSettings);
		campoFecha.addDateChangeListener(new DateChangeListener() {
			public void dateChanged(DateChangeEvent event) {
				if (event.getOldDate().isEqual(event.getNewDate())) return;
				
				filtro.setValor(event.getNewDate());
				
				ejecutaListeners();
			}
		});
		campoFecha.setDateToToday();
		
		gbc_campo = new GridBagConstraints();
		gbc_campo.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo.insets = new Insets(0, 0, 0, 5);
		gbc_campo.gridx = 1;
		gbc_campo.gridy = 0;
		
		add(campoFecha, gbc_campo);
		//add(campoTexto, gbc_campo);
		
		btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 0;
		add(btnEliminar, gbc_btnEliminar);
		
		filtro = new Filtro("fecha", Operador.FECHA_EXACTA, LocalDate.now());
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

