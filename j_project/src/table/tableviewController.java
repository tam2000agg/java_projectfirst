/**
 * Sample Skeleton for 'tableview.fxml' Controller Class
 */

package table;
import connectiondb.DBconnection;





import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class tableviewController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;


    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl"
    private TableView<Studentbean> tbl; // Value injected by FXMLLoader

    @FXML // fx:id="discount"
    private ToggleGroup discount; // Value injected by FXMLLoader

    @FXML
    private DatePicker dtpkr;


    Connection con;
    PreparedStatement  pst;
    @FXML // fx:id="txtname"
    private TextField txtname; // Value injected by FXMLLoader

    @FXML // fx:id="radioc"
    private RadioButton radioc; // Value injected by FXMLLoader

    @FXML // fx:id="radiob"
    private RadioButton radiob; // Value injected by FXMLLoader

    @FXML // fx:id="radioboth"
    private RadioButton radioboth; // Value injected by FXMLLoader


    @FXML
    void doboth(MouseEvent event)
    {
    	try {

            PreparedStatement  pst=con.prepareStatement("select * from customerid where cowqty!=? and buffaloqty!=?");
            pst.setFloat(1,0);
            pst.setFloat(2, 0);

            fetchAllRecords(pst);

           tbl.setItems(list);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }


    }
    @FXML
    void dobuffmilk(MouseEvent event)
    {
    	try {

            PreparedStatement  pst=con.prepareStatement("select * from customerid where cowqty=?");
            pst.setFloat(1,0);

            fetchAllRecords(pst);

           tbl.setItems(list);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }


    }
    @FXML
    void docowmilk(MouseEvent event)
    {
    	try {

            PreparedStatement  pst=con.prepareStatement("select * from customerid where buffaloqty=?");
            pst.setFloat(1,0);


            fetchAllRecords(pst);

           tbl.setItems(list);
       }

        catch (SQLException e)
        {

           e.printStackTrace();
       }
    }


      @FXML
    void dodrag(ActionEvent event)
    {
    	try {

            PreparedStatement  pst=con.prepareStatement("select * from customerid where name like ?");
            pst.setString(1, "%"+txtname.getText()+"%");

            fetchAllRecords(pst);

           tbl.setItems(list);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }


    }

    @FXML
    void dofet(ActionEvent event)
   {
String d1=dtpkr.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    try {

            PreparedStatement  pst=con.prepareStatement("select * from customerid where dos=?");
            pst.setString(1,(d1));

            fetchAllRecords(pst);

           tbl.setItems(list);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }

  }

  @FXML
   void doexport(ActionEvent event)
   {
	  try {
          writeExcel();
      } catch (Exception e) {

          e.printStackTrace();
      }

   }
  public void writeExcel() throws Exception {
      Writer writer = null;
      try {
          FileChooser chooser=new FileChooser();

          chooser.setTitle("Select Path:");

          chooser.getExtensionFilters().addAll(
           new FileChooser.ExtensionFilter("All Files", "*.*"));


           File file=chooser.showSaveDialog(null);
           String filePath=file.getAbsolutePath();
          int indx;

          indx=filePath.indexOf(".");
          if(indx!=-1)
          {
           String sub=filePath.substring(0,indx);
           filePath=sub;
          }
           if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
          {
              filePath=filePath+".csv";
          }
           file = new File(filePath);



          writer = new BufferedWriter(new FileWriter(file));
          String text="name,mobile,address,cquantity,bquantity,cprice,bprice,dateofstart,status,image\n";
          writer.write(text);
          for (Studentbean p : list)
          {
              text = p. getName()+ "," + p.getMob()+ "," + p.getAdd() + "," + p.getCq()+ ","+p.getBq()+","+ p.getCp()+","+ p.getBp()+","+p.getDos()+","+p.getStatus()+","+p.getImage()+"\n";
              writer.write(text);
          }
      } catch (Exception ex) {
          ex.printStackTrace();
      }
      finally {

          writer.flush();
           writer.close();
      }
  }

	@FXML
    void dofetch(ActionEvent event)
    {
		try {

            PreparedStatement  pst=con.prepareStatement("select * from customerid");

            fetchAllRecords(pst);

           tbl.setItems(list);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }

    }

	void fetch(){
		 TableColumn<Studentbean, String> name=new TableColumn<Studentbean, String>("name");
         name.setCellValueFactory(new PropertyValueFactory<>("name"));

         TableColumn<Studentbean, String> mob=new TableColumn<Studentbean, String>("mobile no");//Dikhava Title
         mob.setCellValueFactory(new PropertyValueFactory<>("mob"));

         TableColumn<Studentbean, String> add1=new TableColumn<Studentbean, String>("address");//Dikhava Title
         add1.setCellValueFactory(new PropertyValueFactory<>("add"));

         TableColumn<Studentbean, Float> cq=new TableColumn<Studentbean, Float>("cowmilk qty");//Dikhava Title
         cq.setCellValueFactory(new PropertyValueFactory<>("cq"));

         TableColumn<Studentbean, Float> bq=new TableColumn<Studentbean, Float>("buffmilk qty");//Dikhava Title
         bq.setCellValueFactory(new PropertyValueFactory<>("bq"));


         TableColumn<Studentbean, Float> cp=new TableColumn<Studentbean, Float>("cowmilk price");//Dikhava Title
         cp.setCellValueFactory(new PropertyValueFactory<>("cp"));

         TableColumn<Studentbean, Float> bp=new TableColumn<Studentbean, Float>("buffmilk price");//Dikhava Title
         bp.setCellValueFactory(new PropertyValueFactory<>("bp"));

         TableColumn<Studentbean, String> datestrt=new TableColumn<Studentbean, String>(" dateofstrt");//Dikhava Title
         datestrt.setCellValueFactory(new PropertyValueFactory<>("dos"));



         TableColumn<Studentbean, Integer> stat=new TableColumn<Studentbean, Integer>("status");//Dikhava Title
         stat.setCellValueFactory(new PropertyValueFactory<>("status"));//bean field name, no link with table col name

         TableColumn<Studentbean, String> img=new TableColumn<Studentbean, String>(" image");//Dikhava Title
         img.setCellValueFactory(new PropertyValueFactory<>("image"));

         tbl.getColumns().clear();
         tbl.getColumns().addAll(name,mob,add1,cq,bq,cp,bp,datestrt,stat,img);

	}

    ObservableList<Studentbean> list;


    void fetchAllRecords(PreparedStatement pst) throws SQLException
    {
        list=FXCollections.observableArrayList();


            ResultSet table= pst.executeQuery();

                while(table.next())
                {
                	String nme=table.getString("name");
                	String mob=table.getString("mobile");
                	String add=table.getString("address");
                	float cqty1=table.getFloat("cowqty");
                	float bqty1=table.getFloat("buffaloqty");
                	float cprice1=table.getFloat("cowprice");
                	float bprice1=table.getFloat("buffaloprice");
                	String date=table.getString("dos");

                	int stat=table.getInt("status");
                	String imo=table.getString("image");
                	 Studentbean sb=new Studentbean(nme,mob,add,cqty1,bqty1,cprice1,bprice1,date,stat,imo);
                     list.add(sb);

                }


    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 con=DBconnection.doConnect();

        fetch();
        assert tbl != null : "fx:id=\"tbl\" was not injected: check your FXML file 'tableview.fxml'.";

    }
}
