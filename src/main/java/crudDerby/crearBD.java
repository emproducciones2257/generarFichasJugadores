/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Emanuel
 */
public class crearBD {
    private String db;
    private PreparedStatement pre;
    private ResultSet rs;
    private Connection c;
    private String instruccion;
     
    public void crearBase () throws SQLException {
		
	// en db creo la instruccion para crear la base, 
	instruccion = "jdbc:derby:"+ verificacionBase.getProyecto()+";create=true";
        
        conexion conexionConDriver = new conexion(instruccion);
        c=conexionConDriver.getConexion();
        c.close();			
    }			
}
    
    

