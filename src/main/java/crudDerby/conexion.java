/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import java.sql.*;

/**
 *
 * @author Emanuel
 */

public class conexion {
    
    private String rutaLocal;
    private Connection conexion = null;
    
    public conexion (String ruta) {	
        rutaLocal = ruta;
    }
    
    public Connection getConexion () {
        
    try {
        // cargo el driver de la base de datos 
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conexion = DriverManager.getConnection(rutaLocal);
        

	} catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
			
                System.out.println("Fallo al crear la conexion");
                
		}
    return conexion;
    }	   
}