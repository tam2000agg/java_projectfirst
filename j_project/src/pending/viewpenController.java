/**
 * Sample Skeleton for 'viewpen.fxml' Controller Class
 */

package pending;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;



public class viewpenController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    Connection con;
    PreparedStatement pst;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader

    @FXML // fx:id="txtamn"
    private TextField txtamn; // Value injected by FXMLLoader

    @FXML // fx:id="txtpen"
    private TextField txtpen; // Value injected by FXMLLoader

    @FXML // fx:id="txtsum"
    private TextField txtsum; // Value injected by FXMLLoader
    @FXML // fx:id="datestrt"
    private DatePicker datestrt; // Value injected by FXMLLoader

    @FXML // fx:id="datestart"
    private DatePicker datestart; // Value injected by FXMLLoader
    @FXML
    void DOSUM(ActionEvent event) throws SQLException
    {
    float amn=Float.parseFloat(txtamn.getText());
    float pending=Float.parseFloat(txtpen.getText());
    float sum=amn+pending;
    txtsum.setText(String.valueOf(sum));
    LocalDate local=datestrt.getValue();
    java.sql.Date dobb=  java.sql.Date.valueOf(local);
    PreparedStatement pst=  con.prepareStatement("update billing set grandtotal=? where name=? and startdate=?");
    pst.setFloat(1, sum);
    pst.setString(2, si);
    pst.setDate(3, dobb);
    pst.executeUpdate();
	 System.out.println("updated");

    }
    String si;
    @FXML
    void dofetch(ActionEvent event) throws SQLException
    {
    	si=combo.getSelectionModel().getSelectedItem();
		 LocalDate local=datestrt.getValue();
		 java.sql.Date dobb=  java.sql.Date.valueOf(local);
		 pst=con.prepareStatement("select amount from billing where name=? and startdate=?");
 		pst.setString(1, si);
 		pst.setDate(2,dobb);
 		ResultSet table=pst.executeQuery();
 		boolean jasoos=false;
 while(table.next())
 {
	 jasoos=true;
	 float amn=table.getFloat("amount");
	txtamn.setText(String.valueOf(amn));
 }

	 if(jasoos==false)
	    {
	    	Alert al=new Alert(AlertType.ERROR);
			    al.setTitle("data not found");
			    al.setContentText("Fill the proper data");
			    al.show();
	    }

    }

    @FXML
    void dofetch1(ActionEvent event) throws SQLException
    {
LocalDate local1=datestart.getValue();
java.sql.Date dob=java.sql.Date.valueOf(local1);
pst=con.prepareStatement("select pending from billing where name=? and startdate=?");
	pst.setString(1, si);
	pst.setDate(2,dob);
	ResultSet table=pst.executeQuery();
	boolean jasoos=false;
while(table.next())
{
jasoos=true;
float amn=table.getFloat("pending");
txtpen.setText(String.valueOf(amn));
}

if(jasoos==false)
{
	Alert al=new Alert(AlertType.ERROR);
	    al.setTitle("data not found");
	    al.setContentText("Fill the proper data");
	    al.show();
}

}







    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DBconnection.doConnect();
        try
        {
			pst=con.prepareStatement("select name from customerid");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
		      String nme=table.getString("name");
			  combo.getItems().addAll(nme);
			}


        }
        catch (SQLException e)
        {

			e.printStackTrace();
		}

        assert combo != null : "fx:id=\"combo\" was not injected: check your FXML file 'viewpen.fxml'.";
        assert txtamn != null : "fx:id=\"txtamn\" was not injected: check your FXML file 'viewpen.fxml'.";
        assert txtpen != null : "fx:id=\"txtpen\" was not injected: check your FXML file 'viewpen.fxml'.";
        assert txtsum != null : "fx:id=\"txtsum\" was not injected: check your FXML file 'viewpen.fxml'.";

    }
}
