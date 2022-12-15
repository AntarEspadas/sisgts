package mx.uam.ayd.proyecto.presentacion.editarempleado;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;



@SuppressWarnings("serial")
@Component
public class VentanaEditarempleado extends JFrame {
	
	private ControlEditarempleado control;
	private JPanel contentPane1, contentSig;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldClave;
	private JTextField textFieldAdscripcion;
	private JTextField textFieldPuesto;
	private JTextField textFieldDomicilio;
	private JTextField textFieldTurno;
	private JTextField textFieldCelular;
	private JTextField textFieldTelefono;
	private JTextField textFieldCentrodetrabajo;
	private JTextField textFieldContrasenia;
	private JTextField textFieldCorreo;
	private JTextField textFieldConfiCorreo;
	private JTextField textFieldConfiContrasenia;
	private JTextField textFieldCor;
	private JTextField textFieldCon;
	private JTextField textFieldTrab;
	private JTextField textFiedClav;
	
	public VentanaEditarempleado() {
		
		//CREA LA VENTANA
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,600);
		setTitle("Editar Empleado");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
			
		//CREACION DEL PANEL 1
		contentPane1 = new JPanel();
		contentPane1.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
		//contentSig.setBorder(new EmptyBorder(5, 5, 5, 5));//ORIGINALMENTE SOLO ERA 5
		//setContentPane(contentSig);
		contentPane1.setLayout(null);
		getContentPane().add(contentPane1);
		contentPane1.setVisible(true);
				
		JLabel lblClav= new JLabel("Escriba el Id del empleado");
		lblClav.setBounds(167, 117, 186, 16);
		contentPane1.add(lblClav);
						
		//CUADRO DE TEXTO 
		textFiedClav = new JTextField();
		textFiedClav.setBounds(169, 145, 130, 26);
		contentPane1.add(textFiedClav);
		textFiedClav.setColumns(10);
				
		JButton btnCancelar1 = new JButton("Cancelar");
		btnCancelar1.setBounds(52, 260, 89, 23);
		contentPane1.add(btnCancelar1);
		btnCancelar1.addActionListener(e -> control.termina());
				
		JButton btnAceptar1 = new JButton("Aceptar");
		btnAceptar1.setBounds(315, 260, 89, 23);
		contentPane1.add(btnAceptar1);
		
		btnAceptar1.addActionListener(e -> {
			
			if(e.getSource()==btnAceptar1) {
				//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
				if(textFieldClave.getText().equals("")) {
				muestraDialogoConMensaje("Ningun campo debe estar vacio");
			    }else {
				//control.verificaIdEmpleado(.getText());
				contentPane1.setVisible(false);
				contentSig.setVisible(true);
				//limpia();

			}//FIN DEL ESE DE NINGUN CAMPO VACIO
		}//FIN DEL IF BOTON SIGUIENTE

		});//FIN DEL ACTION LISTENER				
		
			
		//CREACION DEL PANEL 2 
		contentSig = new JPanel();
		contentSig.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
		//contentPane.setBorder(new EmptyBorder(5, 112, 350, 350));//ORIGINALMENTE SOLO ERA 5
		//setContentPane(contentPane);
		contentSig.setLayout(null);
		getContentPane().add(contentSig);
		contentSig.setVisible(false);
				
				
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
		JLabel lblNombre = new JLabel("Nombre(s)");
		lblNombre.setBounds(230, 53, 80, 16);
		contentSig.add(lblNombre);
				
		JLabel lblApellido = new JLabel("Apellidos");
		lblApellido.setBounds(28, 145, 80, 16);
		contentSig.add(lblApellido);
				
		JLabel lblClave= new JLabel("IdEmpleado");
		lblClave.setBounds(28, 53, 110, 16);
		contentSig.add(lblClave);
				
		//CUADRO DE TEXTO 
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(292, 48, 130, 26);
		contentSig.add(textFieldNombre);
		textFieldNombre.setColumns(10);
				
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(92, 140, 130, 26);
		contentSig.add(textFieldApellido);
		textFieldApellido.setColumns(10);
				
		textFieldClave = new JTextField();
		textFieldClave.setBounds(92, 48, 130, 26);
		contentSig.add(textFieldClave);
		textFieldClave.setColumns(10);
				
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(28, 225, 110, 16);
		contentSig.add(lblCorreo);
				
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(232, 226, 110, 16);
		contentSig.add(lblContrasea);
				
		JLabel lblCentroDeTrabajo = new JLabel("TipoEmpleado");
		lblCentroDeTrabajo.setBounds(230, 145, 110, 16);
		contentSig.add(lblCentroDeTrabajo);
				
		textFieldCor = new JTextField();
		textFieldCor.setColumns(10);
		textFieldCor.setBounds(92, 223, 130, 26);
		contentSig.add(textFieldCor);
				
		textFieldCon = new JTextField();
		textFieldCon.setColumns(10);
		textFieldCon.setBounds(292, 223, 130, 26);
		contentSig.add(textFieldCon);
				
		textFieldTrab = new JTextField();
		textFieldTrab.setColumns(10);
		textFieldTrab.setBounds(292, 140, 130, 26);
		contentSig.add(textFieldTrab);
				
		JButton btnCancelar2 = new JButton("Cancelar ");
		btnCancelar2.setBounds(49, 315, 89, 23);
		contentSig.add(btnCancelar2);
				
		JButton btnAceptar2 = new JButton("Aceptar");
		btnAceptar2.setBounds(317, 315, 89, 23);
		contentSig.add(btnAceptar2);
		
	}
	
	//METODO QUE LIMPIA LAS CASILLAS
	public void limpia() {		
		//correo.setText("");
		//contrasenia.setText("");
	}//FIN DEL METODO QUE LIMPIA LAS CASILLAS

	 public void muestra(ControlEditarempleado control){
			
		this.control = control;
			
		setVisible(true);

	}
		 
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}
