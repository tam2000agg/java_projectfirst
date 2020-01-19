/**
 * Sample Skeleton for 'patview.fxml' Controller Class
 */

package patient;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class patviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tname"
    private TextField tname; // Value injected by FXMLLoader

    @FXML // fx:id="tid"
    private TextField tid; // Value injected by FXMLLoader

    @FXML // fx:id="tdonor"
    private TextField tdonor; // Value injected by FXMLLoader

    @FXML // fx:id="tblood"
    private TextField tblood; // Value injected by FXMLLoader

    @FXML // fx:id="tage"
    private TextField tage; // Value injected by FXMLLoader

    @FXML // fx:id="tcontact"
    private TextField tcontact; // Value injected by FXMLLoader

    Connection con;
    PreparedStatement pst;

    @FXML
    void dodelete(ActionEvent event) {
    	 Alert confirm=new Alert(AlertType.CONFIRMATION);
         confirm.setTitle("DELETING..");
         confirm.setContentText("R U sure?");
         Optional<ButtonType> res= confirm.showAndWait();
         if(res.get()==ButtonType.OK)
         {
         try
            {
             String name1=(tid.getText());
             PreparedStatement pst=  con.prepareStatement("delete from pattient where patientid=?");
     		pst.setString(1, name1);
     		  pst.executeUpdate();//fire query in table
     	      System.out.println("deleted");
     	   }
         catch (SQLException e)
     	{
     	    e.printStackTrace();
     	}
         }

    }

    @FXML
    void dodrop(ActionEvent event) throws SQLException {
    	pst=con.prepareStatement("drop table pattient");
    	pst.executeUpdate();
    	System.out.println("deleted table body");
    }

    @FXML
    void dofetch(ActionEvent event) throws SQLException
    {
    	String name1=tid.getText();
    	pst=con.prepareStatement("select * from pattient where patientid=?");
    	pst.setString(1,name1 );
    	ResultSet table=pst.executeQuery();
    	boolean jasus=false;

    	while(table.next())
    	{
    		jasus=true;
    		tcontact.setText(table.getString("contact"));
    		tage.setText(String.valueOf(table.getInt("age")));
    		tblood.setText(table.getString("bldgrup"));
    		tname.setText(table.getString("name"));
    		tdonor.setText(table.getString("donorid"));

    	}
    	if(jasus==false)
    	{
    		  Alert al=new Alert(AlertType.ERROR);
    		    al.setTitle("WRONG INPUTING");
    		    al.setContentText("FILL PROPER NAME");
    		    al.show();

    	}

    }

    @FXML
    void dosave(ActionEvent event) throws SQLException {
    	String name=tname.getText();
    	String patid=tid.getText();
    	String donid=tdonor.getText();
    	String mob=tcontact.getText();
    	String grp=tblood.getText();
        Integer age=Integer.parseInt(tage.getText());
    	PreparedStatement pst=  con.prepareStatement("insert into pattient values(?,?,?,?,?,?,?)");
        pst.setString(1,name);
        pst.setString(2, patid);
        pst.setString(3,donid);
        pst.setString(4,grp);
        pst.setInt(5,age);
        pst.setString(7,"india");
        pst.setString(6,mob);

        pst.executeUpdate();//fire query in table
        System.out.println("Saved");
    	 }



    @FXML
    void doupdate(ActionEvent event) throws SQLException
    {
    	String name=tname.getText();
    	String patid=tid.getText();
    	String donid=tdonor.getText();
    	String mob=tcontact.getText();
    	String grp=tblood.getText();
        Integer age=Integer.parseInt(tage.getText());
        PreparedStatement pst= con.prepareStatement("update pattient set name=?, donorid=?,bldgrup=?,age=?,contact=? where patientid=?");
        pst.setString(1,name);
        pst.setString(6, patid);
        pst.setString(2,donid);
        pst.setString(3,grp);
        pst.setInt(4,age);

        pst.setString(5,mob);

        pst.executeUpdate();//fire query in table
        System.out.println("updated");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	  con=DBconnection.doConnect();
        assert tname != null : "fx:id=\"tname\" was not injected: check your FXML file 'patview.fxml'.";
        assert tid != null : "fx:id=\"tid\" was not injected: check your FXML file 'patview.fxml'.";
        assert tdonor != null : "fx:id=\"tdonor\" was not injected: check your FXML file 'patview.fxml'.";
        assert tblood != null : "fx:id=\"tblood\" was not injected: check your FXML file 'patview.fxml'.";
        assert tage != null : "fx:id=\"tage\" was not injected: check your FXML file 'patview.fxml'.";
        assert tcontact != null : "fx:id=\"tcontact\" was not injected: check your FXML file 'patview.fxml'.";

    }
}
