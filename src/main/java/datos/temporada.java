/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import control.cFondo;
import crudDerby.sqlGestionTorneo;
import java.util.ArrayList;
/**
 *
 * @author Emanuel
 */
public class temporada {
    
    private int idTemporada;
    
    private String nombreTemporada;
    
    private String datosExtras;
    
    private int edad;
    
    private ArrayList<temporada> t;
    
    sqlGestionTorneo torneo = new sqlGestionTorneo();

    public int getIdTemporada() {
        return idTemporada;
    }

    public void setIdTemporada(int idTemporada) {
        this.idTemporada = idTemporada;
    }

    public String getNombreTemporada() {
        return nombreTemporada;
    }

    public void setNombreTemporada(String nombreTemporada) {
        this.nombreTemporada = nombreTemporada;
    }

    public String getDatosExtras() {
        return datosExtras;
    }

    public void setDatosExtras(String datosExtras) {
        this.datosExtras = datosExtras;
    }
    
    public ArrayList<temporada> getListadoTorneos (){
        t = torneo.leerRegistro(cFondo.getObjConnection());
        return t;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int obtenerIdTemporada (String nombreTemporada){
        int i=50;
        
        for (temporada e : t) {
            if (nombreTemporada.equals(e.getNombreTemporada())){
                i=e.getIdTemporada();  
            }
        }
        return i;
        
    }

    @Override
    public String toString() {
        return "temporada{" + "idTemporada=" + idTemporada + ", nombreTemporada=" + nombreTemporada + ", datosExtras=" + datosExtras + '}';
    }

}
