package mx.uam.ayd.proyecto.presentacion.consultarCitas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.util.Filtro;
import mx.uam.ayd.proyecto.util.Operador;

import java.awt.Rectangle;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * Ventana que permite al personal administrativo consultar una lista de citas
 * 
 * @author Antar Espadas
 */
@Slf4j
public class VentanaConsultarCitas extends JFrame {
	
	private static final String[] columnas = {"Fecha", "Agremiado", "Motivo"};
	
	private List<Filtro> filtros;

	private JTable table;
	private JPanel panelFiltros;

	public VentanaConsultarCitas() {
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 420, 0, 0};
		gridBagLayout.rowHeights = new int[]{50, 152, 0, 212, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
		panelFiltros = new JPanel();
		scrollPane_1.setViewportView(panelFiltros);
		panelFiltros.setLayout(new BoxLayout(panelFiltros, BoxLayout.Y_AXIS));
		
		var model = new DefaultComboBoxModel<String>();
		model.addElement("1");
		model.addElement("2");

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"UwU", "Onii", "Chan"},
				{null, null, null},
			},
			new String[] {
				"Fecha", "Nombre", "Motivo"
			}
		));
		scrollPane.setViewportView(table);
		
		panelFiltros.add(new ComponenteFiltro());
	}
	
	public void muestra(List<Cita> citas, List<Filtro> filtros) {
		
		this.filtros = filtros;

		var filas = citas.stream()
				.sorted()
				.map(cita -> new Object[] {cita.getFecha().toString(), cita.getAgremiado().getNombreCompleto(), cita.getMotivo()})
				.toArray(Object[][]::new);
		
		table.setModel(new DefaultTableModel(filas, columnas));
		
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}
	
	private void agregarFiltro() {
			
		JPanel panelFiltro = new JPanel();
		panelFiltro.setMinimumSize(new Dimension(10, 50));
		panelFiltro.setMaximumSize(new Dimension(32767, 40));
		panelFiltro.setBounds(new Rectangle(0, 0, 50, 50));
		panelFiltros.add(panelFiltro);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{126, 0, 58, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelFiltro.setLayout(gbl_panel_1);
		
		var comboBoxModel = new DefaultComboBoxModel<FiltroModel>();
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_EXACTA, "fecha", "Fecha exacta"));
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_ANTES, "fecha", "Fecha antes"));
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_DESPUES, "fecha", "Fecha despues"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.nombre", "Nombre"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.apellido", "Apellido"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "motivo", "Motivo"));
		
		JComboBox<FiltroModel> comboBox = new JComboBox<>();
		comboBox.setModel(comboBoxModel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panelFiltro.add(comboBox, gbc_comboBox);
		
		JTextField txtCampo = new JTextField();
		txtCampo.setColumns(10);
		
		JDatePicker dateCampo = new JDatePicker();
		
		GridBagConstraints gbc_Campo = new GridBagConstraints();
		gbc_Campo.insets = new Insets(0, 0, 0, 5);
		gbc_Campo.fill = GridBagConstraints.HORIZONTAL;
		gbc_Campo.gridx = 1;
		gbc_Campo.gridy = 0;
		panelFiltro.add(dateCampo, gbc_Campo);
		
		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 0;
		panelFiltro.add(btnEliminar, gbc_btnEliminar);
			
	}

}