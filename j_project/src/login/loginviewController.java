/**
 * Sample Skeleton for 'loginview.fxml' Controller Class
 */

package login;
import java.sql.Connection;
import java.net.URL;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class loginviewController {

    private static final Stage Stage = null;
	@FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // fx:id="loginid"
    private Button loginid; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="user"
    private TextField user; // Value injected by FXMLLoader

    @FXML // fx:id="pass"
    private PasswordField pass; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
  //  double ii = 0;
    @FXML
    private ProgressBar pb;


    class progress implements Runnable{
    	public void run(){
    		for(int i=0;i<100;i++){
    			pb.setProgress(i/100.0);
    			try {
					Thread.sleep(100);
				}
    			catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }

    @FXML
    void dologin(ActionEvent event) throws SQLException
    {

String userid=user.getText();
String password=pass.getText();
pst=con.prepareStatement("select * from logintable");
ResultSet table=pst.executeQuery();
boolean jasus=false;
while(table.next())
{
	String uk=table.getString("uid");
	String pk=table.getString("pwd");

        if(uk.equals(userid)&& pk.equals(password))
        {
        	progress obj=new progress();
        	Thread th1=new Thread(obj);
        	th1.start();

        	 TextInputDialog dialog = new TextInputDialog("");
             dialog.setTitle("Input Data...");
             dialog.setContentText("Please enter ur mobile number");

             // Traditional way to get the response value.
             Optional<String> result = dialog.showAndWait();

             smee(result);   //calling sms function

        	jasus=true;
        	 try {

        		 Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/dashview.fxml"));
                 Scene scene = new Scene(root,1300,900);

                 Stage primaryStage=new Stage();
                 primaryStage.setScene(scene);
                 primaryStage.show();

                 //to hide the login form
                 Scene scene1=(Scene)loginid.getScene();  //loginid is the button in loginform
                 scene1.getWindow().hide();

             }
         catch(Exception e)
             {
                 e.printStackTrace();
             }

        }
    }
if(jasus==false)
{
	    Alert al=new Alert(AlertType.ERROR);
	    al.setTitle("WRONG INPUTING");
	    al.setContentText("FILL PROPER uid and pwd");
	    al.show();


}
    }
      private void setScene(Scene scene) {
		// TODO Auto-generated method stub

	}

      void smee(Optional<String> result)
{
		Date de=new Date();
	 String resp=sms.SST_SMS.bceSunSoftSend((result.get()),("U are login successfully at time"+de) );
     if(resp.contains("successfully"))
         System.out.println("Sent...");
 else
     if(resp.contains("Unknown"))
         System.out.println("Check Internet connection");
     else
         System.out.println("Invalid Mobile Number");

}
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'loginview.fxml'.";
        assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'loginview.fxml'.";
        con=DBconnection.doConnect();
    }
}
