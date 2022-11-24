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
	
	private ControlPrincipal controlPrincipal;
	private final JLabel lblSesion;

	public VentanaInicio() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 71, 156, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{49, 0, 69, 0, 97, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblBienvenida = new JLabel("Bienvenido al sistema de administración SISGTS");
		GridBagConstraints gbc_lblBienvenida = new GridBagConstraints();
		gbc_lblBienvenida.gridwidth = 3;
		gbc_lblBienvenida.insets = new Insets(0, 0, 5, 5);
		gbc_lblBienvenida.gridx = 1;
		gbc_lblBienvenida.gridy = 1;
		add(lblBienvenida, gbc_lblBienvenida);
		
		lblSesion = new JLabel("Usted no ha inicado sesión");
		GridBagConstraints gbc_lblSesion = new GridBagConstraints();
		gbc_lblSesion.gridwidth = 3;
		gbc_lblSesion.insets = new Insets(0, 0, 5, 5);
		gbc_lblSesion.gridx = 1;
		gbc_lblSesion.gridy = 3;
		add(lblSesion, gbc_lblSesion);
		
		JButton btnLoginagremiado = new JButton("login agremiado");
		btnLoginagremiado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlPrincipal.loginAgremiado();
				lblSesion.setText("Sesión iniciado como agremiado");
			}
		});
		GridBagConstraints gbc_btnLoginagremiado = new GridBagConstraints();
		gbc_btnLoginagremiado.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoginagremiado.gridx = 1;
		gbc_btnLoginagremiado.gridy = 5;
		add(btnLoginagremiado, gbc_btnLoginagremiado);
		
		JButton btnLoginempleado = new JButton("login empleado");
		btnLoginempleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlPrincipal.loginEmpleado();
				lblSesion.setText("Sesión iniciado como empleado");
			}
		});
		GridBagConstraints gbc_btnLoginempleado = new GridBagConstraints();
		gbc_btnLoginempleado.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoginempleado.gridx = 3;
		gbc_btnLoginempleado.gridy = 5;
		add(btnLoginempleado, gbc_btnLoginempleado);
	}
	
	public void muestra(ControlPrincipal controlPrincipal) {
		this.controlPrincipal = controlPrincipal;

		setVisible(true);
	}

}
