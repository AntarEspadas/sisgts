package mx.uam.ayd.proyecto.presentacion.administrador;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;


@Component
public class VentanaAdministrador extends Pantalla{
	 private transient ControlAdministrador controlador;
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
				   System.out.println("Estoy en el bototn Registra empleado");
			   }
			});
									
		   lblNewLabel = new JLabel("Registrar Empleado");
		   GridBagConstraints gbclblNewLabel = new GridBagConstraints();
		   gbclblNewLabel.insets = new Insets(0, 0, 5, 5);
		   gbclblNewLabel.gridx = 2;
		   gbclblNewLabel.gridy = 1;
		   add(lblNewLabel, gbclblNewLabel);
									
		   lblRegistrarAgremiado = new JLabel("Registrar Agremiado");
		   GridBagConstraints gbclblRegistrarAgremiado = new GridBagConstraints();
		   gbclblRegistrarAgremiado.insets = new Insets(0, 0, 5, 5);
		   gbclblRegistrarAgremiado.gridx = 6;
		   gbclblRegistrarAgremiado.gridy = 1;
		   add(lblRegistrarAgremiado, gbclblRegistrarAgremiado);
		   GridBagConstraints gbcbtnRegistrarEmpleado = new GridBagConstraints();
		   gbcbtnRegistrarEmpleado.insets = new Insets(0, 0, 5, 5);
		   gbcbtnRegistrarEmpleado.gridx = 2;
		   gbcbtnRegistrarEmpleado.gridy = 3;
		   add(btnRegistrarEmpleado, gbcbtnRegistrarEmpleado);
						
									
		   JButton btnRegistrarAgremiado = new JButton("Registrar Agremiado");
		   GridBagConstraints gbcbtnRegistrarAgremiado = new GridBagConstraints();
		   gbcbtnRegistrarAgremiado.insets = new Insets(0, 0, 5, 5);
		   gbcbtnRegistrarAgremiado.gridx = 6;
		   gbcbtnRegistrarAgremiado.gridy = 3;
		   add(btnRegistrarAgremiado, gbcbtnRegistrarAgremiado);
						
		   lblEditarEmpleado = new JLabel("Editar Empleado");
		   GridBagConstraints gbclblEditarEmpleado = new GridBagConstraints();
		   gbclblEditarEmpleado.insets = new Insets(0, 0, 5, 5);
		   gbclblEditarEmpleado.gridx = 2;
		   gbclblEditarEmpleado.gridy = 7;
		   add(lblEditarEmpleado, gbclblEditarEmpleado);
						
		   JButton btnEditarE = new JButton("Editar");
		   btnEditarE.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   controlador.editaEmpleado();
			   }
		   });
						
		   lblEditarAgremiado = new JLabel("Editar Agremiado");
		   GridBagConstraints gbclblEditarAgremiado = new GridBagConstraints();
		   gbclblEditarAgremiado.insets = new Insets(0, 0, 5, 5);
		   gbclblEditarAgremiado.gridx = 6;
		   gbclblEditarAgremiado.gridy = 7;
		   add(lblEditarAgremiado, gbclblEditarAgremiado);
		   GridBagConstraints gbcbtnNewButton = new GridBagConstraints();
		   gbcbtnNewButton.insets = new Insets(0, 0, 5, 5);
		   gbcbtnNewButton.gridx = 2;
		   gbcbtnNewButton.gridy = 8;
		   add(btnEditarE, gbcbtnNewButton);
						
		   JButton btnEditarA = new JButton("Editar");
		   btnEditarA.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
				   controlador.editaAgremiado();
			   }
		   });
		   
		   GridBagConstraints gbcbtnNewButton1 = new GridBagConstraints();
		   gbcbtnNewButton1.insets = new Insets(0, 0, 5, 5);
		   gbcbtnNewButton1.gridx = 6;
		   gbcbtnNewButton1.gridy = 8;
		   add(btnEditarA, gbcbtnNewButton1);

		}



	    public void muestra(ControlAdministrador controlador){
	        this.controlador=controlador;
	        setVisible(true);
	    }

}
