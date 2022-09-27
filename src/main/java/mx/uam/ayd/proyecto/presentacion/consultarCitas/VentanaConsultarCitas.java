package mx.uam.ayd.proyecto.presentacion.consultarCitas;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import mx.uam.ayd.proyecto.util.Filtro;

import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

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

	private final JTable table;
	private final JPanel panelFiltros;
	private final JButton btnTerminar;
	private final JButton btnAgregarFiltro;
	private final JScrollPane scrollPane_1;
	private final JButton btnEditarFiltros;

	public VentanaConsultarCitas() {
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 420, 0, 0};
		gridBagLayout.rowHeights = new int[]{30, 1, 1, 212, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnEditarFiltros = new JButton("Editar filtros");
		btnEditarFiltros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEditarFiltros.setVisible(false);
				btnTerminar.setVisible(true);
				btnAgregarFiltro.setVisible(true);
				scrollPane_1.setVisible(true);
				
				gridBagLayout.rowHeights[0] = 1;
				gridBagLayout.rowHeights[1] = 152;
				gridBagLayout.rowHeights[2] = 40;
			}
		});
		GridBagConstraints gbc_btnEditarFiltros = new GridBagConstraints();
		gbc_btnEditarFiltros.anchor = GridBagConstraints.SOUTHWEST;
		gbc_btnEditarFiltros.insets = new Insets(0, 0, 5, 5);
		gbc_btnEditarFiltros.gridx = 1;
		gbc_btnEditarFiltros.gridy = 0;
		add(btnEditarFiltros, gbc_btnEditarFiltros);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVisible(false);
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
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.setVisible(false);
		btnTerminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEditarFiltros.setVisible(true);
				btnTerminar.setVisible(false);
				btnAgregarFiltro.setVisible(false);
				scrollPane_1.setVisible(false);
				
				
				gridBagLayout.rowHeights[0] = 30;
				gridBagLayout.rowHeights[1] = 1;
				gridBagLayout.rowHeights[2] = 1;
			}
		});
		GridBagConstraints gbc_btnTerminar = new GridBagConstraints();
		gbc_btnTerminar.fill = GridBagConstraints.VERTICAL;
		gbc_btnTerminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnTerminar.gridx = 0;
		gbc_btnTerminar.gridy = 0;
		panel.add(btnTerminar, gbc_btnTerminar);
		
		btnAgregarFiltro = new JButton("Agregar filtro");
		btnAgregarFiltro.setVisible(false);
		GridBagConstraints gbc_btnAgregarFiltro = new GridBagConstraints();
		gbc_btnAgregarFiltro.gridx = 2;
		gbc_btnAgregarFiltro.gridy = 0;
		panel.add(btnAgregarFiltro, gbc_btnAgregarFiltro);
		btnAgregarFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarFiltro();
			}
		});
		btnAgregarFiltro.addActionListener(e -> {
		});

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
				.map(cita -> new Object[] {cita.getFecha().toString() + " - " + cita.getHora().toString(), cita.getAgremiado().getNombreCompleto(), cita.getMotivo()})
				.toArray(Object[][]::new);

		table.setModel(new DefaultTableModel(filas, columnas));

		revalidate();
		repaint();
	}

	public void cierra() {
		setVisible(false);
	}
	

}