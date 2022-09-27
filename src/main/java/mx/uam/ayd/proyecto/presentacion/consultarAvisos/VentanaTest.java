package mx.uam.ayd.proyecto.presentacion.consultarAvisos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import mx.uam.ayd.proyecto.presentacion.consultarAvisos.Componente;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;

public class VentanaTest extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTest frame = new VentanaTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaTest() {
		setBounds(new Rectangle(100, 100, 500, 500));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 300, 40, 0};
		gridBagLayout.rowHeights = new int[]{30, 48, 147, 40, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.RIGHT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 2;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		Componente aviso1 = new Componente();
		Componente aviso2 = new Componente();
		Componente aviso3 = new Componente();
		Componente[] avisos = {aviso1,aviso2,aviso3};
		
		tabbedPane.add(aviso1);
		tabbedPane.add(aviso2);
		tabbedPane.add(aviso3);
		
		
		
		
		
		
		
	}

}
