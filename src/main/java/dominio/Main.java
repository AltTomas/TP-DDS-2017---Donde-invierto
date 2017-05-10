package dominio;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
    public void start(final Stage primaryStage) {
       
        final Button button = new Button();
        button.setText("Demo");       
        button.setOnAction((ActionEvent event) -> {            
            System.out.println("Donde Invierto: Funcionalidad no implementada");
        });
		        
        final StackPane root = new StackPane();
        
		// Agregar los hijos de la ventana (botón).
        root.getChildren().add(button);
        
		// Crear una escena.
        final Scene scene = new Scene(root, 300, 250);
        
		// Titulo de la ventana.
        primaryStage.setTitle("Donde Invierto");
        primaryStage.setScene(scene);
        
		// Mostrar la ventana.
        primaryStage.show();
    }
 
    /**
     * Main function that opens the "Hello World!" window
     * 
     * @param args the command line arguments
     */
    public static void main(final String[] arguments) {
        launch(arguments);
    }
}