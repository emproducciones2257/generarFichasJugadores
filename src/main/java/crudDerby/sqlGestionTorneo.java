/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import datos.temporada;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Emanuel
 */
public class sqlGestionTorneo {
    
    private String sqlCargar = "INSERT INTO TORNEOS VALUES (DEFAULT,?,?,?)";
    
    private String sqlLeer = "SELECT * FROM TORNEOS";
    
    private String sqlReguperarEdadTorneo = "SELECT \"EDAD\" FROM TORNEOS WHERE \"id_torneo\" = ";
    
    private PreparedStatement pre;
    
    private ResultSet rs;
    
    
    public void cargarTorne (Connection c, temporada t) {
	try {	
            pre=c.prepareStatement(sqlCargar);	
            pre.setString(1, t.getNombreTemporada());
            pre.setInt(2, t.getEdad());
            pre.setString(3, t.getDatosExtras());
            pre.execute();		     
			
	} catch (SQLException e) {
			// TODO Auto-generated catch block
            e.printStackTrace();
			
            System.out.println("Fallo!");
	}	
    }
    
    public ArrayList<temporada> leerRegistro(Connection c) {  
       ArrayList<temporada>  j = new ArrayList<>();
	try {		
            pre= c.prepareStatement(sqlLeer);	
            rs= pre.executeQuery();	
                while (rs.next()) {
                    temporada temp = new temporada();
                    temp.setIdTemporada(rs.getInt("id_torneo"));
                    temp.setNombreTemporada(rs.getString("NOMBRETORNEO"));
                    temp.setEdad(rs.getInt("EDAD"));
                    temp.setDatosExtras(rs.getString("DATOSEXTRAS"));
                    j.add(temp);
		}
            rs.close();		
        } catch (SQLException e) {
        }
        return j;
    }

    public int obtenerEdadTorneo(Connection c,int idTorneo) {
        int edad = -1;
        try {
            pre= c.prepareStatement(sqlReguperarEdadTorneo + idTorneo);
            rs= pre.executeQuery();
            rs.next();
            edad=rs.getInt("EDAD");
            rs.close();		
        } catch (SQLException e) {
        }        
        return edad;
    }
    
}
