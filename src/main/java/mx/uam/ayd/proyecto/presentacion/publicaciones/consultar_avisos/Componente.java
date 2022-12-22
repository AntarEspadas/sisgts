package mx.uam.ayd.proyecto.presentacion.publicaciones.consultar_avisos;

import lombok.Getter;
import mx.uam.ayd.proyecto.negocio.modelo.Aviso;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Componente extends JPanel {

	/**
	 * Create the panel.
	 */
	private final JTextArea texto;
	private final JLabel fecha1;
	private final JLabel imagen;
	private final JLabel lblDestacado;
	private final boolean esAdmin;

	@Getter
	private transient Aviso aviso;
	
	public Componente(boolean esAdmin) {

		this.esAdmin = esAdmin;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 200, 10, 0};
		gridBagLayout.rowHeights = new int[]{23, 0, 0, 200, 0, 123, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel fecha = new JLabel("New label");
		fecha1 = fecha;
		GridBagConstraints gbcFecha = new GridBagConstraints();
		gbcFecha.insets = new Insets(0, 0, 5, 5);
		gbcFecha.gridx = 1;
		gbcFecha.gridy = 1;
		add(fecha, gbcFecha);

		var imagenEstrella = new ImageIcon("./src/main/resources/star-filled.png").getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		var icono = new ImageIcon(imagenEstrella);

		lblDestacado = new JLabel("Destacado!");
		GridBagConstraints gbcLblDestacado = new GridBagConstraints();
		gbcLblDestacado.anchor = GridBagConstraints.WEST;
		gbcLblDestacado.insets = new Insets(0, 0, 5, 5);
		gbcLblDestacado.gridx = 1;
		gbcLblDestacado.gridy = 2;
		add(lblDestacado, gbcLblDestacado);

		lblDestacado.setIcon(icono);
		lblDestacado.setVisible(false);

		JLabel label = new JLabel("Sin Imagen");
		imagen = label;
		GridBagConstraints gbcImagen = new GridBagConstraints();
		gbcImagen.insets = new Insets(0, 0, 5, 5);
		gbcImagen.gridx = 1;
		gbcImagen.gridy = 3;
		add(label, gbcImagen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 5);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 1;
		gbcScrollPane.gridy = 5;
		add(scrollPane, gbcScrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		texto = textArea;
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		
		
		
	}

	public void setAviso(Aviso aviso){

		lblDestacado.setVisible(!esAdmin && aviso.isDestacado());

		this.aviso = aviso;
		texto.setText(aviso.getContenido());
		fecha1.setText(aviso.getFecha());
		var rutaImg = aviso.getImagen();
		if (rutaImg != null){
			var imageIcon = new ImageIcon(aviso.getImagen());
			var imagenEscalada = imageIcon.getImage();
			imagenEscalada = imagenEscalada.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
			var img = new ImageIcon(imagenEscalada);
			imagen.setIcon(img);
			imagen.setText("");
		}
	}
}
