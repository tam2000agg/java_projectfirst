/**
 * Sample Skeleton for 'viewwork.fxml' Controller Class
 */

package workers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;

public class viewworkController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combouid"
    private ComboBox<String> combouid; // Value injected by FXMLLoader
    @FXML // fx:id="label1"
    private Label label1; // Value injected by FXMLLoader
    Connection con;
    PreparedStatement pst;
    @FXML
    void dowork(ActionEvent event) throws SQLException
    {
    	 String item=combouid.getSelectionModel().getSelectedItem();
    	 int indx=combouid.getItems().indexOf(item);
         if(indx==-1)
         {
         pst=con.prepareStatement("insert into workers values (?)");
         pst.setString(1, item);
         pst.executeUpdate();
         combouid.getItems().add(item);
         }


    }
    @FXML // fx:id="txt2"
    private TextField txt2; // Value injected by FXMLLoader

    @FXML
    void doallot(ActionEvent event) throws SQLException
    {

		pst=con.prepareStatement("select name from workers");
		ResultSet table=pst.executeQuery();
		int count=0;
		while(table.next())
		{
			count=count+1;

		}
		int houses=Integer.parseInt(tetx1.getText());
		float avg=houses/count;
		txt2.setText(avg+"");
    }
    @FXML // fx:id="tetx1"
    private TextField tetx1; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
    	con=DBconnection.doConnect();
pst=con.prepareStatement("select name from customerid");
ResultSet table1=pst.executeQuery();
int count=0;
while(table1.next())
{
count=count+1;
}
tetx1.setText(count+"");


        con=DBconnection.doConnect();

			pst=con.prepareStatement("select name from workers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
		      String nme=table.getString("name");
				combouid.getItems().addAll(nme);
			}




        assert combouid != null : "fx:id=\"combouid\" was not injected: check your FXML file 'viewwork.fxml'.";

    }
}
