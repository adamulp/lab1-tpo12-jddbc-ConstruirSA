package vista;

import java.sql.*;
import java.util.ArrayList;


public class ListarBD {
    public static void main(String[] args) {
        ArrayList<String> listaBD = listarBD(
        "org.mariadb.jdbc.Driver",
        "jdbc:mariadb://localhost:3306/",
        "root",
        ""        
        );
        
        System.out.println(listaBD);
        
    }
    
    public static ArrayList<String> listarBD(String driver, String url, String usuario, String clave) {
        Connection conn = null;
        ArrayList<String> listaBD = new ArrayList<>();
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, clave);
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getCatalogs();
            
            // Print the list of databases
            System.out.println("Databases on host:");
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_CAT");
                listaBD.add(tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaBD;
    }
    
}

