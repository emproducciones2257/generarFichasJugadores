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

/**
 *
 * @author Emanuel
 */
public class sqlGestionDelegado {
    
    private String sqlCargaDelegado = "INSERT INTO DELEGADOS VALUES (DEFAULT,?,?,?,?,?,?)";
    
    private String sqlRecuperarIdDelegado = "SELECT \"id_delegado\" FROM DELEGADOS WHERE \"dni\" = ";
    
    private PreparedStatement pre;
    
    private ResultSet resul;
    
    equipo equipoACargar;
    
    Connection conexion;

    sqlGestionDelegado(equipo equipoACargar, Connection objConnection) {
        this.equipoACargar=equipoACargar;
        this.conexion=objConnection;
        
    }

    void cargarDelegado() {
      
        try {
            pre=conexion.prepareStatement(sqlCargaDelegado);
            pre.setInt(1, equipoACargar.getIdEquipo());
            pre.setString(2, equipoACargar.getDelegado().getNombre());
            pre.setString(3, equipoACargar.getDelegado().getDni());
            pre.setString(4, equipoACargar.getDelegado().getDomicilio());
            pre.setString(5, equipoACargar.getDelegado().getTelefono());
            pre.setString(6, equipoACargar.getDelegado().getFecha());
            pre.execute();
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("NO Carga delegado");
            }
        }

    equipo recuperarIdDelegado() {
        try {
            pre=conexion.prepareStatement(sqlRecuperarIdDelegado +"'"+equipoACargar.getDelegado().getDni()+"'");
            resul=pre.executeQuery();
            resul.next();
            equipoACargar.getDelegado().setIdDelegado(resul.getInt("id_delegado"));
        } catch (Exception e) {
        }
        return equipoACargar;
    }  
}
