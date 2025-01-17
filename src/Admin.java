public class Admin extends Utilisateur {
    public void afficherRole() {
        System.out.println("Nom: " + this.getNom());
        System.out.println("Role: Administrateur\n");
    }

    public Admin(String nom, String email) {
        super(nom, email);
    }


}
