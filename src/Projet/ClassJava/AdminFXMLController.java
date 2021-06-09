/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author BOUNOUA
 */
public class AdminFXMLController implements Initializable {

    @FXML
    private BorderPane borderPane2;
    private Button miseAjourBtn;
    @FXML
    private Button historiqueBtn;
    @FXML
    private Button modifierBtn;
    @FXML
    private Button AjouterAgentBtn;
    @FXML
    private Button ExitBtn;
    private FxmlLoader loader = new FxmlLoader();
    private double xoffset =0;
    private double yoffset =0;
    @FXML
    private Button miseJour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void hundleActionButton(ActionEvent event) throws IOException {
       if(event.getSource().equals(historiqueBtn)){
            Pane view = loader.getPage("recharche");
            borderPane2.setCenter(view);
        }else if(event.getSource().equals(modifierBtn)){
            Pane view = loader.getPage("Modifier_employee");
            borderPane2.setCenter(view);
        }else if(event.getSource().equals(AjouterAgentBtn)){
            Pane view = loader.getPage("Ajouter_user");
            borderPane2.setCenter(view);
        }else if(event.getSource().equals(miseJour)) {
            Pane view = loader.getPage("Mise_jour_Echelon");
            borderPane2.setCenter(view);        
        }else if(event.getSource().equals(ExitBtn)){
            Alert alert  =  new Alert(AlertType.CONFIRMATION);
            alert.setTitle("exit");
            alert.setHeaderText("you are  about to logout ");
            alert.setContentText(" are you sure !");
            if (alert.showAndWait().get()== ButtonType.OK) {
                Parent root = FXMLLoader.load(getClass().getResource("/Projet/FXMLFile/MainFXML.fxml"));
                Stage window = (Stage) ExitBtn.getScene().getWindow();
                Scene scene = new Scene(root,975,550);
                scene.setFill(Color.TRANSPARENT);
                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xoffset = event.getSceneX();
                        yoffset = event.getSceneY();
                        }
                     });
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                         @Override
                         public void handle(MouseEvent event) {
                             window.setX(event.getScreenX() - xoffset);
                             window.setY(event.getScreenY() - yoffset);
                         }
                     });
                 window.setScene(scene); 
                 
                 
         } 
        }
        
    }
    
}
