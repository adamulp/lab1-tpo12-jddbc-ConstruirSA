package ConstruirSA;
import java.sql.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/tpo10_db4_herramientasConstruirSA_g3",
                    "root",
                    "");
            // [ WARN] (main) Error: 1049-42000: Unknown database 'tpo10_db4_herramientasconstruirsa_g3'
            
            JOptionPane.showMessageDialog(null, "trabajador agregado");
            
        } catch (SQLException ex) {
            
           JOptionPane.showMessageDialog(null, "Error de conexion");
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }

    }

}
