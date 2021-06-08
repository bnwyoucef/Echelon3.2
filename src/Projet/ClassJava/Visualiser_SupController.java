package Projet.ClassJava;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
/**
 * FXML Controller class
 *
 * @author BOUNOUA
 */
public class Visualiser_SupController implements Initializable {

    @FXML
    private TableView<Employee> tableVisualiserSup;
    @FXML
    private TableColumn<Employee, Number> NuméroVsup;
    @FXML
    private TableColumn<Employee, String> NomVsup;
    @FXML
    private TableColumn<Employee, String> PrénomVsup;
    @FXML
    private TableColumn<Employee, String> GradVsup;
    @FXML
    private TableColumn<Employee, Date> DernierEchelonVsup;
    @FXML
    private Button imprimerVsup;
    private DbConnection conncection = new DbConnection();
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       tableVisualiserSup.setPlaceholder(new Label("Select a date please"));
    }    

    @FXML
    private void ImpimerPosts(ActionEvent event) {
        //TODO: khalil
          
    }

    @FXML
    private void dpClciked(ActionEvent event) {   
        try {
           conncection.VisualiserEmployes(NuméroVsup, NomVsup, PrénomVsup, GradVsup, DernierEchelonVsup, tableVisualiserSup, "Sup");
           tableVisualiserSup.setRowFactory(tv -> new TableRow<Employee>(){
                @Override
                protected void updateItem(Employee item, boolean empty) {
                    super.updateItem(item,empty);  
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  

                if (empty || item == null) {
                    setStyle("");
                } else if (((Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), datePicker.getValue()).getYears() ==2)
                        &&(Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), datePicker.getValue()).getMonths() >= 6))
                        ||(Period.between(LocalDate.parse(dateFormat.format(item.getDernier_echelon())), datePicker.getValue()).getYears() > 2)){
                    setStyle("-fx-background-color: red");
                } else {
                    setStyle("");
                }
            }
            });
                   
        } catch (SQLException ex) {
            Logger.getLogger(Visualiser_SupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
      
}
