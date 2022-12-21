package mx.uam.ayd.proyecto.presentacion.cambiar_contrasena;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import org.springframework.stereotype.Component;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.Arrays;

@Slf4j
@Component
public class VentanaCambiarContrasena extends Pantalla {

	private transient ControlCambiarContrasena controlador;
	private final JPasswordField txtContraseaActual;
	private final JPasswordField txtContraseaNueva;
	private final JPasswordField txtConfirmarContraseaNueva;
	private final JButton btnCambiarContrasea;
	private final JLabel lblLasContraseasNo;

	public VentanaCambiarContrasena() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblContraseaActual = new JLabel("Contraseña actual");
		GridBagConstraints gbcLblContraseaActual = new GridBagConstraints();
		gbcLblContraseaActual.insets = new Insets(0, 0, 5, 5);
		gbcLblContraseaActual.gridx = 1;
		gbcLblContraseaActual.gridy = 1;
		add(lblContraseaActual, gbcLblContraseaActual);

		txtContraseaActual = new JPasswordField();
		GridBagConstraints gbcTxtContraseaActual = new GridBagConstraints();
		gbcTxtContraseaActual.insets = new Insets(0, 0, 5, 5);
		gbcTxtContraseaActual.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtContraseaActual.gridx = 1;
		gbcTxtContraseaActual.gridy = 2;
		add(txtContraseaActual, gbcTxtContraseaActual);
		txtContraseaActual.setColumns(10);

		txtContraseaActual.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}
		});
		
		JLabel lblContraseaNueva = new JLabel("Contraseña nueva");
		GridBagConstraints gbcLblContraseaNueva = new GridBagConstraints();
		gbcLblContraseaNueva.insets = new Insets(0, 0, 5, 5);
		gbcLblContraseaNueva.gridx = 1;
		gbcLblContraseaNueva.gridy = 3;
		add(lblContraseaNueva, gbcLblContraseaNueva);
		
		txtContraseaNueva = new JPasswordField();
		GridBagConstraints gbcTxtContraseaNueva = new GridBagConstraints();
		gbcTxtContraseaNueva.insets = new Insets(0, 0, 5, 5);
		gbcTxtContraseaNueva.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtContraseaNueva.gridx = 1;
		gbcTxtContraseaNueva.gridy = 4;
		add(txtContraseaNueva, gbcTxtContraseaNueva);
		txtContraseaNueva.setColumns(10);

		txtContraseaNueva.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}
		});
		
		JLabel lblConfirmarContraseaNueva = new JLabel("Confirmar contraseña nueva");
		GridBagConstraints gbcLblConfirmarContraseaNueva = new GridBagConstraints();
		gbcLblConfirmarContraseaNueva.insets = new Insets(0, 0, 5, 5);
		gbcLblConfirmarContraseaNueva.gridx = 1;
		gbcLblConfirmarContraseaNueva.gridy = 5;
		add(lblConfirmarContraseaNueva, gbcLblConfirmarContraseaNueva);
		
		txtConfirmarContraseaNueva = new JPasswordField();
		GridBagConstraints gbcTxtConfirmarContraseaNueva = new GridBagConstraints();
		gbcTxtConfirmarContraseaNueva.insets = new Insets(0, 0, 5, 5);
		gbcTxtConfirmarContraseaNueva.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtConfirmarContraseaNueva.gridx = 1;
		gbcTxtConfirmarContraseaNueva.gridy = 6;
		add(txtConfirmarContraseaNueva, gbcTxtConfirmarContraseaNueva);
		txtConfirmarContraseaNueva.setColumns(10);

		txtConfirmarContraseaNueva.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}

			@Override
			public void removeUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}

			@Override
			public void changedUpdate(DocumentEvent documentEvent) {
				deshabilitarBotonSiCamposVacios();
			}
		});
		
		btnCambiarContrasea = new JButton("Cambiar Contraseña");
		btnCambiarContrasea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!btnCambiarContrasea.isEnabled())
					return;

				var contrasenaNueva = txtContraseaNueva.getPassword();
				var confirmacion = txtConfirmarContraseaNueva.getPassword();
				if (!Arrays.equals(contrasenaNueva, confirmacion)) {
					lblLasContraseasNo.setVisible(true);
					return;
				}
				lblLasContraseasNo.setVisible(false);
				var contrasenaActual = txtContraseaActual.getPassword();
				controlador.cambiarContrasena(String.valueOf(contrasenaActual), String.valueOf(contrasenaNueva));
			}
		});
		
		lblLasContraseasNo = new JLabel("Las contraseñas no coinciden");
		lblLasContraseasNo.setForeground(new Color(237, 51, 59));
		GridBagConstraints gbcLblLasContraseasNo = new GridBagConstraints();
		gbcLblLasContraseasNo.insets = new Insets(0, 0, 5, 5);
		gbcLblLasContraseasNo.gridx = 1;
		gbcLblLasContraseasNo.gridy = 7;
		add(lblLasContraseasNo, gbcLblLasContraseasNo);
		GridBagConstraints gbcBtnCambiarContrasea = new GridBagConstraints();
		gbcBtnCambiarContrasea.insets = new Insets(0, 0, 5, 5);
		gbcBtnCambiarContrasea.gridx = 1;
		gbcBtnCambiarContrasea.gridy = 8;
		add(btnCambiarContrasea, gbcBtnCambiarContrasea);
	}

    public void muestra(ControlCambiarContrasena controlador){
        this.controlador = controlador;

		txtContraseaActual.setText("");
		txtContraseaNueva.setText("");
		txtConfirmarContraseaNueva.setText("");
		btnCambiarContrasea.setEnabled(false);
		lblLasContraseasNo.setVisible(false);

		setVisible(true);
    }

	private void deshabilitarBotonSiCamposVacios(){
		var contrasenaActual = txtContraseaActual.getPassword();
		var contrasenaNueva = txtContraseaNueva.getPassword();
		var confirmacionContrasenaNueva = txtConfirmarContraseaNueva.getPassword();

		btnCambiarContrasea.setEnabled(!(contrasenaActual.length == 0 || contrasenaNueva.length == 0 || confirmacionContrasenaNueva.length == 0));
	}

    public void muestraDialogoError() {
		JOptionPane.showMessageDialog(this, "La contraseña es incorrecta");
    }

    public void muestraDialogoExito() {
		JOptionPane.showMessageDialog(this, "Su contraseña se actualizó correctamente");
    }

	public boolean muestraDialogoConfirmacion() {
		return JOptionPane.showConfirmDialog(this, "¿Está seguro de querer cambiar su contraseña?") == 0;
	}
}