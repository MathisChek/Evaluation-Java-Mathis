import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public abstract class Utilisateur {
    private String nom;
    private String email;

    public Utilisateur(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public static void ajouterUtilisateur(String nom, String email, String role) {
        String uri = "mongodb+srv://carmelohays:790SEBfv1lrZNPD2@cluster0.yd7bp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("EvaluationJava");
            MongoCollection<Document> utilisateursCollection = db.getCollection("mathis_utilisateurs");

            utilisateursCollection.insertOne(new Document("nom", nom)
                    .append("email", email)
                    .append("role", role)
            );
            System.out.println("Utilisateur inséré avec succès");
        }
    }

    public static void listerUtilisateurs() {
        String uri = "mongodb+srv://carmelohays:790SEBfv1lrZNPD2@cluster0.yd7bp.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase db = mongoClient.getDatabase("EvaluationJava");
            MongoCollection<Document> utilisateursCollection = db.getCollection("mathis_utilisateurs");

            for (Document utilisateur : utilisateursCollection.find()) {
                System.out.println(utilisateur.toJson());
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void afficherRole();
}
