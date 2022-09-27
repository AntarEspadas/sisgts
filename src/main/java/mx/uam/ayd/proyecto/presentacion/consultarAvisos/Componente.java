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
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 200, 10, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 200, 0, 123, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel fecha = new JLabel("New label");
		fecha1 = fecha;
		GridBagConstraints gbc_fecha = new GridBagConstraints();
		gbc_fecha.insets = new Insets(0, 0, 5, 5);
		gbc_fecha.gridx = 1;
		gbc_fecha.gridy = 1;
		add(fecha, gbc_fecha);
		
		JLabel Imagen = new JLabel("Sin Imagen");
		imagen = Imagen;
		GridBagConstraints gbc_Imagen = new GridBagConstraints();
		gbc_Imagen.insets = new Insets(0, 0, 5, 5);
		gbc_Imagen.gridx = 1;
		gbc_Imagen.gridy = 3;
		add(Imagen, gbc_Imagen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		add(scrollPane, gbc_scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		texto = textArea;
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		
		
		
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
