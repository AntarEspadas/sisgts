package mx.uam.ayd.proyecto.presentacion.Notificaciones;

import java.awt.Rectangle;

import mx.uam.ayd.proyecto.negocio.modelo.Mensaje;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antlr.collections.List;
import lombok.extern.slf4j.Slf4j;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
@Slf4j
@Component
public class VentanaInfoNotificaciones extends Pantalla {
	
	@Autowired
    private VentanaNotificaciones ventanaNotificaciones;
	
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
		listaNotifi.setBounds(10, 71, 472, 188);
		
		add(listaNotifi);
		
		JButton btnMostrar = new JButton("Descargar");
		GridBagConstraints gbc_btnMostrar = new GridBagConstraints();
		gbc_btnMostrar.insets = new Insets(0, 0, 0, 0);
		gbc_btnMostrar.anchor = GridBagConstraints.NORTH;
		gbc_btnMostrar.gridx = 3;
		gbc_btnMostrar.gridy = 6;
		btnMostrar.setBounds(393, 298, 89, 23);
		add(btnMostrar, gbc_btnMostrar);
		
		btnMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(listaNotifi.getSelectedIndex()==-1) {
					JOptionPane.showMessageDialog(null, "Seleccione un mensaje ");
					controlador.verificar();
				}else {
					int indice;
					indice = listaNotifi.getSelectedIndex();
					ventanaNotificaciones.muestra(controlador);
					controlador.descargar();
					System.out.println("valor mensaje "+indice);
					JOptionPane.showMessageDialog(null, "Descargar Mensaje "+indice);
					//controlador.descargar(indice);
				}
			}
		});	
	
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
			    //listModel.addElement("Ya hay mensajes");
			}
			
		}else
			listModel.addElement("Usted no tiene ningun Mensaje");
		
		listaNotifi.setModel(listModel);
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}

	public void muestra(ControlNotificaciones controlador2) {
		// TODO Auto-generated method stub
		setVisible(true);
	}
}
