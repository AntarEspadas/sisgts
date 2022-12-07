package mx.uam.ayd.proyecto.presentacion.login;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
public class VentanaIniciaSesion extends JFrame{
	private ControlIniciaSesion control;
	private JTextField Correo;
	private JPasswordField Contrasenia;
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
		getContentPane().add(lblTite);//contentPane.add(lblTite);
		
		JLabel lblMeseg = new JLabel("TLÁHUAC");
		lblMeseg.setBounds(31, 55, 80, 16);
		getContentPane().add(lblMeseg);//contentPane.add(lblMese);
		
		JLabel lblMese = new JLabel("SECCIÓN 11");
		lblMese.setBounds(20, 42, 103, 16);
		getContentPane().add(lblMese);//contentPane.add(lblMese);
		
		JLabel lblSub = new JLabel("SNTE");
		lblSub.setBounds(31, 11, 67, 23);
		getContentPane().add(lblSub);//contentPane.add(lblSub);
		
		//ETIQUETAS 
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(140, 110, 80, 16);
		contentPane1.add(lblCorreo);
		
		
		JLabel lblClave= new JLabel("Contraseña:");
		lblClave.setBounds(120, 190, 110, 16);
		contentPane1.add(lblClave);
		
		
		//CUADRO DE TEXTO 
		Correo = new JTextField();
		Correo.setBounds(210, 109, 130, 26);
		contentPane1.add(Correo);
		Correo.setColumns(10);
		
		Contrasenia = new JPasswordField();
		Contrasenia.setBounds(210, 190, 130, 26);
		contentPane1.add(Contrasenia);
		Contrasenia.setColumns(10);
		
		//BOTON INGRESAR
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(330, 340, 117, 29);
		contentPane1.add(btnIngresar);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==btnIngresar) {
					//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
					if(Correo.getText().equals("") || Contrasenia.getText().equals("")) {
					muestraDialogoConMensaje("Ningun campo debe estar vacio");
					}else {
						control.RecuperaCorreo(Correo.getText(),Contrasenia.getText());
						//lblSesion.setText("Sesión iniciado como agremiado");
					    contentPane1.setVisible(true);
					    limpia();
						
					}//FIN DEL ESE DE NINGUN CAMPO VACIO
				}//FIN DEL IF BOTON SIGUIENTE
			}//FIN DEL METODO ACTION PERFORMED
		});//FIN DEL ACTION LISTENER
		
		
		//BOTON OLVIDE
		JButton btnOlvide = new JButton("Olvide mi contraseña");
		btnOlvide.setBounds(28, 340, 143, 29);
		contentPane1.add(btnOlvide);
		
		//BOTON CANCELAR
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			control.termina();
			}
		});
		btnCancelar.setBounds(28, 400, 143, 29);
	    contentPane1.add(btnCancelar);
	    
	    JLabel lblNewLabel = new JLabel("Iniciar Sesion");
	    lblNewLabel.setBounds(197, 34, 80, 14);
	    contentPane1.add(lblNewLabel);

	}
	
	//METODO QUE LIMPIA LAS CASILLAS
	public void limpia() {		
		Correo.setText("");
	    Contrasenia.setText("");
	}//FIN DEL METODO QUE LIMPIA LAS CASILLAS
	
	 public void muestra(ControlIniciaSesion control){//, List <Grupo> grupos) {
		
		this.control = control;
		
		Correo.setText("");

		Contrasenia.setText("");
		
		setVisible(true);

	}
	 
	 public void muestraDialogoConMensaje(String mensaje ) {
			JOptionPane.showMessageDialog(this , mensaje);
	}
}
