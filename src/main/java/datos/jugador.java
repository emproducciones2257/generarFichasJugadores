/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Emanuel
 */
public class jugador {
    
    private int idJugador;
    
    private int idEquipo;
    
    private String nombre;
    
    private String dni;
    
    private String fecha;
   
    public jugador (String n, String d, String f){
        nombre=n;
        dni=d;
        fecha=f;
    }
    
    public jugador (String n, String d){
        nombre=n;
        dni=d;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "jugador{" + "idJugador=" + idJugador + ", idEquipo=" + idEquipo + ", nombre=" + nombre + ", dni=" + dni + ", fecha=" + fecha + '}';
    }
    
}
