/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import datos.equipo;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateStringConverter;
import reportes.*;

/**
 * FXML Controller class
 *
 * @author Emanuel
 */
public class GenerarInformeController {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ComboBox <String> comboEquipoA;
    
    @FXML
    private ComboBox <String> comboEquipoB;
    
    @FXML
    private DatePicker selectorFechaJugar;
    
    equipo equipos = new equipo();
    equipo a,b;
    reporte re;
    
    ArrayList<equipo> equipito = new ArrayList<>();
    
    void cargarEquipos(){
        ArrayList<equipo> todosEquipos = equipos.obtenerListadoEquipos(MostrarListadoTorneosController.getIndice());
        equipito=todosEquipos;
    }
    
    @FXML
    void cambiarFormato(){
        selectorFechaJugar.setConverter(new LocalDateStringConverter(FormatStyle.FULL));
    }
    
    @FXML
    private void cargarListaEquiposA(){
        if(MostrarListadoTorneosController.getIndice()==-1){
            System.out.println("Seleccionar Torneo antes de elegir equipo");
        }else{
            cargarEquipos();
            if(comboEquipoA.getItems()!=null)
                comboEquipoA.getItems().clear();
            for (equipo e : equipito) {
                comboEquipoA.getItems().add(e.getNombreEquipo());  
            }
            recuperarSeleccionComboA();
        }
    }
    
    void recuperarSeleccionComboA(){
        comboEquipoA.setOnAction((ActionEvent event) -> { 
           for (equipo e : equipito) {
                if(e.getNombreEquipo().equals(comboEquipoA.getValue()))
                    a = e;
                }
           comboEquipoB.setDisable(false);
            System.out.println("SELECCION: " + a.getNombreEquipo());
        });  
    }
    
    @FXML
    private void cargarListaEquiposB(){
        if(comboEquipoB.getItems()!=null)
            comboEquipoB.getItems().clear();
        for (equipo e : equipito) {
            if(!(e.getNombreEquipo().equals(a.getNombreEquipo())))
                comboEquipoB.getItems().add(e.getNombreEquipo());
        }
        recuperarSeleccionComboB();
    }
    
    void recuperarSeleccionComboB(){
        comboEquipoB.setOnAction((ActionEvent event) -> { 
           for (equipo e : equipito) {
                if(e.getNombreEquipo().equals(comboEquipoB.getValue()))
                    b = e;
                }
            System.out.println("SELECCION: " + b.getNombreEquipo());
        });  
    }
    
    @FXML
    void mostrarReporte(){
        re = new reporte();
            re.generaReporte(a.getIdEquipo(),b.getIdEquipo(),selectorFechaJugar.getEditor().getText());
    }
}
    
    
     
    

