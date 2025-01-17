public class Membre extends Utilisateur{
    public void afficherRole() {
        System.out.println("Nom: " + this.getNom());
        System.out.println("Role: Membre");
    }

    public Membre(String nom, String email) {
        super(nom, email);
    }
}
