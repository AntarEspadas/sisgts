package mx.uam.ayd.proyecto.presentacion.consultarAvisos;

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
	public void setParams(String contenido,String date) {
		texto.setText(contenido);
		fecha1.setText(date);
	}
	public void setParams(String contenido,String date,ImageIcon icoimagen) {
		texto.setText(contenido);
		fecha1.setText(date);
		Image imagen_escalada = icoimagen.getImage();
		imagen_escalada = imagen_escalada.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon img = new ImageIcon(imagen_escalada);
		imagen.setIcon(img);
		imagen.setText("");
	}
}
