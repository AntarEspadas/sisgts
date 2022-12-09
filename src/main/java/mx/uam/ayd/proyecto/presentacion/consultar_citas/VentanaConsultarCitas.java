package mx.uam.ayd.proyecto.presentacion.consultar_citas;

import java.awt.GridBagLayout;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Comparator;
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
	
	private transient ControlConsultarCitas controlador;
	
	private transient List<Filtro> filtros;

	private final JTable table;
	private final JPanel panelFiltros;
	private final JButton btnTerminar;
	private final JButton btnAgregarFiltro;
	private final JScrollPane scrollPane1;
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
				scrollPane1.setVisible(true);
				
				gridBagLayout.rowHeights[0] = 1;
				gridBagLayout.rowHeights[1] = 152;
				gridBagLayout.rowHeights[2] = 40;
			}
		});
		GridBagConstraints gbcBtnEditarFiltros = new GridBagConstraints();
		gbcBtnEditarFiltros.anchor = GridBagConstraints.SOUTHWEST;
		gbcBtnEditarFiltros.insets = new Insets(0, 0, 5, 5);
		gbcBtnEditarFiltros.gridx = 1;
		gbcBtnEditarFiltros.gridy = 0;
		add(btnEditarFiltros, gbcBtnEditarFiltros);
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setVisible(false);
		GridBagConstraints gbcScrollPane1 = new GridBagConstraints();
		gbcScrollPane1.insets = new Insets(0, 0, 5, 5);
		gbcScrollPane1.fill = GridBagConstraints.BOTH;
		gbcScrollPane1.gridx = 1;
		gbcScrollPane1.gridy = 1;
		add(scrollPane1, gbcScrollPane1);
		
		panelFiltros = new JPanel();
		scrollPane1.setViewportView(panelFiltros);
		panelFiltros.setLayout(new BoxLayout(panelFiltros, BoxLayout.Y_AXIS));
		
		var model = new DefaultComboBoxModel<String>();
		model.addElement("1");
		model.addElement("2");
		
		JPanel panel = new JPanel();
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(0, 0, 5, 5);
		gbcPanel.fill = GridBagConstraints.HORIZONTAL;
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 2;
		add(panel, gbcPanel);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[]{0, 0, 0, 0};
		gblPanel.rowHeights = new int[]{0, 0};
		gblPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gblPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gblPanel);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.setVisible(false);
		btnTerminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEditarFiltros.setVisible(true);
				btnTerminar.setVisible(false);
				btnAgregarFiltro.setVisible(false);
				scrollPane1.setVisible(false);
				
				
				gridBagLayout.rowHeights[0] = 30;
				gridBagLayout.rowHeights[1] = 1;
				gridBagLayout.rowHeights[2] = 1;
			}
		});
		GridBagConstraints gbcBtnTerminar = new GridBagConstraints();
		gbcBtnTerminar.fill = GridBagConstraints.VERTICAL;
		gbcBtnTerminar.insets = new Insets(0, 0, 0, 5);
		gbcBtnTerminar.gridx = 0;
		gbcBtnTerminar.gridy = 0;
		panel.add(btnTerminar, gbcBtnTerminar);
		
		btnAgregarFiltro = new JButton("Agregar filtro");
		btnAgregarFiltro.setVisible(false);
		GridBagConstraints gbcBtnAgregarFiltro = new GridBagConstraints();
		gbcBtnAgregarFiltro.gridx = 2;
		gbcBtnAgregarFiltro.gridy = 0;
		panel.add(btnAgregarFiltro, gbcBtnAgregarFiltro);
		btnAgregarFiltro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				agregarFiltro();
			}
		});
		btnAgregarFiltro.addActionListener(e -> {
		});

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 5);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 1;
		gbcScrollPane.gridy = 3;
		add(scrollPane, gbcScrollPane);
		
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
				.sorted(Comparator.comparing(Cita::getFecha))
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