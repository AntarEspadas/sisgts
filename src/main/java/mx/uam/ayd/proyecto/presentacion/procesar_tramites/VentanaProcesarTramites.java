package mx.uam.ayd.proyecto.presentacion.procesar_tramites;

import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.*;

import java.io.*;
import java.util.Vector;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.modelo.Estado;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;
import mx.uam.ayd.proyecto.presentacion.compartido.Pantalla;

@Slf4j
@Component
public class VentanaProcesarTramites extends Pantalla {

    private transient ControlProcesarTramites control;

    private transient List<SolicitudTramite> solicitudesActivas;
    private transient List<SolicitudTramite> solicitudesFinalizadas;
    private final JList<SolicitudTramite> listaSolicitudes;
    private final PanelSolicitudTramite panelDatosSolicitud;
    private final JButton btnAlternarVista;
    private final transient JFileChooser fileChooser;
    private final transient JFileChooser folderChooser;
    private File documentoAdjunto;

    private Vista vista = Vista.TRAMITES_ACTIVOS;

    public VentanaProcesarTramites() {
        setBounds(new Rectangle(100, 100, 500, 500));
        setLayout(new BorderLayout());

        var font = new Font("Arial", Font.PLAIN, 15);

        JLabel lblNorth = new JLabel("Solicitudes de trámites activas");
        lblNorth.setFont(font);
        lblNorth.setBorder(new EmptyBorder(10, 0, 10, 0));
        lblNorth.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblNorth, BorderLayout.NORTH);

        btnAlternarVista = new JButton("Ver trámites finalizados");
        add(btnAlternarVista, BorderLayout.SOUTH);

        JPanel panelProcesamientoSolicitudes = new JPanel();
        panelProcesamientoSolicitudes.setLayout(new FlowLayout());
        panelProcesamientoSolicitudes.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(panelProcesamientoSolicitudes, BorderLayout.CENTER);

        panelDatosSolicitud = new PanelSolicitudTramite();
        panelDatosSolicitud.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDatosSolicitud.setLayout(new GridLayout(10, 2, 10, 10));
        panelProcesamientoSolicitudes.add(panelDatosSolicitud);

        listaSolicitudes = new JList<>();
        JScrollPane barraDespl = new JScrollPane(listaSolicitudes);
        add(barraDespl, BorderLayout.WEST);

        listaSolicitudes.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;

            var solicitudTramite = listaSolicitudes.getSelectedValue();

            if (solicitudTramite == null) return;

