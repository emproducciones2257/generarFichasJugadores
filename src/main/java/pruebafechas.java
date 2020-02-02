
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Emanuel
 */
public class pruebafechas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Obteniendo la fecha actual del sistema.
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        
        // En esta linea de código estamos indicando el nuevo formato que queremos para nuestra fecha.
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        // Aqui usamos la instancia formatter para darle el formato a la fecha. Es importante ver que el resultado es un string.
        String fechaTexto = formatter.format("2019-11-18");

        
        System.out.println(fechaTexto);
                
    }
    
        public static java.sql.Date fechaActual(){
        java.util.Date h = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(h.getTime());
        return fechaActual;
    }
    
}
