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


    
    // ---------------Pedir las credenciales del usuario ---------------------
    private Boolean ingresarUsuarioYclave() {
        JTextField campoUsuario = new JTextField();
        JPasswordField campoContrasenia = new JPasswordField();

        Object[] campos = {
            "Usuario:", campoUsuario,
            "Contraseña:", campoContrasenia
        };

        int option = JOptionPane.showConfirmDialog(null,
                campos, "Ingresar Credenciales:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            this.usuario = campoUsuario.getText();
            this.clave = new String(campoContrasenia.getPassword());

            return true;

        }

        return null; // Se cancelo la entrada de datos

    }
    
    public Boolean ingresarDatos(){
        return ingresarUsuarioYclave();
    }

    // ---------------Almacenar las credenciales en un archivo -----------------
    public static void guardarCredenciales(String usuario,
            String clave,
            String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
                rutaArchivo))) {
            // Escrbirir los datos en un archivo .env
            writer.write("BD_USUARIO=" + usuario);
            writer.newLine();
            writer.write("BD_CLAVE=" + clave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarCredenciales(String usuario, String clave) {
        guardarCredenciales(usuario, clave, RUTA_ARCHIVO_ENV);
    }

    public void guardarCredenciales() {
        guardarCredenciales(usuario, clave);
    }
    
    // ---------------Cargar las credenciales desde un archivo -----------------
    public static GestorDeArchivoEnv cargarCredenciales(
            GestorDeArchivoEnv gestor,
            String rutaArchivo) {
        String usr = "";
        String pass = "";
        try (BufferedReader lector = new BufferedReader(
                new FileReader(rutaArchivo))) {
            String line;
            while ((line = lector.readLine()) != null) {
                // Usando el formato "LLAVE=VALOR" en el .env
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // Usando las llaves "BD_USUARIO" y "BD_CLAVE"
                    if (key.equals("BD_USUARIO")) {
                        usr = value;
                        System.out.println("Usuario: " + value);
                    } else if (key.equals("BD_CLAVE")) {
                        pass = value;
                        System.out.println("Contrasenia: " + value);
                    }
                }
            }
            if (!usr.equals("") && pass != null) {
                return new GestorDeArchivoEnv(usr, pass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static GestorDeArchivoEnv cargarCredenciales() {
        return cargarCredenciales(new GestorDeArchivoEnv(),
                RUTA_ARCHIVO_ENV);
    }

    // ------------------ Probar la clase --------------------------------------
    public static void main(String[] args) {
        // Averiguar is ya existe un archivo .env
        File envFile = new File(RUTA_ARCHIVO_ENV);
        GestorDeArchivoEnv envFileHandler = new GestorDeArchivoEnv();

        if (envFile.exists()) {
            // Si .env existe, cargar las credenciales
            envFileHandler = GestorDeArchivoEnv.cargarCredenciales();
        } else {

            if (envFileHandler.ingresarUsuarioYclave()) {
                // Almacenar usuario y clave en el archivo .env
                envFileHandler.guardarCredenciales();
            }
        }
    }
}
