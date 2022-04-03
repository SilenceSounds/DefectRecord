
package com.mycompany.defectregister;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Xan-T
 */
public class connectToDB {
    
    Connection conection;
    Statement state;
    int index = 0;
    
    public connectToDB(){
        Connection conection = null;
        Statement state = null;
    }
    
    public Connection connectDB(){
        
        
        try{
            String host = "ec2-54-173-77-184.compute-1.amazonaws.com";
            String database = "d6dmeu8vj1dvjr";
            String password= "f9b97c2d40c076e16b3a46dc0e28a2278ebd5c1160433cce0efa89bc7ca13fd3";
            String user = "pbplsdnrfnssvy";
            
            conection = DriverManager.getConnection("jdbc:postgresql://"+host+":5432/"+database,user,password);
            if(conection != null){
                System.out.println("Conexion exitosa");
                
            }
            
        } catch (Exception e){
            System.out.println("Error al conectar a la base." + e);
			e.printStackTrace();
        } finally {
            return conection;
        }

}
    
    public void Insert(String Type, String Autor, String Fase, String Des, String Coment){
        String query = "insert into defectos(tipo,autor,fase,descript,comentarios) "
                        + "values('"+Type+"','"+Autor+"','"+Fase+"','"+Des+"','"+Coment+"')";
        try {
            state=conection.createStatement();
            state.executeUpdate(query);
            System.out.println("Datos guardados");
        } catch (SQLException ex) {
            Logger.getLogger(connectToDB.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    public String[][] showData(){
        String sql = "Select * from defectos";
        String datos[][] = new String[100][5];
        Statement st;
        try{
            st = conection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                
                datos[index][0]=rs.getString(1);
                datos[index][1]=rs.getString(3);
                datos[index][2]=rs.getString(4);
                datos[index][3]=rs.getString(5);
                datos[index][4]=rs.getString(6);
                index++;
               
            }
        }catch(SQLException ex){
        }finally{
            return datos;
        }
           

    }
    



}


