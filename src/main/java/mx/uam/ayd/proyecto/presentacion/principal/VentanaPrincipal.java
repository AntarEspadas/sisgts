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


@SuppressWarnings("serial")
@Component
public class VentanaPrincipal extends JFrame {

	private final JPanel contentPane;
	
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
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				control.publicaciones();
			}
		});
		GridBagConstraints gbc_btnPublicaciones = new GridBagConstraints();
		gbc_btnPublicaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPublicaciones.gridx = 0;
		gbc_btnPublicaciones.gridy = 3;
		panel.add(btnPublicaciones, gbc_btnPublicaciones);
		
		panelContenido = new JPanel();
		gbc_contenido = new GridBagConstraints();
		gbc_contenido.fill = GridBagConstraints.BOTH;
		gbc_contenido.gridx = 2;
		gbc_contenido.gridy = 0;
		//contentPane.add(panelContenido, gbc_contenido);
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
