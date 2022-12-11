package mx.uam.ayd.proyecto.presentacion.notificaciones;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

@Slf4j
@Component
public class VentanaNotificaciones extends Pantalla {
	
	private transient ControlNotificaciones controlador;

	public VentanaNotificaciones() {
		
		setBounds(new Rectangle(100, 100, 500, 500)); setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Casa de gestion");
		lblNewLabel.setBounds(57, 76, 138, 27);
		add(lblNewLabel);

		JTextPane txtpnPorEsteMedio = new JTextPane();
		txtpnPorEsteMedio.setBounds(57, 114, 381, 174);
		txtpnPorEsteMedio.setText("Por este medio se envia acuse de trámite, el cual confirma que su trámite se encuentra en proceso.");
		add(txtpnPorEsteMedio);

		JLabel lblAcuse = new JLabel("Acuse.PDF");
		lblAcuse.setBounds(87, 299, 74, 34);
		add(lblAcuse);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(342, 405, 87, 27);
		add(btnGuardar);
		
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.infoNotificaciones();
			}
		});	
				
			
		
	}

	public void muestra(ControlNotificaciones controlador) {
		this.controlador = controlador;
		setVisible(true);
	}

	public void cierra() {
		setVisible(false);
	}

}
