package mx.uam.ayd.proyecto.presentacion.Notificaciones;

import java.awt.Rectangle;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.awt.Color;

@Slf4j
@Component
public class VentanaInfoNotificaciones extends Pantalla {
	
	private ControlNotificaciones controlador;
	
	public VentanaInfoNotificaciones() {
		
	
		setBounds(new Rectangle(100, 100, 500, 400));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mensajes y Notificaciones");
		lblNewLabel.setBounds(21, 21, 140, 20);
		add(lblNewLabel);
		
		JList listNotificaciones = new JList();
		listNotificaciones.setBackground(new Color(128, 128, 128));
		listNotificaciones.setBounds(21, 298, 436, -222);
		add(listNotificaciones);
	
	}
	
	public void muestra() {
		
		DefaultListModel<String> avisos = new DefaultListModel<>();
		avisos.addElement("Se añadio un mensaje");
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}
}
