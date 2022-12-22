package mx.uam.ayd.proyecto.presentacion.solicitar_tramite;

import mx.uam.ayd.proyecto.negocio.Captcha;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class VentanaCaptcha extends JFrame {
	private final JLabel lblCaptcha;
	private transient ControlSolicitarTramite controlador;
	private JTextField txtRespuesta;
	public VentanaCaptcha() {

		setBounds(300, 300, 500, 300);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 71, 0, 0, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JButton btnGenerarOtroCaptcha = new JButton("Generar otro captcha");
		btnGenerarOtroCaptcha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				var captcha = controlador.generarOtroCaptcha();
				actualizarCaptcha(captcha);
			}
		});
		GridBagConstraints gbcBtnGenerarOtroCaptcha = new GridBagConstraints();
		gbcBtnGenerarOtroCaptcha.anchor = GridBagConstraints.EAST;
		gbcBtnGenerarOtroCaptcha.insets = new Insets(0, 0, 5, 5);
		gbcBtnGenerarOtroCaptcha.gridx = 3;
		gbcBtnGenerarOtroCaptcha.gridy = 0;
		getContentPane().add(btnGenerarOtroCaptcha, gbcBtnGenerarOtroCaptcha);
		
		lblCaptcha = new JLabel();
		GridBagConstraints gbcLblCaptcha = new GridBagConstraints();
		gbcLblCaptcha.gridwidth = 3;
		gbcLblCaptcha.insets = new Insets(0, 0, 5, 5);
		gbcLblCaptcha.gridx = 1;
		gbcLblCaptcha.gridy = 1;
		getContentPane().add(lblCaptcha, gbcLblCaptcha);
		
		txtRespuesta = new JTextField();
		GridBagConstraints gbcTxtRespuesta = new GridBagConstraints();
		gbcTxtRespuesta.insets = new Insets(0, 0, 5, 5);
		gbcTxtRespuesta.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtRespuesta.gridx = 2;
		gbcTxtRespuesta.gridy = 3;
		getContentPane().add(txtRespuesta, gbcTxtRespuesta);
		txtRespuesta.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		GridBagConstraints gbcBtnCancelar = new GridBagConstraints();
		gbcBtnCancelar.anchor = GridBagConstraints.WEST;
		gbcBtnCancelar.insets = new Insets(0, 0, 5, 5);
		gbcBtnCancelar.gridx = 1;
		gbcBtnCancelar.gridy = 4;
		getContentPane().add(btnCancelar, gbcBtnCancelar);
		
		JButton btnNoSoyUn = new JButton("No soy un robot");
		btnNoSoyUn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.validarCaptcha(txtRespuesta.getText());
			}
		});
		GridBagConstraints gbcBtnNoSoyUn = new GridBagConstraints();
		gbcBtnNoSoyUn.anchor = GridBagConstraints.EAST;
		gbcBtnNoSoyUn.insets = new Insets(0, 0, 5, 5);
		gbcBtnNoSoyUn.gridx = 3;
		gbcBtnNoSoyUn.gridy = 4;
		getContentPane().add(btnNoSoyUn, gbcBtnNoSoyUn);
	}

    public void muestra(ControlSolicitarTramite controlador, Captcha captcha){
		this.controlador = controlador;
		actualizarCaptcha(captcha);
        setVisible(true);
    }

    public void cierra(){
        setVisible(false);
    }

    public void muestraDialogoError() {
        JOptionPane.showMessageDialog(this, "Error. Inténtelo de nuevo");
    }

	private void actualizarCaptcha(Captcha captcha){
		var icon = new ImageIcon(captcha.getImagen());
		lblCaptcha.setIcon(icon);
	}
}
