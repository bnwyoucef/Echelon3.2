/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.ClassJava;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
import java.text.DateFormat;  
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import javafx.scene.control.DatePicker;
/////////////////////////


/**
 *
 * @author idwar
 */
public class connect_p2 {
  
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
		    
		    public ObservableList<echelon> getechelonList(TextField id_EMP) throws SQLException{
		        ObservableList<echelon> list = FXCollections.observableArrayList();
		        Connection connection = getConnection();
		        String query = "Select * from historique where Id = "+id_EMP.getText();
		        Statement st;
		        ResultSet rs;    
		        try{
		            st = connection.createStatement();
		            rs = st.executeQuery(query);
		            echelon echelon;
		            while(rs.next()){
		                echelon = new echelon(rs.getInt("Id"),rs.getInt("num_echelon"),rs.getDate("date_echelon")); 
		                list.add(echelon);
		            }
		        }catch(SQLException ex){
		            JOptionPane.showMessageDialog(null, "getechelonList(): " + ex);
		        }
		        return list;
		    }
		    public void showechelons(TableColumn<echelon, Integer>num,TableColumn<echelon,Date> ladate ,TableView<echelon> tableView,TextField id_EMP)throws SQLException{
	                 ObservableList<echelon> List = getechelonList(id_EMP); 		             
		               num.setCellValueFactory(new  PropertyValueFactory<echelon,Integer>("num_echelon"));
		              
		                ladate.setCellValueFactory(new PropertyValueFactory<echelon,Date>("date_echelon"));
		               
                                tableView.setItems(List);
                                 
		            }
                    
		    
    ///************************************************************************************************
    
       public void InsertRecord(TextField txtId,TextField txtNum,DatePicker sdate) throws SQLException, ParseException{
           String query= "";
           int x;
           Date d;
           LocalDate u2  = sdate.getValue();
           String d1 = u2.toString();
           LocalDate locald = LocalDate.now();
             Date sqld =null;
             sqld=Date.valueOf(locald);
           Date  sqlsd=Date.valueOf(u2);
             
             if(sqlsd.compareTo(sqld) <= 0){
                 
           query = "Select MAX(num_echelon) as maximum from historique where Id = "+txtId.getText();
           x=excuteQueryWithResult(query);            
           query = "Select date_echelon as ndate from historique where Id = "+txtId.getText()+" and num_echelon= "+x;
           d = excuteQuerygetDATE(query);
           LocalDate u1 = d.toLocalDate();
           
           if( ChronoUnit.MONTHS.between(u1,u2 )>30){
           x=x+1;
           if (x<=12){
           query = "INSERT INTO historique (Id, num_echelon ,date_echelon) values ('"+txtId.getText()+"','"+x+"','"+ d1+"')";
            excuteQuery(query);
            query = "UPDATE employeeposts SET DernierEchelon= '" + d1 + "' WHERE Numéro= "+ txtId.getText();
        excuteQuery(query);
        }
           }  
           }
          
    }
       ///////////////////////////////////////////////////////////////////////////////// 
    
          public void UpdateRecord(TextField txtId,TextField txtNum,DatePicker sdate) throws SQLException{
          int x;
          LocalDate d1 = sdate.getValue();
          String d2 = d1.toString();
    String query = "UPDATE historique SET date_echelon= '"+ d2 + " 'WHERE Id= '"+ txtId.getText()+"' and num_echelon= "+txtNum.getText();
           excuteQuery(query);
           query = "Select MAX(num_echelon) as maximum from historique where Id = "+txtId.getText();
           x=excuteQueryWithResult(query);
           if (x==Integer.parseInt(txtNum.getText())){
           query = "UPDATE employeeposts SET DernierEchelon= '" + d2 + "' WHERE Numéro= "+ txtId.getText();
        excuteQuery(query);
           }
    }
       //////////////////////////////////////////////////////////////////////////////////
    public void DeleteRecord(TextField txtId) throws SQLException{
      int x;
     Date d;
     
         String  query = "Select MAX(num_echelon) as maximum from historique where Id = "+txtId.getText();
           x=excuteQueryWithResult(query);
         if (x!=1){
             query = "DELETE FROM historique WHERE Id= "+txtId.getText()+" and num_echelon = "+x;//ما تخربش يا حيوان
         excuteQuery(query);
             x=x-1;
             query = "Select date_echelon as ndate from historique where Id = "+txtId.getText()+" and num_echelon= "+x;
             d = excuteQuerygetDATE(query);   
             LocalDate u1 =d.toLocalDate();
             String u2 = u1.toString();
            query = "UPDATE employeeposts SET DernierEchelon= '"+u2+"' WHERE Numéro= "+ txtId.getText();
          
       excuteQuery(query);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public String getnom(TextField txtId) throws SQLException{
        String query;
        String str;
        query = "Select Nom as nom from employeeposts where Numéro = "+txtId.getText();
        return excuteQuerygetsname( query);
        
        
    }
    public String getprenom(TextField txtId) throws SQLException{
        String query;
        String str;
        
        
        query = "Select Prénom as nom from employeeposts where Numéro = "+txtId.getText();
        return excuteQuerygetsname( query);
    }
//***********************************************************************************************************************
    private void excuteQuery(String query) throws SQLException {
        Connection connect = getConnection();
        Statement st;
        try{
            st = connect.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            System.out.print(ex.getMessage().toString());
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
        
         private int excuteQueryWithResult(String query) throws SQLException {
        Connection connect = getConnection();
        Statement st;
        int x = 0;
        try{
            st = connect.createStatement();
            ResultSet rset = st.executeQuery(query);
            while(rset.next()) {   // Repeatedly process each row
              x = rset.getInt("maximum");       // retrieve a 'int'-cell in the row
         }
        }catch(Exception ex){
            System.out.println(ex.getMessage().toString());
            JOptionPane.showMessageDialog(null, ex);
        }
         return x;
         
}
         private Date excuteQuerygetDATE(String query) throws SQLException {
        Connection connect = getConnection();
        Statement st;
        Date x = new Date(2000,01,01);
        
        try{
            st = connect.createStatement();
            ResultSet rset = st.executeQuery(query);
            while(rset.next()) {   // Repeatedly process each row
              x = rset.getDate("ndate");       // retrieve a 'int'-cell in the row
         }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
         return x;
         
}
         private String excuteQuerygetsname(String query) throws SQLException {
        Connection connect = getConnection();
        Statement st;
        String x;
        x="none";
        try{
            st = connect.createStatement();
            ResultSet rset = st.executeQuery(query);
            while(rset.next()) {   // Repeatedly process each row
              x = rset.getString("nom");       // retrieve a 'int'-cell in the row
         }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
         return x;
         
}
    public void MouseClicked(TextField txtNum,DatePicker sdate,TableView<echelon> tableView) {
        txtNum.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getNum_echelon()));
      //  txtNum.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getNum_echelon()));
       // txtDate.setText(String.valueOf(tableView.getSelectionModel().getSelectedItem().getDate_echelon()));  
        sdate.setValue(tableView.getSelectionModel().getSelectedItem().getDate_echelon().toLocalDate());
        
    }
    
}
