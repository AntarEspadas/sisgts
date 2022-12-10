package mx.uam.ayd.proyecto.presentacion.publicaciones.editar_publicacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import mx.uam.ayd.proyecto.negocio.modelo.Aviso;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

@Component
public class VentanaEditarPublicacion extends Pantalla {

	private transient ControlEditarPublicacion controlador;

	private final JTextArea textArea;
	private final JCheckBox validado;
	private final JButton publicar;
	private final JButton btnimagen;
	private final JLabel imagenP;
	private String rutaImagen;
	private final JButton botonPublicar;

	public VentanaEditarPublicacion() {
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 300, 40, 0};
		gridBagLayout.rowHeights = new int[]{30, 200, 20, 147, 0, 40, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		var model = new DefaultComboBoxModel<String>();
		model.addElement("1");
		model.addElement("2");
		
		JLabel imagenPlaceholder = new JLabel();
		imagenP = imagenPlaceholder;
		GridBagConstraints gbcImagenPlaceholder = new GridBagConstraints();
		gbcImagenPlaceholder.fill = GridBagConstraints.VERTICAL;
		gbcImagenPlaceholder.insets = new Insets(0, 0, 5, 5);
		gbcImagenPlaceholder.gridx = 1;
		gbcImagenPlaceholder.gridy = 1;
		add(imagenPlaceholder, gbcImagenPlaceholder);
		
		
		
		JButton btnNewButton1 = new JButton("Imagen");
		
		btnimagen = btnNewButton1;
		btnNewButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnNewButton1.isEnabled()) {

					JFileChooser fc = new JFileChooser();

					FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
		        fc.addChoosableFileFilter(filter);
		        int option = fc.showSaveDialog(null);
				
				if(option == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile();
						String path = file.getAbsolutePath();
						rutaImagen = path;
						ImageIcon imagen1 = new ImageIcon(path);
						Image imagen1Image = imagen1.getImage();
						imagen1Image = imagen1Image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
						ImageIcon img = new ImageIcon(imagen1Image);
				        imagenPlaceholder.setIcon(img);
					

				}
				}
			}
		});
		GridBagConstraints gbcBtnNewButton1 = new GridBagConstraints();
		gbcBtnNewButton1.anchor = GridBagConstraints.EAST;
		gbcBtnNewButton1.insets = new Insets(0, 0, 5, 5);
		gbcBtnNewButton1.gridx = 1;
		gbcBtnNewButton1.gridy = 2;
		add(btnNewButton1, gbcBtnNewButton1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 5);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 1;
		gbcScrollPane.gridy = 3;
		add(scrollPane, gbcScrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(e -> controlador.cancelar());

		botonPublicar = new JButton("Publicar");
		publicar = botonPublicar;
		botonPublicar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (botonPublicar.isEnabled()) {
				String texto = null;
						texto = textArea.getText();
						if (texto.length()!=0) {
							int input = JOptionPane.showConfirmDialog(null, "Deseas Publicar");
					        // 0=yes, 1=no, 2=cancel
					        if (input == 0) {
					        	if(imagenPlaceholder.getIcon()!= null) {
					
					        	controlador.guardadPublicacion(rutaImagen,texto);
					        	}else {
					        		controlador.guardadPublicacion(null,texto);
					        	}
					        }
						}
				}
			}
		});

		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		validado = chckbxNewCheckBox;
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setSelected(false);
		GridBagConstraints gbcChckbxNewCheckBox = new GridBagConstraints();
		gbcChckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
		gbcChckbxNewCheckBox.gridx = 2;
		gbcChckbxNewCheckBox.gridy = 1;
		add(chckbxNewCheckBox, gbcChckbxNewCheckBox);
		GridBagConstraints gbcBtnNewButton = new GridBagConstraints();
		gbcBtnNewButton.insets = new Insets(0, 0, 5, 5);
		gbcBtnNewButton.gridx = 1;
		gbcBtnNewButton.gridy = 4;
		add(botonPublicar, gbcBtnNewButton);


		GridBagConstraints gbcBotonTelegram = new GridBagConstraints();
		gbcBotonTelegram.insets = new Insets(0, 0, 0, 5);
		gbcBotonTelegram.gridx = 1;
		gbcBotonTelegram.gridy = 5;
		add(botonCancelar, gbcBotonTelegram);

	}

	public void muestra(ControlEditarPublicacion controlador, @Nullable Aviso aviso) {
		if (aviso == null){
			botonPublicar.setText("Publicar");
			textArea.setText("");
		}
		else {
			botonPublicar.setText("Guardar");
			textArea.setText(aviso.getContenido());
			if (aviso.getImagen() != null){
				ImageIcon imagen1 = new ImageIcon(aviso.getImagen());
				Image imagenEscalada = imagen1.getImage();
				imagenEscalada = imagenEscalada.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
				ImageIcon img = new ImageIcon(imagenEscalada);
				imagenP.setIcon(img);
			}
		}
		validado.setSelected(false);
		publicar.setEnabled(true);
		btnimagen.setEnabled(true);
		this.controlador = controlador;
		setVisible(true);
	}

	public void activaLogoConfirmacionOcultaCrear() {
		validado.setSelected(true);
		publicar.setEnabled(false);
		btnimagen.setEnabled(false);
		imagenP.setIcon(null);
	}

}
