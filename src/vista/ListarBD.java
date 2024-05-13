package vista;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.GestorDeArchivoEnv;


public class ListarBD {
    private Connection conexion; // lo que importa

    public ListarBD() {
        this.conexion = null;
    }

    public ListarBD(Connection conexion) {
        this.conexion = conexion;
    }
    
    public ListarBD(String url, String usuario, String password){
        this();
        this.conexion = buscarConexion(url, usuario, password);
        
    }
    
    
    
    public static void main(String[] args) {
//        ArrayList<String> listaBD = listarBD(
//        "org.mariadb.jdbc.Driver",
//        "jdbc:mariadb://localhost:3306/",
//        "root",
//        ""        
//        );
    GestorDeArchivoEnv credenciales = new GestorDeArchivoEnv();
    credenciales.ingresarDatos();
    ArrayList<String> listaBD = listarBD(
        "org.mariadb.jdbc.Driver",
        "jdbc:mariadb://localhost:3306/",
        credenciales.getUsuario(),
        credenciales.getClave()
        );
        
        System.out.println(listaBD);
        
    }
    
    public Connection buscarConexion(String url, String usuario, String password){
        if (conexion== null) { // si es la primera vez
            try {
                //cargamos las clases de mariadb que implementan JDBC
                Class.forName("org.mariadb.jdbc.Driver"); //1
                conexion = DriverManager.getConnection(url, usuario, password); //2
            } catch (SQLException | ClassNotFoundException ex) { // si me olvide de importar la libreria
                System.out.println("No se puede conectar o no se puede cargar el driver.");
            }
        }
        return conexion; //retorna la conexion establecida
    }
    
    public static void listDatabaseUsers() {
        Connection conn = null;
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=password");
            
            // Get metadata
            DatabaseMetaData metaData = conn.getMetaData();
            
            // Retrieve the list of users
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT User FROM mysql.user");
            
            // Print the list of users
            System.out.println("Users in the database:");
            while (resultSet.next()) {
                String username = resultSet.getString("USER");
                System.out.println(username);
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

