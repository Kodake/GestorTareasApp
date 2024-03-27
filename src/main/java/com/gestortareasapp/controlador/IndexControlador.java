package com.gestortareasapp.controlador;

import com.gestortareasapp.modelo.Tarea;
import com.gestortareasapp.servicio.ITareaServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexControlador implements Initializable {
    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private ITareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareasTabla;

    @FXML
    private TableColumn<Tarea, Integer> idTareaCol;

    @FXML
    private TableColumn<Tarea, String> nombreCol;

    @FXML
    private TableColumn<Tarea, String> responsableCol;

    @FXML
    private TableColumn<Tarea, String> estatusCol;

    private final ObservableList<Tarea> tareasList =
            FXCollections.observableArrayList();

    @FXML
    private TextField nombreTexto;

    @FXML
    private TextField responsableTexto;

    @FXML
    private TextField estatusTexto;

    private Integer idTarea;

    @FXML
    private Button agregarButton;

    @FXML
    private Button modificarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    private Button limpiarButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modificarButton.setVisible(false);
        tareasTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarTareas();
    }

    private void configurarColumnas() {
        idTareaCol.setCellValueFactory(new PropertyValueFactory<>("idTarea"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        responsableCol.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estatusCol.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    private void listarTareas() {
        logger.info("Ejecutando listado de Tareas...");
        tareasList.clear();
        tareasList.addAll(tareaServicio.listar());
        tareasTabla.setItems(tareasList);
    }

    public void agregarTarea() {
        if (nombreCol.getText().isEmpty()) {
            mostrarMensaje("Error Validación", "Debe proporcionar el Nombre de la tarea.");
            nombreTexto.requestFocus();
            return;
        } else {
            var tarea = new Tarea();
            recolectarDatos(tarea);
            tarea.setIdTarea(null);
            tareaServicio.guardar(tarea);
            mostrarMensaje("Información", "Tarea guardada satisfactoriamente.");
            limpiarFormulario();
            listarTareas();
        }
    }

    public void modificarTarea() {
        if (idTarea == null) {
            mostrarMensaje("Informaciön", "Debe seleccionar una tarea.");
            return;
        }
        if (nombreCol.getText().isEmpty()) {
            mostrarMensaje("Error Validación", "Debe proporcionar el Nombre de la tarea.");
            nombreTexto.requestFocus();
            return;
        }

        var tarea = new Tarea();
        recolectarDatos(tarea);
        tareaServicio.guardar(tarea);
        mostrarMensaje("Información", "Tarea actualizada satisfactoriamente.");
        limpiarFormulario();
        listarTareas();
    }

    public void eliminarTarea() {
        var tarea = tareasTabla.getSelectionModel().getSelectedItem();
        if (tarea != null) {
            logger.info("Registro a eliminar: " + tarea.toString());
            tareaServicio.eliminar(tarea);
            mostrarMensaje("Información", "Tarea eliminada satisfactoriamente.");
            limpiarFormulario();
            listarTareas();
        } else {
            mostrarMensaje("Error", "No se ha seleccionado ninguna tarea.");
        }
    }

    public void limpiarFormulario() {
        idTarea = null;
        nombreTexto.clear();
        responsableTexto.clear();
        estatusTexto.clear();
        agregarButton.setVisible(true);
        modificarButton.setVisible(false);
    }

    public void cargarTarea() {
        agregarButton.setVisible(false);
        modificarButton.setVisible(true);
        var tarea = tareasTabla.getSelectionModel().getSelectedItem();
        if (tarea != null) {
            idTarea = tarea.getIdTarea();
            nombreTexto.setText(tarea.getNombre());
            responsableTexto.setText(tarea.getResponsable());
            estatusTexto.setText(tarea.getEstatus());
        }
    }

    private void recolectarDatos(Tarea tarea) {
        if (idTarea != null) {
            tarea.setIdTarea(idTarea);
        }
        tarea.setNombre(nombreTexto.getText());
        tarea.setResponsable(responsableTexto.getText());
        tarea.setEstatus(estatusTexto.getText());
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
