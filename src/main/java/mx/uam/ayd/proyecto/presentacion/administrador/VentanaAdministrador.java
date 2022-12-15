package mx.uam.ayd.proyecto.presentacion.administrador;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;


@Component
public class VentanaAdministrador extends Pantalla{
	 private ControlAdministrador controlador;
	 private JLabel lblNewLabel;
	 private JLabel lblRegistrarAgremiado;
	 private JLabel lblEditarEmpleado;
	 private JLabel lblEditarAgremiado;
	public VentanaAdministrador() {
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			setLayout(gridBagLayout);
									
		   JButton btnRegistrarEmpleado = new JButton("Registrar Empleado");
		   btnRegistrarEmpleado.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
			   }
			});
									
		   lblNewLabel = new JLabel("Registrar Empleado");
		   GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		   gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		   gbc_lblNewLabel.gridx = 2;
		   gbc_lblNewLabel.gridy = 1;
		   add(lblNewLabel, gbc_lblNewLabel);
									
		   lblRegistrarAgremiado = new JLabel("Registrar Agremiado");
		   GridBagConstraints gbc_lblRegistrarAgremiado = new GridBagConstraints();
		   gbc_lblRegistrarAgremiado.insets = new Insets(0, 0, 5, 5);
		   gbc_lblRegistrarAgremiado.gridx = 6;
		   gbc_lblRegistrarAgremiado.gridy = 1;
		   add(lblRegistrarAgremiado, gbc_lblRegistrarAgremiado);
		   GridBagConstraints gbc_btnRegistrarEmpleado = new GridBagConstraints();
		   gbc_btnRegistrarEmpleado.insets = new Insets(0, 0, 5, 5);
		   gbc_btnRegistrarEmpleado.gridx = 2;
		   gbc_btnRegistrarEmpleado.gridy = 3;
		   add(btnRegistrarEmpleado, gbc_btnRegistrarEmpleado);
						
									
		   JButton btnRegistrarAgremiado = new JButton("Registrar Agremiado");
		   GridBagConstraints gbc_btnRegistrarAgremiado = new GridBagConstraints();
		   gbc_btnRegistrarAgremiado.insets = new Insets(0, 0, 5, 5);
		   gbc_btnRegistrarAgremiado.gridx = 6;
		   gbc_btnRegistrarAgremiado.gridy = 3;
		   add(btnRegistrarAgremiado, gbc_btnRegistrarAgremiado);
						
		   lblEditarEmpleado = new JLabel("Editar Empleado");
		   GridBagConstraints gbc_lblEditarEmpleado = new GridBagConstraints();
		   gbc_lblEditarEmpleado.insets = new Insets(0, 0, 5, 5);
		   gbc_lblEditarEmpleado.gridx = 2;
		   gbc_lblEditarEmpleado.gridy = 7;
		   add(lblEditarEmpleado, gbc_lblEditarEmpleado);
						
		   JButton btnEditarE = new JButton("Editar");
		   btnEditarE.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   controlador.editaEmpleado();
			   }
		   });
						
		   lblEditarAgremiado = new JLabel("Editar Agremiado");
		   GridBagConstraints gbc_lblEditarAgremiado = new GridBagConstraints();
		   gbc_lblEditarAgremiado.insets = new Insets(0, 0, 5, 5);
		   gbc_lblEditarAgremiado.gridx = 6;
		   gbc_lblEditarAgremiado.gridy = 7;
		   add(lblEditarAgremiado, gbc_lblEditarAgremiado);
		   GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		   gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		   gbc_btnNewButton.gridx = 2;
		   gbc_btnNewButton.gridy = 8;
		   add(btnEditarE, gbc_btnNewButton);
						
		   JButton btnEditarA = new JButton("Editar");
		   btnEditarA.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   controlador.editaAgremiado();
			   }
		   });
		   
		   GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		   gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		   gbc_btnNewButton_1.gridx = 6;
		   gbc_btnNewButton_1.gridy = 8;
		   add(btnEditarA, gbc_btnNewButton_1);

		}



	    public void muestra(ControlAdministrador controlador){
	        this.controlador=controlador;
	        setVisible(true);
	    }

}
