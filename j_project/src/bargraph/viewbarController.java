/**
 * Sample Skeleton for 'viewbar.fxml' Controller Class

 */

package bargraph;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;


public class viewbarController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="chart"
    private BarChart<String, Float> chart; // Value injected by FXMLLoader
    @FXML // fx:id="s"
    private DatePicker s; // Value injected by FXMLLoader

    @FXML // fx:id="e"
    private DatePicker e; // Value injected by FXMLLoader

    Connection con;
    PreparedStatement pst;

    @FXML
    void doload(ActionEvent event) throws SQLException
    {

    	//pst=con.prepareStatement("select startdate=?,enddate=?,amount from billing ORDER BY startdate asc");
         pst=con.prepareStatement("select startdate,SUM(amount) from billing where startdate>=? and startdate<=? group by startdate");
         LocalDate local=s.getValue();
         java.sql.Date dostrt=  java.sql.Date.valueOf(local);
         LocalDate local1=e.getValue();
         java.sql.Date doend=  java.sql.Date.valueOf(local1);
         pst.setDate(1,dostrt);
         pst.setDate(2,doend);
    	XYChart.Series<String,Float> series=new XYChart.Series<>();
    	ResultSet table=pst.executeQuery();

    	boolean jasoos=false;
    	while(table.next())
    	{
    		jasoos=true;
    		series.getData().add(new XYChart.Data<>(String.valueOf(table.getDate("startdate")),table.getFloat("SUM(amount)")));


        }
    	if(jasoos==true)
    	{

    		chart.getData().add(series);
    	}
    	else
    	{
    		System.out.println("wrong inputing of Dates");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    	con=DBconnection.doConnect();

    	 assert chart != null : "fx:id=\"chart\" was not injected: check your FXML file 'viewbar.fxml'.";
         assert s != null : "fx:id=\"s\" was not injected: check your FXML file 'viewbar.fxml'.";
         assert e != null : "fx:id=\"e\" was not injected: check your FXML file 'viewbar.fxml'.";

    }
}