            panelDatosSolicitud.muestraSolicitudTramite(solicitudTramite);
        });

        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        fileChooser.setFileFilter(filter);

        folderChooser = new JFileChooser();
        folderChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
        folderChooser.setDialogTitle("Seleccione una ruta");
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderChooser.setAcceptAllFileFilterUsed(false);

        panelDatosSolicitud.getBtnDescargarDocumentos().addActionListener(e -> guardarDocumentos(listaSolicitudes.getSelectedValue()));

        panelDatosSolicitud.getBtnSiguiente().addActionListener(this::botonSiguienteClick);

        panelDatosSolicitud.getBtnAdjuntarDocTramite().addActionListener(e -> adjuntarArchivo());

        panelDatosSolicitud.getBtnFinalizarTramite().addActionListener(e -> {
            var solicitud = listaSolicitudes.getSelectedValue();
            var rutaDocumento = documentoAdjunto.toPath();
            control.finalizarTramite(solicitud, rutaDocumento);
        });

        btnAlternarVista.addActionListener(e -> control.alternarVista());

    }

    /**
     * Muestra la vista correspondiente al procesamiento de trámites
     *
     * @param solicitudes            lista de solicitudes de trámites pendientes
     * @param solicitudesFinalizadas lista de trámites finalizados
     * @param control                apuntador a ControlProcesarTramites
     */
    void muestra(List<SolicitudTramite> solicitudes, List<SolicitudTramite> solicitudesFinalizadas, ControlProcesarTramites control) {

        this.control = control;
        this.solicitudesActivas = solicitudes;
        this.solicitudesFinalizadas = solicitudesFinalizadas;

        panelDatosSolicitud.setVisible(false);

        listaSolicitudes.setListData(new Vector<>(solicitudes));

        setVisible(true);

        if (this.solicitudesActivas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay solicitudes de trámites activas", "", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void botonSiguienteClick(ActionEvent e) {
        var solicitud = listaSolicitudes.getSelectedValue();
        if (panelDatosSolicitud.getRadioBtnAceptar().isSelected()) control.aceptarDocumentos(solicitud);
        else if (panelDatosSolicitud.getRadioBtnRechazar().isSelected())
            control.rechazarDocumentos(solicitud, (String) panelDatosSolicitud.getComboBoxMotivosRechazo().getSelectedItem());
        else {
            JOptionPane.showMessageDialog(this, "Seleccione 'Aceptar documentos' o 'Rechazar documentos' antes de proceder", "Operación no válida", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void actualizarTramiteEnLista(SolicitudTramite viejoValor, SolicitudTramite nuevoValor) {
        var indice = solicitudesActivas.indexOf(viejoValor);

        solicitudesActivas.remove(indice);
        listaSolicitudes.clearSelection();

        if (nuevoValor.getEstado() == Estado.FINALIZADO) {
            solicitudesFinalizadas.add(nuevoValor);
            panelDatosSolicitud.setVisible(false);
            listaSolicitudes.setListData(new Vector<>(solicitudesActivas));
        } else {
            solicitudesActivas.add(indice, nuevoValor);
            listaSolicitudes.setListData(new Vector<>(solicitudesActivas));
            listaSolicitudes.setSelectedIndex(indice);
        }
    }

    /**
     * Abre una ventana que permite al usuario elegir la carpeta en la que desea
     * guardar los documentos adjuntos a una solicitud de trámites
     *
     * @param solicitudSeleccionada solicitud seleccionada de la lista por el
     *                              usuario
     */
    void guardarDocumentos(SolicitudTramite solicitudSeleccionada) {

        if (folderChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
            return;

        var directorio = folderChooser.getSelectedFile().toString();

        var documentosAdjuntos = solicitudSeleccionada.getRequisitos();

        for (Documento documento : documentosAdjuntos) {
            var nombreArchivo = "solicitud_" + solicitudSeleccionada.getIdSolicitud() + "_" + documento.getIdDocumento() + ".pdf";
            var archivo = Path.of(directorio, nombreArchivo).toFile();

            try (FileOutputStream out = new FileOutputStream(archivo)) {
                out.write(documento.getArchivo());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error, selecciona una nueva ruta", "Ha ocurrido un error", JOptionPane.ERROR_MESSAGE);
                break;
            }

        }

    }

    public boolean confirmarCambioEstado(Estado estado) {

        return JOptionPane.showConfirmDialog(this,

                "¿Esta seguro de querer marcar la solicitud como \"" + estado.dislay() + "\"?", "Confirmar selección", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0;
    }

    /**
     * Abre una ventana para elegir el archivo pdf que debe adjuntarse a un trámite
     * va a darse por finalizado
     */
    void adjuntarArchivo() {

        if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;

        panelDatosSolicitud.getLblArchivoAdjunto().setText(fileChooser.getSelectedFile().getName());
        documentoAdjunto = fileChooser.getSelectedFile();
        panelDatosSolicitud.getBtnFinalizarTramite().setEnabled(true);

    }

    /**
     * Muestra un diálogo que indica que se produjo un error al seleccionar el documento
     */
    public void muestraDialogoErrorDocumento() {
        var mensaje = "Ha ocurrido un error, seleccione nuevamente el documento";
        JOptionPane.showMessageDialog(this, mensaje, "Error al adjuntar documento", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Alterna las solicitudes desplegadas, entre mostrar las solicitudes no
     * finalizadas a las solicitudes finalizadas o viceversa.
     */
    void alternarVista() {

        List<SolicitudTramite> solicitudesAMostrar;

        var mensajeErrorBase = "No hay solicitudes de trámite ";
        String mensajeError;

        if (vista == Vista.TRAMITES_ACTIVOS) {
            vista = Vista.TRAMITES_FINALIZADOS;
            btnAlternarVista.setText("Ver solicitudes de trámite activas");
            solicitudesAMostrar = solicitudesFinalizadas;

            mensajeError = mensajeErrorBase + "finalizadas";
        } else {
            vista = Vista.TRAMITES_ACTIVOS;
            btnAlternarVista.setText("Ver trámites finalizados");
            solicitudesAMostrar = solicitudesActivas;

            mensajeError = mensajeErrorBase + "activas";
        }

        listaSolicitudes.setListData(new Vector<>(solicitudesAMostrar));

        if (solicitudesAMostrar.isEmpty()) {
            panelDatosSolicitud.setVisible(false);

            JOptionPane.showMessageDialog(this, mensajeError, "", JOptionPane.INFORMATION_MESSAGE);
        } else {
            listaSolicitudes.setSelectedIndex(0);
        }

    }

}

enum Vista {
    TRAMITES_ACTIVOS, TRAMITES_FINALIZADOS
}