/**
 * Sample Skeleton for 'viewmilk.fxml' Controller Class
 */

package milkaman1;

import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class viewmilkController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listcust"
    private ListView<String> listcust; // Value injected by FXMLLoader

    @FXML // fx:id="cowqty"
    private TextField cowqty1; // Value injected by FXMLLoader

    @FXML // fx:id="buffaloqty"
    private TextField buffaloqty1; // Value injected by FXMLLoader

    @FXML // fx:id="vardate"
    private DatePicker vardate; // Value injected by FXMLLoader

    Connection con;
    PreparedStatement pst;

    @FXML
    void dodeleting(ActionEvent event)
    {

    	ObservableList<String>all=listcust.getSelectionModel().getSelectedItems();
    	/*ArrayList <String> list=new ArrayList<String>();
    	 list.addAll(all);
         listcust.getItems().clear();
    	 listcust.getItems().addAll(list);*/
         listcust.getItems().retainAll(all);
    }

    @FXML
    void dodelete1(ActionEvent event)
    {
    	LocalDate local=vardate.getValue();
        java.sql.Date dostrt=  java.sql.Date.valueOf(local);

    	String all=listcust.getSelectionModel().getSelectedItem();
    	 try {
    	 PreparedStatement pst=  con.prepareStatement("delete from regularid where name=? and dvari=?");

			pst.setString(1,all);

    	 pst.setDate(2,dostrt);
    	 pst.executeUpdate();//fire query in table
    	 }
    	 catch (SQLException e) {

 			e.printStackTrace();
 		}
	      System.out.println("deleted");

    }
String stat;
    @FXML
    void dodoubleclick(MouseEvent event) throws SQLException
    {
     if(event.getClickCount()==1)

     {
	String si=listcust.getSelectionModel().getSelectedItem();
    pst=con.prepareStatement("select cowqty,buffaloqty,status from customerid where name=?");
    		pst.setString(1, si);
    		ResultSet table=pst.executeQuery();
    while(table.next())
    {
    	float cow=table.getFloat("cowqty");
    	float buff=table.getFloat("buffaloqty");
        stat=table.getString("status");
    	cowqty1.setText(String.valueOf(cow));
    	buffaloqty1.setText(String.valueOf(buff));
    }
    }
    }
float f;
float f1;
    @FXML
    void dosaving(ActionEvent event)
    {
    	 if((cowqty1.getText().equals(""))||(buffaloqty1.getText().equals(""))||(vardate.getValue()==null))
    			 {
    		 Alert al=new Alert(AlertType.ERROR);
			    al.setTitle("All blocks are compulsory to fill");
			    al.setContentText("Fill the compulsory data");
			    al.show();
    			 }
    	 else
    	 {
    		    LocalDate local=vardate.getValue();
    	    	java.sql.Date dob=java.sql.Date.valueOf(local);
    	String si=listcust.getSelectionModel().getSelectedItem();
         if(stat.equals("0"))
         {
            	 f=Float.parseFloat(cowqty1.getText())*-1.0f;

            	 f1=Float.parseFloat(buffaloqty1.getText())*-1.0f;

        }
         else
         {
          f=Float.parseFloat(cowqty1.getText());
    	  f1=Float.parseFloat(buffaloqty1.getText());
         }
    	  try {

         	 PreparedStatement pst=  con.prepareStatement("insert into regularid values (?,?,?,?)"); //in parameters
         	  pst.setString(2, si);
              pst.setFloat(1, f);
              pst.setDate(4,dob);
              pst.setFloat(3, f1);
              pst.executeUpdate();

            listcust.getItems().removeAll(si);
              System.out.println("Saved");




          }
          catch (SQLException e)
          {
              e.printStackTrace();
          }

     	 }


    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	  listcust.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        con=DBconnection.doConnect();
        try
        {
			pst=con.prepareStatement("select name from customerid");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
		      String nme=table.getString("name");
				listcust.getItems().addAll(nme);
			}


        }
        catch (SQLException e)
        {

			e.printStackTrace();
		}

        assert listcust != null : "fx:id=\"listcust\" was not injected: check your FXML file 'viewmilk.fxml'.";
        assert cowqty1 != null : "fx:id=\"cowqty\" was not injected: check your FXML file 'viewmilk.fxml'.";
        assert buffaloqty1 != null : "fx:id=\"buffaloqty\" was not injected: check your FXML file 'viewmilk.fxml'.";
        assert vardate != null : "fx:id=\"vardate\" was not injected: check your FXML file 'viewmilk.fxml'.";

    }
}
