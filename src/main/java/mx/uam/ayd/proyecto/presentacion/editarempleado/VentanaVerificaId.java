package mx.uam.ayd.proyecto.presentacion.editarempleado;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Slf4j
@Component
public class VentanaVerificaId extends JFrame {

    private JTextField textFiedClav;
    private ControlEditarempleado control;
    private JPanel contentPane1;
    public VentanaVerificaId() {
        //CREA LA VENTANA
        setSize(500, 600);
        setTitle("Editar Empleado");
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        //CREACION DEL PANEL 1
        contentPane1 = new JPanel();
        contentPane1.setBounds(5, 80, 450, 500);
        contentPane1.setLayout(null);
        getContentPane().add(contentPane1);
        contentPane1.setVisible(true);
        JLabel lblClav = new JLabel("Escriba el Id del empleado");
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
        btnCancelar1.addActionListener(e -> cierra());

        JButton btnAceptar1 = new JButton("Aceptar");
        btnAceptar1.setBounds(315, 260, 89, 23);
        contentPane1.add(btnAceptar1);

        btnAceptar1.addActionListener(e -> {

            if (e.getSource() == btnAceptar1) {
                //VERIFICA QUE LOS CAMPOS NO ESTEN VACIOS Y SI ES ASI NO DEJA AVANZAR
                if (textFiedClav.getText().equals("")) {
                    muestraDialogoConMensaje("Ningun campo debe estar vacio");
                } else {
                    long id = Long.parseLong(textFiedClav.getText());
                    control.verificaIdEmpleado(id);
                    limpia();


                }//FIN DEL ESE DE NINGUN CAMPO VACIO
            }//FIN DEL IF BOTON SIGUIENTE

        });//FIN DEL ACTION LISTENER


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

    }
    public void muestra(ControlEditarempleado control){

        this.control = control;

        setVisible(true);

    }
    
    public void limpia() {
    	
    	textFiedClav.setText("");
    }

    public void muestraDialogoConMensaje(String mensaje ) {
        JOptionPane.showMessageDialog(this , mensaje);
    }

    public void cierra() {
        setVisible(false);	//DEJA DE MOSTRAR LA VENTANA
    }//FIN DEL METODO TERMINA

}
