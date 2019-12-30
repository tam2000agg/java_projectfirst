/**
 * Sample Skeleton for 'blupview.fxml' Controller Class
 */

package billupdate;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class blupviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader

    @FXML // fx:id="datestrt"
    private DatePicker datestrt; // Value injected by FXMLLoader

    @FXML // fx:id="dateend"
    private DatePicker dateend; // Value injected by FXMLLoader
    @FXML // fx:id="txtpen"
    private TextField txtpen; // Value injected by FXMLLoader


      Connection con;
    PreparedStatement pst;
    @FXML
    void doupdate(ActionEvent event)
    {
    	  if( dateend.getValue()==null || datestrt.getValue()==null||txtname.getText()=="")
       	 {
       		 Alert al=new Alert(AlertType.ERROR);
   			    al.setTitle("All blocks  are compulsory to fill");
   			    al.setContentText("Fill the compulsory data");
   			    al.show();
       	 }
    	  else
    	  {
    		  LocalDate local=datestrt.getValue();
    		  java.sql.Date dobb=  java.sql.Date.valueOf(local);

    		  LocalDate local1=dateend.getValue();
    		  java.sql.Date dob=  java.sql.Date.valueOf(local1);
    		   float pend= Float.parseFloat(txtpen.getText());

    		  try {

             	  PreparedStatement pst=  con.prepareStatement("update billing set pending=?,status=? where startdate=? and enddate=? and name=?"); //in parameters
             	  pst.setFloat(1, pend);
             	  pst.setDate(3,dobb);
                  pst.setInt(2, 1);

                  pst.setDate(4, dob);
                  pst.setString(5, txtname.getText());

                  pst.executeUpdate();

                  System.out.println("updated ");




              }
              catch (SQLException e)
              {
                  e.printStackTrace();
              }

    	  }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DBconnection.doConnect();
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'blupview.fxml'.";
        assert datestrt != null : "fx:id=\"datestrt\" was not injected: check your FXML file 'blupview.fxml'.";
        assert dateend != null : "fx:id=\"dateend\" was not injected: check your FXML file 'blupview.fxml'.";

    }
}
