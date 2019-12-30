/**
 * Sample Skeleton for 'dashview.fxml' Controller Class
 */

package dashboard;

import java.io.IOException;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class dashviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;



    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    URL url;
    Media media;
    MediaPlayer mediaplayer;
    AudioClip audio;

    AudioClip audio1;
    @FXML
    void dorock(MouseEvent event) {

	    url=getClass().getResource("Barbie Girl - Aqua.mp3");
        audio1=new AudioClip(url.toString());
        audio1.play();
    }

    @FXML
    void doturn(MouseEvent event) {
    	audio1.stop();

    }

       void playSound()
       {

        url=getClass().getResource("tamanna.wav");
        audio=new AudioClip(url.toString());
        audio.play();
       }

       @FXML
       void dobg(MouseEvent event)
       {


               try {
                       Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("bargraph/viewbar.fxml"));
                       Scene scene = new Scene(root,1086,810);
                       //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                       Stage primaryStage=new Stage();
                       primaryStage.setScene(scene);
                       primaryStage.show();
                   }
               catch(Exception e)
                   {
                       e.printStackTrace();
                   }
           }





       @FXML
       void dofc(MouseEvent event)
       {
      playSound();
      try
      {
    	  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("choosefat/fatview.fxml"));
       Scene scene = new Scene(root,1000,750);
      //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      Stage primaryStage=new Stage();
      primaryStage.setScene(scene);
      primaryStage.show();
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
       }
       @FXML
       void dopm(MouseEvent event) throws IOException {
    	   playSound();
    	   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("pending/viewpen.fxml"));
    	   Scene scene = new Scene(root,616,537);
    	   Stage primaryStage=new Stage();
    	      primaryStage.setScene(scene);
    	      primaryStage.show();
       }
       @FXML
       void dobu(MouseEvent event) throws IOException {
    	   playSound();
    	   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billupdate/blupview.fxml"));
    	   Scene scene = new Scene(root,869,678);
    	   Stage primaryStage=new Stage();
    	      primaryStage.setScene(scene);
    	      primaryStage.show();
       }
       @FXML
       void dobc(MouseEvent event) {
    	   playSound();
    	   try {
    		   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("table3/viewtableview.fxml"));
               Scene scene = new Scene(root,970,720);
               //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
               Stage primaryStage=new Stage();
               primaryStage.setScene(scene);
               primaryStage.show();
           }
       catch(Exception e)
           {
               e.printStackTrace();
           }
       }






       @FXML
       void dodf(MouseEvent event)
       {
    	   playSound();
    	   try {
    		   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dltall/dltview.fxml"));
               Scene scene = new Scene(root,800,800);
               //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
               Stage primaryStage=new Stage();
               primaryStage.setScene(scene);
               primaryStage.show();
           }
       catch(Exception e)
           {
               e.printStackTrace();
           }
       }

       @FXML
       void dow(MouseEvent event)
       {
          Alert confirm=new Alert(AlertType.CONFIRMATION);
          confirm.setTitle("Closing..");
          confirm.setContentText("R U sure?");
          Optional<ButtonType> res= confirm.showAndWait();
          if(res.get()==ButtonType.OK)
                  System.exit(1);
       }
       @FXML
       void dobs(MouseEvent event) {
    	   playSound();
    	   try {
    		   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkman2/viewm.fxml"));
               Scene scene = new Scene(root,1150,950);
               //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
               Stage primaryStage=new Stage();
               primaryStage.setScene(scene);
               primaryStage.show();
           }
       catch(Exception e)
           {
               e.printStackTrace();
           }
       }

       @FXML
       void doch(MouseEvent event) {
    	   playSound();
               try {
            	   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("table/tableview.fxml"));
                   Scene scene = new Scene(root,1163,850);
                   //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                   Stage primaryStage=new Stage();
                   primaryStage.setScene(scene);
                   primaryStage.show();
               }
           catch(Exception e)
               {
                   e.printStackTrace();
               }
           }




       @FXML
       void dore(MouseEvent event) {
    	   playSound();
    	   try {
    		   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkaman1/viewmilk.fxml"));
               Scene scene = new Scene(root,1000,750);
               //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
               Stage primaryStage=new Stage();
               primaryStage.setScene(scene);
               primaryStage.show();
           }
       catch(Exception e)
           {
               e.printStackTrace();
           }
       }

       @FXML
       void dovl(MouseEvent event) {
    	   playSound();
    	   try {
    		   Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("table2/viewtable.fxml"));
               Scene scene = new Scene(root,1060,700);
               //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
               Stage primaryStage=new Stage();
               primaryStage.setScene(scene);
               primaryStage.show();
           }
       catch(Exception e)
           {
               e.printStackTrace();
           }
       }

    @FXML
    void docr(MouseEvent event)
    {
playSound();
        try {
        	 Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("milkman/milkview.fxml"));
                Scene scene = new Scene(root,1050,800);

                Stage primaryStage=new Stage();
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
