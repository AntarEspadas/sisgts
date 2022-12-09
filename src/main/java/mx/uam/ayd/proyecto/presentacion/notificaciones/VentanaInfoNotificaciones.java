package mx.uam.ayd.proyecto.presentacion.notificaciones;

import java.awt.Rectangle;

import mx.uam.ayd.proyecto.negocio.modelo.Mensaje;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
@Slf4j
@Component
public class VentanaInfoNotificaciones extends Pantalla {

	@Autowired
    private VentanaNotificaciones ventanaNotificaciones;

	private transient ControlNotificaciones controlador;

	private final JList<String> listaNotifi;
	
	public VentanaInfoNotificaciones() {
		
	
		setBounds(new Rectangle(100, 100, 500, 400));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mensajes y Notificaciones");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(21, 21, 166, 20);
		add(lblNewLabel);
				
		
		listaNotifi = new JList<>();
		listaNotifi.setBounds(10, 71, 472, 188);
		
		add(listaNotifi);
		
		JButton btnMostrar = new JButton("Descargar");
		GridBagConstraints gbcBtnMostrar = new GridBagConstraints();
		gbcBtnMostrar.insets = new Insets(0, 0, 0, 0);
		gbcBtnMostrar.anchor = GridBagConstraints.NORTH;
		gbcBtnMostrar.gridx = 3;
		gbcBtnMostrar.gridy = 6;
		btnMostrar.setBounds(393, 298, 89, 23);
		add(btnMostrar, gbcBtnMostrar);
		
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
					log.info("Valor mensaje = {}", indice);
					JOptionPane.showMessageDialog(null, "Descargar Mensaje "+indice);
				}
			}
		});	
	
	}
	
	public void muestra(ControlNotificaciones controlador, List<Mensaje> mensajes) {

		this.controlador = controlador;
		
		log.info("{}",mensajes);
		var listModel = new DefaultListModel<String>();
		//Asociar el modelo de lista al JList
		if(!mensajes.isEmpty()) {

			listModel.addAll(
					mensajes.stream()
							.map(Mensaje::toString)
							.collect(Collectors.toList())
			);
			
		}else
			listModel.addElement("Usted no tiene ningun Mensaje");
		
		listaNotifi.setModel(listModel);
		setVisible(true);
	}
	
	public void cierra() {
		setVisible(false);
	}

	public void muestra(ControlNotificaciones controlador) {
		this.controlador = controlador;
		setVisible(true);
	}
}
