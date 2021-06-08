package Projet.ClassJava;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

/**
 * FXML Controlleimport javafx.scene.control.TableView;
r class
 *
 * @author BOUNOUA
 */
public class Visualiser_OrdinaleController implements Initializable {

    @FXML
    private TableColumn<Employee, Number> NuméroVord;
    @FXML
    private TableColumn<Employee, String> NomVord;
    @FXML
    private TableColumn<Employee, String> PrénomVord;
    @FXML
    private TableColumn<Employee, String> GradVord;
    @FXML
    private TableColumn<Employee, Date> DernierEchelonVord;
    @FXML
    private Button imprimerVordinale;
    @FXML
    private TableView<Employee> tableVOrdinale;
    private DbConnection connection = new DbConnection();
    @FXML
    private DatePicker DatePicker;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableVOrdinale.setPlaceholder(new Label("Select a date please"));
    }    

    @FXML
    private void imprimerOrdinale(ActionEvent event) {
        //TODO: khalil
    }

    @FXML
    private void onChange(ActionEvent event) {
              try {
            connection.VisualiserEmployes(NuméroVord, NomVord, PrénomVord, GradVord, DernierEchelonVord, tableVOrdinale, "ordinale");
            
            tableVOrdinale.setRowFactory(tv -> new TableRow<Employee>(){
            @Override
            protected void updateItem(Employee item, boolean empty) {
                super.updateItem(item,empty);
                long millis=System.currentTimeMillis();  
                java.sql.Date date=new java.sql.Date(millis);  
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
                String strDate = dateFormat.format(date); 
                LocalDate date1 = LocalDate.parse(strDate);
              
                if (empty || item == null) {
                    setStyle("");
                } else if (((Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getYears() ==2)
                        &&(Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getMonths() >= 6))
                ){
                    setStyle("-fx-background-color: yellow");
                } else if(((Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getYears() ==3)
                        &&(Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getMonths() <= 6))){
                    setStyle("-fx-background-color: orange");
                }else if(((Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getYears() ==3)
                        &&(Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getMonths() > 6))
                        ||(Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), DatePicker.getValue()).getYears() > 3)){
                    setStyle("-fx-background-color: red");
                }else{
                    setStyle("");
                }
            }
            });           
        } catch (SQLException ex) {
            Logger.getLogger(Visualiser_OrdinaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
