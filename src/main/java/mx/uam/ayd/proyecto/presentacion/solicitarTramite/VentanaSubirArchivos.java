package mx.uam.ayd.proyecto.presentacion.solicitarTramite;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.TipoTramite;
import mx.uam.ayd.proyecto.presentacion.Notificaciones.ControlNotificaciones;
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
	
	private TipoTramite tipoTramite;
	private ControlNotificaciones control;

	public VentanaSubirArchivos() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 209, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 112, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
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
		GridBagConstraints gbc_chckbxConfirmarSolicitudDe = new GridBagConstraints();
		gbc_chckbxConfirmarSolicitudDe.anchor = GridBagConstraints.WEST;
		gbc_chckbxConfirmarSolicitudDe.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxConfirmarSolicitudDe.gridx = 1;
		gbc_chckbxConfirmarSolicitudDe.gridy = 3;
		add(chckbxConfirmarSolicitudDe, gbc_chckbxConfirmarSolicitudDe);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 18, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnAnterior = new JButton("Anterior");
		btnAnterior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controlador.elegirTramite();
			}
		});
		GridBagConstraints gbc_btnAnterior = new GridBagConstraints();
		gbc_btnAnterior.insets = new Insets(0, 0, 0, 5);
		gbc_btnAnterior.gridx = 1;
		gbc_btnAnterior.gridy = 0;
		panel_1.add(btnAnterior, gbc_btnAnterior);
		
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
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.gridx = 3;
		gbc_btnEnviar.gridy = 0;
		panel_1.add(btnEnviar, gbc_btnEnviar);
	}
	
	private ControlSolicitarTramite controlador;
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
