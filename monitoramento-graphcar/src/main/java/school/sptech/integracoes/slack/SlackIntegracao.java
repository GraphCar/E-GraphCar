package school.sptech.integracoes.slack;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SlackIntegracao {

    public static final String BASE_URL_SLACK = "https://slack.com/api/chat.postMessage";
    public static final String WEBHOOK_SLACK = "https://hooks.slack.com/services/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";

    public static String enviarMensagem(String mensagem) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(WEBHOOK_SLACK))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                            "text": "%s", 
                        }""".formatted(mensagem)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 ) {
            return "Houve um erro ao enviar a mensagem";
        } else {
            return response.body();
        }
    }
}