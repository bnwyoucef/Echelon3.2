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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author idwar
 */
public class RecharcheController implements Initializable {

    @FXML
    private TableView<Employee> tableVisualiser;
    @FXML
    private TableColumn<Employee, String> Nom;
    @FXML
    private TableColumn<Employee, String> Prénom;
    @FXML
    private TableColumn<Employee, String> Grad;
    @FXML
    private TableColumn<Employee, Date> DernierEchelon;
    @FXML
    private Button recharchbtn;
    @FXML
    private TextField textnom;
    @FXML
    private TextField textprenom;
     private DbConnection conn1 = new DbConnection();
     private connect_p2 conn2 = new connect_p2();
     private FxmlLoader loader = new FxmlLoader();
    @FXML
    private AnchorPane scene01;
    private BorderPane borderPane2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            conn1.EmployeVis( Nom, Prénom, Grad,DernierEchelon, tableVisualiser, "admin");
        } catch (SQLException ex) {
            Logger.getLogger(Modifier_employeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void recharch(ActionEvent event) throws SQLException {
           conn1.EmployeVis( Nom, Prénom, Grad,DernierEchelon, tableVisualiser, "admin");
                      
          if ((textnom.getText().isEmpty())^(textprenom.getText().isEmpty())){
        conn1.EmployeRecharche(Nom, Prénom, Grad, DernierEchelon, tableVisualiser, textnom, textprenom,"or");
        }  
         if ((!textnom.getText().isEmpty())&&(!textprenom.getText().isEmpty())){
        conn1.EmployeRecharche(Nom, Prénom, Grad, DernierEchelon, tableVisualiser, textnom, textprenom,"and");
        } 
        
    }
    @FXML
     private void mouseClickedEmp(MouseEvent event) {
       passer.data = conn1.MouseClickedrecharch( tableVisualiser);
     
     AnchorPane pane =  (AnchorPane) loader.getPage("historiqueDeEchlo");
       scene01.getChildren().setAll(pane);
            
      }

    @FXML
    private void reload(ActionEvent event) throws SQLException {
         conn1.EmployeVis( Nom, Prénom, Grad,DernierEchelon, tableVisualiser, "admin");
    }
}
