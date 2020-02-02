/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudDerby;

import control.*;
import java.io.File;
import java.sql.SQLException;

/**
 *
 * @author Emanuel
 */
public class verificacionBase {
    
    private static String proyecto;
    
    private static String db;
    
    cFondo c= new cFondo();

    public static String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public static String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
    
    public void verificarBase () throws SQLException {
        
        // esta instruccion me almacena el separador, osea barra en windows
		
        String barra = File.separator;
		
	// localizo donde tengo la carpeta del proyecto
		
	setProyecto(System.getProperty("user.dir")+barra+"bdJugadores") ;
        
       
		
	setDb("jdbc:derby:"+ proyecto);
		
	//con esta instruccion almaceno, si existe, esa ruta en file
        
	File url = new File(proyecto);

	if (url.exists()) {
	}else {
            System.out.println("No Existe. Creando...");
            crearBD i = new crearBD();
            i.crearBase();
	}
    }
    
}
