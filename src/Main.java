import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Utilisateur> utilisateurs = new ArrayList();
        utilisateurs.add(new Admin("Mathis", "mathis.chekraoui@gmail.com"));
        utilisateurs.add(new Membre("Anthony", "anthony.chekraoui@gmail.com"));

        for (Utilisateur utilisateur : utilisateurs) {
            utilisateur.afficherRole();
        }

        ChatGPTExample.genererTexte("Donne-moi une idée de projet pour un développeur débutant.");

        Utilisateur.ajouterUtilisateur("Alice", "alice@example.com", "Admin");
        Utilisateur.listerUtilisateurs();
    }
}