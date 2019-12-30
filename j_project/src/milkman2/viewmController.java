/**
 * Sample Skeleton for 'viewm.fxml' Controller Class

 */

package milkman2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import connectiondb.DBconnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class viewmController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listcust"
    private ListView<String> listcust; // Value injected by FXMLLoader

    @FXML // fx:id="datestrt"
    private DatePicker datestrt; // Value injected by FXMLLoader

    @FXML // fx:id="dateend"
    private DatePicker dateend; // Value injected by FXMLLoader

    @FXML // fx:id="txtdays"
    private TextField txtdays; // Value injected by FXMLLoader

    @FXML // fx:id="txtcowqty"
    private TextField txtcowqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtbuffqty"
    private TextField txtbuffqty; // Value injected by FXMLLoader

    @FXML // fx:id="txtcowprice"
    private TextField txtcowprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtbuffprice"
    private TextField txtbuffprice; // Value injected by FXMLLoader

    @FXML // fx:id="txtcowvar"
    private TextField txtcowvar; // Value injected by FXMLLoader

    @FXML // fx:id="txtbuffvar"
    private TextField txtbuffvar; // Value injected by FXMLLoader

    @FXML // fx:id="txtamount"
    private TextField txtamount; // Value injected by FXMLLoader



    Connection con;
    PreparedStatement pst;
    LocalDate local;
    LocalDate local1;
    float cowqty;
    float cowprice;
    float buffqty;
    float buffprice;
    float sum;


    @FXML
    void dobill(ActionEvent event)
    {
        cowqty= (Float.parseFloat(txtcowqty.getText())* Float.parseFloat(txtdays.getText()))+Float.parseFloat(txtcowvar.getText());
        cowprice=cowqty* Float.parseFloat(txtcowprice.getText());

        buffqty= (Float.parseFloat(txtbuffqty.getText())* Float.parseFloat(txtdays.getText()))+Float.parseFloat(txtbuffvar.getText());
        buffprice=buffqty* Float.parseFloat(txtbuffprice.getText());
       sum=cowprice+buffprice;
       txtamount.setText(sum+"");
    }



    @FXML
    void dodays(ActionEvent event)
    {
    	String dob="";
    	  if(datestrt.getValue()==null)
          {
         dob=datestrt.getEditor().getText();
            local=LocalDate.parse(dob);
          }
          else
          {
            local=datestrt.getValue();
          }
    	  local1=dateend.getValue();
       	  long noofdays=ChronoUnit.DAYS.between(local, local1);
       	  noofdays+=1;
       	  txtdays.setText(noofdays+"");

    }

    @FXML
    void dodoubleclick(MouseEvent event) throws SQLException
    {
    	 if(event.getClickCount()==1)

         {
       String si=listcust.getSelectionModel().getSelectedItem();
        pst=con.prepareStatement("select cowqty,buffaloqty,cowprice,buffaloprice,dos from customerid where name=?");
        		pst.setString(1, si);
        		ResultSet table=pst.executeQuery();
        while(table.next())
        {
        	float cow=table.getFloat("cowqty");
        	float buff=table.getFloat("buffaloqty");
        	float cow1=table.getFloat("cowprice");
        	float buff1=table.getFloat("buffaloprice");
        	String datee=table.getString("dos");

        	txtcowqty.setText(String.valueOf(cow));
        	txtbuffqty.setText(String.valueOf(buff));
        	txtcowprice.setText(String.valueOf(cow1));
        	txtbuffprice.setText(String.valueOf(buff1));
        	datestrt.getEditor().setText(datee);
        }
        }

    }
