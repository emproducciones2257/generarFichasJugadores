/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import control.cFondo;
import crudDerby.sqlGestionEquipo;
import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class equipo {
    
    private int idEquipo;
    
    private int idTorneo;
    
    private delegado Delegado;
    
    private String nombreEquipo;
    
    private ArrayList<jugador> jugadores;
    
    private ArrayList<equipo> equipos;
    
    public equipo() {
        this.jugadores = new ArrayList<>();
    }

    public ArrayList<equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<equipo> equipos) {
        this.equipos = equipos;
    }
    
    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdTorneo() {
        return idTorneo;
    }

    public void setIdTorneo(int idTorneo) {
        this.idTorneo = idTorneo;
    }
    
    public delegado getDelegado() {
        return Delegado;
    }

    public void setDelegado(delegado Delegado) {
        this.Delegado = Delegado;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public ArrayList<jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<jugador> jugadores) {
        this.jugadores = jugadores;
    }
    
    public ArrayList<equipo> obtenerListadoEquipos() {
        
        ArrayList<equipo> listadoEquipos= new sqlGestionEquipo(this, cFondo.getObjConnection()).recuperarEquipos(cFondo.getObjConnection());

        return listadoEquipos;
    }

    @Override
    public String toString() {
        return "equipo{" + "idEquipo=" + idEquipo + ", idTorneo=" + idTorneo + 
                ", Delegado=" + Delegado.toString() + ", nombreEquipo=" + nombreEquipo + ", jugadores=" + jugadores.toString() + '}';
    }  

    public ArrayList<equipo> obtenerListadoEquipos(int indice) {
        
        ArrayList<equipo> listadoEquipos= new sqlGestionEquipo(this, cFondo.getObjConnection()).recuperarEquiposPorCampeonato(cFondo.getObjConnection(),indice);

        return listadoEquipos;
        }
}
