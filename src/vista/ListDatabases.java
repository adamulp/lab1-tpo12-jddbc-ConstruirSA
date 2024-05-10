/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.sql.*;

public class ListDatabases {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver"); // Change this to your database's driver
            
            // Connect to localhost with appropriate credentials
            conn = DriverManager.getConnection("jdbc:mysql://localhost/", "root", ""); // Change URL, username, and password
            
            // Get metadata
            DatabaseMetaData metaData = conn.getMetaData();
            
            // Retrieve the list of databases
            ResultSet resultSet = metaData.getCatalogs();
            
            // Print the list of databases
            System.out.println("Databases on localhost:");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("TABLE_CAT"));
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
    }
}

