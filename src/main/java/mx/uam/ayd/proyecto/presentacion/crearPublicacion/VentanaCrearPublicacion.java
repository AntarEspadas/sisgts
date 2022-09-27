package mx.uam.ayd.proyecto.presentacion.crearPublicacion;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

@Component
public class VentanaCrearPublicacion extends Pantalla {

	private ControlCrearPublicacion controlador;

	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JCheckBox validado;
	private JButton publicar;
	private JButton btnimagen;
	private JLabel imagen_p;
	private String ruta_imagen;

	public VentanaCrearPublicacion() {
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
		
		JLabel imagen_placeholder = new JLabel();
		imagen_p = imagen_placeholder;
		GridBagConstraints gbc_imagen_placeholder = new GridBagConstraints();
		gbc_imagen_placeholder.fill = GridBagConstraints.VERTICAL;
		gbc_imagen_placeholder.insets = new Insets(0, 0, 5, 5);
		gbc_imagen_placeholder.gridx = 1;
		gbc_imagen_placeholder.gridy = 1;
		add(imagen_placeholder, gbc_imagen_placeholder);
		
		
		
		JButton btnNewButton_1 = new JButton("Imagen");
		
		btnimagen = btnNewButton_1;
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnNewButton_1.isEnabled()) {
				Image imagen = null;
				JFileChooser fc = new JFileChooser();

				
				java.awt.Component frame = null;
				
				
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images","jpg","png");
		        fc.addChoosableFileFilter(filter);
		        int option = fc.showSaveDialog(null);
				
				if(option == JFileChooser.APPROVE_OPTION){
						File file = fc.getSelectedFile();
						String path = file.getAbsolutePath();
						ruta_imagen = path;
						ImageIcon imagen1 = new ImageIcon(path);
						Image imagen_escalada = imagen1.getImage();
						imagen_escalada = imagen_escalada.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH);
						ImageIcon img = new ImageIcon(imagen_escalada);
				        imagen_placeholder.setIcon(img);
					

				}else{

				}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		add(btnNewButton_1, gbc_btnNewButton_1);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JButton botonTelegram = new JButton("Telegram");
		botonTelegram.setEnabled(false);
		botonTelegram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnNewButton = new JButton("Publicar");
		publicar = btnNewButton;
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnNewButton.isEnabled()) {
				String texto = null;
						texto = textArea.getText();
						if (texto.length()!=0) {
							int input = JOptionPane.showConfirmDialog(null, "Deseas Publicar");
					        // 0=yes, 1=no, 2=cancel
					        if (input == 0) {
					        	if(imagen_placeholder.getIcon()!= null) {
					
					        	controlador.crearPublicacion(ruta_imagen,texto);
					        	}else {
					        		controlador.crearPublicacion(null,texto);
					        	}
					        }
							botonTelegram.setEnabled(true);
						}
				}
			}
		});

		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		validado = chckbxNewCheckBox;
		chckbxNewCheckBox.setEnabled(false);
		chckbxNewCheckBox.setSelected(false);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxNewCheckBox.gridx = 2;
		gbc_chckbxNewCheckBox.gridy = 1;
		add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		add(btnNewButton, gbc_btnNewButton);


		GridBagConstraints gbc_botonTelegram = new GridBagConstraints();
		gbc_botonTelegram.insets = new Insets(0, 0, 0, 5);
		gbc_botonTelegram.gridx = 1;
		gbc_botonTelegram.gridy = 5;
		add(botonTelegram, gbc_botonTelegram);

	}

	public void muestra(ControlCrearPublicacion controlador) {
		// TODO Auto-generated method stub
		validado.setSelected(false);
		publicar.setEnabled(true);
		btnimagen.setEnabled(true);
		this.controlador = controlador;
		setVisible(true);
	}

	public void activaLogoConfirmacionOcultaCrear() {
		// TODO Auto-generated method stub
		validado.setSelected(true);
		publicar.setEnabled(false);
		btnimagen.setEnabled(false);
		imagen_p.setIcon(null);
	}

}
