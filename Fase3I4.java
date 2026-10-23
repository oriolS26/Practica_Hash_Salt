import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fase3I4 {
    public static void main(String[] args) {
        Fase3I4 inici = new Fase3I4();
        inici.start();
    }
    private List<Usuaris> usuarisList = new ArrayList<>();

    public String calcularSHA256(String text) {
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

    public String calcularSalt(){
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        StringBuilder saltAleatoria = new StringBuilder();
        for(byte b : saltBytes){
            saltAleatoria.append(String.format("%02X", b & 0xFF));
        }
        return saltAleatoria.toString();
    }

    public void start() {

    }
}
