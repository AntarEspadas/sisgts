package mx.uam.ayd.proyecto.presentacion.perfil;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;

import org.springframework.stereotype.Component;


import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

@Component
public class VentanaPerfil extends Pantalla {

    
    private JLabel lblNombre = new JLabel();
    private JLabel lblCorreo = new JLabel();
	private JLabel lblContraseña = new JLabel();
	private JLabel lblClave = new JLabel();

    public VentanaPerfil(){
        setBounds(new Rectangle(100, 100, 500, 400));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{36, 64, 321, 58, 0, 0};
		gridBagLayout.rowHeights = new int[]{45, 30, 30, 30, 30, 30, 30, 30, 30, 85, 27, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblN1 = new JLabel("Nombre:");
		GridBagConstraints gbc_lblN1 = new GridBagConstraints();
		gbc_lblN1.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblN1.insets = new Insets(0, 0, 5, 5);
		gbc_lblN1.gridx = 2;
		gbc_lblN1.gridy = 1;
		add(lblN1, gbc_lblN1);
		
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.anchor = GridBagConstraints.WEST;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 2;
		gbclblNombre.gridy = 2;
		add(lblNombre, gbclblNombre);

		JLabel lblN2 = new JLabel("Correo:");
		GridBagConstraints gbc_lblN2 = new GridBagConstraints();
		gbc_lblN2.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblN2.insets = new Insets(0, 0, 5, 5);
		gbc_lblN2.gridx = 2;
		gbc_lblN2.gridy = 3;
		add(lblN2, gbc_lblN2);
		
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.anchor = GridBagConstraints.WEST;
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 2;
		gbc_lblCorreo.gridy = 4;
		add(lblCorreo, gbc_lblCorreo);
		
		JLabel lblN3 = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblN3 = new GridBagConstraints();
		gbc_lblN3.anchor = GridBagConstraints.WEST;
		gbc_lblN3.insets = new Insets(0, 0, 5, 5);
		gbc_lblN3.gridx = 2;
		gbc_lblN3.gridy = 5;
		add(lblN3, gbc_lblN3);
		
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.anchor = GridBagConstraints.WEST;
		gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseña.gridx = 2;
		gbc_lblContraseña.gridy = 6;
		add(lblContraseña, gbc_lblContraseña);
		
		JLabel lblN4 = new JLabel("Clave:");
		GridBagConstraints gbc_lblN4 = new GridBagConstraints();
		gbc_lblN4.anchor = GridBagConstraints.WEST;
		gbc_lblN4.insets = new Insets(0, 0, 5, 5);
		gbc_lblN4.gridx = 2;
		gbc_lblN4.gridy = 7;
		add(lblN4, gbc_lblN4);
		
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.anchor = GridBagConstraints.WEST;
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.gridx = 2;
		gbc_lblClave.gridy = 8;
		add(lblClave, gbc_lblClave);
    }
        
    //llamamos a los datos del agremiado, solo los que se ustan usando hasta el momento, nombre, contraseña, clave, correo
    public void muestra(Agremiado agremiado) {
        ///this.controlador = controlador;

        lblNombre.setText(agremiado.getNombreCompleto());
        lblCorreo.setText(agremiado.getCorreo());
    	lblContraseña.setText(agremiado.getContraseña());
    	lblClave.setText(agremiado.getClave());       


        setVisible(true);
    }
    
}
