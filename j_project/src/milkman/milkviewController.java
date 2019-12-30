/**
 * Sample Skeleton for 'milkview.fxml' Controller Class
 */
package milkman;
import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class milkviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader

    @FXML // fx:id="txtaddress"
    private TextField txtaddress; // Value injected by FXMLLoader

    @FXML // fx:id="txtmobile"
    private TextField txtmobile; // Value injected by FXMLLoader

    @FXML // fx:id="cqty"
    private TextField cqty; // Value injected by FXMLLoader

    @FXML // fx:id="bqty"
    private TextField bqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtstatus"
    private TextField txtstatus; // Value injected by FXMLLoader

    @FXML // fx:id="strtpicker"
    private DatePicker strtpicker; // Value injected by FXMLLoader

    @FXML // fx:id="imageview"
    private ImageView imageview; // Value injected by FXMLLoader

    Connection con;
    PreparedStatement pst;
    String imi;

    @FXML
    void dobrowse(ActionEvent event) throws MalformedURLException
    {
        FileChooser chooser=new FileChooser();
    	chooser.setTitle("open file");
    	File file=chooser.showOpenDialog(null);
    	chooser.getExtensionFilters().addAll(new ExtensionFilter("Images","*.bmp","*.png","*.jpg","*.gif"));
    	if(file!=null)
    	{
    		String imagepath=file.getPath();
    		imi=imagepath;
    		Image image=new Image(file.toURI().toURL().toString()); //uniform resource identifier-method

    		imageview.setImage(image);
        }
    }

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
            String name1=(txtname.getText());
            PreparedStatement pst=  con.prepareStatement("delete from customerid where name=?");
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
    private Rectangle rect;

    @FXML
    void dofetch(ActionEvent event) throws SQLException
    {
    	icoimg.setVisible(false);
String name1=txtname.getText();
pst=con.prepareStatement("select * from customerid where name=?");
pst.setString(1,name1);
ResultSet table=pst.executeQuery();
boolean jasus=false;

while(table.next())
{
	jasus=true;
	String mob=table.getString("mobile");
	String add=table.getString("address");
	float cqty1=table.getFloat("cowqty");
	float bqty1=table.getFloat("buffaloqty");

	String date=table.getString("dos");

	int stat=table.getInt("status");
	String imo=table.getString("image");
	 File file=new File(imo);
	 String imagepath;
	 try {
			imagepath = file.toURI().toURL().toString();


 		Image image=new Image(imagepath);
 		imageview.setImage(image);

	      }
	 catch (MalformedURLException e)
	      {

			e.printStackTrace();
		  }
txtaddress.setText(add);
txtmobile.setText(mob);
icoimg.setVisible(true);
cqty.setText(String.valueOf(cqty1));
bqty.setText(String.valueOf(bqty1));


strtpicker.getEditor().setText(date);

txtstatus.setText(String.valueOf(stat));

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
    private ImageView icoimg;

    @FXML
    void dosave(ActionEvent event) throws SQLException
    {

     if((txtname.getText().equals("")) || (cqty.getText().equals(""))||(bqty.getText().equals(""))|| strtpicker.getValue()==null )
    	 {
    		    Alert al=new Alert(AlertType.ERROR);
			    al.setTitle("All blocks  are compulsory to fill");
			    al.setContentText("Fill the compulsory data");
			    al.show();
    	 }
    	 else if(txtmobile.getText().length()!=10)
     	{
                icoimg.setVisible(false);
     		    Alert al=new Alert(AlertType.ERROR);
 			    al.setTitle("check ur mobile digits");
 			    al.setContentText("Fill the proper mobile number");
 			    al.show();
     	}

    	 else
    	 {

    	String name=txtname.getText();
    	String mob=txtmobile.getText();

    	String add=txtaddress.getText();
    	float cowq=Float.parseFloat(cqty.getText());
    	float bufq=Float.parseFloat(bqty.getText());



    	String imii=imi;


       LocalDate local=strtpicker.getValue();
        java.sql.Date dostrt=  java.sql.Date.valueOf(local);

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Input Data...");
        dialog.setContentText("Please enter ur mobile number");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        smee(result,name,dostrt);

        PreparedStatement pst=  con.prepareStatement("insert into customerid values(?,?,?,?,?,?,?,?,?,?)");
        pst.setString(1,name);
        pst.setString(2, mob);
        pst.setString(3, add);
        pst.setFloat(4, cowq);
        pst.setFloat(5, bufq);
        pst.setFloat(6, 0);
        pst.setFloat(7, 0);
        pst.setDate(8,dostrt);
        pst.setString(9,"1");
        pst.setString(10,imii);


        pst.executeUpdate();//fire query in table
        System.out.println("Saved");
    	 }
    }



    void smee(Optional<String> result,String name,  java.sql.Date de)
    {

    	 String resp=sms.SST_SMS.bceSunSoftSend((result.get()),("Hello "+name+"!"+"  u join are ferm on :"+de+"thanku for joining") );
         if(resp.contains("successfully"))
             System.out.println("Sent...");
     else
         if(resp.contains("Unknown"))
             System.out.println("Check Internet connection");
         else
             System.out.println("Invalid Mobile Number");

    }




    @FXML
    void dopressed(KeyEvent event)  //key released event
    {
      int k=txtmobile.getCaretPosition();
      if(k>0)
      {
      char ch =txtmobile.getText().charAt(k-1);
      check1(ch,k-1);
      }
      check();  //check valdation for mobile function
    }
    void check1(char l,int o)
    {
    	  if (!(l >= 48 && l <= 57))
    	  {
    	       String p=txtmobile.getText().substring(0,o);


    	        txtmobile.setText(p);
                txtmobile.positionCaret(o);
    		    Alert al=new Alert(AlertType.ERROR);
			    al.setTitle("please use only 0-9 digits");
			    al.setContentText("wrong input");
			    al.show();
    	  }


    }

   void check()
    {

    	if(txtmobile.getText().length()==10)
    	{
    		icoimg.setVisible(true);
    	}
    	else
    	{
    		icoimg.setVisible(false);
    	}
    }





     @FXML
    void doupdate(ActionEvent event) throws SQLException
    {
	     if(((txtstatus.getText().equals(""))||txtname.getText().equals("")) || (cqty.getText().equals(""))||(bqty.getText().equals("")) )
    	 {
    		 Alert al=new Alert(AlertType.ERROR);
			    al.setTitle("All blocks  are compulsory to fill");
			    al.setContentText("Fill the compulsory data");
			    al.show();
    	 }
	     else if(txtmobile.getText().length()!=10)
	     	{
	                icoimg.setVisible(false);
	     		    Alert al=new Alert(AlertType.ERROR);
	 			    al.setTitle("check ur mobile digits");
	 			    al.setContentText("Fill the proper mobile number");
	 			    al.show();
	     	}
	     else{
        String name=txtname.getText();
    	String mob=txtmobile.getText();
    	String add=txtaddress.getText();
    	float cowq=Float.parseFloat(cqty.getText());
    	float bufq=Float.parseFloat(bqty.getText());

    	int sts=Integer.parseInt(txtstatus.getText());
    	//String imii=imi;

    	Date ndob =picker();


    PreparedStatement pst= con.prepareStatement("update customerid set mobile=?,address=?,cowqty=?,buffaloqty=?,dos=?,status=? where name=?");
    pst.setString(7,name);
    pst.setString(1, mob);
    pst.setString(2, add);
    pst.setFloat(3, cowq);
    pst.setFloat(4, bufq);


    pst.setDate(5,ndob);

    pst.setInt(6,sts);
    //pst.setString(7, imii);

    pst.executeUpdate();//fire query in table
    System.out.println("updated");
	     }
    }

    Date picker()
    {
    	LocalDate local;
    	String dob="";
        	  if(strtpicker.getValue()==null)  //if chooses without datepicker
              {
             dob=strtpicker.getEditor().getText();
                local=LocalDate.parse(dob);
              }
              else   //chooses from date picker for update starting date
              {
                local=strtpicker.getValue();
              }

        	  java.sql.Date ndob=java.sql.Date.valueOf(local);
return ndob;

   }


    @FXML // This method is called by the FXMLLoader when initialization is complete

    void initialize() throws SQLException
    {

    	con=DBconnection.doConnect();


    }
}
