package mx.uam.ayd.proyecto.presentacion.publicaciones.consultarAvisos;

import lombok.Getter;
import lombok.Setter;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Componente extends JPanel {

	/**
	 * Create the panel.
	 */
	private JTextArea texto;
	private JLabel fecha1;
	private JLabel imagen;

	@Getter
	private Aviso aviso;
	
	public Componente() {
		setLayout(null);
		
		JLabel fecha = new JLabel("Label principal");
		fecha.setBounds(189, 5, 67, 14);
		fecha1 = fecha;
		add(fecha);
		
		JLabel Imagen = new JLabel("No hay Imagen");
		Imagen.setBounds(186, 144, 73, 14);
		imagen = Imagen;
		add(Imagen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 254, 385, 37);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(32, 254, 383, 35);
		add(textArea);
		textArea.setEnabled(false);
		texto = textArea;
		textArea.setLineWrap(true);
		
		
		
		
	}

	public void setAviso(Aviso aviso){
		this.aviso = aviso;
		texto.setText(aviso.getContenido());
		fecha1.setText(aviso.getFecha());
		var rutaImg = aviso.getImagen();
		if (rutaImg != null){
			var imageIcon = new ImageIcon(aviso.getImagen());
			var imagen_escalada = imageIcon.getImage();
			imagen_escalada = imagen_escalada.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
			var img = new ImageIcon(imagen_escalada);
			imagen.setIcon(img);
			imagen.setText("");
		}
	}
}
