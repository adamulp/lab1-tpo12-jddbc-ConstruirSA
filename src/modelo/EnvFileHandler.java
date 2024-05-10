package modelo;

import java.io.*;
import javax.swing.*;

public class EnvFileHandler {
    private static final String ENV_FILE_NAME = ".env";
    
    public static void main(String[] args) {
        // Check if .env file exists in the current directory
        File envFile = new File(ENV_FILE_NAME);
        
        if (envFile.exists()) {
            // .env file exists, load credentials from it
            loadCredentials();
        } else {
            // .env file does not exist, prompt for username and password
            String username = JOptionPane.showInputDialog(null, "Enter username:");
            String password = getPasswordInput();
            
            // Save username and password to .env file
            saveCredentials(username, password);
        }
    }
    
    private static void loadCredentials() {
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
                        System.out.println("Username: " + value);
                    } else if (key.equals("DB_PASSWORD")) {
                        System.out.println("Password: " + value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static String getPasswordInput() {
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
            return new String(passwordField.getPassword());
        } else {
            return ""; // Handle cancel button action
        }
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
