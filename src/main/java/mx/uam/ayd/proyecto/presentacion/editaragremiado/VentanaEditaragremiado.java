package mx.uam.ayd.proyecto.presentacion.editaragremiado;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Agremiado;

import javax.swing.JButton;



@SuppressWarnings("serial")
@Component
public class VentanaEditaragremiado extends JFrame {
	
	private JPanel contentSig;
	private ControlEditaragremiado control;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldClave;
	private JTextField textFieldFiliacion;
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
	private JTextField textFieldAds;
	private JTextField textFieldpue;
	private JTextField textFieldDom;
	private JTextField textFieldTur;
	private JTextField textFieldCel;
	private JTextField textFieldTel;
	private JTextField textFieldCor;
	private JTextField textFieldCon;
	private JTextField textFieldTrab;
	private JTextField textFiedClav;
	
	public VentanaEditaragremiado() {
		
		//CREA LA VENTANA
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,600);
		setTitle("Editar Agremiado");
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		//CREACION DEL PANEL 2 
		contentSig = new JPanel();
		contentSig.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
		//contentPane.setBorder(new EmptyBorder(5, 112, 350, 350));//ORIGINALMENTE SOLO ERA 5
		//setContentPane(contentPane);
		contentSig.setLayout(null);
		getContentPane().add(contentSig);
		contentSig.setVisible(true);
		
		
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
		lblNombre.setBounds(28, 42, 80, 16);
		contentSig.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellidos");
		lblApellido.setBounds(232, 42, 80, 16);
		contentSig.add(lblApellido);
		
		JLabel lblClave= new JLabel("Clave");
		lblClave.setBounds(28, 79, 110, 16);
		contentSig.add(lblClave);
		
		JLabel lblFiliacion = new JLabel("Filiacion");
		lblFiliacion.setBounds(232, 79, 80, 16);
		contentSig.add(lblFiliacion);
		
		//CUADRO DE TEXTO 
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(92, 37, 130, 26);
		contentSig.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(292, 37, 130, 26);
		contentSig.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldClave = new JTextField();
		textFieldClave.setBounds(92, 74, 130, 26);
		contentSig.add(textFieldClave);
		textFieldClave.setColumns(10);
		
		textFieldFiliacion = new JTextField();
		textFieldFiliacion.setBounds(292, 74, 130, 26);
		contentSig.add(textFieldFiliacion);
		textFieldFiliacion.setColumns(10);
		
		JLabel lblAdscripcion = new JLabel("Adscripcion");
		lblAdscripcion.setBounds(28, 113, 110, 16);
		contentSig.add(lblAdscripcion);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(231, 113, 110, 16);
		contentSig.add(lblPuesto);
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setBounds(28, 150, 110, 16);
		contentSig.add(lblDomicilio);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setBounds(232, 150, 110, 16);
		contentSig.add(lblTurno);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(28, 188, 110, 16);
		contentSig.add(lblCelular);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(231, 189, 110, 16);
		contentSig.add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(28, 225, 110, 16);
		contentSig.add(lblCorreo);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(232, 226, 110, 16);
		contentSig.add(lblContrasea);
		
		JLabel lblCentroDeTrabajo = new JLabel("Centro de trabajo");
		lblCentroDeTrabajo.setBounds(28, 267, 110, 16);
		contentSig.add(lblCentroDeTrabajo);
		
		textFieldAds = new JTextField();
		textFieldAds.setColumns(10);
		textFieldAds.setBounds(92, 106, 130, 26);
		contentSig.add(textFieldAds);
		
		textFieldpue = new JTextField();
		textFieldpue.setColumns(10);
		textFieldpue.setBounds(292, 111, 130, 26);
		contentSig.add(textFieldpue);
		
		textFieldDom = new JTextField();
		textFieldDom.setColumns(10);
		textFieldDom.setBounds(92, 148, 130, 26);
		contentSig.add(textFieldDom);
		
		textFieldTur = new JTextField();
		textFieldTur.setColumns(10);
		textFieldTur.setBounds(292, 148, 130, 26);
		contentSig.add(textFieldTur);
		
		textFieldCel = new JTextField();
		textFieldCel.setColumns(10);
		textFieldCel.setBounds(92, 186, 130, 26);
		contentSig.add(textFieldCel);
		
		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		textFieldTel.setBounds(292, 186, 130, 26);
		contentSig.add(textFieldTel);
		
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
		textFieldTrab.setBounds(118, 262, 130, 26);
		contentSig.add(textFieldTrab);
		
		JButton btnCancelar2 = new JButton("Cancelar ");
		btnCancelar2.setBounds(28, 315, 89, 23);
		contentSig.add(btnCancelar2);
		btnCancelar2.addActionListener(e -> cierra());
		
		JButton btnAceptar2 = new JButton("Aceptar");
		btnAceptar2.setBounds(293, 315, 89, 23);
		contentSig.add(btnAceptar2);
		
	}
	
	//METODO QUE LIMPIA LAS CASILLAS
	public void limpia() {		
		//correo.setText("");
		//contrasenia.setText("");
	}//FIN DEL METODO QUE LIMPIA LAS CASILLAS
		
	 public void muestra(ControlEditaragremiado control, Agremiado agremiado){
			
		this.control = control;
		
		String clave=new String();
		clave=String.valueOf(agremiado.getClave());
		textFieldClave.setText(clave);
		
		String nombree=new String();
		nombree=String.valueOf(agremiado.getNombre());
		textFieldNombre.setText(nombree);
		
		String apellido=new String();
		apellido=String.valueOf(agremiado.getApellidos());
		textFieldApellido.setText(apellido);
		
		String correo=new String();
		correo=String.valueOf(agremiado.getCorreo());
		textFieldCor.setText(correo);
		
		String contrasenia=new String();
		contrasenia=String.valueOf(agremiado.getContrasenia());
		textFieldCon.setText(contrasenia);
		
		String filiacion =new String();
		filiacion=String.valueOf(agremiado.getFiliacion());
		textFieldFiliacion.setText(filiacion);
		
		String adscripcion =new String();
		adscripcion=String.valueOf(agremiado.getAdscripcion());
		textFieldAds.setText(adscripcion);
		
		String puesto =new String();
		puesto=String.valueOf(agremiado.getPuesto());
		textFieldpue.setText(puesto);
		
		String turno =new String();
		turno=String.valueOf(agremiado.getTurno());
		textFieldTur.setText(turno);
		
		String domicilio =new String();
		domicilio=String.valueOf(agremiado.getDomicilio());
		textFieldDom.setText(domicilio);
		
		String celular =new String();
		celular=String.valueOf(agremiado.getCelular());
		textFieldCel.setText(celular);
		
		String telefono =new String();
		telefono=String.valueOf(agremiado.getTelefono());
		textFieldTel.setText(telefono);
		
		String cTrabajo =new String();
		cTrabajo=String.valueOf(agremiado.getCentrotrabajo());
		textFieldTrab.setText(cTrabajo);
			
		setVisible(true);

	}
		 
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
	public void cierra() {
		setVisible(false);	//DEJA DE MOSTRAR LA VENTANA
	}//FIN DEL METODO TERMINA
}
