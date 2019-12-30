/**
 * Sample Skeleton for 'dltview.fxml' Controller Class
 */

package dltall;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

public class dltviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    @FXML // fx:id="img1"
    private ImageView img1; // Value injected by FXMLLoader

    @FXML // fx:id="label1"
    private Label label1; // Value injected by FXMLLoader

    @FXML
    void dodelete(ActionEvent event)
    {

    	Alert confirm=new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("DELETING..");
        confirm.setContentText("R U sure?");
        Optional<ButtonType> res= confirm.showAndWait();
        if(res.get()==ButtonType.OK)
        {
        	try
           {
            img1.setVisible(true);
            label1.setVisible(true);
            String name1=(txtname.getText());
            PreparedStatement pst=  con.prepareStatement("delete from customerid where name=?");
            pst.setString(1, name1);
            pst.executeUpdate();
            String name2=(txtname.getText());
            pst1=con.prepareStatement("delete from regularid where name=?");
            pst1.setString(1,name2);
            pst1.executeUpdate();
            String name3=(txtname.getText());
            pst2=con.prepareStatement("delete from billing where name=?");
            pst2.setString(1,name3);
            pst2.executeUpdate();




    		  pst.executeUpdate();//fire query in table
    	      System.out.println("deleted");

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
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'dltview.fxml'.";

    }
}
