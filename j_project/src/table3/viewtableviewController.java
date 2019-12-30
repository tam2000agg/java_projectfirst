/**
 * Sample Skeleton for 'viewtableview.fxml' Controller Class

 */

package table3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import connectiondb.DBconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import table2.STUDENTBEAM;


public class viewtableviewController

{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tbl3"
    private TableView<StudentBean> tbl3; // Value injected by FXMLLoader

    Connection con;
    PreparedStatement  pst;

    @FXML
    void dopaid(MouseEvent event)
    {
    	 try{

             PreparedStatement  pst=con.prepareStatement("select * from billing where status=?");
             pst.setInt(1,1);
             fetchAllRecords3(pst);
             tbl3.setItems(list2);
           }
            catch(Exception ex)
           {
                ex.printStackTrace();
           }

    }
    @FXML
    private ToggleGroup discount;
    @FXML
    void dopending(MouseEvent event)
    {
    	 try{

             PreparedStatement  pst=con.prepareStatement("select * from billing where status=0");

             fetchAllRecords3(pst);
             tbl3.setItems(list2);
                   }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }

    }
    @FXML
    private ComboBox<String> combo;

    @FXML
    void docombo(ActionEvent event)
    {
    	try {

            PreparedStatement  pst=con.prepareStatement("select * from billing where name=?");
       pst.setString(1, combo.getSelectionModel().getSelectedItem());
            fetchAllRecords3(pst);

           tbl3.setItems(list2);
       }
        catch (SQLException e)
        {

           e.printStackTrace();
       }


    }




    @FXML
    void dofetch2(ActionEvent event)
    {

        try{

         PreparedStatement  pst=con.prepareStatement("select * from billing");

         fetchAllRecords3(pst);
         tbl3.setItems(list2);
               }
        catch(Exception ex)
        {
            ex.printStackTrace();
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
            String text="name,stardate,enddate,totalqtyofcowmilk,totalqtyofbuffmilk,totalamount,status,pending,grandtotal\n";
            writer.write(text);
            for (StudentBean p : list2)
            {
                text = p. getName()+ "," + p. getStrtdate()+ "," + p. getEnddate() + "," + p.getTqc()+ ","+ p.getTqb()+ ","+p. getAmn() + ","+p. getStat()+","+p.getPending()+","+p.getGrandtotal()+"\n";
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

    void fetch()
    {

  	  TableColumn<StudentBean, String> name=new TableColumn<StudentBean, String>("name");//Dikhava Title
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<StudentBean, String> datestrt=new TableColumn<StudentBean, String>(" startdate");//Dikhava Title
        datestrt.setCellValueFactory(new PropertyValueFactory<>("strtdate"));

        TableColumn<StudentBean, String> dateend=new TableColumn<StudentBean, String>("enddate");//Dikhava Title
        dateend.setCellValueFactory(new PropertyValueFactory<>("enddate"));

        TableColumn<StudentBean, Float> tcq=new TableColumn<StudentBean, Float>("total milk of cow");//Dikhava Title
        tcq.setCellValueFactory(new PropertyValueFactory<>("tqc"));

        TableColumn<StudentBean, Float> tbq=new TableColumn<StudentBean, Float>("total milk of buff");//Dikhava Title
        tbq.setCellValueFactory(new PropertyValueFactory<>("tqb"));

        TableColumn<StudentBean, Float> amnt=new TableColumn<StudentBean, Float>("total amount");//Dikhava Title
        amnt.setCellValueFactory(new PropertyValueFactory<>("amn"));

        TableColumn<StudentBean, Integer> stat=new TableColumn<StudentBean, Integer>("payment recieved");//Dikhava Title
        stat.setCellValueFactory(new PropertyValueFactory<>("stat"));
        TableColumn<StudentBean, Float> pen=new TableColumn<StudentBean, Float>("pending money");//Dikhava Title
        pen.setCellValueFactory(new PropertyValueFactory<>("pending"));

        TableColumn<StudentBean, Float> gt=new TableColumn<StudentBean, Float>("grand total");//Dikhava Title
        gt.setCellValueFactory(new PropertyValueFactory<>("grandtotal"));

        tbl3.getColumns().clear();
        tbl3.getColumns().addAll(name,datestrt,dateend,tcq,tbq,amnt,stat,pen,gt);


    }
    ObservableList<StudentBean> list2;

    void fetchAllRecords3(PreparedStatement pst) throws SQLException
    {
    	 list2=FXCollections.observableArrayList();

          ResultSet table= pst.executeQuery();

              while(table.next())
              {
            	String nme=table.getString("name");
              	String date=table.getString("startdate");
              	String date1=table.getString("enddate");

              	float totalc=table.getFloat("totalqtycow");
              	float totalb=table.getFloat("totalqtybuff");

              	float amnt=table.getFloat("amount");


              	int stat=table.getInt("status");
              	float pen=table.getFloat("pending");
              	float gt=table.getFloat("grandtotal");
              	 StudentBean sb2=new StudentBean(nme,date,date1,totalc,totalb,amnt,stat,pen,gt);
                 list2.add(sb2);

              }

         }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	 con=DBconnection.doConnect();
    	 try
         {
 			pst=con.prepareStatement("select name from billing");
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

        assert tbl3 != null : "fx:id=\"tbl3\" was not injected: check your FXML file 'viewtableview.fxml'.";

    }
}
