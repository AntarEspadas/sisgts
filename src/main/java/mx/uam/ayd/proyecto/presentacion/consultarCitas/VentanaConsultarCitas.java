package mx.uam.ayd.proyecto.presentacion.consultarCitas;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.JDatePicker;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.Pantalla;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana que permite al personal administrativo consultar una lista de citas
 * 
 * @author Antar Espadas
 */
@Slf4j
@Component
public class VentanaConsultarCitas extends Pantalla {
	
	private static final String[] columnas = {"Fecha", "Agremiado", "Motivo"};
	
	private ControlConsultarCitas controlador;
	
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
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		add(scrollPane_1, gbc_scrollPane_1);
		
		panelFiltros = new JPanel();
		scrollPane_1.setViewportView(panelFiltros);
		panelFiltros.setLayout(new BoxLayout(panelFiltros, BoxLayout.Y_AXIS));
		
		var model = new DefaultComboBoxModel<String>();
		model.addElement("1");
		model.addElement("2");
		
		JButton btnAgregarFiltro = new JButton("Agregar filtro");
		btnAgregarFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarFiltro();
			}
		});
		btnAgregarFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAgregarFiltro = new GridBagConstraints();
		gbc_btnAgregarFiltro.anchor = GridBagConstraints.EAST;
		gbc_btnAgregarFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgregarFiltro.gridx = 1;
		gbc_btnAgregarFiltro.gridy = 2;
		add(btnAgregarFiltro, gbc_btnAgregarFiltro);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
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
	}
	
	public void muestra(ControlConsultarCitas controlador) {
		
		this.controlador = controlador;
		
		filtros = new ArrayList<>();
		panelFiltros.removeAll();
		agregarFiltro();
		
		setVisible(true);
	}
	
	
	private void agregarFiltro() {
		var componenteFiltro = new ComponenteFiltro();
		filtros.add(componenteFiltro.getFiltro());
		
		log.info("filtros = {}", filtros);
		
		componenteFiltro.addCambioFiltroListener(filtro -> {
			log.info("Aplicando filtro {}", filtro);
			actualizaCitas();
		});
		
		componenteFiltro.getBtnEliminar().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelFiltros.remove(componenteFiltro);
				filtros.remove(componenteFiltro.getFiltro());

				log.info("filtros = {}", filtros);

				actualizaCitas();
			}
		});
		
		panelFiltros.add(componenteFiltro);
		
		actualizaCitas();
		
	}
	
	private void actualizaCitas() {
		var citas = controlador.getCitas(new ArrayList<>(filtros));
		mostrarCitas(citas);
	}
	
	private void mostrarCitas(List<Cita> citas) {
		var filas = citas.stream()
				.sorted()
				.map(cita -> new Object[] {cita.getFecha().toString(), cita.getAgremiado().getNombreCompleto(), cita.getMotivo()})
				.toArray(Object[][]::new);

		table.setModel(new DefaultTableModel(filas, columnas));

		revalidate();
		repaint();
	}

	public void cierra() {
		setVisible(false);
	}
	

}