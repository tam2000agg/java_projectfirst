package donor;
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

public class donorviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txt_name"
    private TextField txt_name; // Value injected by FXMLLoader

    @FXML // fx:id="txt_id"
    private TextField txt_id; // Value injected by FXMLLoader

    @FXML // fx:id="txt_bloodgrup"
    private TextField txt_bloodgrup; // Value injected by FXMLLoader

    @FXML // fx:id="txt_age"
    private TextField txt_age; // Value injected by FXMLLoader

    @FXML // fx:id="txt_contact"
    private TextField txt_contact; // Value injected by FXMLLoader

    @FXML // fx:id="txt_o"
    private TextField txt_o; // Value injected by FXMLLoader

    @FXML // fx:id="txt_a"
    private TextField txt_a; // Value injected by FXMLLoader

    @FXML // fx:id="txt_b"
    private TextField txt_b; // Value injected by FXMLLoader

    @FXML // fx:id="txt_ab"
    private TextField txt_ab; // Value injected by FXMLLoader



    Connection con;

    PreparedStatement pst;
    PreparedStatement pst1;



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
             String name1=(txt_id.getText());
             PreparedStatement pst=  con.prepareStatement("delete from donorr where id=?");
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
    	pst=con.prepareStatement("drop table donorr");
    	pst.executeUpdate();
    	System.out.println("deleted table body");
    }
    @FXML
    void doblood(ActionEvent event) throws SQLException
    {
    	 pst=con.prepareStatement("select bldgrup,COUNT(bldgrup)from donorr group by bldgrup");
    	 ResultSet table=pst.executeQuery();
    		while(table.next())
        	{
    			if(table.getString("bldgrup").equals("o+"))
    			{
    		pst=con.prepareStatement("select COUNT(bldgrup) from donorr where bldgrup=?");
    		pst.setString(1, "o+");
    		txt_o.setText(table.getString("COUNT(bldgrup)"));
    			}
    			else if(table.getString("bldgrup").equals("a+"))
    			{
        	pst1=con.prepareStatement("select COUNT(bldgrup) from donorr where bldgrup=?");
        	pst1.setString(1, "a+");
        	txt_a.setText(table.getString("COUNT(bldgrup)"));
        	    }

    			else if(table.getString("bldgrup").equals("b+"))
    			{
        	pst1=con.prepareStatement("select COUNT(bldgrup) from donorr where bldgrup=?");
        	pst1.setString(1, "b+");
        	txt_b.setText(table.getString("COUNT(bldgrup)"));
        	    }
    			else
    			{
    				pst1=con.prepareStatement("select COUNT(bldgrup) from donorr where bldgrup=?");
    	        	pst1.setString(1, "ab+");
    	        	txt_ab.setText(table.getString("COUNT(bldgrup)"));
    			}



        	}
    }

    @FXML
    void dofetch(ActionEvent event) throws SQLException
    {
    	String name1=txt_id.getText();
    	pst=con.prepareStatement("select * from donorr where id=?");
    	pst.setString(1,name1 );
    	ResultSet table=pst.executeQuery();
    	boolean jasus=false;

    	while(table.next())
    	{
    		jasus=true;
    		txt_contact.setText(table.getString("contact"));
    		txt_age.setText(String.valueOf(table.getInt("age")));
    		txt_bloodgrup.setText(table.getString("bldgrup"));
    		txt_name.setText(table.getString("name"));

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
    	String name=txt_name.getText();
    	String mob=txt_contact.getText();

    	String id=txt_id.getText();
    	String grp=txt_bloodgrup.getText();
    	Integer age=Integer.parseInt(txt_age.getText());
    	PreparedStatement pst=  con.prepareStatement("insert into donorr values(?,?,?,?,?,?)");
        pst.setString(1,name);
        pst.setString(2, id);
        pst.setString(3,grp);
        pst.setInt(4,age);
        pst.setString(5,mob);
        pst.setString(6,"india");


        pst.executeUpdate();//fire query in table
        System.out.println("Saved");
    	 }


    @FXML
    void doupdate(ActionEvent event) throws SQLException
    {
    	String name=txt_name.getText();
    	String mob=txt_contact.getText();

    	String id=txt_id.getText();
    	String grp=txt_bloodgrup.getText();
    	Integer age=Integer.parseInt(txt_age.getText());
    	PreparedStatement pst= con.prepareStatement("update donorr set name=?, bldgrup=?,age=?,contact=? where id=?");
    	 pst.setString(1,name);
         pst.setString(5, id);
         pst.setString(2,grp);
         pst.setInt(3,age);
         pst.setString(4,mob);
         pst.executeUpdate();//fire query in table
         System.out.println("updated");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	con=DBconnection.doConnect();
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'donorview.fxml'.";
        assert txt_id != null : "fx:id=\"txt_id\" was not injected: check your FXML file 'donorview.fxml'.";
        assert txt_bloodgrup != null : "fx:id=\"txt_bloodgrup\" was not injected: check your FXML file 'donorview.fxml'.";
        assert txt_age != null : "fx:id=\"txt_age\" was not injected: check your FXML file 'donorview.fxml'.";
        assert txt_contact != null : "fx:id=\"txt_contact\" was not injected: check your FXML file 'donorview.fxml'.";

    }
}
