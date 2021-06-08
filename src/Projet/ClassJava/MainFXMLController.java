package Projet.ClassJava;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author BOUNOUA
 */
public class MainFXMLController implements Initializable {
    
    private Button testSup;
    private Button testOrd;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button submitButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
           	   if (!usernameField.getText().matches("[a-zA-Z0-9_]{4,}")) {
               return;
           }
           if (passwordField.getText().isEmpty()) {
               return;
           }

           int status = userconnection.checkLogin(usernameField.getText().trim().toLowerCase(), passwordField.getText());

           switch (status) {
               case 0: {
            	   
                   String query ="";
            	   query = "SELECT admin FROM user WHERE username='"+usernameField.getText()+"'";
            	   DbConnection conn = new DbConnection();
            	   Connection connection = conn.getConnection();
            	   Statement st;
            	   ResultSet rs;
            	   st = connection.createStatement();
                   rs = st.executeQuery(query);
                   int test;
                   while(rs.next()){
                       test = rs.getInt("admin");
                       if(test == 1) {Stage stage = new Stage();
                       Parent root = FXMLLoader.load(getClass().getResource("/Projet/FXMLFile/AdminFXML.fxml"));
                       Stage window = (Stage) submitButton.getScene().getWindow();
                       window.setScene(new Scene(root,975,550)); 
                       
                       } 
                       
                   
                   else  {Stage stage = new Stage();
                   Parent root = FXMLLoader.load(getClass().getResource("/Projet/FXMLFile/AgentPersonnelFXML.fxml"));
                   Stage window = (Stage) submitButton.getScene().getWindow();
                   window.setScene(new Scene(root,950,550));  
                      
               } }
            	       
               }
               break;
               case -1:
                   JOptionPane.showMessageDialog(null, "Connection Failed");
                   break;
               case 1:
                   JOptionPane.showMessageDialog(null, "Username or password wrong");
                   break;
           }
       }

    @FXML
    private void exitProgramm(ActionEvent event) {
       System.exit(0);
    }}
    