boolean check(String s,java.sql.Date dob) throws SQLException
{
	PreparedStatement pst1;

	pst1=con.prepareStatement("select * from billing where name=? and startdate=?");
	pst1.setString(1,s);
	pst1.setDate(2,dob);
	ResultSet table=pst1.executeQuery();
	boolean jasoos=false;
    while(table.next())
    {
    	jasoos=true;
    }

	return jasoos;

}
    @FXML
    void dosave(ActionEvent event) throws SQLException
    {
    	   if( dateend.getValue()==null )
      	 {
      		 Alert al=new Alert(AlertType.ERROR);
  			    al.setTitle("All blocks  are compulsory to fill");
  			    al.setContentText("Fill the compulsory data");
  			    al.show();
      	 }
    	   else
    	   {

    	String si=listcust.getSelectionModel().getSelectedItem();
    	float f=(Float.parseFloat(txtcowqty.getText())* Float.parseFloat(txtdays.getText()))+Float.parseFloat(txtcowvar.getText());
    	float f1=(Float.parseFloat(txtbuffqty.getText())* Float.parseFloat(txtdays.getText()))+Float.parseFloat(txtbuffvar.getText());
    	String dob="";
  	  if(datestrt.getValue()==null)
        {
       dob=datestrt.getEditor().getText();
          local=LocalDate.parse(dob);
        }
        else
        {
          local=datestrt.getValue();
        }
  	      java.sql.Date dobb=  java.sql.Date.valueOf(local);

           local1=dateend.getValue();
    	java.sql.Date dob1=  java.sql.Date.valueOf(local1);
    	float amn=Float.parseFloat(txtamount.getText());

    boolean t	=check(si,dobb);//calling function

    if(t)
    {
    	Alert al=new Alert(AlertType.ERROR);
		    al.setTitle("already have data");
		    al.setContentText("Fill the unsaved data");
		    al.show();
    }
    else{

    	  TextInputDialog dialog = new TextInputDialog("");
          dialog.setTitle("Input Data...");
          dialog.setContentText("Please enter ur mobile number");

          // Traditional way to get the response value.
          Optional<String> result = dialog.showAndWait();

          smee(result,si,dobb,dob1,amn);

    	 try {

         	  PreparedStatement pst=  con.prepareStatement("insert into billing values (?,?,?,?,?,?,?,?,?)"); //in parameters
         	  pst.setDate(2,dobb);
              pst.setString(1, si);
              pst.setFloat(4,f);
              pst.setFloat(5,f1);
              pst.setDate(3, dob1);
              pst.setFloat(6,amn);
              pst.setInt(7,0);
              pst.setInt(8,0);
              pst.setFloat(9,0);
              pst.executeUpdate();

              System.out.println("Saved");




          }
          catch (SQLException e)
          {
              e.printStackTrace();
          }
    	  }
    	  }
    }
    void smee(Optional<String> result,String name,  java.sql.Date de,java.sql.Date df,float amn)
    {

    	 String resp=sms.SST_SMS.bceSunSoftSend((result.get()),("Hello "+name+"!"+"ur bill from:"+de+"to"+df+"is:"+amn));
         if(resp.contains("successfully"))
             System.out.println("Sent...");
     else
         if(resp.contains("Unknown"))
             System.out.println("Check Internet connection");
         else
             System.out.println("Invalid Mobile Number");

    }

    @FXML
    void doupdate(ActionEvent event) throws SQLException
    {
    	   if( dateend.getValue()==null )
        	 {
        		 Alert al=new Alert(AlertType.ERROR);
    			    al.setTitle("compulsory to fill end date and strt date");
    			    al.setContentText("Fill the compulsory data");
    			    al.show();
        	 }
    	   else
    	   {
    	 java.sql.Date strt=  java.sql.Date.valueOf(local);
         java.sql.Date end=  java.sql.Date.valueOf(local1);

         String all=listcust.getSelectionModel().getSelectedItem();

         PreparedStatement pst=  con.prepareStatement("update billing set enddate=?,totalqtycow=?,totalqtybuff=?,amount=? where name=? and startdate=?");
         pst.setFloat(2,cowqty);
         pst.setFloat(3,buffqty);
         pst.setFloat(4, sum);
    	 pst.setString(5,all);
    	 pst.setDate(6,strt);
    	 pst.setDate(1,end);
    	 pst.executeUpdate();
    	 System.out.println("updated");
    }
    }

    @FXML
    void dovariation(ActionEvent event) throws SQLException
    {
    	float sum=0;
    	float sum1=0;

        java.sql.Date strt=  java.sql.Date.valueOf(local);
        java.sql.Date end=  java.sql.Date.valueOf(local1);
        String all=listcust.getSelectionModel().getSelectedItem();
    	 pst=con.prepareStatement("select cmilkvari, bmilkvari from regularid where name=? and dvari>=? and dvari<=? ");
     //   pst=con.prepareStatement("select SUM(cmilkvari) as cq,Sum(bmilkvari) as bq from regularid where name=? and dvari>=? and dvari<=? ");
        pst.setString(1, all);
    	pst.setDate(2,strt);
    	pst.setDate(3,end);
    		ResultSet table=pst.executeQuery();
    		  while(table.next())
    		    {

    			  float cow=table.getFloat("cmilkvari");
    			  sum+=cow;
    			  float buffalo=table.getFloat("bmilkvari");
    			  sum1+=buffalo;
    			  //txtcowvar.setText(String.valueOf(table.getFloat("cq")));

    			  //txtbuffvar.setText(String.valueOf(table.getFloat("bq")));

    		    }

    		  txtcowvar.setText(String.valueOf(sum));
    		  txtbuffvar.setText(String.valueOf(sum1));
    }

    @FXML
    void dodelete(ActionEvent event)
    {

    		  if( dateend.getValue()==null )
         	 {
         		 Alert al=new Alert(AlertType.ERROR);
     			    al.setTitle("compulsory to fill end date and strt date");
     			    al.setContentText("Fill the compulsory data");
     			    al.show();
         	 }
    		  else
    		  {
    	Alert confirm=new Alert(AlertType.CONFIRMATION);
        confirm.setTitle("DELETING..");
        confirm.setContentText("R U sure?");
        Optional<ButtonType> res= confirm.showAndWait();
        if(res.get()==ButtonType.OK)
        {
        	try
           {
        		String dob="";
        	  	  if(datestrt.getValue()==null)
        	        {
        	       dob=datestrt.getEditor().getText();
        	          local=LocalDate.parse(dob);
        	        }
        	        else
        	        {
        	          local=datestrt.getValue();
        	        }
        	  	      java.sql.Date dobb=  java.sql.Date.valueOf(local);




        		   local1=dateend.getValue();
        	        java.sql.Date end = java.sql.Date.valueOf(local1);
        	        String all=listcust.getSelectionModel().getSelectedItem();
            PreparedStatement pst=  con.prepareStatement("delete from billing where name=? and startdate>=? and startdate<=?");
            pst.setString(1,all);
    		pst.setDate(2, dobb);
    	    pst.setDate(3,end);
    		  pst.executeUpdate();//fire query in table
    	      System.out.println("deleted");

    	    }

    	catch (SQLException e)
    	{
    	    e.printStackTrace();
    	}
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



    }
}
