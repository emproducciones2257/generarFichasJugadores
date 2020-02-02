/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.temporada;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public final class MostrarListadoTorneosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ComboBox<String> comboTorneos;
    
    ArrayList<temporada> temporada;
    
    temporada torneos = new temporada();
        
    public static int indice=-1;

    public static void setIndice(int indice) {
        MostrarListadoTorneosController.indice = indice;
    }
    
    public static int getIndice(){ 
        return indice;
    }

    @FXML
    public void cargaCombo () {
        
        if(comboTorneos.getItems()!=null){
            comboTorneos.getItems().clear();
        }
        temporada= torneos.getListadoTorneos();
        for (temporada object : temporada) {
            comboTorneos.getItems().add(object.getNombreTemporada());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboTorneos.setOnAction((ActionEvent event) -> {   
            indice=torneos.obtenerIdTemporada(comboTorneos.getValue());
        });
        
    }    
    
}
