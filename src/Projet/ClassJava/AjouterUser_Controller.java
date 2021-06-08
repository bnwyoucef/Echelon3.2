package Projet.ClassJava;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class AjouterUser_Controller implements Initializable{

    @FXML
    private TableView<user> tableAjouter_user;

    @FXML
    private TableColumn<user, String> username;

    @FXML
    private TableColumn<user, String> password;

    @FXML
    private TableColumn<user, Boolean> admin;

    @FXML
    private TextField txtFieldusername;

    @FXML
    private TextField txtfieldpassword;

    @FXML
    private Button AjouterusrBtn;

    @FXML
    private RadioButton rbSup;

    @FXML
    private ToggleGroup rbgroup;
    @FXML
    private Button supusrBtn;
    @FXML
    private RadioButton rbOrd;
    userconnection usercnct = new userconnection();
    private userconnection usershow = new userconnection();

    @FXML
    void buttonsClicked(ActionEvent event) throws SQLException {
            if(rbSup.isSelected()){
            	usercnct.Ajouteruser( txtFieldusername, txtfieldpassword,  true);
            	
            }else if(rbOrd.isSelected()){
            	usercnct.Ajouteruser( txtFieldusername, txtfieldpassword,  false);
            	

            }
            usershow.Visualiseruser(username, password, admin, tableAjouter_user, null);
        	}
    
   @Override
public void initialize(URL arg0, ResourceBundle arg1) {
	   try {
		usershow.Visualiseruser(username, password, admin, tableAjouter_user, null);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
   @FXML
   void buttonsClickedsuprm(ActionEvent event) throws SQLException {
	   String query ="";
	   query = "SELECT admin FROM user ";
	   DbConnection conn = new DbConnection();
	   Connection connection = conn.getConnection();
	   Statement st;
	   ResultSet rs;
	   st = connection.createStatement();
       rs = st.executeQuery(query);
       int test=0;
       while(rs.next()){
           test = test+rs.getInt("admin");
           
       
       } if(test > 1) {
	   usercnct.suprimeruser(txtFieldusername);
	   usershow.Visualiseruser(username, password, admin, tableAjouter_user, null);

        }else {
	   JOptionPane.showMessageDialog(null, "youcan not delete");
        }
       
   
   }
    @FXML
   void MouseClicked(MouseEvent event) {
	   usercnct.MouseClicked(txtFieldusername, txtfieldpassword, tableAjouter_user);

   }
 
 
   
   
  }
    
