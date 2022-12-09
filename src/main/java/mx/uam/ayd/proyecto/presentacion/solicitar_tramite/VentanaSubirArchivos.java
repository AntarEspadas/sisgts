package mx.uam.ayd.proyecto.presentacion.solicitar_tramite;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import java.awt.GridBagLayout;
import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Ventana que permite al agremiado subir archivos para el trámite que quiere solicitar
 * 
 * @author Antar Espadas
 *
 */
@Component
public class VentanaSubirArchivos extends Pantalla {
	
	private final HashMap<String, File> archivos = new HashMap<>();
	
	private transient TipoTramite tipoTramite;

	public VentanaSubirArchivos() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 209, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 112, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbcScrollPane = new GridBagConstraints();
		gbcScrollPane.insets = new Insets(0, 0, 5, 5);
		gbcScrollPane.fill = GridBagConstraints.BOTH;
		gbcScrollPane.gridx = 1;
		gbcScrollPane.gridy = 1;
		add(scrollPane, gbcScrollPane);
		
		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		chckbxConfirmarSolicitudDe = new JCheckBox("Confirmar solicitud de trámite");
		chckbxConfirmarSolicitudDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!chckbxConfirmarSolicitudDe.isEnabled())
					return;
				btnEnviar.setEnabled(chckbxConfirmarSolicitudDe.isSelected());
			}
		});
		chckbxConfirmarSolicitudDe.setEnabled(false);
		GridBagConstraints gbcChckbxConfirmarSolicitudDe = new GridBagConstraints();
		gbcChckbxConfirmarSolicitudDe.anchor = GridBagConstraints.WEST;
		gbcChckbxConfirmarSolicitudDe.insets = new Insets(0, 0, 5, 5);
		gbcChckbxConfirmarSolicitudDe.gridx = 1;
		gbcChckbxConfirmarSolicitudDe.gridy = 3;
		add(chckbxConfirmarSolicitudDe, gbcChckbxConfirmarSolicitudDe);
		
		JPanel panel1 = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		gbcPanel1.insets = new Insets(0, 0, 5, 5);
		gbcPanel1.fill = GridBagConstraints.BOTH;
		gbcPanel1.gridx = 1;
		gbcPanel1.gridy = 4;
		add(panel1, gbcPanel1);
		GridBagLayout gblPanel1 = new GridBagLayout();
		gblPanel1.columnWidths = new int[]{0, 0, 18, 0, 0};
		gblPanel1.rowHeights = new int[]{0, 0};
		gblPanel1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gblPanel1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel1.setLayout(gblPanel1);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.elegirTramite();
			}
		});
		GridBagConstraints gbcBtnAnterior = new GridBagConstraints();
		gbcBtnAnterior.insets = new Insets(0, 0, 0, 5);
		gbcBtnAnterior.gridx = 1;
		gbcBtnAnterior.gridy = 0;
		panel1.add(btnAnterior, gbcBtnAnterior);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					leerArchivosYSolicitar();
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		GridBagConstraints gbcBtnEnviar = new GridBagConstraints();
		gbcBtnEnviar.gridx = 3;
		gbcBtnEnviar.gridy = 0;
		panel1.add(btnEnviar, gbcBtnEnviar);
	}
	
	private transient ControlSolicitarTramite controlador;
	private final JPanel panel;
	private final JCheckBox chckbxConfirmarSolicitudDe;
	private final JButton btnEnviar;

	/**
	 * Muestra la ventana
	 * 
	 * @param controlador El controlador responsable de esta ventana
	 * @param tipoTramite El tipo de trámite para el cual se van a subir los archivos
	 */
	public void muestra(ControlSolicitarTramite controlador, TipoTramite tipoTramite) {
		this.controlador = controlador;
		this.tipoTramite = tipoTramite;
		
		btnEnviar.setEnabled(false);
		chckbxConfirmarSolicitudDe.setEnabled(false);
		chckbxConfirmarSolicitudDe.setSelected(false);

		panel.removeAll();
		
		for (var requerimiento : tipoTramite.getRequerimientos()) {
			var componenteSubirArchivo = new ComponenteSubirArchivo(requerimiento);
			componenteSubirArchivo.addArchivoSeleccionadoListener(this::archivoSeleccionado);
			panel.add(componenteSubirArchivo);
		}
		
		setVisible(true);
	}

	/**
	 * Muestra un cuadro de diálogo indicando que la operación fue exitosa
	 */
	public void muestraDialogoExito(){
		JOptionPane.showMessageDialog(this, "Su solicitud se ha enviado con éxito");
	}

	void archivoSeleccionado(String nombreDocumento, File archivo) {
		archivos.put(nombreDocumento, archivo);
		
		if (archivos.size() == tipoTramite.getRequerimientos().length) {
			chckbxConfirmarSolicitudDe.setEnabled(true);
		}
		
	}
	
	void leerArchivosYSolicitar() throws IOException {
		
		var archivosAEnviar = new HashMap<String, byte[]>();

		for (var entrada : archivos.entrySet()) {
			var archivo = entrada.getValue();
			byte[] bytes;
			try (var stream = new FileInputStream(archivo)) {
				bytes = stream.readAllBytes();
			}

			archivosAEnviar.put(entrada.getKey(), bytes);
		}
		
		controlador.enviar(archivosAEnviar);
	}
}
