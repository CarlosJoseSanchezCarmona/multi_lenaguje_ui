package es.ideas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * JavaFX App
 */
public class MultiLenguajeUI extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {       
        MultiLenguajeUI.primaryStage = stage;
        //Crear una instancia de la clase FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        //Establecer la localización del fichero FXML a cargar
        loader.setLocation(getClass().getResource("primary.fxml"));   
        //Establecer la localización del fichero de internacionalización
        loader.setResources(ResourceBundle.getBundle("es.ideas.i18n/traducir",
                Locale.getDefault()));
        Parent raiz = loader.load();
        PrimaryController pc = loader.getController();
        pc.setMainWindow(this);
           
        //Preparar y arrancar escena
        scene = new Scene(raiz);   
        stage.setScene(scene);
        stage.show();
    }
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    public static void main(String[] args) {
        launch();
    }

}