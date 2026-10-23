public class Usuaris {
    public String nom;
    public String saltAleatoria;
    public String contrasenyaHash;

    public Usuaris(String nom, String saltAleatoria, String contrasenyaHash) {
        this.nom = nom;
        this.saltAleatoria = saltAleatoria;
        this.contrasenyaHash = contrasenyaHash;
    }
}
