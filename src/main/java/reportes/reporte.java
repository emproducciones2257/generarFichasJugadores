/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import control.cFondo;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Emanuel
 */
public class reporte {
    
    public void generaReporte(int idequipoa,int idequipob, String fecha) {
        Map<String,Object> filtro = new HashMap();
        filtro.put("idequipoa",idequipoa);
        filtro.put("idequipob",idequipob);
        filtro.put("fechaPartido", fecha);
        try {
            JasperReport rere;
            InputStream in = getClass().getResourceAsStream("/archivosReportes/fichaJugadores.jrxml"); 
            rere = JasperCompileManager.compileReport(in);
            JasperPrint jp = JasperFillManager.fillReport(rere,filtro,cFondo.getObjConnection());
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            System.out.println("Paso esto: " + e.getMessage());
        }
        
        
    }
   
}
    

  
