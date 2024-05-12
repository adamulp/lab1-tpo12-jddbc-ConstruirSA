package modelo;

import java.io.*;
import javax.swing.*;

public class GestorDeArchivoEnv {
    private static String RUTA_ARCHIVO_ENV = "../.env";
    private String usuario;
    private String clave;

    public GestorDeArchivoEnv() {
    }

    public GestorDeArchivoEnv(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public static String getRUTA_ARCHIVO_ENV() {
        return RUTA_ARCHIVO_ENV;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public static void setRUTA_ARCHIVO_ENV(String RUTA_ARCHIVO_ENV) {
        GestorDeArchivoEnv.RUTA_ARCHIVO_ENV = RUTA_ARCHIVO_ENV;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
 
    public static void main(String[] args) {
        // Averiguar is ya existe un archivo .env
        File envFile = new File(RUTA_ARCHIVO_ENV);
        GestorDeArchivoEnv envFileHandler = new GestorDeArchivoEnv();
        
        if (envFile.exists()) {
            // Si .env existe, cargar las credenciales
            envFileHandler = GestorDeArchivoEnv.cargarCredenciales();
        } else {
          
            if(envFileHandler.ingresarUsuarioYclave()){
                // Almanecer usuario y clave en el archivo .env
                guardarCredenciales(envFileHandler.usuario, envFileHandler.clave);
            }
        }
    }
    
    
    
    public static GestorDeArchivoEnv cargarCredenciales(GestorDeArchivoEnv gestor, String rutaArchivo){
        String usr = "";
        String pass = "";
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = lector.readLine()) != null) {
                // Usando el formato "KEY=VALUE" en el .env
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // Usando las llaves "DB_USERNAME" y "DB_PASSWORD"
                    if (key.equals("DB_USERNAME")) {
                        usr = value;
                        System.out.println("Username: " + value);
                    } else if (key.equals("DB_PASSWORD")) {
                        pass = value;
                        System.out.println("Password: " + value);
                    }
                }
            }
            if(!usr.equals("") && pass != null){
                return new GestorDeArchivoEnv(usr, pass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static GestorDeArchivoEnv cargarCredenciales() {
        return cargarCredenciales(new GestorDeArchivoEnv(), RUTA_ARCHIVO_ENV);
    }
    
    private Boolean ingresarUsuarioYclave() {
        JTextField campoUsuario = new JTextField();
        JPasswordField campoContrasenia = new JPasswordField();
        
        Object[] campos = {
            "Usuario:", campoUsuario,
            "Contraseña:", campoContrasenia
        };
        
        int option = JOptionPane.showConfirmDialog(null, campos, "Ingresar Credenciales:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            this.usuario = campoUsuario.getText();
            this.clave = new String(campoContrasenia.getPassword());
            
            return true;

        }
        
        return null; // Se cancelo la entrada de datos

    }
    
    public static void guardarCredenciales(String usuario, String clave) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_ARCHIVO_ENV))) {
            // Escrbirir los datos en un archivo .env
            writer.write("DB_USERNAME=" + usuario);
            writer.newLine();
            writer.write("DB_PASSWORD=" + clave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}