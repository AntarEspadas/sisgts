package mx.uam.ayd.proyecto.presentacion.principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;


@SuppressWarnings("serial")
@Component
public class VentanaPrincipal extends JFrame {

	private final JPanel contentPane;
	private JPanel panel1;
	private ControlPrincipal control;
	private final JPanel panelContenido;
	
	private java.awt.Component componente;

	GridBagConstraints gbc_contenido;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{172, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		panel1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Registrar Empleado");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		panel1.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnRegistrar = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar = new GridBagConstraints();
		gbc_btnRegistrar.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegistrar.gridx = 0;
		gbc_btnRegistrar.gridy = 4;
		panel1.add(btnRegistrar, gbc_btnRegistrar);
		
		JLabel lblNewLabel_1 = new JLabel("Registrar Agremiado");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 5;
		panel1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnRegistrar_1 = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrar_1 = new GridBagConstraints();
		gbc_btnRegistrar_1.fill = GridBagConstraints.VERTICAL;
		gbc_btnRegistrar_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnRegistrar_1.gridx = 0;
		gbc_btnRegistrar_1.gridy = 6;
		panel1.add(btnRegistrar_1, gbc_btnRegistrar_1);
		
		panel1.setVisible(false);
		
		btnRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.RegistraEmpleado();
			}
		});
		
		btnRegistrar_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.RegistraAgremiado();
			}
		});
		
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel1.setVisible(false);
				control.ventanaInicio();
			}
		});
		GridBagConstraints gbc_btnInicio = new GridBagConstraints();
		gbc_btnInicio.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInicio.insets = new Insets(0, 0, 5, 0);
		gbc_btnInicio.gridx = 0;
		gbc_btnInicio.gridy = 0;
		panel.add(btnInicio, gbc_btnInicio);
		
		JButton btnTramites = new JButton("Trámites");
		btnTramites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel1.setVisible(false);				
				control.tramites();
			}
		});
		GridBagConstraints gbc_btnTramites = new GridBagConstraints();
		gbc_btnTramites.insets = new Insets(0, 0, 5, 0);
		gbc_btnTramites.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTramites.gridx = 0;
		gbc_btnTramites.gridy = 1;
		panel.add(btnTramites, gbc_btnTramites);
		
		JButton btnCitas = new JButton("Citas");
		btnCitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel1.setVisible(false);
				control.citas();
			}
		});
		GridBagConstraints gbc_btnCitas = new GridBagConstraints();
		gbc_btnCitas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCitas.insets = new Insets(0, 0, 5, 0);
		gbc_btnCitas.gridx = 0;
		gbc_btnCitas.gridy = 2;
		panel.add(btnCitas, gbc_btnCitas);
		
		JButton btnPublicaciones = new JButton("Publicaciones");
		btnPublicaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel1.setVisible(false);
				control.publicaciones();
			}
		});
		GridBagConstraints gbc_btnPublicaciones = new GridBagConstraints();
		gbc_btnPublicaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPublicaciones.insets = new Insets(0, 0, 5, 0);
		gbc_btnPublicaciones.gridx = 0;
		gbc_btnPublicaciones.gridy = 3;
		panel.add(btnPublicaciones, gbc_btnPublicaciones);
		
		JButton btnNewButton = new JButton("Administrador");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel1.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		panelContenido = new JPanel();
		gbc_contenido = new GridBagConstraints();
		gbc_contenido.fill = GridBagConstraints.BOTH;
		gbc_contenido.gridx = 2;
		gbc_contenido.gridy = 0;
		contentPane.add(panelContenido, gbc_contenido);
	}
	
	public void muestra(ControlPrincipal control) {
		
		this.control = control;
	}
	
	public void muestraComponente(java.awt.Component componente) {
		if (this.componente != null)
			contentPane.remove(this.componente);
		this.componente = componente;
		contentPane.add(componente, gbc_contenido);
		repaint();
		setVisible(true);
	}

	public void quitaComponente(java.awt.Component componente){
		contentPane.remove(componente);
		repaint();
	}
}
