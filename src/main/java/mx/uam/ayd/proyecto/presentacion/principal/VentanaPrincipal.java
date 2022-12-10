package mx.uam.ayd.proyecto.presentacion.principal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


@Component
public class VentanaPrincipal extends JFrame {

	private final JPanel contentPane;
	
	private transient ControlPrincipal control;
	private java.awt.Component componente;

	GridBagConstraints gbcContenido;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gblContentPane = new GridBagLayout();
		gblContentPane.columnWidths = new int[]{172, 0, 0, 0};
		gblContentPane.rowHeights = new int[]{0, 0};
		gblContentPane.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gblContentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gblContentPane);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(0, 0, 0, 5);
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		contentPane.add(panel, gbcPanel);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[]{0, 0};
		gblPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gblPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gblPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gblPanel);
		
		JButton btnInicio = new JButton("Inicio");
		btnInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.ventanaInicio();
			}
		});
		GridBagConstraints gbcBtnInicio = new GridBagConstraints();
		gbcBtnInicio.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnInicio.insets = new Insets(0, 0, 5, 0);
		gbcBtnInicio.gridx = 0;
		gbcBtnInicio.gridy = 0;
		panel.add(btnInicio, gbcBtnInicio);
		
		JButton btnTramites = new JButton("Trámites");
		btnTramites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.tramites();
			}
		});
		GridBagConstraints gbcBtnTramites = new GridBagConstraints();
		gbcBtnTramites.insets = new Insets(0, 0, 5, 0);
		gbcBtnTramites.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnTramites.gridx = 0;
		gbcBtnTramites.gridy = 1;
		panel.add(btnTramites, gbcBtnTramites);
		
		JButton btnCitas = new JButton("Citas");
		btnCitas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.citas();
			}
		});
		GridBagConstraints gbcBtnCitas = new GridBagConstraints();
		gbcBtnCitas.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnCitas.insets = new Insets(0, 0, 5, 0);
		gbcBtnCitas.gridx = 0;
		gbcBtnCitas.gridy = 2;
		panel.add(btnCitas, gbcBtnCitas);
		
		JButton btnPublicaciones = new JButton("Publicaciones");
		btnPublicaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.publicaciones();
			}
		});
		GridBagConstraints gbcBtnPublicaciones = new GridBagConstraints();
		gbcBtnPublicaciones.insets = new Insets(0, 0, 5, 0);
		gbcBtnPublicaciones.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnPublicaciones.gridx = 0;
		gbcBtnPublicaciones.gridy = 3;
		panel.add(btnPublicaciones, gbcBtnPublicaciones);
		
		JButton btnNotificaciones = new JButton("Avisos");
		btnNotificaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.avisos();
			}
		});
		GridBagConstraints gbcBtnNotificaciones = new GridBagConstraints();
		gbcBtnNotificaciones.insets = new Insets(0, 0, 5, 0);
		gbcBtnNotificaciones.fill = GridBagConstraints.HORIZONTAL;
		gbcBtnNotificaciones.gridx = 0;
		gbcBtnNotificaciones.gridy = 4;
		panel.add(btnNotificaciones, gbcBtnNotificaciones);
		
		gbcContenido = new GridBagConstraints();
		gbcContenido.fill = GridBagConstraints.BOTH;
		gbcContenido.gridx = 2;
		gbcContenido.gridy = 0;
	}
	
	public void muestra(ControlPrincipal control) {
		
		this.control = control;
	}
	
	public void muestraComponente(java.awt.Component componente) {
		if (this.componente != null)
			contentPane.remove(this.componente);
		this.componente = componente;
		contentPane.add(componente, gbcContenido);
		repaint();
		setVisible(true);
	}

	public void quitaComponente(java.awt.Component componente){
		contentPane.remove(componente);
		repaint();
	}
}
