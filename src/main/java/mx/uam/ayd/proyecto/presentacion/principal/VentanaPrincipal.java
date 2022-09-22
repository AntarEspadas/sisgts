package mx.uam.ayd.proyecto.presentacion.principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
@Component
public class VentanaPrincipal extends JFrame {

	private JPanel mainPanel;
	
	private ControlPrincipal control;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
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
	public VentanaPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit screenTk = Toolkit.getDefaultToolkit();
	    Dimension dimScreen = screenTk.getScreenSize();
	    int anchoScreen = dimScreen.width;
        int altoScreen = dimScreen.height;
        Dimension dimMinima = new Dimension((int)(anchoScreen*.8), (int)(altoScreen*.8));
        setMinimumSize(dimMinima);

        // setExtendedState(JFrame.MAXIMIZED_BOTH);

		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setVisible(true);
		setContentPane(mainPanel);


		JLabel lblTop = new JLabel("SISTEMA DE GESTIÓN DE TRAMITES SINDICALES");
		lblTop.setFont(new Font("Consolas", Font.BOLD, 20));
		lblTop.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblTop, BorderLayout.NORTH);

		JButton btnTramites = new JButton("Tramites");
        JButton btnCitas = new JButton("Citas");
        JButton btnPublicaciones = new JButton("Publicaciones");
        
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(3, 1));
        btnPanel.add(btnTramites); btnPanel.add(btnCitas); btnPanel.add(btnPublicaciones);
        mainPanel.add(btnPanel, BorderLayout.WEST);

        btnTramites.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				control.procesarTramites();
				
			}
		});

	}
	
	public void muestra(ControlPrincipal control) {
		
		this.control = control;
		
		setVisible(true);
	}

    
}
