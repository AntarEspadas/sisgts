package mx.uam.ayd.proyecto.presentacion.login;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
public class VentanaIniciaSesion extends JFrame{
	private transient ControlIniciaSesion control;
	private JTextField correo;
	private JPasswordField contrasenia;
	private JPanel contentPane1;
	
	public VentanaIniciaSesion() {
		
		//CREA LA VENTANA
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,600);
		setTitle("Iniciar Sesion");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		//CREACION DEL PANEL 1 
		contentPane1 = new JPanel();
		contentPane1.setBounds(5, 80, 450, 500);
		contentPane1.setLayout(null);
		getContentPane().add(contentPane1);
		contentPane1.setVisible(true);
		
		//CREACION DEL TITULO
		JLabel lblTite = new JLabel("Sistema de Gestión de Trámites ");
		lblTite.setBounds(180, 28, 180, 16);
		getContentPane().add(lblTite);
		
		JLabel lblMeseg = new JLabel("TLÁHUAC");
		lblMeseg.setBounds(31, 55, 80, 16);
		getContentPane().add(lblMeseg);
		
		JLabel lblMese = new JLabel("SECCIÓN 11");
		lblMese.setBounds(20, 42, 103, 16);
		getContentPane().add(lblMese);
		
		JLabel lblSub = new JLabel("SNTE");
		lblSub.setBounds(31, 11, 67, 23);
		getContentPane().add(lblSub);
		
		//ETIQUETAS 
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(140, 110, 80, 16);
		contentPane1.add(lblCorreo);
		
		
		JLabel lblClave= new JLabel("Contraseña:");
		lblClave.setBounds(120, 190, 110, 16);
		contentPane1.add(lblClave);
		
		
		//CUADRO DE TEXTO 
		correo = new JTextField();
		correo.setBounds(210, 109, 130, 26);
		contentPane1.add(correo);
		correo.setColumns(10);
		
		contrasenia = new JPasswordField();
		contrasenia.setBounds(210, 190, 130, 26);
		contentPane1.add(contrasenia);
		contrasenia.setColumns(10);
		
		//BOTON INGRESAR
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(330, 340, 117, 29);
		contentPane1.add(btnIngresar);
		//FIN DEL METODO ACTION PERFORMED
		btnIngresar.addActionListener(e -> {
			var password = String.valueOf(contrasenia.getPassword());
			if(e.getSource()==btnIngresar) {
				//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
				if(correo.getText().equals("") || password.equals("")) {
				muestraDialogoConMensaje("Ningun campo debe estar vacio");
				}else {
					control.verificaCorreoYContrasenia(correo.getText(), password);
					contentPane1.setVisible(true);
					limpia();

				}//FIN DEL ESE DE NINGUN CAMPO VACIO
			}//FIN DEL IF BOTON SIGUIENTE
		});//FIN DEL ACTION LISTENER
		
		
		//BOTON OLVIDE
		JButton btnOlvide = new JButton("Olvide mi contraseña");
		btnOlvide.setBounds(28, 340, 143, 29);
		contentPane1.add(btnOlvide);
		
		//BOTON CANCELAR
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(e -> control.termina());
		btnCancelar.setBounds(28, 400, 143, 29);
	    contentPane1.add(btnCancelar);
	    
	    JLabel lblNewLabel = new JLabel("Iniciar Sesion");
	    lblNewLabel.setBounds(197, 34, 80, 14);
	    contentPane1.add(lblNewLabel);

	}
	
	//METODO QUE LIMPIA LAS CASILLAS
	public void limpia() {		
		correo.setText("");
	    contrasenia.setText("");
	}//FIN DEL METODO QUE LIMPIA LAS CASILLAS
	
	 public void muestra(ControlIniciaSesion control){
		
		this.control = control;
		
		correo.setText("");

		contrasenia.setText("");
		
		setVisible(true);

	}
	 
	 public void muestraDialogoConMensaje(String mensaje ) {
			JOptionPane.showMessageDialog(this , mensaje);
	}
}
