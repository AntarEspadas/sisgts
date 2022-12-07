package mx.uam.ayd.proyecto.presentacion.Notificaciones;

import java.awt.Rectangle;

import mx.uam.ayd.proyecto.negocio.modelo.Mensaje;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import org.springframework.stereotype.Component;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Slf4j
@Component
public class VentanaInfoNotificaciones extends Pantalla {
	
	private ControlNotificaciones controlador;
	
	private final JList<String> listaNotifi;
	
	public VentanaInfoNotificaciones() {
		
	
		setBounds(new Rectangle(100, 100, 500, 400));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mensajes y Notificaciones");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(21, 21, 166, 20);
		add(lblNewLabel);
				
		
		listaNotifi = new JList<String>();
		listaNotifi.setBounds(10, 71, 472, 159);
		
		add(listaNotifi);
		
	
	}
	
	@SuppressWarnings("unchecked")
	public void muestra(ControlNotificaciones control, java.util.List<Mensaje> mensajes) {
		
		log.info("{}",mensajes);
		@SuppressWarnings("rawtypes")
		DefaultListModel listModel = new DefaultListModel();
		//Asociar el modelo de lista al JList
		//list.setModel(listModel);
		if(mensajes.size()!=0) {
			
			//Recorrer el contenido del ArrayList
			for(int i=0; i<mensajes.size(); i++) {
			    //Añadir cada elemento del ArrayList en el modelo de la lista
			    listModel.add(i, mensajes.get(i));
			    listModel.addElement("Ya hay mensajes");
			}
			
		}else
			listModel.addElement("Usted no tiene ningun Mensaje");
		
		listaNotifi.setModel(listModel);
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}

	
}
