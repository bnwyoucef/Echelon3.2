package Projet.ClassJava;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BOUNOUA
 */
public class Modifier_employeeController implements Initializable {

    @FXML
    private TableView<Employee> tableModifierEmployee;
    @FXML
    private TableColumn<Employee, Integer> numéroEmployee;
    @FXML
    private TableColumn<Employee, String> nomEmployee;
    @FXML
    private TableColumn<Employee, String> prénomEmployee;
    @FXML
    private TableColumn<Employee, String> gradEmployee;
    private DbConnection connection = new DbConnection();
    @FXML
    private TextField txtfieldNom;
    @FXML
    private TextField txtFiledPrenom;
    @FXML
    private Button AjouterEmpBtn;
    @FXML
    private Button SuprimerEmpBtn;
    @FXML
    private Button ModifierEmpBtn;
    @FXML
    private RadioButton rbSup;
    @FXML
    private ToggleGroup rbgroup;
    @FXML
    private RadioButton rbOrd;
    @FXML
    private TextField txtFieldDernierExh;
    private Echelon2 echlon = new Echelon2();
    @FXML
    private ComboBox<String> comboBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            connection.modifierEmployes(numéroEmployee, nomEmployee, prénomEmployee, gradEmployee, tableModifierEmployee, "admin");
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_employeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> items = comboBox.getItems();
        items.add("Professeur");
        items.add("Maitre de conférences de classe A");
        items.add("Maitre de conférences de classe B");
        items.add("Maitre assistant de classe A");
        items.add("Maitre assistant de classe B");
        
    }    

    @FXML
    private void mouseClickedEmp(MouseEvent event) {
        connection.MouseClicked(txtfieldNom, txtFiledPrenom,txtFieldDernierExh, tableModifierEmployee);
    }

    @FXML
    private void buttonsClicked(ActionEvent event) throws SQLException {
        String gradSelected = comboBox.valueProperty().get();
        if(event.getSource().equals(ModifierEmpBtn)){
            if(rbSup.isSelected()){
                connection.suprimerEmployee(tableModifierEmployee);
                connection.AjouterEmployee(txtfieldNom, txtFiledPrenom, gradSelected, txtFieldDernierExh, 1);
                connection.modifierEmployes(numéroEmployee, nomEmployee, prénomEmployee, gradEmployee, tableModifierEmployee,"admin");
             //   echlon.miseAjourEchelon(txtFieldDernierExh, txtFieldNum,txtfieldNom,txtFiledPrenom);

            }else{
                connection.UpdateEmployee(tableModifierEmployee, txtfieldNom, txtFiledPrenom, gradSelected, txtFieldDernierExh);
                connection.modifierEmployes(numéroEmployee, nomEmployee, prénomEmployee, gradEmployee, tableModifierEmployee,"admin");
               //  echlon.miseAjourEchelon(txtFieldDernierExh, txtFieldNum,txtfieldNom,txtFiledPrenom);
            }
        }else if(event.getSource().equals(SuprimerEmpBtn)){
            connection.suprimerEmployee(tableModifierEmployee);
            ////حذف سجل  الترقيات الموظف////
            //connection.suprimerechelons(txtFieldNum);
            connection.modifierEmployes(numéroEmployee, nomEmployee, prénomEmployee, gradEmployee, tableModifierEmployee,"admin");
        }else if(event.getSource().equals(AjouterEmpBtn)){
            if(rbSup.isSelected()){
                connection.AjouterEmployee(txtfieldNom, txtFiledPrenom, gradSelected, txtFieldDernierExh, 1);
                connection.modifierEmployes(numéroEmployee, nomEmployee, prénomEmployee, gradEmployee, tableModifierEmployee,"admin");
                // echlon.miseAjourEchelon(txtFieldDernierExh, txtFieldNum,txtfieldNom,txtFiledPrenom);
                connection.ajouterfirstechelon(txtFieldDernierExh);
            }else if(rbOrd.isSelected()){
                connection.AjouterEmployee(txtfieldNom, txtFiledPrenom, gradSelected, txtFieldDernierExh, 0);
                connection.modifierEmployes(numéroEmployee, nomEmployee, prénomEmployee, gradEmployee, tableModifierEmployee,"admin");
                // echlon.miseAjourEchelon(txtFieldDernierExh, txtFieldNum,txtfieldNom,txtFiledPrenom);
                connection.ajouterfirstechelon(txtFieldDernierExh);
            }
        }
    }

    
}
