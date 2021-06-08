package Projet.ClassJava;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author yahoui
 */
public class userconnection {

		    
		    public static Connection getConnection(){
		        Connection connection;
		        try{
		            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/echelon","root","");
		            return connection;
		        }catch(SQLException ex){
		            JOptionPane.showMessageDialog(null, "Somthing wrong in data base connection" + ex);
		            return null;
		        }
		    }
		    
		    public ObservableList<user> getuserList(String usr) throws SQLException{
		        ObservableList<user> list = FXCollections.observableArrayList();
		        Connection connection = getConnection();
		        String query = "Select * from user ";
		      
		        
		        Statement st;
		        ResultSet rs;
		        
		        try{
		            st = connection.createStatement();
		            rs = st.executeQuery(query);
		            user user;
		            while(rs.next()){
		                user = new user(rs.getString("username"),rs.getString("password"),rs.getBoolean("admin"));
		                list.add(user);
		            }
		        }catch(SQLException ex){
		            JOptionPane.showMessageDialog(null, "getuserList(): " + ex);
		        }
		        
		        return list;
		    }
		    public void Visualiseruser(TableColumn<user, String>username,TableColumn<user, String>password,
		            TableColumn<user,Boolean> admin ,TableView<user> tableView,String user) 
		             throws SQLException{
	                 ObservableList<user> usList = getuserList(user); 		             
		                username.setCellValueFactory(new PropertyValueFactory<user,String>("username"));
		                password.setCellValueFactory(new PropertyValueFactory<user,String>("password"));
		                admin.setCellValueFactory(new PropertyValueFactory<user,Boolean>("admin"));
		                tableView.setItems(usList);
		            }
		        
		     
		        
		        private void excuteQuery(String query){
		            Connection connection = getConnection();
		            Statement st;
		            try{
		                st = connection.createStatement();
		                st.executeUpdate(query);
		            }catch(SQLException ex){
		                JOptionPane.showMessageDialog(null, ex);
		            } 
		                   
		        }
		        
		           public void Ajouteruser(TextField txtFieldusername,TextField txtfieldpassword,Boolean post) throws SQLException{
		               String query= "";
		               if(post ==false){
		                   query = "INSERT INTO user (username, password ,admin) values ('"+txtFieldusername.getText()+"','"+ txtfieldpassword.getText()+"','"+0+"')";
		               }else {
		            	   query = "INSERT INTO user (username, password ,admin) values ('"+txtFieldusername.getText()+"','"+ txtfieldpassword.getText()+"','"+1+"')";
		               }
		           
		            excuteQuery(query);
		        }
		           public void suprimeruser(TextField txtNum) throws SQLException{
		               String query = "DELETE FROM user WHERE username = '"+txtNum.getText()+"'";
		               excuteQuery(query);
		           }
		           public void MouseClicked(TextField txtNom,TextField txtPrenom,TableView<user> tableView) {
		               txtNom.setText(tableView.getSelectionModel().getSelectedItem().getUsername());
		               txtPrenom.setText(tableView.getSelectionModel().getSelectedItem().getPassword());
		          
		           }
		           public static int checkLogin(String username, String password) {
		               Connection connect = userconnection.getConnection();
		               if(connect == null)
		                   return -1;
		               
		               String sql = "SELECT * FROM user WHERE username=? AND password=?";
		               try {
		                   PreparedStatement prest = connect.prepareStatement(sql);
		                   prest.setString(1, username);
		                   prest.setString(2, password);
		                   
		                   ResultSet rs = prest.executeQuery();
		                   
		                   while(rs.next()) {
		                       return 0;
		                   }
		                   
		               } catch(SQLException se) {
		                   //se.printStackTrace();
		                   System.out.println("SQL Error !");
		               }
		               
		               return 1;
		           }
		           
		       
		        
		          

				
}

