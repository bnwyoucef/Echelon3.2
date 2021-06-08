/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author BOUNOUA
 */
public class Mise_jour_EchelonController implements Initializable {

    @FXML
    private TableView<Employee> tableEchelon;
    @FXML
    private TableColumn<Employee, Number> nEchelon;
    @FXML
    private TableColumn<Employee, String> nomEchelon;
    @FXML
    private TableColumn<Employee, String> nPrénom;
    @FXML
    private TableColumn<Employee, String> nGrad;
    @FXML
    private TableColumn<Employee, Date> nDernier;
    @FXML
    private TextField modifEchelon;
    DbConnection connection = new DbConnection();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try { 
            connection.VisualiserEmployes(nEchelon, nomEchelon, nPrénom, nGrad, nDernier, tableEchelon, "admin");
        } catch (SQLException ex) {
            Logger.getLogger(Mise_jour_EchelonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void enrigistrer(ActionEvent event) throws SQLException {
        connection.MiseEchelon(modifEchelon, tableEchelon);
        connection.hisMiseechelon(tableEchelon, modifEchelon);
        connection.VisualiserEmployes(nEchelon, nomEchelon, nPrénom, nGrad, nDernier, tableEchelon, "admin");
    }

    @FXML
    private void clickedMouse(MouseEvent event) {
        connection.MouseEchelon(modifEchelon, tableEchelon);
    }
    
}
