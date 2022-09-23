package mx.uam.ayd.proyecto.presentacion.procesarTramites;

import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;

import java.io.*;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.Pantalla;

@Slf4j
@Component
public class VentanaProcesarTramites extends Pantalla {
    
    private ControlProcesarTramites control;

    private JLabel lblNorth, lblNo, lblFecha, lblEstado, lblTipo, lblSolicitante, lblDocumentos;
    private JList<String> listaSolicitudes;
    private JPanel panelProcesamientoSolicitudes, panelDatosSolicitud;
    private JScrollPane barraDespl;
    private JButton btnDescargarDocumentos, btnSiguiente, btnTramitesFinalizados;
    private JRadioButton radioBtnAceptar, radioBtnRechazar;
    private List <Documento> documentosAdjuntos;
    private File path;
    private SolicitudTramite solicitudSeleccionada;
    private JComboBox comboBoxMotivosRechazo;



    public VentanaProcesarTramites(){
        setBounds(new Rectangle(100, 100, 500, 500));
        setLayout(new BorderLayout());
        
        lblNorth = new JLabel("Solicitudes de trámites activas");
		lblNorth.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNorth.setBorder(new EmptyBorder(10,0,10,0));
        lblNorth.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNorth, BorderLayout.NORTH);

        btnTramitesFinalizados = new JButton("Tramites finalizados");
        add(btnTramitesFinalizados, BorderLayout.SOUTH);

        panelProcesamientoSolicitudes = new JPanel();
        panelProcesamientoSolicitudes.setLayout(new FlowLayout());
        panelProcesamientoSolicitudes.setBorder(new LineBorder(new Color(255,255,255), 2, true));
        add(panelProcesamientoSolicitudes, BorderLayout.CENTER);
        
        panelDatosSolicitud = new JPanel();
        // panelDatosSolicitud.setBorder(new EmptyBorder(10,10,10,10));
        panelDatosSolicitud.setBorder(new LineBorder(new Color(0,255,255), 2, true));
        panelDatosSolicitud.setLayout(new GridLayout(7,1,10,10));
        // add(panelDatosSolicitud, BorderLayout.CENTER);
        panelProcesamientoSolicitudes.add(panelDatosSolicitud);
        
        lblNo = new JLabel();
        lblNo.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFecha = new JLabel();
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        
        lblEstado = new JLabel();
        lblEstado.setFont(new Font("Arial", Font.PLAIN, 15));
        lblTipo = new JLabel();
        lblTipo.setFont(new Font("Arial", Font.PLAIN, 15));        
        
        lblSolicitante = new JLabel();
        lblSolicitante.setFont(new Font("Arial", Font.PLAIN, 15));
        
        lblDocumentos = new JLabel();
        lblDocumentos.setFont(new Font("Arial", Font.BOLD, 15));
        
        btnDescargarDocumentos = new JButton("Descargar documentos");
        btnDescargarDocumentos.setFont(new Font("Arial", Font.PLAIN, 15));
        
