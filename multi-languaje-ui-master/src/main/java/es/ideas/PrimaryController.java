package es.ideas;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class PrimaryController implements Initializable {
    //Creacion de los togglrbutton para cada ifioma 
    @FXML
    private ToggleButton tgpSp;
    @FXML
    private ToggleButton tgpEn;
    @FXML
    private ToggleButton tgpFr;
    private MultiLenguajeUI aplicacionPrincipal;
    public void setMainWindow(MultiLenguajeUI main){
        this.aplicacionPrincipal= main;
    }
    
    @FXML
    private ChoiceBox<String> cbSemana;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Creacion de dias de la semana llamando a las key propies para octener el dia de la semana
        String dias_semana[] = {
            rb.getString("lunes"),
            rb.getString("martes"),
            rb.getString("miércoles"),
            rb.getString("jueves"),
            rb.getString("viernes"),
            rb.getString("sábado"),
            rb.getString("domingo")};
        //asignando a cbSemana la informacion de dias de la semana
        cbSemana.setItems(FXCollections.observableArrayList(dias_semana));     
                        
        //Crear un ToggleGroup para agrupar los ToggleButton
        //Sólo puede haber uno seleccionado cada vez.
        ToggleGroup tg= new ToggleGroup();
        tg.getToggles().addAll(tgpSp,tgpEn,tgpFr);   
        //Listener para cambiar el idioma
        tg.selectedToggleProperty().addListener((obs,oldValue,newValue)->{
            if (newValue != null ){
               ToggleButton tb = (ToggleButton) newValue.getToggleGroup().getSelectedToggle();     
               //Se comprueba el valor del Texto del ToggleButton
               switch (tb.getText()){
                   case "Ingles":
                       Locale.setDefault(Locale.ENGLISH);
                       System.out.println("Has seleccionado idioma INGLÉS"); 
                      
                       break;  
                   case "Frances":
                       Locale.setDefault(Locale.FRENCH);
                       System.out.println("Has seleccionado idioma FRANCÉS");
                       break;
                    default:
                       Locale.setDefault(new Locale("es"));
                      
                       System.out.println("Has seleccionado idioma ESPAÑOL");                       
                         }
               try{
                Parent pane = getFXMLLoader().load();
                MultiLenguajeUI.getPrimaryStage().getScene().setRoot(pane);
               }catch(IOException ieo){                   
               }               
               MultiLenguajeUI.getPrimaryStage().show();
            }
               
        });        
    }

    
private FXMLLoader getFXMLLoader(){
        FXMLLoader loader = new FXMLLoader();
        //al cargar darle los datos de la localizacion del fichero Properties
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/traducir",
                Locale.getDefault()));
        loader.setLocation(getClass().getResource("primary.fxml"));
        return loader;
    }
    
}
