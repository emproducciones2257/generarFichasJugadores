/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import crudDerby.*;
import datos.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import avisos.dialogo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class CargaEquipoController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private ComboBox<String> comboCiudad;
    @FXML
    private Label lblNombreEquipo,lblLocalidad,lblTelefono,lblNombreDele,lblDniDele,lblFechaDele;
    
    
    @FXML
    public TextField txNomEquipo,txTelefono,txtnomDelegado,txtDNIDelegado,txtJugNom1,txtJugNom2,
            txtJugNom3,txtJugNom4,txtJugNom5,txtJugNom6,txtJugNom7,txtJugNom8,txtJugNom9,txtJugNom10,
            txtJugNom11,txtJugNom12,txtDniJug1,txtDniJug2,txtDniJug3,txtDniJug4,txtDniJug5,txtDniJug6,txtDniJug7,
            txtDniJug8,txtDniJug9,txtDniJug10,txtDniJug11,txtDniJug12;
    
    @FXML
    public DatePicker dtJug1,dtJug2,dtJug3,dtJug4,dtJug5,dtJug6,dtJug7,dtJug8,dtJug9,dtJug10,
            dtJug11,dtJug12,dtFechaDelegado; 
    
    DatePicker[] fechasJugadores;
    TextField [] nombresJugadores;
    TextField [] dniJugadores;
    sqlGestionTorneo torneo = new sqlGestionTorneo();
    sqlGestionJugadores jugadores = new sqlGestionJugadores();
    dialogo dialogo = new dialogo();
    MostrarListadoTorneosController listado = new MostrarListadoTorneosController();
    
    public void CargaEquipoController() {
        fechasJugadores = new DatePicker[]{ 
            dtJug1,dtJug2,dtJug3,dtJug4,dtJug5,dtJug6,dtJug7,dtJug8,dtJug9,dtJug10,
            dtJug11,dtJug12};
        
        nombresJugadores = new TextField[]{
            txtJugNom1,txtJugNom2,
            txtJugNom3,txtJugNom4,txtJugNom5,txtJugNom6,txtJugNom7,txtJugNom8,txtJugNom9,txtJugNom10,
            txtJugNom11,txtJugNom12
            };
        
        dniJugadores = new TextField[]{
            txtDniJug1,txtDniJug2,txtDniJug3,txtDniJug4,txtDniJug5,txtDniJug6,txtDniJug7,
            txtDniJug8,txtDniJug9,txtDniJug10,txtDniJug11,txtDniJug12
            };
    }
    
    @FXML
    public void texto (){
        comboCiudad.setAccessibleHelp("Elegi ciudad che");
    }
    
    @FXML
    private void juntarDatos () throws SQLException {
        equipo equipoACargar = new equipo();
        
        if(verificarCampos()){
            equipoACargar.setNombreEquipo(txNomEquipo.getText());
            equipoACargar.setDelegado(generarDelegado());
            equipoACargar.setJugadores(cargarJugadores());
            equipoACargar.setIdTorneo(MostrarListadoTorneosController.getIndice());
            int edadTorneo = torneo.obtenerEdadTorneo(cFondo.getObjConnection(),equipoACargar.getIdTorneo());
            //controlar aca los dnies        
            if(verificarDni(equipoACargar)){
                cargarNuevoEquipo c = new cargarNuevoEquipo(equipoACargar);
                    if(edadTorneo==0) {
                        c.realizarCargaEquipo();
                        dialogo.msjCargaEquipo(equipoACargar.getNombreEquipo());
                        limpiarComponenetes();
                        resetearEstilos();
                    }
                    else if(verificarMayoriaEdad(edadTorneo)>1){
                        dialogo.msjError(equipoACargar.getNombreEquipo(),dialogo.JUGADOR_MAYOR_35);
                    }else{
                        c.realizarCargaEquipo();
                        dialogo.msjCargaEquipo(equipoACargar.getNombreEquipo());
                        limpiarComponenetes();
                        resetearEstilos();
                    }
            }else{
                dialogo.msjError(equipoACargar.getNombreEquipo(),dialogo.DNI_REPETIDO);
            } 
        }else dialogo.msjCargaCategoria(Alert.AlertType.ERROR,dialogo.ALERTA, dialogo.CARGA_EQUIPO, dialogo.VERIFICAR_CAMPOS_INGRESADOS);
    }
    
    private delegado generarDelegado (){
        delegado d = new delegado(txtnomDelegado.getText(), txtDNIDelegado.getText());
        d.setFecha(verificarFechaNula(dtFechaDelegado));
        d.setDomicilio(comboCiudad.getValue());
        d.setTelefono(txTelefono.getText());
        return d;
    }
    
    ArrayList<jugador> cargarJugadores(){
        ArrayList<jugador> temp = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            String nombre = devolverGuionNulo(nombresJugadores[i].getText());
            String dni = devolverGuionNulo(dniJugadores[i].getText());
            String fecha = verificarFechaNula(fechasJugadores[i]);
            jugador jug = new jugador(nombre,dni,fecha);
            temp.add(i,jug);
        }
        return temp; 
    }
    
    String verificarFechaNula(DatePicker date){
        date = verificarCambioFoco(date);
        if(date.getEditor()==null){
            return devolverGuionNulo("-");
        }
        return devolverGuionNulo(date.getEditor().getText());
    }
    
    DatePicker verificarCambioFoco(DatePicker dtpckr){
        dtpckr.focusedProperty().addListener(new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (!newValue){
                dtpckr.setValue(dtpckr.getConverter().fromString(dtpckr.getEditor().getText()));
            }
        }
    });
 return dtpckr;
    }
    
    String devolverGuionNulo(String verificar){
        String devolver = "-";
        if((verificar.equals(""))||(verificar.equals("-"))){
            return devolver;
        }else {
            devolver=verificar;
        }   
        return devolver;
    }
    
    int devolverEdad(DatePicker fechaNac){
        Calendar fechaActual = Calendar.getInstance();
        Date temp = new Date();
        DatePicker date = new DatePicker();
        
        date.setValue(date.getConverter().fromString(fechaNac.getEditor().getText()));
 
        // Cálculo de las diferencias.
        int año = fechaActual.get(Calendar.YEAR) - date.getValue().getYear();
        int mes = fechaActual.get((Calendar.MONTH)+1) - date.getValue().getMonthValue();
        int dia = fechaActual.get(Calendar.DAY_OF_MONTH) - date.getValue().getDayOfMonth();

        // Hay que comprobar si el día de su cumpleaños es posterior
        // a la fecha actual, para restar 1 a la diferencia de años,
        // pues aún no ha sido su cumpleaños.
 
        if(mes < 0 // Aún no es el mes de su cumpleaños
           || (mes==0 && dia < 0)) { // o es el mes pero no ha llegado el día.
            año--;
        }
        return año;
    }
    private int verificarMayoriaEdad(int edadTorneo) {
        int cantidad=0;
        
        for (DatePicker e : fechasJugadores) {
            if(e.getValue()!=null){
                if(devolverEdad(e)<edadTorneo) cantidad++;  //*
            }  
        }
        return cantidad;
    }
    
    boolean verificarCampos(){
        boolean estado=true;
        if(MostrarListadoTorneosController.getIndice()==-1){
            dialogo.msjCargaCategoria(Alert.AlertType.ERROR, dialogo.ALERTA, dialogo.CARGA_EQUIPO, dialogo.ELEGIR_CATEGORIA);
            return estado=false;
        }
        if(txNomEquipo.getText().isEmpty()){
            cambioEstiloRojo(lblNombreEquipo);
            estado=false;
        }
        if(comboCiudad.getValue()==null){
            cambioEstiloRojo(lblLocalidad);
            estado=false;
        }
        if(txTelefono.getText().isEmpty()){
            cambioEstiloRojo(lblTelefono);
            estado=false; 
        }
        if(txtnomDelegado.getText().isEmpty()){
            cambioEstiloRojo(lblNombreDele);
            estado=false; 
        }
        if(txtDNIDelegado.getText().isEmpty()){
            cambioEstiloRojo(lblDniDele);
            estado=false; 
        }
        if(dtFechaDelegado.getValue()==null){
            cambioEstiloRojo(lblFechaDele);
            estado=false; 
        }
        return estado;
    }
    
    void resetearEstilos(){
        cambiarEstiloNormal(lblNombreEquipo);
        cambiarEstiloNormal(lblLocalidad);
        cambiarEstiloNormal(lblTelefono);
        cambiarEstiloNormal(lblNombreDele);
        cambiarEstiloNormal(lblDniDele);
        cambiarEstiloNormal(lblFechaDele);
    }
    
    void cambiarEstiloNormal(Label componenete){
        componenete.getStyleClass().remove("estiloRojo");
    }
    
    void cambioEstiloRojo (Label componente){
        componente.getStyleClass().add("estiloRojo");
    }
    
    void cargaCiudades(){
        ArrayList<String> localidades = new ArrayList<>();
        localidades.add("Castilla");
        localidades.add("Rawson");
        localidades.add("Rivas");
        localidades.add("Franklin");
        localidades.add("Suipacha");
        localidades.add("Carmen de Areco");
        localidades.add("Tres Sargentos");
        localidades.add("Gouin");
        localidades.add("Capitan Sarmiento");
        localidades.add("Achupallas");
        localidades.add("Chivilcoy");
        
        Collections.sort(localidades);
        
        for (String e : localidades) {
            comboCiudad.getItems().add(e);
        }
    }
   
    Boolean verificarDni(equipo equipoACargar) throws SQLException{
        Boolean estado=true;
        ArrayList<String> dniRegistrados = jugadores.obtenerListaDni(cFondo.getObjConnection(),equipoACargar.getIdTorneo());
        Collections.sort(dniRegistrados);
        for (jugador j : equipoACargar.getJugadores()) {
            for (String dniRegistrado : dniRegistrados) {
                if(j.getDni().equals(dniRegistrado)){
                    estado=false;
                }
            }
        }
        for (String dniRegistrado : dniRegistrados) {
                if(equipoACargar.getDelegado().getDni().equals(dniRegistrado)){
                    estado=false;
                }    
        }       
        return estado;
       }
    
    void limpiarComponenetes(){
        txNomEquipo.clear();
        comboCiudad.getItems().clear();
        cargaCiudades();
        txTelefono.clear();
        txtnomDelegado.clear();
        txtDNIDelegado.clear();
        dtFechaDelegado.getEditor().clear();
        for (DatePicker e : fechasJugadores) {
            if(e.getValue()!=null){
                e.getEditor().clear();
            }  
        }
        
        for (int i = 0; i < 12; i++) {
            nombresJugadores[i].clear();
            dniJugadores[i].clear();
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CargaEquipoController();
        cargaCiudades(); 
    }
}
