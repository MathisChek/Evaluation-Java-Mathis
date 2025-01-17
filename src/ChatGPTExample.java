import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ChatGPTExample {

    // Remplace par ta propre clé d'API OpenAI
    private static String OPENAI_API_KEY = "";

    // URL pour le modèle GPT-3.5-turbo
    private static String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    // OkHttp client (réutilisable)
    private static final OkHttpClient client = new OkHttpClient();

    // Gson pour sérialiser/désérialiser le JSON
    private static final Gson gson = new Gson();

    public static void genererTexte(String prompt) {

        //Recuperation de la clé api pour chatgpt
        Properties props = new Properties();
        try (InputStream input = ChatGPTExample.class.getResourceAsStream("/config.properties")) {
            if (input == null) {
                System.out.println("Fichier config.properties introuvable !");
                return; // ou continue
            }
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Tu lis la clé, l'URL...
        OPENAI_API_KEY = props.getProperty("openai.api.key");
        OPENAI_URL  = props.getProperty("openai.api.url", "https://api.openai.com/v1/chat/completions");

        try {
            // Envoi la requête à ChatGPT
            String answer = sendChatGPTRequest(prompt);

            // Affiche la réponse
            System.out.println("Idée de projet :");
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String sendChatGPTRequest(String prompt) throws IOException {
        // 1) Construire l'objet JSON pour le corps de la requête
        JsonObject requestBodyJson = new JsonObject();

        // On utilise le modèle GPT-3.5-turbo
        requestBodyJson.addProperty("model", "gpt-3.5-turbo");

        // Créer le tableau de messages
        JsonArray messagesArray = new JsonArray();

        // Ajout d'un message "user" avec ton prompt
        JsonObject userMessage = new JsonObject();
        userMessage.addProperty("role", "user");
        userMessage.addProperty("content", prompt);
        messagesArray.add(userMessage);

        // Ajout du tableau messages au corps JSON
        requestBodyJson.add("messages", messagesArray);

        // Sérialiser l'objet JSON en chaîne
        String jsonString = gson.toJson(requestBodyJson);

        // 2) Construire le corps de la requête POST
        RequestBody body = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonString.getBytes()
                );

        // 3) Construire la requête
        Request request = new Request.Builder()
                .url(OPENAI_URL)
                .addHeader("Authorization", "Bearer " + OPENAI_API_KEY) // IMPORTANT
                .addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        // 4) Envoyer la requête et récupérer la réponse
        try (Response response = client.newCall(request).execute()) {
            // Si ce n'est pas un code 2xx, on jette une exception
            if (!response.isSuccessful()) {
                throw new IOException("Réponse non valide du serveur: " + response);
            }

            // Corps de la réponse en String
            String responseBody = response.body().string();

            // 5) Parse le JSON de la réponse pour extraire le contenu
            JsonObject responseJson = gson.fromJson(responseBody, JsonObject.class);
            JsonArray choicesArray = responseJson.getAsJsonArray("choices");

            if (choicesArray != null && choicesArray.size() > 0) {
                JsonObject firstChoice = choicesArray.get(0).getAsJsonObject();
                JsonObject messageObject = firstChoice.getAsJsonObject("message");
                // Récupérer le texte renvoyé par l'assistant
                String content = messageObject.get("content").getAsString();
                return content;
            } else {
                return "Aucune réponse disponible.";
            }
        }
    }
}
