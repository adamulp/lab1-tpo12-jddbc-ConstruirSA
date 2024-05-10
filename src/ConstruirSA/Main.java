//Desde el método main de un nuevo proyecto Java de nombre construirSA:
//
//#Cargar el driver MariaDB. ^
//#Establecer la conexión a la base de datos previamente creada. ^
//#Insertar 3 empleados.
//#Insertar 2 herramientas.
//#Listar todas las herramientas con stock superior a 10.
//#Dar de baja al primer empleado ingresado a la base de datos.

package ConstruirSA;
import java.sql.*;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {

        
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/tpo10_db4_herramientasconstruirsa_g3",
                    "root",
                    "");
            
//            JOptionPane.showMessageDialog(null, "trabajador agregado");
            
            
            //INSERT EMPLEADOS = ESCLAVOS
            
//            String sql= "insert into empleado(dni, apellido, nombre, acceso, estado)"
//                    + " values "
//                    + "(42242442, 'hollmann', 'nicolas', 3, 1),"
//                    + "(12436578, 'adam', 'rigg', 2,1),"
//                    + "(98786354, 'enzo', 'casalegno',1,1);";
            
            String sql= "insert into empleado(dni, apellido, nombre, acceso, estado)"
                    + " values "
                    + "(42242876, Allendez, 'Alexis', 4, 1);";
            
            PreparedStatement ps = conexion.prepareStatement(sql);
            int filas = ps.executeUpdate();
            if(filas > 0){
                JOptionPane.showMessageDialog(null, "Empleados agregado exitosamente");
                System.out.println(filas);
            }
            
            
            
            
            
            
            //MANEJO DE ERRORES CONEXIONES
        } catch (SQLException ex) {
            ex.printStackTrace();
            
           JOptionPane.showMessageDialog(null, "Error de conexion");
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }
        
    }

}
