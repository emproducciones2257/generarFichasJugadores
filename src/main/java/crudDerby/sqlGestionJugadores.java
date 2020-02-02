/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import datos.equipo;
import datos.jugador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class sqlGestionJugadores {
    
    Connection conexion;
    equipo equipoACargar;
    PreparedStatement pre;
    ResultSet resul;
    private String instruccionCargaJugadores ="INSERT INTO JUGADORES VALUES (DEFAULT,?,?,?,?)";
    private String recuperarDni1 ="SELECT DISTINCT  \"dni\" FROM JUGADORES WHERE \"id_equipo\" IN (SELECT \"id_equipo\" FROM EQUIPOS WHERE \"id_torneo\"=1)\n" +
"UNION\n" +
"SELECT \"dni\" FROM DELEGADOS WHERE \"id_equipo\" IN (SELECT \"id_equipo\" FROM EQUIPOS WHERE \"id_torneo\"=1)";
    
    private String recuperarDni2 ="SELECT DISTINCT  \"dni\" FROM JUGADORES WHERE \"id_equipo\" IN (SELECT \"id_equipo\" FROM EQUIPOS WHERE \"id_torneo\"=2)\n" +
"UNION\n" +
"SELECT \"dni\" FROM DELEGADOS WHERE \"id_equipo\" IN (SELECT \"id_equipo\" FROM EQUIPOS WHERE \"id_torneo\"=2)";
    
    private String recuperarDni3 ="SELECT DISTINCT  \"dni\" FROM JUGADORES WHERE \"id_equipo\" IN (SELECT \"id_equipo\" FROM EQUIPOS WHERE \"id_torneo\"=3)\n" +
"UNION\n" +
"SELECT \"dni\" FROM DELEGADOS WHERE \"id_equipo\" IN (SELECT \"id_equipo\" FROM EQUIPOS WHERE \"id_torneo\"=3)";

    public sqlGestionJugadores(equipo equipoACargar, Connection conexion) {
        this.conexion=conexion;
        this.equipoACargar=equipoACargar;
    }
    
    public sqlGestionJugadores(){
        
    }

    void cargarJugadores() throws SQLException {
            try {
                pre=conexion.prepareStatement(instruccionCargaJugadores);
                for (jugador jugador : equipoACargar.getJugadores()) {
                    pre.setInt(1, equipoACargar.getIdEquipo());
                    pre.setString(2,jugador.getNombre());
                    pre.setString(3, jugador.getDni());
                    pre.setString(4, jugador.getFecha());
                    pre.execute();
                }
        } catch (SQLException e) {
            
        }
    }

    public ArrayList<String> obtenerListaDni(Connection c, int idTorneo) throws SQLException {
        ArrayList<String> dni = new ArrayList<>();
        switch (idTorneo) {
            case 1:
                pre=c.prepareStatement(recuperarDni1);
                break;
            case 2: 
                pre=c.prepareStatement(recuperarDni2);
                break;
            default:
                pre=c.prepareStatement(recuperarDni3);
                break;
        }
        resul=pre.executeQuery();
        while (resul.next()) {
            String temp = resul.getString("dni");
            if(!temp.equals("-"))
            dni.add(temp);
        }
        resul.close();
        return dni;
    }
 
}
