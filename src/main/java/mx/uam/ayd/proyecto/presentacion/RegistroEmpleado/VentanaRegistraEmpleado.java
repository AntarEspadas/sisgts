package mx.uam.ayd.proyecto.presentacion.RegistroEmpleado;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

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
public class VentanaRegistraEmpleado extends JFrame {
	private JPanel contentPane1, contentSig;
	private ControlRegistraEmpleado control;
	private JTextField textFieldIdEmpleado;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldTempleado;
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
	
	public VentanaRegistraEmpleado() {
			
			//CREA LA VENTANA
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(500,600);
			setTitle("Registra Empleado");
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
			JLabel lblIdEmpleado = new JLabel("IdEmpleado:");
			lblIdEmpleado.setBounds(28, 110, 80, 16);
			contentPane1.add(lblIdEmpleado);
			
			JLabel lblNombre = new JLabel("Nombre(s):");
			lblNombre.setBounds(240, 110, 80, 16);
			contentPane1.add(lblNombre);
			
			JLabel lblApellido= new JLabel("Apellidos");
			lblApellido.setBounds(28, 190, 110, 16);
			contentPane1.add(lblApellido);
			
			JLabel lblTempleado = new JLabel("Tipo de Empleado");
			lblTempleado.setBounds(240, 190, 80, 16);
			contentPane1.add(lblTempleado);
			
			//CUADRO DE TEXTO 
			textFieldIdEmpleado = new JTextField();
			textFieldIdEmpleado.setBounds(92, 109, 130, 26);
			contentPane1.add(textFieldIdEmpleado);
			textFieldIdEmpleado.setColumns(10);
			
			textFieldNombre = new JTextField();
			textFieldNombre.setBounds(320, 109, 130, 26);
			contentPane1.add(textFieldNombre);
			textFieldNombre.setColumns(10);
			
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(92, 190, 130, 26);
			contentPane1.add(textFieldApellido);
			textFieldApellido.setColumns(10);
			
			textFieldTempleado = new JTextField();
			textFieldTempleado.setBounds(320, 190, 130, 26);
			contentPane1.add(textFieldTempleado);
			textFieldTempleado.setColumns(10);
			
			//LLAMA AL METODO 
			siguiente();
			
			//BOTON AGREGA
			JButton btnAgregar = new JButton("Agregar");
			btnAgregar.setBounds(330, 340, 117, 29);//original btnAgregar.setBounds(28, 189, 117, 29);
			contentSig.add(btnAgregar);//contentPane.add(btnAgregar);
			
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
						if(textFieldIdEmpleado.getText().equals("") || textFieldNombre.getText().equals("")||textFieldApellido.getText().equals("")||textFieldTempleado.getText().equals("")) {
						muestraDialogoConMensaje("Ningun campo debe estar vacio");
					}else {//MUESTRA EL SIGUIENTE PANEL
						//control.Recupera1(textFieldNombre.getText(), textFieldApellido.getText());
						control.Recuperaid(textFieldIdEmpleado.getText());
						/*contentPane1.setVisible(false);
					    contentSig.setVisible(true);*/
						if(control.existe==0) {
							contentPane1.setVisible(false);
						    contentSig.setVisible(true);
						}else {
							contentPane1.setVisible(true);
							contentSig.setVisible(false);
						}
						
					}//FIN DEL ELSE
					}//FIN DEL IF BOTON SIGUIENTE
				}//FIN DEL METODO
			});//TERMINA ACTIONLISTENER

			//BOTON AGREGAR EN EL PANEL DE CORREOS
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
					    	control.RegistraEmpleado(textFieldIdEmpleado.getText(), textFieldNombre.getText(), textFieldApellido.getText(), textFieldTempleado.getText(), textFieldCorreo.getText(), textFieldContrasenia.getText());
					        //control.termina();
					    	contentPane1.setVisible(true);
					        contentSig.setVisible(false);
					        limpia();
					    }else {
					    	contentPane1.setVisible(false);
					        contentSig.setVisible(true);
					    }
					    
					    				    
					    
					        }else {
					        	muestraDialogoConMensaje("Los campos no coinciden por favor rectifique si escribio bien su contraseña o su correo");
					        	}//FIN DEL ELSE DE CUADROS DE TEXTO NO COINCIDEN
						}//FIN DEL ESE DE NINGUN CAMPO VACIO
					}//FIN DEL IF BOTON AGREGA
				}//FIN DEL METODO ACTION PERFORMED
			});//FIN DEL ACTION LISTENER
			
			//BOTON ATRAS CUANDO ESTA EN EL PANEL CORREO
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
			
	    }
		
		//METODO QUE INICIALIZA EL CONTENIDO DEL PANEL 2 CORREOS
		private void siguiente() {

			JLabel lblCorreo = new JLabel("Correo:");
			lblCorreo.setBounds(28, 100, 80, 16);
			contentSig.add(lblCorreo);
			
			JLabel lblContrasenia = new JLabel("Contraseña:");
			lblContrasenia.setBounds(240, 100, 80, 16);
			contentSig.add(lblContrasenia);
			
			JLabel lblConfiCorreo = new JLabel("Confirma Correo:");
			lblConfiCorreo.setBounds(28, 170, 110, 16);
			contentSig.add(lblConfiCorreo);
			
			JLabel lblConfiContrasenia = new JLabel("Confirma Contraseña:");
			lblConfiContrasenia.setBounds(240, 170, 130, 16);
			contentSig.add(lblConfiContrasenia);
			
			textFieldCorreo = new JTextField();
			textFieldCorreo.setBounds(92, 109, 130, 26);
			contentSig.add(textFieldCorreo);
			textFieldCorreo.setColumns(10);
			
			textFieldContrasenia = new JTextField();
			textFieldContrasenia.setBounds(320, 109, 130, 26);
			contentSig.add(textFieldContrasenia);
			textFieldContrasenia.setColumns(10);
			
			textFieldConfiCorreo = new JTextField();
			textFieldConfiCorreo.setBounds(92, 190, 130, 26);
			contentSig.add(textFieldConfiCorreo);
			textFieldConfiCorreo.setColumns(10);
			
			textFieldConfiContrasenia = new JTextField();
			textFieldConfiContrasenia.setBounds(320, 190, 130, 26);
			contentSig.add(textFieldConfiContrasenia);
			textFieldConfiContrasenia.setColumns(10);
			
		}//FIN DEL METODO PANEL 2 CORREOS
		
		//METODO QUE LIMPIA LAS CASILLAS
		public void limpia() {
			textFieldIdEmpleado.setText("");
			textFieldNombre.setText("");
			textFieldApellido.setText("");
			textFieldTempleado.setText("");
			textFieldCorreo.setText("");
			textFieldContrasenia.setText("");
			textFieldConfiCorreo.setText("");
			textFieldConfiContrasenia.setText("");
		}//FIN DEL METODO QUE LIMPIA LAS CASILLAS
		
        /*public void fini() {
			
			contentPane1.setVisible(true);
		    contentSig.setVisible(false);
		    
		}*/
		
	    public void muestra(ControlRegistraEmpleado control){//, List <Grupo> grupos) {
			
			this.control = control;
			
			textFieldIdEmpleado.setText("");
			
			textFieldNombre.setText("");

			textFieldApellido.setText("");
			
			textFieldTempleado.setText("");
			
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
