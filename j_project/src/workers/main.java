
package workers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application
{
    @Override
 public void start(Stage primaryStage)
   {
        try {
                Parent root=FXMLLoader.load(getClass().getResource("viewwork.fxml"));
                Scene scene = new Scene(root,724,533);
                //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}