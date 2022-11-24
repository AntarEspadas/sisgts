package mx.uam.ayd.proyecto.presentacion.RegistrarAgremiado;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.RegistroEmpleado.ControlRegistraEmpleado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
//import mx.uam.ayd.proyecto.negocio.modelo.Grupo;

@SuppressWarnings("serial")
@Component
public class VentanaRegistraAgremiado extends JFrame {
	private JPanel contentPane1, contentSig, content3, content4;
	private ControlRegistraAgremiado control;
	/*@Autowired
	private ControlRegistraAgremiado verificausuario;
	@Autowired
	private ControlRegistraAgremiado verificacorreo;*/
	
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
	
	

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAgregarUsuario frame = new VentanaAgregarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	
	public VentanaRegistraAgremiado() {
			
			//CREA LA VENTANA
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(500,600);
			setTitle("Registra Agremiado");
			getContentPane().setLayout(null);
			setLocationRelativeTo(null);
			
			//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//CREACION DEL PANEL 1 
			contentPane1 = new JPanel();
			contentPane1.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
			//contentPane.setBorder(new EmptyBorder(5, 112, 350, 350));//ORIGINALMENTE SOLO ERA 5
			//setContentPane(contentPane);
			contentPane1.setLayout(null);
			getContentPane().add(contentPane1);
			contentPane1.setVisible(true);
			
			//CREACION DEL PANEL 2
			contentSig = new JPanel();
			contentSig.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
			//contentSig.setBorder(new EmptyBorder(5, 5, 5, 5));//ORIGINALMENTE SOLO ERA 5
			//setContentPane(contentSig);
			contentSig.setLayout(null);
			getContentPane().add(contentSig);
			contentSig.setVisible(false);
			
			//CREACION DEL PANEL 3
			content3 = new JPanel();
			content3.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
			//contentSig.setBorder(new EmptyBorder(5, 5, 5, 5));//ORIGINALMENTE SOLO ERA 5
			//setContentPane(contentSig);
			content3.setLayout(null);
			getContentPane().add(content3);
			content3.setVisible(false);
			
			//CREACION DEL PANEL 4
			content4 = new JPanel();
			content4.setBounds(5, 80, 450, 500);//setBounds(100, 100, 292, 266);
			//contentSig.setBorder(new EmptyBorder(5, 5, 5, 5));//ORIGINALMENTE SOLO ERA 5
			//setContentPane(contentSig);
			content4.setLayout(null);
			getContentPane().add(content4);
			content4.setVisible(false);
			
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
			JLabel lblNombre = new JLabel("Nombre(s):");
			lblNombre.setBounds(28, 110, 80, 16);
			contentPane1.add(lblNombre);
			
			JLabel lblApellido = new JLabel("Apellidos:");
			lblApellido.setBounds(240, 110, 80, 16);
			contentPane1.add(lblApellido);
			
			JLabel lblClave= new JLabel("Clave");
			lblClave.setBounds(28, 190, 110, 16);
			contentPane1.add(lblClave);
			
			JLabel lblFiliacion = new JLabel("Filiacion");
			lblFiliacion.setBounds(240, 190, 80, 16);
			contentPane1.add(lblFiliacion);
			
			//CUADRO DE TEXTO 
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(92, 109, 130, 26);
			contentPane1.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(320, 109, 130, 26);
			contentPane1.add(textFieldApellido);
			textFieldApellido.setColumns(10);
			
			textFieldClave = new JTextField();
			textFieldClave.setBounds(92, 190, 130, 26);
			contentPane1.add(textFieldClave);
			textFieldClave.setColumns(10);
			
			textFieldFiliacion = new JTextField();
			textFieldFiliacion.setBounds(320, 190, 130, 26);
			contentPane1.add(textFieldFiliacion);
			textFieldFiliacion.setColumns(10);
			
			//LLAMA AL METODO 
			siguiente();
			siguiente1();
			siguiente2();
			
			//BOTON AGREGA
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(330, 340, 117, 29);//original btnAgregar.setBounds(28, 189, 117, 29);
			content4.add(btnAgregar);//contentPane.add(btnAgregar);
			
			//BOTON CANCELAR
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				control.termina();
				}
			});
			btnCancelar.setBounds(28, 340, 117, 29); //original btnCancelar.setBounds(157, 189, 117, 29);
			contentPane1.add(btnCancelar);//contentPane.add(btnCancelar);

			//BOTON SIGUIENTE EN EL PANEL NOMBRE APELLIDO
			JButton btnSiguiente = new JButton("Siguiente");
			btnSiguiente.setBounds(330, 340, 117, 29);
			contentPane1.add(btnSiguiente);//contentPane.add(btnSiguiente);
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==btnSiguiente) {
						//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
						if(textFieldNombre.getText().equals("") || textFieldApellido.getText().equals("")||textFieldClave.getText().equals("")||textFieldFiliacion.getText().equals("")) {
						muestraDialogoConMensaje("Ningun campo debe estar vacio");
					}else {//MUESTRA EL SIGUIENTE PANEL
						control.RecuperaClave(textFieldClave.getText());
						//control.Recupera(textFieldNombre.getText(), textFieldApellido.getText());
						/*contentPane1.setVisible(false);
						contentSig.setVisible(true);*/
						if(control.existe==0) {
							contentPane1.setVisible(false);
						    contentSig.setVisible(true);
							/*contentPane1.setVisible(false);
							contentSig.setVisible(false);
							content3.setVisible(false);
							content4.setVisible(true);*/
						}else {
							contentPane1.setVisible(true);
							contentSig.setVisible(false);
							content3.setVisible(false);
							content4.setVisible(false);
							/*contentPane1.setVisible(true);
							contentSig.setVisible(false);*/
						}
					}//FIN DEL ELSE
					}//FIN DEL IF BOTON SIGUIENTE
				}//FIN DEL METODO
			});//TERMINA ACTIONLISTENER

			
			//BOTON SIGUIENTE EN EL PANEL 2 ADSCRIPCION
			JButton btnSiguiente1 = new JButton("Siguiente");
			btnSiguiente1.setBounds(330, 340, 117, 29);
			contentSig.add(btnSiguiente1);//contentPane.add(btnSiguiente);
			btnSiguiente1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==btnSiguiente1) {
						//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
						if(textFieldAdscripcion.getText().equals("") || textFieldPuesto.getText().equals("")||textFieldDomicilio.getText().equals("")||textFieldTurno.getText().equals("")) {
						muestraDialogoConMensaje("Ningun campo debe estar vacio");
					}else {//MUESTRA EL SIGUIENTE PANEL
						contentPane1.setVisible(false);
						contentSig.setVisible(false);
						content3.setVisible(true);
					}//FIN DEL ELSE
					}//FIN DEL IF BOTON SIGUIENTE
				}//FIN DEL METODO
			});//TERMINA ACTIONLISTENER
			
			
			//BOTON SIGUIENTE EN EL PANEL 3 CELULAR
			JButton btnSiguiente2 = new JButton("Siguiente");
			btnSiguiente2.setBounds(330, 340, 117, 29);
			content3.add(btnSiguiente2);//contentPane.add(btnSiguiente);
			btnSiguiente2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==btnSiguiente2) {
						//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
						if(textFieldCelular.getText().equals("") || textFieldTelefono.getText().equals("")||textFieldCentrodetrabajo.getText().equals("")) {
						muestraDialogoConMensaje("Ningun campo debe estar vacio");
					}else {//MUESTRA EL SIGUIENTE PANEL
						//control.Recupera(textFieldNombre.getText(), textFieldApellido.getText());
						contentPane1.setVisible(false);
						contentSig.setVisible(false);
						content3.setVisible(false);
						content4.setVisible(true);
					}//FIN DEL ELSE
					}//FIN DEL IF BOTON SIGUIENTE
				}//FIN DEL METODO
			});//TERMINA ACTIONLISTENER
			
	
			//BOTON SIGUIENTE EN EL PANEL 4 DE CORREOS
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==btnAgregar) {
						//VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
						if(textFieldCorreo.getText().equals("") || textFieldContrasenia.getText().equals("")||textFieldConfiCorreo.getText().equals("")||textFieldConfiContrasenia.getText().equals("")) {
						muestraDialogoConMensaje("Ningun campo debe estar vacio");
						}else {
						//VERIFICA QUE LOS CAMPOS DE CONTRASEÑA Y DE CORREO COINCIDAN
						if(textFieldCorreo.getText().equals(textFieldConfiCorreo.getText()) && textFieldContrasenia.getText().equals(textFieldConfiContrasenia.getText())) {
							control.recuperaCorreo(textFieldCorreo.getText());
							if(control.verifica==0) {
								control.RegistraAgremiado(textFieldNombre.getText(), textFieldApellido.getText(), textFieldClave.getText(), textFieldFiliacion.getText(), textFieldAdscripcion.getText(), textFieldPuesto.getText(), textFieldDomicilio.getText(), textFieldTurno.getText(),textFieldCelular.getText(),textFieldTelefono.getText(), textFieldCentrodetrabajo.getText(), textFieldCorreo.getText(), textFieldContrasenia.getText());
						        contentPane1.setVisible(true);
						        contentSig.setVisible(false);
						        content3.setVisible(false);
						        content4.setVisible(false);
						        limpia();
							}else {
								contentPane1.setVisible(false);
								contentSig.setVisible(false);
								content3.setVisible(false);
								content4.setVisible(true);
							}
						    
					        }else {
					        	muestraDialogoConMensaje("Los campos no coinciden por favor rectifique si escribio bien su contraseña o su correo");
					        	}//FIN DEL ELSE DE CUADROS DE TEXTO NO COINCIDEN
						}//FIN DEL ESE DE NINGUN CAMPO VACIO
					}//FIN DEL IF BOTON SIGUIENTE
				}//FIN DEL METODO ACTION PERFORMED
			});//FIN DEL ACTION LISTENER
					
			//BOTON ATRAS CUANDO ESTA EN EL PANEL ADSCRIPCION
			JButton btnAtras = new JButton("Atras");
			btnAtras.setBounds(28, 340, 117, 29);
			contentSig.add(btnAtras);//contentPane.add(btnAtras);
			btnAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//MUESTRA EL PANEL ANTERIOR
					if(e.getSource()==btnAtras) {
						contentSig.setVisible(false);
						contentPane1.setVisible(true);
					}//FIN DEL IF
				}//FIN DEL METODO ACTION PERFORMED
			});//FIN DEL ACTION LISTENER
			
			//BOTON ATRAS 2 EN EL PANEL CELULAR
		    JButton btnAtras2 = new JButton("Atras");
			btnAtras2.setBounds(28, 340, 117, 29);
			content3.add(btnAtras2);//contentPane.add(btnAtras);
			btnAtras2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//MUESTRA EL PANEL ANTERIOR
					if(e.getSource()==btnAtras2) {
						contentPane1.setVisible(false);
						content3.setVisible(false);
						contentSig.setVisible(true);
					}//FIN DEL IF
				}//FIN DEL METODO ACTION PERFORMED
			});//FIN DEL ACTION LISTENER
			
			//BOTON ATRAS 3 EN EL PANEL CORREO
			JButton btnAtras3 = new JButton("Atras");
			btnAtras3.setBounds(28, 340, 117, 29);
			content4.add(btnAtras3);//contentPane.add(btnAtras);
			btnAtras3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource()==btnAtras3) {
						//MUESTRA EL PANEL ANTERIOR
						contentPane1.setVisible(false);
						content4.setVisible(false);
						contentSig.setVisible(false);
						content3.setVisible(true);
						
					}//FIN DEL IF
				}//FIN DEL METODO ACTION PERFORMED
			});//FIN DEL ACTION LISTENER
		
		}
	
	//METODO QUE INICIALIZA EL CONTENIDO DEL PANEL 2 ADSCRIPCION
			private void siguiente1() {

				JLabel lbladscripcion = new JLabel("Adscripcion:");
				lbladscripcion.setBounds(28, 100, 80, 16);
				contentSig.add(lbladscripcion);
				
				JLabel lblpuesto = new JLabel("Puesto:");
				lblpuesto.setBounds(240, 100, 80, 16);
				contentSig.add(lblpuesto);
				
				JLabel lbldomicilio = new JLabel("Domicilio:");
				lbldomicilio.setBounds(28, 170, 110, 16);
				contentSig.add(lbldomicilio);
				
				JLabel lblturno = new JLabel("Turno:");
				lblturno.setBounds(240, 170, 130, 16);
				contentSig.add(lblturno);
				
				textFieldAdscripcion = new JTextField();
				textFieldAdscripcion.setBounds(92, 109, 130, 26);
				contentSig.add(textFieldAdscripcion);
				textFieldAdscripcion.setColumns(10);
				
				textFieldPuesto = new JTextField();
				textFieldPuesto.setBounds(320, 109, 130, 26);
				contentSig.add(textFieldPuesto);
				textFieldPuesto.setColumns(10);
				
				textFieldDomicilio = new JTextField();
				textFieldDomicilio.setBounds(92, 190, 130, 26);
				contentSig.add(textFieldDomicilio);
				textFieldDomicilio.setColumns(10);
				
				textFieldTurno = new JTextField();
				textFieldTurno.setBounds(320, 190, 130, 26);
				contentSig.add(textFieldTurno);
				textFieldTurno.setColumns(10);
				
			}//FIN DEL METODO PANEL 2 ADSCRIPCION
		
			//METODO QUE INICIALIZA EL CONTENIDO DEL PANEL 3 CELULAR
			private void siguiente2() {

				JLabel lblcelular = new JLabel("Celular:");
				lblcelular.setBounds(28, 100, 80, 16);
				content3.add(lblcelular);
				
				JLabel lbltelefono = new JLabel("Telefono:");
				lbltelefono.setBounds(240, 100, 80, 16);
				content3.add(lbltelefono);
				
				JLabel lblcentrodetrabajo = new JLabel("Centro de trabajo:");
				lblcentrodetrabajo.setBounds(28, 170, 110, 16);
				content3.add(lblcentrodetrabajo);
				
				textFieldCelular = new JTextField();
				textFieldCelular.setBounds(92, 109, 130, 26);
				content3.add(textFieldCelular);
				textFieldCelular.setColumns(10);
				
				textFieldTelefono = new JTextField();
				textFieldTelefono.setBounds(320, 109, 130, 26);
				content3.add(textFieldTelefono);
				textFieldTelefono.setColumns(10);
				
				textFieldCentrodetrabajo = new JTextField();
				textFieldCentrodetrabajo.setBounds(92, 190, 130, 26);
				content3.add(textFieldCentrodetrabajo);
				textFieldCentrodetrabajo.setColumns(10);
				
			}//FIN DEL METODO PANEL 3 CELULAR 
	
	
		//METODO QUE INICIALIZA EL CONTENIDO DEL PANEL 4 CORREOS
		private void siguiente() {

			JLabel lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(28, 100, 80, 16);
			content4.add(lblCorreo);
			
			JLabel lblContrasenia = new JLabel("Contraseña:");
			lblContrasenia.setBounds(240, 100, 80, 16);
			content4.add(lblContrasenia);
			
			JLabel lblConfiCorreo = new JLabel("Confirma Correo:");
			lblConfiCorreo.setBounds(28, 170, 110, 16);
			content4.add(lblConfiCorreo);
			
			JLabel lblConfiContrasenia = new JLabel("Confirma Contraseña:");
			lblConfiContrasenia.setBounds(240, 170, 130, 16);
			content4.add(lblConfiContrasenia);
			
			textFieldCorreo = new JTextField();
			textFieldCorreo.setBounds(92, 109, 130, 26);
			content4.add(textFieldCorreo);
			textFieldCorreo.setColumns(10);
			
			textFieldContrasenia = new JTextField();
			textFieldContrasenia.setBounds(320, 109, 130, 26);
			content4.add(textFieldContrasenia);
			textFieldContrasenia.setColumns(10);
			
			textFieldConfiCorreo = new JTextField();
			textFieldConfiCorreo.setBounds(92, 190, 130, 26);
			content4.add(textFieldConfiCorreo);
			textFieldConfiCorreo.setColumns(10);
			
			textFieldConfiContrasenia = new JTextField();
			textFieldConfiContrasenia.setBounds(320, 190, 130, 26);
			content4.add(textFieldConfiContrasenia);
			textFieldConfiContrasenia.setColumns(10);
			
		}//FIN DEL METODO PANEL 4 CORREOS
		
		//METODO QUE LIMPIA LAS CASILLAS
		public void limpia() {
			
			textFieldNombre.setText("");
			textFieldApellido.setText("");
			textFieldClave.setText("");
			textFieldFiliacion.setText("");
			textFieldAdscripcion.setText("");
			textFieldPuesto.setText("");
			textFieldDomicilio.setText("");
			textFieldTurno.setText("");
			textFieldCelular.setText("");
			textFieldTelefono.setText("");
			textFieldCentrodetrabajo.setText("");
			textFieldCorreo.setText("");
			textFieldContrasenia.setText("");
			textFieldConfiCorreo.setText("");
			textFieldConfiContrasenia.setText("");
		}//FIN DEL METODO QUE LIMPIA LAS CASILLAS
		
		/*public void fini() {
			
			contentPane1.setVisible(true);
		    contentSig.setVisible(false);
		    content3.setVisible(false);
		    content4.setVisible(false);
		}*/
		
	    public void muestra(ControlRegistraAgremiado control){//, List <Grupo> grupos) {
			
			this.control = control;
			
			textFieldNombre.setText("");

			textFieldApellido.setText("");
			
			textFieldClave.setText("");
			
			textFieldFiliacion.setText("");
			
			textFieldCorreo.setText("");
			
			setVisible(true);

			}

		/*DefaultComboBoxModel <String> comboBoxModel = new DefaultComboBoxModel <>();
			
			
			for(Grupo grupo:grupos) {
				comboBoxModel.addElement(grupo.getNombre());
			}
			
			comboBoxGrupo.setModel(comboBoxModel);*/
			
	//		setVisible(true);

	//	}
		
		public void muestraDialogoConMensaje(String mensaje ) {
			JOptionPane.showMessageDialog(this , mensaje);
		}
}
