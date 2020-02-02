/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import datos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class sqlGestionEquipo {
    
    private String instruccionCargaEquipo = "INSERT INTO EQUIPOS VALUES (DEFAULT,?,?,?)";
    
    private String instruccionRecuperarIdEquipo = "SELECT \"id_equipo\" FROM EQUIPOS WHERE \"nombre\" = ";
    
    private String instruccionActualizarId ="UPDATE EQUIPOS SET \"id_delegado\" = ";
    
    private String instruccionRecuperarListadoEquipos = "SELECT  FROM EQUIPOS";
    
    private String instruccionRecuperarListadoEquiposPorCampeonato = "SELECT * FROM EQUIPOS WHERE \"id_torneo\" = ";
    
    equipo equipoACargar;
    
    Connection conexion;
    
    PreparedStatement pre;
    
    ResultSet resu;

    public sqlGestionEquipo(equipo e, Connection c) {
        
        equipoACargar=e;
        conexion=c;
    }
    
    public equipo cargarEquipo (){
        try {
            pre=conexion.prepareStatement(instruccionCargaEquipo);
            pre.setInt(1, equipoACargar.getIdTorneo());
            pre.setInt(2, equipoACargar.getIdTorneo());
            pre.setString(3, equipoACargar.getNombreEquipo());
            pre.execute();
        } catch (Exception e) {
            System.out.println("Fallo!");
        }
        return equipoACargar;
    }
    
    public equipo recuperarIdequipo () {
        try {
            pre=conexion.prepareStatement(instruccionRecuperarIdEquipo+"'"+equipoACargar.getNombreEquipo()+"'");
            resu=pre.executeQuery();
            resu.next();
            equipoACargar.setIdEquipo(resu.getInt("id_equipo")); 
        } catch (Exception e) {
        }
    return equipoACargar;
    }   

    void actualizarIdDelegadoEnEquipo() {
        try {
            System.out.println("aca" + equipoACargar.toString());
            pre=conexion.prepareStatement(instruccionActualizarId+equipoACargar.getDelegado().getIdDelegado()
                    + "WHERE \"nombre\" =" +"'"+equipoACargar.getNombreEquipo()+"'");
            pre.execute();
            System.out.println(equipoACargar.toString());
        } catch (Exception e) {
        }
        
    }
    
    public ArrayList <equipo> recuperarEquipos (Connection c){
        ArrayList <equipo> equipos = new ArrayList<>();
        try {
            pre= c.prepareStatement(instruccionRecuperarListadoEquipos);
            resu=pre.executeQuery();
            while (resu.next()) {
               equipo temp = new equipo();
               temp.setIdEquipo(resu.getInt("id_equipo"));
               temp.setNombreEquipo(resu.getString("nombre"));
               equipos.add(temp);
            }
            resu.close();
        } catch (Exception e) {
        }
        return equipos;
    }

    public ArrayList<equipo> recuperarEquiposPorCampeonato(Connection c, int indice) {
        ArrayList <equipo> equipos = new ArrayList<>();
        try {
            pre= c.prepareStatement(instruccionRecuperarListadoEquiposPorCampeonato + indice);
            resu=pre.executeQuery();
            while (resu.next()) {
               equipo temp = new equipo();
               temp.setIdEquipo(resu.getInt("id_equipo"));
               temp.setNombreEquipo(resu.getString("nombre"));
               equipos.add(temp);
            }
            resu.close();
        } catch (Exception e) {
        }
        return equipos;
    }
}
