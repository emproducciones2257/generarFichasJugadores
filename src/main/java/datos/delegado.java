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
public class delegado extends jugador{
    
    private int idDelegado;
    
    private String domicilio;
    
    private String telefono;

    public delegado(String n, String d) {
        super(n,d);
    }

    public int getIdDelegado() {
        return idDelegado;
    }

    public void setIdDelegado(int idDelegado) {
        this.idDelegado = idDelegado;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "delegado{" + "idDelegado=" + idDelegado + ", domicilio=" + domicilio + ", telefono=" + telefono + '}';
    }

}
