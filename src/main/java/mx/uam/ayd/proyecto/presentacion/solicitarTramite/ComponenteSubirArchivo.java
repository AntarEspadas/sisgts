package mx.uam.ayd.proyecto.presentacion.solicitarTramite;

import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;

public class ComponenteSubirArchivo extends JPanel {
	
	private final List<ArchivoSeleccionadoListener> listeners = new ArrayList<>();

	private final JFileChooser fileChooser;
	private JCheckBox chckbxSeleccionado;

	public ComponenteSubirArchivo(String nombreDocumento) {
		
		var filtros = new FileNameExtensionFilter("Archivos PDF", "pdf");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filtros);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 75, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNombredocumento = new JLabel(nombreDocumento);
		GridBagConstraints gbc_lblNombredocumento = new GridBagConstraints();
		gbc_lblNombredocumento.insets = new Insets(0, 0, 0, 5);
		gbc_lblNombredocumento.gridx = 1;
		gbc_lblNombredocumento.gridy = 0;
		add(lblNombredocumento, gbc_lblNombredocumento);
		
		JLabel lblFormato = new JLabel(".PDF");
		GridBagConstraints gbc_lblFormato = new GridBagConstraints();
		gbc_lblFormato.insets = new Insets(0, 0, 0, 5);
		gbc_lblFormato.gridx = 2;
		gbc_lblFormato.gridy = 0;
		add(lblFormato, gbc_lblFormato);
		
		var padre = this;
		JButton btnAdjuntar = new JButton("Adjuntar");
		btnAdjuntar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int resultado = fileChooser.showOpenDialog(padre);
				if (resultado != JFileChooser.APPROVE_OPTION)
					return;
				btnAdjuntar.setText("Cambiar");
				chckbxSeleccionado.setSelected(true);
				var archivo = fileChooser.getSelectedFile();
				for (var listener : listeners) {
					listener.archivoSeleccionado(nombreDocumento, archivo);
				}
			}
		});
		GridBagConstraints gbc_btnAdjuntar = new GridBagConstraints();
		gbc_btnAdjuntar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAdjuntar.gridx = 3;
		gbc_btnAdjuntar.gridy = 0;
		add(btnAdjuntar, gbc_btnAdjuntar);
		
		chckbxSeleccionado = new JCheckBox("");
		chckbxSeleccionado.setEnabled(false);
		GridBagConstraints gbc_chckbxSeleccionado = new GridBagConstraints();
		gbc_chckbxSeleccionado.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSeleccionado.gridx = 4;
		gbc_chckbxSeleccionado.gridy = 0;
		add(chckbxSeleccionado, gbc_chckbxSeleccionado);
	}
	
	public void addArchivoSeleccionadoListener(ArchivoSeleccionadoListener listener) {
		listeners.add(listener);
	}

}

@FunctionalInterface
interface ArchivoSeleccionadoListener {
	void archivoSeleccionado(String nombreDocumento, File archivo);
}