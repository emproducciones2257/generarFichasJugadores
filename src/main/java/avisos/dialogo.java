/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avisos;

import javafx.scene.control.Alert;

/**
 *
 * @author Emanuel
 * 
 */

public class dialogo {
    
    public static final String ALERTA = "Alerta";
    public static final String CONFIRMACION = "Confirmacion";
    public static final String CARGA_EQUIPO ="Carga de equipo: ";
    public static final String CARGA_CATEGORIA ="Carga de equipo: ";
    public static final String AGREGADO_OK = "Agregado Correctamente";
    public static final String DNI_REPETIDO = "Un dni se encuentra registrado como jugador en el torno";
    public static final String JUGADOR_MAYOR_35 = "Mas de un jugador menor a 35 Años";
    public static final String REGISTRO_CATEGORIA ="Ingresando categoria";
    public static final String VERIFICAR_CAMPOS_INGRESADOS ="Verificar campos imcompletos";
    public static final String ELEGIR_CATEGORIA ="Seleccionar categoria para continuar con la carga del equipo";

    
    
    public void msjCargaEquipo(String nombreEquipo){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(CONFIRMACION);
        alerta.setHeaderText(CARGA_EQUIPO + nombreEquipo);
        alerta.setContentText(AGREGADO_OK);
        alerta.showAndWait();
    }
    
    public void msjError(String nombreEquipo,String causaError){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(ALERTA);
        alerta.setHeaderText(CARGA_EQUIPO + nombreEquipo);
        alerta.setContentText(causaError);
        alerta.showAndWait();
    }
    
    public void msjCargaCategoria(Alert.AlertType tipoAlerta,String titulo,String registro,String mensaje){
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setHeaderText(registro);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
        
    }
}
