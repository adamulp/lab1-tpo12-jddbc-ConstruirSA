package modelo;

import java.io.*;
import javax.swing.*;

public class EnvFileHandler {
    private static final String ENV_FILE_NAME = ".env";
    private String usuario;
    private String clave;

    public EnvFileHandler() {
    }

    public EnvFileHandler(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }
    
    
    
    public static void main(String[] args) {
        // Check if .env file exists in the current directory
        File envFile = new File(ENV_FILE_NAME);
        EnvFileHandler envFileHandler = new EnvFileHandler();
        
        if (envFile.exists()) {
            // .env file exists, load credentials from it
            envFileHandler = EnvFileHandler.loadCredentials();
        } else {
            // .env file does not exist, prompt for username and password
//            String username = JOptionPane.showInputDialog(null, "Enter username:");
//            String password = getPasswordInput();
            
            if(envFileHandler.getUserAndPass()){
                // Save username and password to .env file
                saveCredentials(envFileHandler.usuario, envFileHandler.clave);
            }
        }
    }
    
    private static EnvFileHandler loadCredentials() {
        String usr = "";
        String pass = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(ENV_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming .env file format is "KEY=VALUE"
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    // Assuming the keys for username and password are "DB_USERNAME" and "DB_PASSWORD"
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
                return new EnvFileHandler(usr, pass);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private Boolean getUserAndPass() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };
        
        int option = JOptionPane.showConfirmDialog(null, message, "Enter credentials:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            this.usuario = usernameField.getText();
            this.clave = new String(passwordField.getPassword());
            
            return true;

        }
        
        return null; // User canceled input

    }
    
    private static void saveCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ENV_FILE_NAME))) {
            // Write username and password to .env file
            writer.write("DB_USERNAME=" + username);
            writer.newLine();
            writer.write("DB_PASSWORD=" + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