        radioBtnAceptar = new JRadioButton("Aceptar documentos");
        radioBtnAceptar.setFont(new Font("Arial", Font.PLAIN, 15));
        radioBtnRechazar = new JRadioButton("Rechazar documentos");
        radioBtnRechazar.setFont(new Font("Arial", Font.PLAIN, 15));
        
        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 15));

        comboBoxMotivosRechazo = new JComboBox();
        


        /* panelLbls = new JPanel();
        panelLbls.setLayout(new GridLayout(4,2,10,10)); */

        panelDatosSolicitud.add(lblNo);
        panelDatosSolicitud.add(lblFecha);

        panelDatosSolicitud.add(lblEstado);
        panelDatosSolicitud.add(lblTipo);

        panelDatosSolicitud.add(lblSolicitante);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        panelDatosSolicitud.add(lblDocumentos);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        panelDatosSolicitud.add(btnDescargarDocumentos);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        panelDatosSolicitud.add(radioBtnAceptar);
        panelDatosSolicitud.add(radioBtnRechazar);

        panelDatosSolicitud.add(btnSiguiente);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        
        panelDatosSolicitud.setVisible(false);
        
        btnDescargarDocumentos.addActionListener(e -> guardarDocumentos(solicitudSeleccionada));

        radioBtnAceptar.addActionListener(e -> radioBtnRechazar.setSelected(false));
        
        radioBtnRechazar.addActionListener(e -> radioBtnAceptar.setSelected(false));
        

    }

    public void muestra(List<SolicitudTramite> solicitudes, ControlProcesarTramites control) {
        
        this.control = control;

        String [] datosListaSolicitudes = new String [solicitudes.size()];
        
        int i = 0;
        for (SolicitudTramite solicitud : solicitudes) {
            datosListaSolicitudes[i] = solicitud.toString();
            i++;
        }

        listaSolicitudes = new JList<String> (datosListaSolicitudes);
        barraDespl = new JScrollPane(listaSolicitudes);

        add(barraDespl, BorderLayout.WEST);

        setVisible(true);

        listaSolicitudes.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                int index = listaSolicitudes.getSelectedIndex();
                SolicitudTramite solicitudSeleccionada = solicitudes.get(index);
                String estadoSol = solicitudSeleccionada.getEstado();

                switch (estadoSol) {
                    case "Pendiente":
                        control.tramitePendiente(solicitudSeleccionada);
                        break;
                    
                    case "En progreso":
                        control.tramiteEnProgreso(solicitudSeleccionada);
                        break;

                    case "Rechazada":
                        control.tramiteRechazado(solicitudSeleccionada);
                        break;

                    default:
                        break;
                }
            }
        });
    }
    

    public void ventanaTramitePendiente(SolicitudTramite solicitudSeleccionada_) {

        // log.info("Seleccionada solicitud pendiente");

        this.solicitudSeleccionada = solicitudSeleccionada_;

        radioBtnAceptar.setVisible(true);
        radioBtnAceptar.setSelected(false);
        radioBtnRechazar.setVisible(true);
        radioBtnRechazar.setSelected(false);
        btnDescargarDocumentos.setVisible(true);
        btnSiguiente.setVisible(true);

        lblNo.setText("Número de solicitud: "+String.valueOf(solicitudSeleccionada.getIdSolicitud()));
        lblFecha.setText("Fecha de registro: "+String.valueOf(solicitudSeleccionada.getFechaSolicitud()).substring(0,10));

        lblEstado.setText("Estado de la solicitud: "+solicitudSeleccionada.getEstado());
        lblTipo.setText("Trámite solicitado: "+solicitudSeleccionada.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: "+solicitudSeleccionada.getSolicitante().getNombre() + " " + solicitudSeleccionada.getSolicitante().getApellidos()+" ("+solicitudSeleccionada.getSolicitante().getClave()+")");
        
        documentosAdjuntos = solicitudSeleccionada.getRequisitos();
        
        String strDocumentos = "";
        for (Documento documento : documentosAdjuntos) {
            strDocumentos += " " + documento.getTipoDocumento();
        }
        strDocumentos += ".";
        lblDocumentos.setText("Documentos: " + strDocumentos);

        documentosAdjuntos = solicitudSeleccionada.getRequisitos();

        panelDatosSolicitud.setVisible(true);

    }

    public void ventanaTramiteEnProgreso(SolicitudTramite solicitudSeleccionada) {

        // log.info("Seleccionada solicitud en progreso");

        this.solicitudSeleccionada = solicitudSeleccionada;

        radioBtnAceptar.setVisible(false);
        radioBtnRechazar.setVisible(false);
        btnDescargarDocumentos.setVisible(true);
        btnSiguiente.setVisible(false);

        lblNo.setText("Número de solicitud: "+String.valueOf(solicitudSeleccionada.getIdSolicitud()));
        lblFecha.setText("Fecha de registro: "+String.valueOf(solicitudSeleccionada.getFechaSolicitud()).substring(0,10));

        lblEstado.setText("Estado de la solicitud: "+solicitudSeleccionada.getEstado());
        lblTipo.setText("Trámite solicitado: "+solicitudSeleccionada.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: "+solicitudSeleccionada.getSolicitante().getNombre() + " " + solicitudSeleccionada.getSolicitante().getApellidos()+" ("+solicitudSeleccionada.getSolicitante().getClave()+")");
        
        documentosAdjuntos = solicitudSeleccionada.getRequisitos();
        
        String strDocumentos = "";
        for (Documento documento : documentosAdjuntos) {
            strDocumentos += " " + documento.getTipoDocumento();
        }
        strDocumentos += ".";
        lblDocumentos.setText("Documentos: " + strDocumentos);

        documentosAdjuntos = solicitudSeleccionada.getRequisitos();

        panelDatosSolicitud.setVisible(true);
        
    }

    public void ventanaTramiteRechazado(SolicitudTramite solicitudSeleccionada) {
        
        // log.info("Seleccionada solicitud rechazada");
        

    }

    public void guardarDocumentos(SolicitudTramite solicitudSeleccionada){
        
        for (Documento documento : documentosAdjuntos) {
            path = new File (FileSystemView.getFileSystemView().getHomeDirectory()+"\\DocsSisGTS\\Solicitud"+
            String.valueOf(solicitudSeleccionada.getIdSolicitud())+documento.getTipoDocumento()+".pdf");

            try (FileOutputStream out = new FileOutputStream(path)) {
                out.write(documento.getArchivo());
                out.close();

            } catch (Exception e_) {
                log.info("Error: "+e_.getMessage());
            }
        }

    }
    


}
