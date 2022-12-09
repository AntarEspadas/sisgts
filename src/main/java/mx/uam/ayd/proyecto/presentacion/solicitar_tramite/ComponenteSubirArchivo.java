package mx.uam.ayd.proyecto.presentacion.solicitar_tramite;

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
		GridBagConstraints gbcLblNombredocumento = new GridBagConstraints();
		gbcLblNombredocumento.insets = new Insets(0, 0, 0, 5);
		gbcLblNombredocumento.gridx = 1;
		gbcLblNombredocumento.gridy = 0;
		add(lblNombredocumento, gbcLblNombredocumento);
		
		JLabel lblFormato = new JLabel(".PDF");
		GridBagConstraints gbcLblFormato = new GridBagConstraints();
		gbcLblFormato.insets = new Insets(0, 0, 0, 5);
		gbcLblFormato.gridx = 2;
		gbcLblFormato.gridy = 0;
		add(lblFormato, gbcLblFormato);
		
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
		GridBagConstraints gbcBtnAdjuntar = new GridBagConstraints();
		gbcBtnAdjuntar.insets = new Insets(0, 0, 0, 5);
		gbcBtnAdjuntar.gridx = 3;
		gbcBtnAdjuntar.gridy = 0;
		add(btnAdjuntar, gbcBtnAdjuntar);
		
		chckbxSeleccionado = new JCheckBox("");
		chckbxSeleccionado.setEnabled(false);
		GridBagConstraints gbcChckbxSeleccionado = new GridBagConstraints();
		gbcChckbxSeleccionado.insets = new Insets(0, 0, 0, 5);
		gbcChckbxSeleccionado.gridx = 4;
		gbcChckbxSeleccionado.gridy = 0;
		add(chckbxSeleccionado, gbcChckbxSeleccionado);
	}
	
	public void addArchivoSeleccionadoListener(ArchivoSeleccionadoListener listener) {
		listeners.add(listener);
	}

}

@FunctionalInterface
interface ArchivoSeleccionadoListener {
	void archivoSeleccionado(String nombreDocumento, File archivo);
}