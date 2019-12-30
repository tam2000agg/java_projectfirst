
package table3;

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
                Parent root=FXMLLoader.load(getClass().getResource("viewtableview.fxml"));
                Scene scene = new Scene(root,1100,800);
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