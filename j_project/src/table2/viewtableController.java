/**
 * Sample Skeleton for 'viewtable.fxml' Controller Class
 */

package table2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import connectiondb.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import table.Studentbean;

public class viewtableController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl1"
    private TableView<STUDENTBEAM> tbl1; // Value injected by FXMLLoader

    @FXML // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader

    @FXML // fx:id="txtsea"
    private TextField txtsea; // Value injected by FXMLLoader

    @FXML
    private DatePicker strt;

    @FXML
    private DatePicker end;

    Connection con;
    PreparedStatement  pst;
    LocalDate local;
    LocalDate local1;
    @FXML
    void dofetchdate(ActionEvent event)
    {
    	 local=strt.getValue();
     	java.sql.Date dob=  java.sql.Date.valueOf(local);
      local1=end.getValue();
     	java.sql.Date dob1=  java.sql.Date.valueOf(local1);

    	try {

            PreparedStatement  pst=con.prepareStatement("select * from regularid where dvari>=? and dvari<=?");
            pst.setDate(1,dob);
        	pst.setDate(2,dob1);
            fetchAllRecords(pst);

           tbl1.setItems(list1);
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
             new FileChooser.ExtensionFilter("All Files", "*.*")

                );
             File file=chooser.showSaveDialog(null);
            String filePath=file.getAbsolutePath();

            if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
            {
                filePath=filePath+".csv";
            }
             file = new File(filePath);



            writer = new BufferedWriter(new FileWriter(file));
            String text="variationofcowmilk,name,variationinbuffmilk,dateofvariation\n";
            writer.write(text);
            for (STUDENTBEAM p : list1)
            {
                text = p. getCvary()+ "," + p.getName()+ "," + p. getBvary() + "," + p.getDvary()+ "\n";
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
    void dofetch1(ActionEvent event)
    {

    	try {

            PreparedStatement  pst=con.prepareStatement("select * from regularid");

            fetchAllRecords(pst);

           tbl1.setItems(list1);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }

    }

    @FXML
    void doclicky(ActionEvent event) {



         	try {

                PreparedStatement  pst=con.prepareStatement("select * from regularid where name=?");
           pst.setString(1, combo.getSelectionModel().getSelectedItem());
                fetchAllRecords(pst);

               tbl1.setItems(list1);
           }
            catch (SQLException e)
            {

               e.printStackTrace();
           }

    }


    void fetch()
    {
    	  TableColumn<STUDENTBEAM, Float> cvary=new TableColumn<STUDENTBEAM, Float>("cowmilk vary qty");//Dikhava Title
          cvary.setCellValueFactory(new PropertyValueFactory<>("cvary"));


     	  TableColumn<STUDENTBEAM, String> name=new TableColumn<STUDENTBEAM, String>("name");//Dikhava Title
          name.setCellValueFactory(new PropertyValueFactory<>("name"));


          TableColumn<STUDENTBEAM, Float> bvary=new TableColumn<STUDENTBEAM, Float>("buffalomilk vary qty");//Dikhava Title
          bvary.setCellValueFactory(new PropertyValueFactory<>("bvary"));

          TableColumn<STUDENTBEAM, String> datevary=new TableColumn<STUDENTBEAM, String>(" dates of variations");//Dikhava Title
          datevary.setCellValueFactory(new PropertyValueFactory<>("dvary"));

          tbl1.getColumns().clear();
          tbl1.getColumns().addAll(cvary,name,bvary,datevary);

    }
    ObservableList<STUDENTBEAM> list1;

    void fetchAllRecords(PreparedStatement pst) throws SQLException
    {
    	 list1=FXCollections.observableArrayList();
              ResultSet table= pst.executeQuery();

                 while(table.next())
                 {

                 	float cqtyvary=table.getFloat("cmilkvari");
                 	 String nme=table.getString("name");

                 	float bqtyvary=table.getFloat("bmilkvari");

                 	String date=table.getString("dvari");
                 	 STUDENTBEAM sb1=new STUDENTBEAM(cqtyvary,nme,bqtyvary,date);
                     list1.add(sb1);

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

fetch();




        assert tbl1 != null : "fx:id=\"tbl1\" was not injected: check your FXML file 'viewtable.fxml'.";

    }
}
