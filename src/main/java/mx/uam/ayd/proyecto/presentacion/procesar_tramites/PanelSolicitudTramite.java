package mx.uam.ayd.proyecto.presentacion.procesar_tramites;

import lombok.Getter;
import mx.uam.ayd.proyecto.negocio.modelo.Documento;
import mx.uam.ayd.proyecto.negocio.modelo.SolicitudTramite;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelSolicitudTramite extends JPanel {


    private final JLabel lblNo;
    private final JLabel lblFecha;
    private final JLabel lblEstado;
    private final JLabel lblTipo;
    private final JLabel lblSolicitante;
    private final JLabel lblDocumentos;
    private final JLabel lblMotivoRechazo;
    @Getter
    private final JLabel lblArchivoAdjunto;

    @Getter
    private final JButton btnDescargarDocumentos;
    @Getter
    private final JButton btnSiguiente;
    @Getter
    private final JButton btnConfirmarRechazo;
    @Getter
    private final JButton btnAdjuntarDocTramite;
    @Getter
    private final JButton btnFinalizarTramite;
    @Getter
    private final JRadioButton radioBtnAceptar;
    @Getter
    private final JRadioButton radioBtnRechazar;
    @Getter
    private final transient JComboBox<String> comboBoxMotivosRechazo;


    public PanelSolicitudTramite() {

        setBorder(new EmptyBorder(10, 10, 10, 10));

        var font = new Font("Arial", Font.PLAIN, 15);
        String[] opcionesComboBox = {"Archivos corruptos", "Archivos ilegibles",
                "Documentos no coincidentes con los requerimientos del trámite"};
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 360, 360, 0, 0};
        gridBagLayout.rowHeights = new int[]{0, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 0, 0};
        gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        setLayout(gridBagLayout);

        lblNo = new JLabel();
        lblNo.setFont(font);


        GridBagConstraints gbcLblNo = new GridBagConstraints();
        gbcLblNo.anchor = GridBagConstraints.WEST;
        gbcLblNo.fill = GridBagConstraints.VERTICAL;
        gbcLblNo.insets = new Insets(0, 0, 5, 5);
        gbcLblNo.gridx = 1;
        gbcLblNo.gridy = 1;
        add(lblNo, gbcLblNo);
        lblFecha = new JLabel();
        lblFecha.setFont(font);
        GridBagConstraints gbcLblFecha = new GridBagConstraints();
        gbcLblFecha.anchor = GridBagConstraints.WEST;
        gbcLblFecha.fill = GridBagConstraints.VERTICAL;
        gbcLblFecha.insets = new Insets(0, 0, 5, 5);
        gbcLblFecha.gridx = 2;
        gbcLblFecha.gridy = 1;
        add(lblFecha, gbcLblFecha);

        lblEstado = new JLabel();
        lblEstado.setFont(font);

        GridBagConstraints gbcLblEstado = new GridBagConstraints();
        gbcLblEstado.anchor = GridBagConstraints.WEST;
        gbcLblEstado.fill = GridBagConstraints.VERTICAL;
        gbcLblEstado.insets = new Insets(0, 0, 5, 5);
        gbcLblEstado.gridx = 1;
        gbcLblEstado.gridy = 2;
        add(lblEstado, gbcLblEstado);
        lblTipo = new JLabel();
        lblTipo.setFont(font);
        GridBagConstraints gbcLblTipo = new GridBagConstraints();
        gbcLblTipo.anchor = GridBagConstraints.WEST;
        gbcLblTipo.fill = GridBagConstraints.VERTICAL;
        gbcLblTipo.insets = new Insets(0, 0, 5, 5);
        gbcLblTipo.gridx = 2;
        gbcLblTipo.gridy = 2;
        add(lblTipo, gbcLblTipo);

        lblSolicitante = new JLabel();
        lblSolicitante.setFont(font);

        GridBagConstraints gbcLblSolicitante = new GridBagConstraints();
        gbcLblSolicitante.anchor = GridBagConstraints.WEST;
        gbcLblSolicitante.fill = GridBagConstraints.VERTICAL;
        gbcLblSolicitante.insets = new Insets(0, 0, 5, 5);
        gbcLblSolicitante.gridx = 1;
        gbcLblSolicitante.gridy = 3;
        add(lblSolicitante, gbcLblSolicitante);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = 2;
        gbc.gridy = 3;
        JLabel label1 = new JLabel("");
        add(label1, gbc); // lbl vacio

        lblDocumentos = new JLabel();
        lblDocumentos.setFont(font);

        GridBagConstraints gbcLblDocumentos = new GridBagConstraints();
        gbcLblDocumentos.anchor = GridBagConstraints.WEST;
        gbcLblDocumentos.fill = GridBagConstraints.VERTICAL;
        gbcLblDocumentos.insets = new Insets(0, 0, 5, 5);
        gbcLblDocumentos.gridx = 1;
        gbcLblDocumentos.gridy = 4;
        add(lblDocumentos, gbcLblDocumentos);

        btnDescargarDocumentos = new JButton("Descargar documentos");
        btnDescargarDocumentos.setFont(font);
        GridBagConstraints gbcBtnDescargarDocumentos = new GridBagConstraints();
        gbcBtnDescargarDocumentos.fill = GridBagConstraints.BOTH;
        gbcBtnDescargarDocumentos.insets = new Insets(0, 0, 5, 5);
        gbcBtnDescargarDocumentos.gridx = 2;
        gbcBtnDescargarDocumentos.gridy = 4;
        add(btnDescargarDocumentos, gbcBtnDescargarDocumentos);

        radioBtnAceptar = new JRadioButton("Aceptar documentos");
        radioBtnAceptar.setFont(font);

        GridBagConstraints gbcRadioBtnAceptar = new GridBagConstraints();
        gbcRadioBtnAceptar.fill = GridBagConstraints.BOTH;
        gbcRadioBtnAceptar.insets = new Insets(0, 0, 5, 5);
        gbcRadioBtnAceptar.gridx = 1;
        gbcRadioBtnAceptar.gridy = 5;
        add(radioBtnAceptar, gbcRadioBtnAceptar);
        radioBtnRechazar = new JRadioButton("Rechazar documentos");
        radioBtnRechazar.setFont(font);
        GridBagConstraints gbcRadioBtnRechazar = new GridBagConstraints();
        gbcRadioBtnRechazar.fill = GridBagConstraints.BOTH;
        gbcRadioBtnRechazar.insets = new Insets(0, 0, 5, 5);
        gbcRadioBtnRechazar.gridx = 2;
        gbcRadioBtnRechazar.gridy = 5;
        add(radioBtnRechazar, gbcRadioBtnRechazar);

        btnSiguiente = new JButton("Siguiente");
        btnSiguiente.setFont(font);

        GridBagConstraints gbcBtnSiguiente = new GridBagConstraints();
        gbcBtnSiguiente.fill = GridBagConstraints.BOTH;
        gbcBtnSiguiente.insets = new Insets(0, 0, 5, 5);
        gbcBtnSiguiente.gridx = 1;
        gbcBtnSiguiente.gridy = 6;
        add(btnSiguiente, gbcBtnSiguiente);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.anchor = GridBagConstraints.WEST;
        gbc1.fill = GridBagConstraints.VERTICAL;
        gbc1.insets = new Insets(0, 0, 5, 5);
        gbc1.gridx = 2;
        gbc1.gridy = 6;
        JLabel label2 = new JLabel("");
        add(label2, gbc1); // lbl vacio

        lblMotivoRechazo = new JLabel("Motivo de rechazo: ");
        lblMotivoRechazo.setFont(font);

        GridBagConstraints gbcLblMotivoRechazo = new GridBagConstraints();
        gbcLblMotivoRechazo.fill = GridBagConstraints.BOTH;
        gbcLblMotivoRechazo.insets = new Insets(0, 0, 5, 5);
        gbcLblMotivoRechazo.gridx = 1;
        gbcLblMotivoRechazo.gridy = 7;
        add(lblMotivoRechazo, gbcLblMotivoRechazo);
        comboBoxMotivosRechazo = new JComboBox<>(opcionesComboBox);
        GridBagConstraints gbcComboBoxMotivosRechazo = new GridBagConstraints();
        gbcComboBoxMotivosRechazo.fill = GridBagConstraints.BOTH;
        gbcComboBoxMotivosRechazo.insets = new Insets(0, 0, 5, 5);
        gbcComboBoxMotivosRechazo.gridx = 2;
        gbcComboBoxMotivosRechazo.gridy = 7;
        add(comboBoxMotivosRechazo, gbcComboBoxMotivosRechazo);

        btnConfirmarRechazo = new JButton("Confirmar rechazo de solicitud");
        btnConfirmarRechazo.setFont(font);

        GridBagConstraints gbcBtnConfirmarRechazo = new GridBagConstraints();
        gbcBtnConfirmarRechazo.fill = GridBagConstraints.BOTH;
        gbcBtnConfirmarRechazo.insets = new Insets(0, 0, 5, 5);
        gbcBtnConfirmarRechazo.gridx = 1;
        gbcBtnConfirmarRechazo.gridy = 8;
        add(btnConfirmarRechazo, gbcBtnConfirmarRechazo);
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.fill = GridBagConstraints.VERTICAL;
        gbc2.insets = new Insets(0, 0, 5, 5);
        gbc2.gridx = 2;
        gbc2.gridy = 8;
        JLabel label = new JLabel("");
        add(label, gbc2); // lbl vacio

        btnAdjuntarDocTramite = new JButton("Adjuntar documento de trámite");
        btnAdjuntarDocTramite.setFont(font);

        GridBagConstraints gbcBtnAdjuntarDocTramite = new GridBagConstraints();
        gbcBtnAdjuntarDocTramite.fill = GridBagConstraints.BOTH;
        gbcBtnAdjuntarDocTramite.insets = new Insets(0, 0, 5, 5);
        gbcBtnAdjuntarDocTramite.gridx = 1;
        gbcBtnAdjuntarDocTramite.gridy = 9;
        add(btnAdjuntarDocTramite, gbcBtnAdjuntarDocTramite);
        lblArchivoAdjunto = new JLabel();
        lblArchivoAdjunto.setFont(font);
        GridBagConstraints gbcLblArchivoAdjunto = new GridBagConstraints();
        gbcLblArchivoAdjunto.anchor = GridBagConstraints.WEST;
        gbcLblArchivoAdjunto.fill = GridBagConstraints.VERTICAL;
        gbcLblArchivoAdjunto.insets = new Insets(0, 0, 5, 5);
        gbcLblArchivoAdjunto.gridx = 2;
        gbcLblArchivoAdjunto.gridy = 9;
        add(lblArchivoAdjunto, gbcLblArchivoAdjunto);

        btnFinalizarTramite = new JButton("Finalizar trámite");
        btnFinalizarTramite.setFont(font);

        GridBagConstraints gbcBtnFinalizarTramite = new GridBagConstraints();
        gbcBtnFinalizarTramite.fill = GridBagConstraints.BOTH;
        gbcBtnFinalizarTramite.insets = new Insets(0, 0, 5, 5);
        gbcBtnFinalizarTramite.gridx = 1;
        gbcBtnFinalizarTramite.gridy = 10;
        add(btnFinalizarTramite, gbcBtnFinalizarTramite);

        radioBtnAceptar.addActionListener(e -> {
            radioBtnRechazar.setSelected(false);
            comboBoxMotivosRechazo.setVisible(false);
        });

        radioBtnRechazar.addActionListener(e -> {
            radioBtnAceptar.setSelected(false);
            comboBoxMotivosRechazo.setVisible(true);
            comboBoxMotivosRechazo.setEnabled(true);
        });

        setVisible(false);
    }

    public void muestraSolicitudTramite(SolicitudTramite solicitudTramite) {

        switch (solicitudTramite.getEstado()){
            case PENDIENTE:
                muestraTramitePendiente();
                break;
            case EN_PROGRESO:
                muestraTramiteEnProgreso();
                break;
            case RECHAZADO:
                muestraTramiteRechazado(solicitudTramite);
                break;
            case FINALIZADO:
                muestraTramiteFinalizado();
        }

        lblNo.setText("Número de solicitud: " + solicitudTramite.getIdSolicitud());
        lblFecha.setText(
                "Fecha de registro: " + String.valueOf(solicitudTramite.getFechaSolicitud()).substring(0, 10));

        lblEstado.setText("Estado de la solicitud: " + solicitudTramite.getEstado());
        lblTipo.setText("Trámite solicitado: " + solicitudTramite.getTipoTramite().getNombreTramite());

        lblSolicitante.setText("Solicitante: " + solicitudTramite.getSolicitante().getNombre() + " "
                + solicitudTramite.getSolicitante().getApellidos() + " ("
                + solicitudTramite.getSolicitante().getClave() + ")");

        List<Documento> documentosAdjuntos = new ArrayList<>(solicitudTramite.getRequisitos());

        StringBuilder strDocumentos = new StringBuilder();
        for (Documento documento : documentosAdjuntos) {
            strDocumentos.append(" ").append(documento.getTipoDocumento());
        }
        lblDocumentos.setText("Documentos: " + strDocumentos);

        setVisible(true);
    }

    private void muestraTramitePendiente(){
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
    }

    private void muestraTramiteEnProgreso(){
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
    }

    private void muestraTramiteRechazado(SolicitudTramite solicitudTramite){
        radioBtnAceptar.setVisible(false);
        radioBtnRechazar.setVisible(false);
        lblDocumentos.setVisible(false);
        btnDescargarDocumentos.setVisible(false);
        btnSiguiente.setVisible(false);
        lblMotivoRechazo.setVisible(true);
        comboBoxMotivosRechazo.setVisible(true);
        comboBoxMotivosRechazo.setSelectedItem(solicitudTramite.getMotivoRechazo());
        comboBoxMotivosRechazo.setEnabled(false);
        btnConfirmarRechazo.setVisible(false);
        btnAdjuntarDocTramite.setVisible(false);
        lblArchivoAdjunto.setVisible(false);
        btnFinalizarTramite.setVisible(false);
    }

    private void muestraTramiteFinalizado(){
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
    }

}
