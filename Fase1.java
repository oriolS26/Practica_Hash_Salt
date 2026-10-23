import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Fase1 {
    public static void main(String[] args) {
        Fase1 hashSHA256 = new Fase1();
        hashSHA256.calculateHashSHA256();
    }

    public String calculateHashSHA256(String missatge) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hashResult = sha256.digest(missatge.getBytes(StandardCharsets.UTF_8));

            StringBuilder textOut = new StringBuilder();
            for (int i = 0; i < hashResult.length; i++) {
                if (i > 0) {
                    textOut.append("-");
                }
                textOut.append(String.format("%02X", hashResult[i] & 0xFF));
            }
            return textOut.toString();
        } catch (NoSuchAlgorithmException e) {
            return "No s'ha trobat l'algoritme SHA-256: " + e.getMessage();
        }
    }
}