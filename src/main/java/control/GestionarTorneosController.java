/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import avisos.dialogo;
import crudDerby.sqlGestionTorneo;
import datos.temporada;
import java.net.URL;
import java.sql.*;
import java.util.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class GestionarTorneosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label lblNombreTorneo;
    @FXML
    private Label lblIngreseEdad;
    @FXML
    private TextField tfNombreTorneo;
    @FXML
    private TextField tfDatosExtras;
    @FXML
    private TextField txtEdad;
    @FXML
    private TableView<temporada> tabla;
    @FXML
    private TableColumn <temporada,String> torneo;
    @FXML
    private TableColumn <temporada,String> extra;
    @FXML
    private TableColumn<temporada,Integer> edad;
    private ObservableList<temporada> torneosDisponibles;
    temporada t = new temporada();
    dialogo mensaje = new dialogo();
    
    sqlGestionTorneo carga = new sqlGestionTorneo();
    
    @FXML
    private void mostrarRegistro (Connection con){
        ArrayList<temporada>  j = carga.leerRegistro(con);

        torneosDisponibles = FXCollections.observableArrayList(j);
        
        torneo.setCellValueFactory(new PropertyValueFactory<>("nombreTemporada"));
        extra.setCellValueFactory(new PropertyValueFactory<>("datosExtras"));
        edad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        tabla.setItems(torneosDisponibles);   
    }
    
    @FXML
    private void eventoConsultaTorneos () {
        mostrarRegistro(cFondo.getObjConnection());
    }
    
    @FXML
    private void eventoBoton () throws SQLException {
        if(verificarCamposIngresados()){
           juntarDatos();
           limpiarComponentes();
           carga.cargarTorne(cFondo.getObjConnection(), t);
           mostrarRegistro(cFondo.getObjConnection());
           mensaje.msjCargaCategoria(Alert.AlertType.INFORMATION,dialogo.CONFIRMACION, dialogo.CARGA_EQUIPO, dialogo.AGREGADO_OK);
        }else{
            mensaje.msjCargaCategoria(Alert.AlertType.ERROR,dialogo.ALERTA, dialogo.CARGA_CATEGORIA, dialogo.VERIFICAR_CAMPOS_INGRESADOS);
            cambiarEstiloNormal(lblNombreTorneo);
            cambiarEstiloNormal(lblIngreseEdad);
        } 
    }
    
    private void juntarDatos () {
        t.setNombreTemporada(tfNombreTorneo.getText());
        t.setEdad(Integer.valueOf(txtEdad.getText()));
        t.setDatosExtras(tfDatosExtras.getText());  
    }
    
    private void limpiarComponentes (){
        tfNombreTorneo.clear();
        tfDatosExtras.clear();
        txtEdad.clear();
    }
    
    boolean verificarCamposIngresados(){
        boolean estado = true;
        if(tfNombreTorneo.getText().isEmpty()) {
            cambioEstiloRojo(lblNombreTorneo);
            estado=false;
        }
        if(txtEdad.getText().isEmpty()){
            cambioEstiloRojo(lblIngreseEdad);
            estado=false;
        }
        return estado;
    }
    
    void cambiarEstiloNormal(Label componenete){
        componenete.getStyleClass().remove("estiloRojo");
    }
    
    void cambioEstiloRojo (Label componente){
        componente.getStyleClass().add("estiloRojo");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
