/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import control.cFondo;
import datos.equipo;
import java.sql.SQLException;

/**
 *
 * @author Emanuel
 */
public class cargarNuevoEquipo {
    
    equipo equipoACargar;

    
    public cargarNuevoEquipo (equipo e) {
        
        equipoACargar=e;
    }
    
    
    public void realizarCargaEquipo () throws SQLException{
        // 1 - registro el equipo *
        // 2 - consultar el ultimo registro para obtener el id del equipo y guardarlo en el objeto *
        // 3 - registrar el delegado*
        // 4 - consulto el ultimo registro del delegado para obtener el id y guardar en el objeto*
        // 5 - actualizo el ultimo registro de equipo para almacenar el id delegado*
        // 6 - registrar los jugadores y a la mierda
        sqlGestionEquipo gestionEquipo = new sqlGestionEquipo(equipoACargar, cFondo.getObjConnection());
        equipoACargar=gestionEquipo.cargarEquipo();
        equipoACargar=gestionEquipo.recuperarIdequipo();
        ///////////////////////////////////////////////////////////////
        sqlGestionDelegado gestionDelegado = new sqlGestionDelegado(equipoACargar, cFondo.getObjConnection());
        gestionDelegado.cargarDelegado();
        equipoACargar=gestionDelegado.recuperarIdDelegado();
        //////////////////////////////////////////////////////////////
        gestionEquipo.actualizarIdDelegadoEnEquipo();
        //////////////////////////////////////////////////////////
        sqlGestionJugadores gestionJugadores = new sqlGestionJugadores(equipoACargar, cFondo.getObjConnection());
        gestionJugadores.cargarJugadores();
    }
    
}
