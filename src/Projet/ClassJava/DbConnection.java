
package Projet.ClassJava;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author BOUNOUA
 */
public class DbConnection {
    
    public Connection getConnection(){
        Connection connection;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/echelon","root","");
            return connection;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Somthing wrong in data base connection" + ex);
            return null;
        }
    }
    
    public ObservableList<Employee> getEmployeesList(String employeePost) throws SQLException{
        ObservableList<Employee> list = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "";
        if(employeePost.equalsIgnoreCase("Sup")){
            query = "Select * from employeeposts WHERE PosteSupérieur =1 ORDER BY grade";
        }else if(employeePost.equalsIgnoreCase("ordinale")){
             query = "Select * From employeeposts WHERE PosteSupérieur =0 ORDER BY grade";
        }else if(employeePost.equalsIgnoreCase("admin")){
            query ="Select * From employeeposts";
        }else if(employeePost.equalsIgnoreCase("historique")){
            query = "Select * from historique";
        }
        
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Employee employee;
            if(employeePost.equals("Sup") || employeePost.equals("ordinale") || employeePost.equals("admin")){
                while(rs.next()){
                    employee = new Employee(rs.getInt("Numéro"),rs.getString("Nom"),rs.getString("Prénom"),rs.getString("Grade"),rs.getDate("DernierEchelon"));
                    list.add(employee);
            }
            }else if(employeePost.equals("historique")){
                while(rs.next()){
                employee = new Employee(rs.getInt("Numéro"), rs.getString("Nom"), rs.getString("Prénom"), rs.getDate("dateEchelon"), rs.getInt("EchelonNumber"));
                list.add(employee);
            }  
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "getEmployeeList(): " + ex);
        }
        
        return list;
    }
        
    
    public void VisualiserEmployes( TableColumn<Employee, Number> Nmbr,TableColumn<Employee, String> Nom,TableColumn<Employee, String> Prénom,
        TableColumn<Employee, String> Grad,TableColumn<Employee, Date> DernierEchelon,TableView<Employee> tableView,String employeePost) 
        throws SQLException{
            ObservableList<Employee> empList = getEmployeesList(employeePost); 
            Nmbr.setSortable(false);
            Nmbr.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(tableView.getItems().indexOf(column.getValue())+1));
            //Nmbr.setCellValueFactory(new PropertyValueFactory<Employee,Number>("id"));
            Nom.setCellValueFactory(new PropertyValueFactory<Employee,String>("nom"));
            Prénom.setCellValueFactory(new PropertyValueFactory<Employee,String>("prénom"));
            Grad.setCellValueFactory(new PropertyValueFactory<Employee,String>("Grad"));
            DernierEchelon.setCellValueFactory(new PropertyValueFactory<Employee,Date>("dernier_echelon"));
            tableView.setItems(empList);
            
        }
    
       public void modifierEmployes( TableColumn<Employee, Integer> Nmbr,TableColumn<Employee, String> Nom,TableColumn<Employee, String> Prénom,
        TableColumn<Employee, String> Grad,TableView<Employee> tableView,String employeePost) 
        throws SQLException{
            ObservableList<Employee> empList = getEmployeesList(employeePost); 
            Nmbr.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
            Nom.setCellValueFactory(new PropertyValueFactory<Employee,String>("nom"));
            Prénom.setCellValueFactory(new PropertyValueFactory<Employee,String>("prénom"));
            Grad.setCellValueFactory(new PropertyValueFactory<Employee,String>("Grad"));
            tableView.setItems(empList);
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
    
       public void AjouterEmployee(TextField txtNom,TextField txtPrenom,String txtGrad,TextField txtDernier,int post) throws SQLException{
           String query= "";
           int number = getEmployeesList("admin").size() +1;
           if(post ==0){
               query = "INSERT INTO employeeposts VALUES(" +number +",'"+ txtNom.getText() +
                "','" + txtPrenom.getText() + "','" + txtGrad +"','" + txtDernier.getText() + "',"+ 0+ ")";
           }else {
               query = "INSERT INTO employeeposts VALUES(" +number +",'"+ txtNom.getText() +
                "','" + txtPrenom.getText() + "','" + txtGrad +"','" + txtDernier.getText() + "',"+ 1+ ")";
           }
       
        excuteQuery(query);
       }
        ///// هاذي لي تزيد الترقية الاولى/////
       public void ajouterfirstechelon(TextField txtDernier) throws SQLException{
           int number = getEmployeesList("admin").size();
       String query = "INSERT INTO historique (Id, num_echelon ,date_echelon) values ('"
               +number+"','"+1+"','"+ txtDernier.getText()+"')";
        excuteQuery(query);
       }
       
        public void hisMiseechelon(TableView<Employee> table,TextField txtDernier){
            String query = "INSERT INTO historique (Id, num_echelon ,date_echelon) values ('"+ table.getSelectionModel().getSelectedItem().getId()
                    +"','"+1+"','"+ txtDernier.getText()+"')";
            excuteQuery(query);
       }
       
      public void miseHistorique(TextField txtId, TextField nom,TextField prenom, int numEchelon, TextField date) throws SQLException {
          int number =0;
          for(Employee emp : getEmployeesList("historique")){
              if(emp.getId() == Integer.parseInt(txtId.getText())){
                  if(emp.getHistorique() > number){
                      number = emp.getHistorique();
                  }
              }
          }
          number++;
          String query = "INSERT INTO historique VALUES("+txtId.getText() +",'"+ nom.getText() +"','"+ prenom.getText()+"'," + number +",'" + date.getText() +"')";
          excuteQuery(query);
      }
    
     public void UpdateEmployee(TableView<Employee> table,TextField txtNom,TextField txtPrenom,String txtGrad,TextField txtDernier) throws SQLException{
        String query = "UPDATE employeeposts SET Nom= '" + txtNom.getText() + "', Prénom = '" + txtPrenom.getText()+"',Grade='"+
                txtGrad + "',DernierEchelon='" + txtDernier.getText() + "' WHERE Numéro= "
                + table.getSelectionModel().getSelectedItem().getId();
        excuteQuery(query);
    }
    public void MiseEchelon(TextField txtDernier, TableView<Employee> tableView) throws SQLException{
        String query = "UPDATE employeeposts SET DernierEchelon= '" + txtDernier.getText() + "' WHERE Numéro= "+ 
                tableView.getSelectionModel().getSelectedItem().getId();
        excuteQuery(query);   
    }
    
    public void suprimerEmployee(TableView<Employee> table) throws SQLException{
        String query = "DELETE FROM employeeposts WHERE Numéro= "+table.getSelectionModel().getSelectedItem().getId();
        excuteQuery(query);
    }
    //////هاذي تحذف سجل الترقيات للموظف //////
     
    public void suprimerechelons(TextField txtNum) throws SQLException{
        String query = "DELETE FROM historique WHERE Id = "+txtNum.getText();
        excuteQuery(query);
    }
    ////////////
    public void MouseClicked(TextField txtNom,TextField txtPrenom,TextField txtDernier,TableView<Employee> tableView) {
        //txtNum.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getId()));
        txtNom.setText(tableView.getSelectionModel().getSelectedItem().getNom());
        txtPrenom.setText(tableView.getSelectionModel().getSelectedItem().getPrénom());
        //txtGrad.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getGrad()));
        txtDernier.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getDernier_echelon()));
       
    }
    
    public void MouseEchelon (TextField field, TableView<Employee> table) {
        field.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getDernier_echelon()));
    }
    
     public void historiqueEchelons( TableColumn<Employee, Integer> Nmbr,TableColumn<Employee, String> Nom,TableColumn<Employee, String> Prénom,
        TableColumn<Employee, Integer> numEchelon,TableColumn<Employee, Date> DernierEchelon,TableView<Employee> tableView) 
        throws SQLException{
            ObservableList<Employee> empList = getEmployeesList("historique"); 
            Nmbr.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
            Nom.setCellValueFactory(new PropertyValueFactory<Employee,String>("nom"));
            Prénom.setCellValueFactory(new PropertyValueFactory<Employee,String>("prénom"));
            numEchelon.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("historique"));
            DernierEchelon.setCellValueFactory(new PropertyValueFactory<Employee,Date>("dernier_echelon"));
            tableView.setItems(empList);
        }
}
