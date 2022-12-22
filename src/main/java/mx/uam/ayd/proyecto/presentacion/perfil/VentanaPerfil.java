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
	private JLabel lblContrasena = new JLabel();
	private JLabel lblClave = new JLabel();

    public VentanaPerfil(){
        setBounds(new Rectangle(100, 100, 500, 400));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{36, 64, 321, 58, 0, 0};
		gridBagLayout.rowHeights = new int[]{45, 30, 30, 30, 30, 30, 30, 30, 30, 85, 27, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblN1 = new JLabel("Nombre completo:");
		GridBagConstraints gbclblN1 = new GridBagConstraints();
		gbclblN1.anchor = GridBagConstraints.NORTHWEST;
		gbclblN1.insets = new Insets(0, 0, 5, 5);
		gbclblN1.gridx = 2;
		gbclblN1.gridy = 1;
		add(lblN1, gbclblN1);
		
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.anchor = GridBagConstraints.WEST;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 2;
		gbclblNombre.gridy = 2;
		add(lblNombre, gbclblNombre);

		JLabel lblN2 = new JLabel("Correo :");
		GridBagConstraints gbclblN2 = new GridBagConstraints();
		gbclblN2.anchor = GridBagConstraints.NORTHWEST;
		gbclblN2.insets = new Insets(0, 0, 5, 5);
		gbclblN2.gridx = 2;
		gbclblN2.gridy = 3;
		add(lblN2, gbclblN2);
		
		GridBagConstraints gbclblCorreo = new GridBagConstraints();
		gbclblCorreo.anchor = GridBagConstraints.WEST;
		gbclblCorreo.insets = new Insets(0, 0, 5, 5);
		gbclblCorreo.gridx = 2;
		gbclblCorreo.gridy = 4;
		add(lblCorreo, gbclblCorreo);
		
		JLabel lblN3 = new JLabel("Contraseña :");
		GridBagConstraints gbclblN3 = new GridBagConstraints();
		gbclblN3.anchor = GridBagConstraints.WEST;
		gbclblN3.insets = new Insets(0, 0, 5, 5);
		gbclblN3.gridx = 2;
		gbclblN3.gridy = 5;
		add(lblN3, gbclblN3);
		
		GridBagConstraints gbclblContrasena = new GridBagConstraints();
		gbclblContrasena.anchor = GridBagConstraints.WEST;
		gbclblContrasena.insets = new Insets(0, 0, 5, 5);
		gbclblContrasena.gridx = 2;
		gbclblContrasena.gridy = 6;
		add(lblContrasena, gbclblContrasena);
		
		JLabel lblN4 = new JLabel("Clave :");
		GridBagConstraints gbclblN4 = new GridBagConstraints();
		gbclblN4.anchor = GridBagConstraints.WEST;
		gbclblN4.insets = new Insets(0, 0, 5, 5);
		gbclblN4.gridx = 2;
		gbclblN4.gridy = 7;
		add(lblN4, gbclblN4);
		
		GridBagConstraints gbclblClave = new GridBagConstraints();
		gbclblClave.anchor = GridBagConstraints.WEST;
		gbclblClave.insets = new Insets(0, 0, 5, 5);
		gbclblClave.gridx = 2;
		gbclblClave.gridy = 8;
		add(lblClave, gbclblClave);
    }
        
    //llamamos a los datos del agremiado, solo los que se ustan usando hasta el momento, nombre, contraseña, clave, correo
    public void muestra(Agremiado agremiado) {
        
        lblNombre.setText(agremiado.getNombreCompleto());
        lblCorreo.setText(agremiado.getCorreo());
    	lblContrasena.setText(agremiado.getContrasenia());
    	lblClave.setText(agremiado.getClave());       


        setVisible(true);
    }
    
}
