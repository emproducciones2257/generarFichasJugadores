package control;


import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import crudDerby.*;
import java.sql.SQLException;

public class cFondo implements Initializable {
    
    private conexion conesion;
    private static Connection objConnection;
    
    public static Connection getObjConnection() {
        return objConnection;
    }

    public void setObjConnection(Connection objConnection) {
        cFondo.objConnection = objConnection;
    }
    
    public void crearConexionPrinciparABD(String ruta){
        conesion = new conexion(ruta);
	objConnection = conesion.getConexion();  
    }
   
    @FXML
    private Pane paneGestionTorneos;
    
    @FXML
    private Pane paneCargaEquipos;
    
    @FXML
    private Pane paneGenerarInforme;
    
    @FXML
    public void boton1(){
        paneGestionTorneos.setVisible(true);
        paneCargaEquipos.setVisible(false);
        paneGenerarInforme.setVisible(false);

    }
    @FXML
    public void boton2(){
        paneGestionTorneos.setVisible(false);
        paneCargaEquipos.setVisible(true);
        paneGenerarInforme.setVisible(false);
    }
    
    @FXML
    public void boton3(){
        paneGestionTorneos.setVisible(false);
        paneCargaEquipos.setVisible(false);
        paneGenerarInforme.setVisible(true);
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        verificacionBase v = new verificacionBase();
        try {
            v.verificarBase();
        } catch (SQLException ex) {
            
        }
        paneGestionTorneos.setVisible(false);
        paneCargaEquipos.setVisible(false);
        paneGenerarInforme.setVisible(false);
        crearConexionPrinciparABD(verificacionBase.getDb());

    }    
}
