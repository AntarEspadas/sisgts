package mx.uam.ayd.proyecto.presentacion.consultarCitas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.JDatePicker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mx.uam.ayd.proyecto.util.Operador;

public class ComponenteFiltro extends JPanel {

	public ComponenteFiltro() {
					
		setMinimumSize(new Dimension(10, 50));
		setMaximumSize(new Dimension(32767, 50));
		setBounds(new Rectangle(0, 0, 50, 50));
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{126, 0, 58, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gbl_panel_1);
		
		var comboBoxModel = new DefaultComboBoxModel<FiltroModel>();
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_EXACTA, "fecha", "Fecha exacta"));
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_ANTES, "fecha", "Fecha antes"));
		comboBoxModel.addElement(new FiltroModel(Operador.FECHA_DESPUES, "fecha", "Fecha despues"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.nombre", "Nombre"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "agremiado.apellido", "Apellido"));
		comboBoxModel.addElement(new FiltroModel(Operador.CONTIENE, "motivo", "Motivo"));
		
		JComboBox<FiltroModel> comboBox = new JComboBox<>();
		comboBox.setModel(comboBoxModel);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JTextField txtCampo = new JTextField();
		txtCampo.setColumns(10);
		
		JDatePicker dateCampo = new JDatePicker();
		
		GridBagConstraints gbc_Campo = new GridBagConstraints();
		gbc_Campo.insets = new Insets(0, 0, 0, 5);
		gbc_Campo.fill = GridBagConstraints.HORIZONTAL;
		gbc_Campo.gridx = 1;
		gbc_Campo.gridy = 0;
		add(dateCampo, gbc_Campo);
		
		JButton btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.gridx = 2;
		gbc_btnEliminar.gridy = 0;
		add(btnEliminar, gbc_btnEliminar);
	}

}

@Getter
@AllArgsConstructor
class FiltroModel{
	private Operador operador;
	
	private String atributo;
	
	private String etiqueta;
	
	@Override
	public String toString() {
		return etiqueta;
	}
}

