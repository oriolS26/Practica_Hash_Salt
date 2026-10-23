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
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==================================================");
        System.out.println("   PART 2: Verificació de la integritat");
        System.out.println("==================================================");

        try{
            System.out.print("Introdueix el missatge de l'emissor: ");
            String missatgeEmissor = scanner.nextLine();
            
            Path rutaMissatge = Path.of("missatge.txt");
            Files.writeString(rutaMissatge, missatgeEmissor, StandardCharsets.UTF_8);
            System.out.println("-> Creat el fitxer 'missatge.txt' amb èxit.");

            String hashOriginal = calcularSHA256(missatgeEmissor);
            Path rutaHash = Path.of("missatge.hash");
            Files.writeString(rutaHash, hashOriginal, StandardCharsets.UTF_8);
            System.out.println("-> Creat el fitxer 'missatge.hash' amb el resum original.");

            System.out.println("\n--- SIMULACIÓ DE RECEPCIÓ ---");
            System.out.print("Introdueix el nom del fitxer a llegir (ex: missatge.txt o missatgeCanviat.txt): ");
            String nomFitxerRebut = scanner.nextLine();

            Path rutaRebut = Path.of(nomFitxerRebut);
            if (!Files.exists(rutaRebut)) {
                System.out.println("Error: El fitxer '" + nomFitxerRebut + "' no existeix al directori.");
                return;
            }

            String missatgeRebut = Files.readString(rutaRebut, StandardCharsets.UTF_8);
            String hashRebut = calcularSHA256(missatgeRebut);

            String hashGuardat = Files.readString(rutaHash, StandardCharsets.UTF_8);

            System.out.println("--- RESULTAT DE LA VERIFICACIÓ ---");
            System.out.println("Hash original (missatge.hash): " + hashGuardat);
            System.out.println("Hash del fitxer rebut (" + nomFitxerRebut + "): " + hashRebut);

            if (hashRebut.equals(hashGuardat)) {
                System.out.println("[INTEGRITAT OK] Els hashes coincideixen. El missatge NO ha estat modificat.");
            } else {
                System.out.println("[ALERTA] INTEGRITAT COMPROMESA! Els hashes NO coincideixen. El missatge ha estat alterat!");
            }

        } catch (IOException e) {
            System.out.println("S'ha produït un error d'E/S: " + e.getMessage());
        }
    }
}
