package mx.uam.ayd.proyecto.presentacion.procesarTramites;

import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;

import java.io.*;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;
import mx.uam.ayd.proyecto.presentacion.agregarUsuario.Pantalla;

@Slf4j
@Component
public class VentanaProcesarTramites extends Pantalla {

    private ControlProcesarTramites control;

    private JLabel lblNorth, lblNo, lblFecha, lblEstado, lblTipo, lblSolicitante, lblDocumentos, lblMotivoRechazo,
            lblArchivoAdjunto;
    private List<SolicitudTramite> solicitudes, solicitudesFinalizadas;
    private JList<String> listaSolicitudes;
    private JPanel panelProcesamientoSolicitudes, panelDatosSolicitud;
    private JScrollPane barraDespl;
    private JButton btnDescargarDocumentos, btnSiguiente, btnAlternarVista, btnConfirmarRechazo, btnAdjuntarDocTramite,
            btnFinalizarTramite;
    private JRadioButton radioBtnAceptar, radioBtnRechazar;
    private List<Documento> documentosAdjuntos;
    private Path pathDocTramiteFinalizado;
    private SolicitudTramite solicitudSeleccionada;
    private JComboBox<String> comboBoxMotivosRechazo;
    private JFileChooser chooser;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");

    public VentanaProcesarTramites() {
        setBounds(new Rectangle(100, 100, 500, 500));
        setLayout(new BorderLayout());

        lblNorth = new JLabel("Solicitudes de trámites activas");
        lblNorth.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNorth.setBorder(new EmptyBorder(10, 0, 10, 0));
        lblNorth.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNorth, BorderLayout.NORTH);

        btnAlternarVista = new JButton("Ver trámites finalizados");
        add(btnAlternarVista, BorderLayout.SOUTH);

        panelProcesamientoSolicitudes = new JPanel();
        panelProcesamientoSolicitudes.setLayout(new FlowLayout());
        panelProcesamientoSolicitudes.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(panelProcesamientoSolicitudes, BorderLayout.CENTER);

        panelDatosSolicitud = new JPanel();
        panelDatosSolicitud.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDatosSolicitud.setLayout(new GridLayout(10, 2, 10, 10));
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

        lblMotivoRechazo = new JLabel("Motivo de rechazo: ");
        lblMotivoRechazo.setFont(new Font("Arial", Font.BOLD, 15));
        String[] opcionesComboBox = { "Archivos corruptos", "Archivos ilegibles",
                "Documentos no coincidentes con los requerimientos del trámite" };
        comboBoxMotivosRechazo = new JComboBox<String>(opcionesComboBox);

        btnConfirmarRechazo = new JButton("Confirmar rechazo de solicitud");
        btnConfirmarRechazo.setFont(new Font("Arial", Font.PLAIN, 15));

        btnAdjuntarDocTramite = new JButton("Adjuntar documento de trámite");
        btnAdjuntarDocTramite.setFont(new Font("Arial", Font.PLAIN, 15));
        lblArchivoAdjunto = new JLabel();
        lblArchivoAdjunto.setFont(new Font("Arial", Font.ITALIC, 15));

        btnFinalizarTramite = new JButton("Finalizar trámite");
        btnFinalizarTramite.setFont(new Font("Arial", Font.PLAIN, 15));

        panelDatosSolicitud.add(lblNo);
        panelDatosSolicitud.add(lblFecha);

        panelDatosSolicitud.add(lblEstado);
        panelDatosSolicitud.add(lblTipo);

        panelDatosSolicitud.add(lblSolicitante);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        panelDatosSolicitud.add(lblDocumentos);
        panelDatosSolicitud.add(btnDescargarDocumentos);

        panelDatosSolicitud.add(radioBtnAceptar);
        panelDatosSolicitud.add(radioBtnRechazar);

        panelDatosSolicitud.add(btnSiguiente);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        panelDatosSolicitud.add(lblMotivoRechazo);
        panelDatosSolicitud.add(comboBoxMotivosRechazo);

