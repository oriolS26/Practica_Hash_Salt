import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Fase2 {
    public static void main(String[] args) {
        Fase2 inici = new Fase2();
        inici.start();
    }

    public static String calcularSHA256(String text) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashResult = sha256.digest(text.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder textOut = new StringBuilder();
            for (int i = 0; i < hashResult.length; i++) {
                if (i > 0) {
                    textOut.append("-");
                }
                textOut.append(String.format("%02X", hashResult[i] & 0xFF));
            }
            return textOut.toString();
        } catch (NoSuchAlgorithmException e) {
            return "Error: " + e.getMessage();
        }
    }

    public void start() {
        
    }
}
