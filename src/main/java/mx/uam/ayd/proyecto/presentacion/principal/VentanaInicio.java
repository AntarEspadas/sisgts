package mx.uam.ayd.proyecto.presentacion.principal;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

import org.springframework.stereotype.Component;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class VentanaInicio extends Pantalla {
	
	private transient ControlPrincipal controlPrincipal;
	private final JLabel lblSesion;

	public VentanaInicio() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 71, 156, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{49, 0, 69, 0, 97, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblBienvenida = new JLabel("Bienvenido al sistema de administración SISGTS");
		GridBagConstraints gbcLblBienvenida = new GridBagConstraints();
		gbcLblBienvenida.gridwidth = 3;
		gbcLblBienvenida.insets = new Insets(0, 0, 5, 5);
		gbcLblBienvenida.gridx = 1;
		gbcLblBienvenida.gridy = 1;
		add(lblBienvenida, gbcLblBienvenida);
		
		lblSesion = new JLabel("Usted no ha inicado sesión");
		GridBagConstraints gbcLblSesion = new GridBagConstraints();
		gbcLblSesion.gridwidth = 3;
		gbcLblSesion.insets = new Insets(0, 0, 5, 5);
		gbcLblSesion.gridx = 1;
		gbcLblSesion.gridy = 3;
		add(lblSesion, gbcLblSesion);
		
		JButton btnLoginagremiado = new JButton("login agremiado");
		btnLoginagremiado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlPrincipal.loginAgremiado();
				lblSesion.setText("Sesión iniciado como agremiado");
			}
		});
		GridBagConstraints gbcBtnLoginagremiado = new GridBagConstraints();
		gbcBtnLoginagremiado.insets = new Insets(0, 0, 5, 5);
		gbcBtnLoginagremiado.gridx = 1;
		gbcBtnLoginagremiado.gridy = 5;
		add(btnLoginagremiado, gbcBtnLoginagremiado);
		
		JButton btnLoginempleado = new JButton("login empleado");
		btnLoginempleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				controlPrincipal.loginEmpleado();
				lblSesion.setText("Sesión iniciado como empleado");
			}
		});
		GridBagConstraints gbcBtnLoginempleado = new GridBagConstraints();
		gbcBtnLoginempleado.insets = new Insets(0, 0, 5, 5);
		gbcBtnLoginempleado.gridx = 3;
		gbcBtnLoginempleado.gridy = 5;
		add(btnLoginempleado, gbcBtnLoginempleado);
	}
	
	public void muestra(ControlPrincipal controlPrincipal) {
		this.controlPrincipal = controlPrincipal;

		setVisible(true);
	}

}