        panelDatosSolicitud.add(btnConfirmarRechazo);
        panelDatosSolicitud.add(new JLabel("")); // lbl vacio

        panelDatosSolicitud.add(btnAdjuntarDocTramite);
        panelDatosSolicitud.add(lblArchivoAdjunto);

        panelDatosSolicitud.add(btnFinalizarTramite);

        panelDatosSolicitud.setVisible(false);

        listaSolicitudes = new JList<String>();
        barraDespl = new JScrollPane(listaSolicitudes);
        add(barraDespl, BorderLayout.WEST);

        listaSolicitudes.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                int index = listaSolicitudes.getSelectedIndex();
                SolicitudTramite solicitudSeleccionada;
                String vista = btnAlternarVista.getText();

                switch (vista) {
                    case "Ver trámites finalizados":
                        solicitudSeleccionada = solicitudes.get(index);
                        break;
                    default:
                        solicitudSeleccionada = solicitudesFinalizadas.get(index);
                        break;
                }

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

                    case "Finalizado":
                        control.tramiteFinalizado(solicitudSeleccionada);
                        break;

                    default:
                        panelDatosSolicitud.setVisible(false);
                        break;
                }

            }

        });

        btnDescargarDocumentos.addActionListener(e -> guardarDocumentos(solicitudSeleccionada));

        radioBtnAceptar.addActionListener(e -> radioBtnRechazar.setSelected(false));

        radioBtnRechazar.addActionListener(e -> radioBtnAceptar.setSelected(false));

        btnSiguiente.addActionListener(e -> control.procesarSolicitud(solicitudSeleccionada));

        btnConfirmarRechazo.addActionListener(e -> control.confirmarRechazo(solicitudSeleccionada));

        btnAdjuntarDocTramite.addActionListener(e -> adjuntarArchivo());

        btnFinalizarTramite.addActionListener(e -> finalizarTramite());

        btnAlternarVista.addActionListener(e -> control.alternarVista());

    }

    void muestra(List<SolicitudTramite> solicitudes_, List<SolicitudTramite> solicitudesFinalizadas_,
            ControlProcesarTramites control) {

        this.control = control;
        this.solicitudes = solicitudes_;
        this.solicitudesFinalizadas = solicitudesFinalizadas_;

        String[] datosListaSolicitudes = new String[solicitudes.size()];

        int i = 0;
        for (SolicitudTramite solicitud : solicitudes) {
            datosListaSolicitudes[i] = solicitud.toString();
            i++;
        }

        listaSolicitudes.setListData(datosListaSolicitudes);
        setVisible(true);

        if (solicitudes.size() == 0) {
            JOptionPane.showMessageDialog(this, "No hay solicitudes de trámites activas", "",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    void ventanaTramitePendiente(SolicitudTramite solicitudSeleccionada) {

        this.solicitudSeleccionada = solicitudSeleccionada;

        radioBtnAceptar.setVisible(true);
        radioBtnAceptar.setSelected(false);
        radioBtnRechazar.setVisible(true);
        radioBtnRechazar.setSelected(false);
        lblDocumentos.setVisible(true);
        btnDescargarDocumentos.setVisible(true);
        btnSiguiente.setVisible(true);
        lblMotivoRechazo.setVisible(false);
        comboBoxMotivosRechazo.setVisible(false);
        btnConfirmarRechazo.setVisible(false);
        btnAdjuntarDocTramite.setVisible(false);
        lblArchivoAdjunto.setVisible(false);
        btnFinalizarTramite.setVisible(false);

        lblNo.setText("Número de solicitud: " + String.valueOf(solicitudSeleccionada.getIdSolicitud()));
        lblFecha.setText(
                "Fecha de registro: " + String.valueOf(solicitudSeleccionada.getFechaSolicitud()).substring(0, 10));

        lblEstado.setText("Estado de la solicitud: " + solicitudSeleccionada.getEstado());
        lblTipo.setText("Trámite solicitado: " + solicitudSeleccionada.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: " + solicitudSeleccionada.getSolicitante().getNombre() + " "
                + solicitudSeleccionada.getSolicitante().getApellidos() + " ("
                + solicitudSeleccionada.getSolicitante().getClave() + ")");

        documentosAdjuntos = solicitudSeleccionada.getRequisitos();

        String strDocumentos = "";
        for (Documento documento : documentosAdjuntos) {
            strDocumentos += " " + documento.getTipoDocumento();
        }
        lblDocumentos.setText("Documentos: " + strDocumentos);

        documentosAdjuntos = solicitudSeleccionada.getRequisitos();
        panelDatosSolicitud.setVisible(true);

    }

    void ventanaTramiteEnProgreso(SolicitudTramite solicitudSeleccionada) {

        this.solicitudSeleccionada = solicitudSeleccionada;
        this.pathDocTramiteFinalizado = null;

        radioBtnAceptar.setVisible(false);
        radioBtnRechazar.setVisible(false);
        lblDocumentos.setVisible(true);
        btnDescargarDocumentos.setVisible(true);
        btnSiguiente.setVisible(false);
        lblMotivoRechazo.setVisible(false);
        comboBoxMotivosRechazo.setVisible(false);
        btnConfirmarRechazo.setVisible(false);
        btnAdjuntarDocTramite.setVisible(true);
        lblArchivoAdjunto.setVisible(true);
        lblArchivoAdjunto.setText("Ningún documento seleccionado");
        btnFinalizarTramite.setVisible(true);
        btnFinalizarTramite.setEnabled(false);

        lblNo.setText("Número de solicitud: " + String.valueOf(solicitudSeleccionada.getIdSolicitud()));
        lblFecha.setText(
                "Fecha de registro: " + String.valueOf(solicitudSeleccionada.getFechaSolicitud()).substring(0, 10));

        lblEstado.setText("Estado de la solicitud: " + solicitudSeleccionada.getEstado());
        lblTipo.setText("Trámite solicitado: " + solicitudSeleccionada.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: " + solicitudSeleccionada.getSolicitante().getNombre() + " "
                + solicitudSeleccionada.getSolicitante().getApellidos() + " ("
                + solicitudSeleccionada.getSolicitante().getClave() + ")");

        documentosAdjuntos = solicitudSeleccionada.getRequisitos();

        String strDocumentos = "";
        for (Documento documento : documentosAdjuntos) {
            strDocumentos += " " + documento.getTipoDocumento();
        }
        lblDocumentos.setText("Documentos: " + strDocumentos);

        documentosAdjuntos = solicitudSeleccionada.getRequisitos();

        panelDatosSolicitud.setVisible(true);

    }

    void ventanaTramiteRechazado(SolicitudTramite solicitudSeleccionada) {

        this.solicitudSeleccionada = solicitudSeleccionada;

        radioBtnAceptar.setVisible(false);
        radioBtnRechazar.setVisible(false);
        lblDocumentos.setVisible(false);
        btnDescargarDocumentos.setVisible(false);
        btnSiguiente.setVisible(false);
        lblMotivoRechazo.setVisible(true);
        comboBoxMotivosRechazo.setVisible(true);
        comboBoxMotivosRechazo.setSelectedItem(solicitudSeleccionada.getMotivoRechazo());
        comboBoxMotivosRechazo.setEnabled(false);
        btnConfirmarRechazo.setVisible(false);
        btnAdjuntarDocTramite.setVisible(false);
        lblArchivoAdjunto.setVisible(false);
        btnFinalizarTramite.setVisible(false);

        lblNo.setText("Número de solicitud: " + String.valueOf(solicitudSeleccionada.getIdSolicitud()));
        lblFecha.setText(
                "Fecha de registro: " + String.valueOf(solicitudSeleccionada.getFechaSolicitud()).substring(0, 10));

        lblEstado.setText("Estado de la solicitud: " + solicitudSeleccionada.getEstado());
        lblTipo.setText("Trámite solicitado: " + solicitudSeleccionada.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: " + solicitudSeleccionada.getSolicitante().getNombre() + " "
                + solicitudSeleccionada.getSolicitante().getApellidos() + " ("
                + solicitudSeleccionada.getSolicitante().getClave() + ")");

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

    void ventanaTramiteFinalizado(SolicitudTramite solicitudSeleccionada) {

        this.solicitudSeleccionada = solicitudSeleccionada;

        lblDocumentos.setVisible(false);
        btnDescargarDocumentos.setVisible(false);
        radioBtnAceptar.setVisible(false);
        radioBtnRechazar.setVisible(false);
        btnSiguiente.setVisible(false);
        lblMotivoRechazo.setVisible(false);
        comboBoxMotivosRechazo.setVisible(false);
        btnConfirmarRechazo.setVisible(false);
        btnAdjuntarDocTramite.setVisible(false);
        lblArchivoAdjunto.setVisible(false);
        btnFinalizarTramite.setVisible(false);

        lblNo.setText("Número de solicitud: " + String.valueOf(solicitudSeleccionada.getIdSolicitud()));
        lblFecha.setText("Fecha de finalización: "
                + String.valueOf(solicitudSeleccionada.getFechaFinalizacion()).substring(0, 16));

        lblEstado.setText("Estado de la solicitud: " + solicitudSeleccionada.getEstado());
        lblTipo.setText("Trámite solicitado: " + solicitudSeleccionada.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: " + solicitudSeleccionada.getSolicitante().getNombre() + " "
                + solicitudSeleccionada.getSolicitante().getApellidos() + " ("
                + solicitudSeleccionada.getSolicitante().getClave() + ")");

        panelDatosSolicitud.setVisible(true);

    }

    void guardarDocumentos(SolicitudTramite solicitudSeleccionada) {

        chooser = new JFileChooser();
        chooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        chooser.setDialogTitle("Seleccione una ruta");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            for (Documento documento : documentosAdjuntos) {
                File path = new File(chooser.getSelectedFile() + "\\Solicitud"
                        + String.valueOf(solicitudSeleccionada.getIdSolicitud()) + documento.getTipoDocumento()
                        + ".pdf");

                try (FileOutputStream out = new FileOutputStream(path)) {
                    out.write(documento.getArchivo());
                    out.close();

                } catch (Exception e_) {
                    log.info("Error: " + e_.getMessage());
                }

            }

        }

    }

    void procesarSolicitud(SolicitudTramite solicitudSeleccionada) {

        if (radioBtnAceptar.isSelected()) {

            int opcionSeleccionada = JOptionPane.showConfirmDialog(this,
                    "¿Esta seguro de aceptar los documentos y marcar la solicitud como \"En progreso\"?",
                    "Confirmar selección", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcionSeleccionada == 0) {

                int index = solicitudes.indexOf(solicitudSeleccionada);
                solicitudes.remove(solicitudSeleccionada);
                SolicitudTramite solicitudActualizada = control.aceptarDocumentos(solicitudSeleccionada);
                actualizarLista(index, solicitudActualizada);

            }

        } else if (radioBtnRechazar.isSelected()) {

            int opcionSeleccionada = JOptionPane.showConfirmDialog(this,
                    "¿Esta seguro de rechazar los documentos y marcar la solicitud como \"Rechazada\"?",
                    "Confirmar selección", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (opcionSeleccionada == 0) {

                btnDescargarDocumentos.setVisible(false);
                btnSiguiente.setVisible(false);
                radioBtnAceptar.setVisible(false);
                radioBtnRechazar.setVisible(false);
                lblMotivoRechazo.setVisible(true);
                comboBoxMotivosRechazo.setSelectedIndex(0);
                comboBoxMotivosRechazo.setVisible(true);
                comboBoxMotivosRechazo.setEnabled(true);
                btnConfirmarRechazo.setVisible(true);

            }

        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleccione 'Aceptar documentos' o 'Rechazar documentos' antes de proceder", "Operación no válida",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    void confirmarRechazo(SolicitudTramite solicitudSeleccionada) {

        String motivoRechazo = comboBoxMotivosRechazo.getSelectedItem().toString();
        int index = solicitudes.indexOf(solicitudSeleccionada);
        solicitudes.remove(solicitudSeleccionada);
        SolicitudTramite solicitudActualizada = control.rechazarDocumentos(solicitudSeleccionada, motivoRechazo);
        actualizarLista(index, solicitudActualizada);

    }

    void actualizarLista(int index, SolicitudTramite solicitudActualizada) {

        if (index == -1) {

            String[] datosListaSolicitudes = new String[solicitudes.size()];
            int i = 0;
            for (SolicitudTramite solicitud : solicitudes) {
                datosListaSolicitudes[i++] = solicitud.toString();
            }

            try {
                listaSolicitudes.setListData(datosListaSolicitudes);
            } catch (IndexOutOfBoundsException e) {
                control.tramiteFinalizado(solicitudActualizada);
            }

            this.solicitudesFinalizadas.add(solicitudActualizada);

        } else {

            this.solicitudes.add(index, solicitudActualizada);
            String[] datosListaSolicitudes = new String[solicitudes.size()];
            int i = 0;
            for (SolicitudTramite solicitud : solicitudes) {
                datosListaSolicitudes[i++] = solicitud.toString();
            }

            try {
                listaSolicitudes.setListData(datosListaSolicitudes);
            } catch (IndexOutOfBoundsException e) {
                listaSolicitudes.setSelectedIndex(index);
                this.solicitudSeleccionada = solicitudActualizada;
            }
        }

    }

    void adjuntarArchivo() {

        chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            lblArchivoAdjunto.setText(chooser.getSelectedFile().getName());
            this.pathDocTramiteFinalizado = chooser.getSelectedFile().toPath();
            btnFinalizarTramite.setEnabled(true);

        }

    }

    void finalizarTramite() {

        int opcionSeleccionada = JOptionPane.showConfirmDialog(this,
                    "¿Esta seguro de adjuntar el documento y marcar el trámite como \"Finalizado\"?",
                    "Confirmar selección", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (opcionSeleccionada == 0) {
            solicitudes.remove(solicitudSeleccionada);
            SolicitudTramite solicitudActualizada = control.finalizarTramite(solicitudSeleccionada,
                    pathDocTramiteFinalizado);
            actualizarLista(-1, solicitudActualizada);
        }

        

    }

    void alternarVista() {

        if (btnAlternarVista.getText() == "Ver trámites finalizados") {

            panelDatosSolicitud.setVisible(false);
            btnAlternarVista.setText("Ver solicitudes de trámites activas");
            String[] datosListaSolicitudesFinalizadas = new String[solicitudesFinalizadas.size()];
            int i = 0;
            for (SolicitudTramite solicitudFinalizada : solicitudesFinalizadas) {
                datosListaSolicitudesFinalizadas[i++] = solicitudFinalizada.toString();
            }

            try {
                listaSolicitudes.setListData(datosListaSolicitudesFinalizadas);
                if (solicitudesFinalizadas.size() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay registro de trámites finalizados", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IndexOutOfBoundsException e) {
                if (solicitudesFinalizadas.size() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay registro de trámites finalizados", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else {

            panelDatosSolicitud.setVisible(false);
            btnAlternarVista.setText("Ver trámites finalizados");
            String[] datosListaSolicitudes = new String[solicitudes.size()];
            int i = 0;
            for (SolicitudTramite solicitud : solicitudes) {
                datosListaSolicitudes[i++] = solicitud.toString();
            }

            try {
                listaSolicitudes.setListData(datosListaSolicitudes);
                if (solicitudes.size() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay solicitudes de trámites activas", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IndexOutOfBoundsException e) {
                if (solicitudes.size() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay solicitudes de trámites activas", "",
                            JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }

    }

}
