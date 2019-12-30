/**
 * Sample Skeleton for 'fatview.fxml' Controller Class

 */

package choosefat;

import java.net.URL;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class fatviewController {
	Connection con;
    PreparedStatement pst;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    @FXML // fx:id="txt1"
    private TextField txt1; // Value injected by FXMLLoader

    @FXML // fx:id="txt2"
    private TextField txt2; // Value injected by FXMLLoader

    @FXML // fx:id="txt3"
    private TextField txt3; // Value injected by FXMLLoader

    @FXML // fx:id="txt4"
    private TextField txt4; // Value injected by FXMLLoader

    @FXML
    private RadioButton radio1;

    @FXML
    private RadioButton radio2;

    @FXML
    private RadioButton radio3;

    @FXML
    private RadioButton radio4;

    @FXML
    private TextField txtcowp;

    @FXML
    private TextField txtbuffp;


    @FXML
    private ToggleGroup d;
void gettext(String nme) throws SQLException
{
	{
 	   pst=con.prepareStatement("select * from  fattable where type=?");
		pst.setString(1, nme);
		ResultSet table=pst.executeQuery();
while(table.next())
{
	txt2.setText(table.getString("fat5"));
	txt3.setText(table.getString("fat7"));
	txt4.setText(table.getString("fat9"));
	txt1.setText(table.getString("fat4"));
}
    }
}
@FXML
void click(MouseEvent event) throws SQLException {


      String name;
   if(event.getClickCount()==1)
      {
          name=listmilk.getSelectionModel().getSelectedItem();
           if(name.equals("cow"))
           gettext(name);

           else
           gettext(name);

      }
    }
    @FXML
    void doset(ActionEvent event) throws SQLException
    {
    	String name;
    name=listmilk.getSelectionModel().getSelectedItem();
    	PreparedStatement pst=  con.prepareStatement("update fattable set fat4=?,fat5=?,fat7=?,fat9=? where type=?");
    pst.setString(5,name);
    pst.setString(1, txt1.getText());
    pst.setString(2, txt2.getText());
    pst.setString(3, txt3.getText());
    pst.setString(4, txt4.getText());
    pst.executeUpdate();//fire query in table
    System.out.println("updated");

    }

    @FXML // fx:id="listmilk"
    private ListView<String> listmilk; // Value injected by FXMLLoader


    @FXML
    private ListView<String> listnames;


    @FXML
    void DOFETCH(ActionEvent event)
    {
int index=listmilk.getSelectionModel().getSelectedIndex();


check(index);
    }

    @FXML
    void doupdate(ActionEvent event) throws SQLException
    {
    	ObservableList<String> name1=listnames.getSelectionModel().getSelectedItems();
    	pst=con.prepareStatement("update customerid set cowprice=?,buffaloprice=? where name=?");
    	for(String nam:name1)
    	{
    		pst.setString(3, nam);
    	pst.setFloat(1, Float.parseFloat(txtcowp.getText()));
    	pst.setFloat(2,Float.parseFloat(txtbuffp.getText()));
    	pst.executeUpdate();
    	System.out.println("updated again");
    	}

    }
    void check(int indx)
    {
    	if(indx==0)
    	{
    		if(radio1.isSelected())
    		{
    			txtcowp.setText(txt1.getText());
    		}
    		else if(radio2.isSelected())
    		{
    			txtcowp.setText(txt2.getText());
    		}
    		else if(radio3.isSelected())
    		{
    			txtcowp.setText(txt3.getText());
    		}
    		else
    		{
    			txtcowp.setText(txt4.getText());
    		}

    	}
    	else
    	{
    		if(radio1.isSelected())
    		{
    			txtbuffp.setText(txt1.getText());
    		}
    		else if(radio2.isSelected())
    		{
    			txtbuffp.setText(txt2.getText());
    		}
    		else if(radio3.isSelected())
    		{
    			txtbuffp.setText(txt3.getText());
    		}
    		else
    		{
    			txtbuffp.setText(txt4.getText());
    		}

    	}

    }
    @FXML
    void initialize() {
    	con=DBconnection.doConnect();
    	  listnames.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    	String s[]={"cow","buffalo"};
    	listmilk.getItems().addAll(s);
    	 try
         {
 			pst=con.prepareStatement("select name from customerid");
 			ResultSet table=pst.executeQuery();
 			while(table.next())
 			{
 		      String nme=table.getString("name");
 				listnames.getItems().addAll(nme);
 			}


         }
         catch (SQLException e)
         {

 			e.printStackTrace();
 		}
        assert txt1 != null : "fx:id=\"txt1\" was not injected: check your FXML file 'fatview.fxml'.";
        assert txt2 != null : "fx:id=\"txt2\" was not injected: check your FXML file 'fatview.fxml'.";
        assert txt3 != null : "fx:id=\"txt3\" was not injected: check your FXML file 'fatview.fxml'.";
        assert txt4 != null : "fx:id=\"txt4\" was not injected: check your FXML file 'fatview.fxml'.";
        assert listmilk != null : "fx:id=\"listmilk\" was not injected: check your FXML file 'fatview.fxml'.";

    }
}
