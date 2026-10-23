import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Fase1 {
    public static void main(String[] args) {
        Fase1 inici = new Fase1();
        inici.start();
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

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int opcio;

        do{
            System.out.println("\n==================================================");
            System.out.println("   FASE 1: Càlcul de missatges (SHA-256)");
            System.out.println("==================================================");
            System.out.println("1. Introduir un missatge i mostrar el seu hash");
            System.out.println("2. Introduir dos missatges i comparar si coincideixen");
            System.out.println("3. Calcular diverses vegades el hash del mateix missatge");
            System.out.println("0. Sortir");
            System.out.print("Tria una opció: ");

            opcio = scanner.nextInt();
            scanner.nextLine();

            switch(opcio){
                case 1:
                    System.out.println("Introduexi el missatge: ");
                    String missatge = scanner.nextLine();
                    System.out.println("Hash SHA-256: " + calculateHashSHA256(missatge));
                break;
                case 2:
                    System.out.println("Introdueix el primer missatge: ");
                    String missatge1 = scanner.nextLine();
                    System.out.println("Introdueix el segon missatge: ");
                    String missatge2 = scanner.nextLine();

                    String hashMissatge1 = calculateHashSHA256(missatge1);
                    String hashMissatge2 = calculateHashSHA256(missatge2);

                    System.out.println("El hash del primer missatge: " + hashMissatge1);
                    System.out.println();
                    System.out.println("El hash del segon missatge: " + hashMissatge2);

                    if(hashMissatge1.equals(hashMissatge2)){
                        System.out.println("Tenen el matexi hash");
                    }else{
                        System.out.println("El hash no és el mateix");
                    }
                break;
                case 3:
                    System.out.println("Introdueix el missatge: ");
                    String missatgeRepetitiu = scanner.nextLine();
                    System.out.println();
                    for(int i = 0;i < 2;i++){
                        String hashRepetitiu = calculateHashSHA256(missatgeRepetitiu);
                        System.out.println("Hash del missatge: " + hashRepetitiu);
                        System.out.println();
                    }
                break;
                case 0:
                    System.out.println("Sortint...");
                break;
                default:
                    System.out.println("Opció no vàlida");
            }
        }while(opcio != 0);
    }
}