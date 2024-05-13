//Desde el método main de un nuevo proyecto Java de nombre construirSA:
//
//#Cargar el driver MariaDB. ^
//#Establecer la conexión a la base de datos previamente creada. ^
//#Insertar 3 empleados. ^
//#Insertar 2 herramientas. ^
//#Listar todas las herramientas con stock superior a 10. ^
//#Dar de baja al primer empleado ingresado a la base de datos.

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
            
//            JOptionPane.showMessageDialog(null, "trabajador agregado");
            
            
            //INSERT EMPLEADOS
            
//            String sql= "insert into empleado(dni, apellido, nombre, acceso, estado)"
//                    + " values "
//                    + "(42242442, 'hollmann', 'nicolas', 3, 1),"
//                    + "(12436578, 'adam', 'rigg', 2,1),"
//                    + "(98786354, 'enzo', 'casalegno',1,1);";

//            String sql = "insert into herramienta(nombre, descripcion, stock, estado)"
//                    + "values"
//                    + "('soldadora','MIG',5,1),"
//                    + "('pala','ancha',15,1);";

               //CONEXIÓN CON BD PARA INSERT

//            PreparedStatement ps = conexion.prepareStatement(sql);
//            int filas = ps.executeUpdate();
//            if(filas > 0){
//                JOptionPane.showMessageDialog(null, "Herramientas agregadas exitosamente");
//                System.out.println(filas);
//            }
            

            //SELECT TABLA HERRAMIENTA
            
//                String sql = "select * from herramienta where stock > 10";
//
//                //CONEXION DE BD PARA SELECT
//            PreparedStatement ps = conexion.prepareStatement(sql);
//            ResultSet cons = ps.executeQuery();
//            
//            //MOSTRAR RESULTADOS
//            
//            while(cons.next()){
//                System.out.println("ID: " + cons.getInt("idHerramienta"));
//                System.out.println("Nombre: " + cons.getString("nombre"));
//                System.out.println("Descripcion: " + cons.getString("descripcion"));
//                System.out.println("Stock: " + cons.getInt("stock"));
//                System.out.println("Estado: " + cons.getBoolean("estado"));
//            }


                //UPDATE AN EMPLEADO
                
               String sql = "update empleado set estado = 0 where dni = 42242442";
            PreparedStatement ps = conexion.prepareStatement(sql);
            int filas = ps.executeUpdate();
            if (filas == 1) {
                JOptionPane.showMessageDialog(null, "Empleado dado de baja correctamente");             
            }

                
            
            
            
            
            
            //MANEJO DE ERRORES CONEXIONES
        } catch (SQLException ex) {
            ex.printStackTrace();
            
           JOptionPane.showMessageDialog(null, "Error de conexion");
           // Error: 1049-42000: Unknown database
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver");
        }
        
    }

}
