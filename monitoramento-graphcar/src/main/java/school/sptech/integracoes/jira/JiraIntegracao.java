package school.sptech.integracoes.jira;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Base64;

public class JiraIntegracao {

    public static final String BASE_URL_JIRA = "https://graphcar-servers.atlassian.net/";
    public static final String MID_URL_REST = "rest/api/3/";

    private static final String AUTHORIZATION_VALUE = "Basic " +
            Base64.getEncoder().encodeToString(("graphcar@outlook.com:ATATT3xFfGF0AZ6gmvOCSW696SauZn7lMOsTQ03kufEiCZtr3tDojPIL-MD7oNSQUlFTd7U7uJKPCYCGAfwRNJjTRQgp0sxrV_dMM8fStUs3-HKINugs_aR7fBmih4R3YJrIIgPa3sRY0t0bbH5os0C5FD57Kovx1c_zp3Ok0OAz9zkjR1vFN3k=2A445D94")
                    .getBytes());

    public static JSONObject getRecurso(String endpoint) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL_JIRA + MID_URL_REST + endpoint))
                .header("Authorization", AUTHORIZATION_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 ) {
            return null;
        } else {
            return new JSONObject(response.body());
        }
    }

    public static JSONObject postRecurso(String endpoint, String componente, Double uso, String hostName)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String nivelChamado = uso > 90.0 ? "Crítico" : "Alerta";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL_JIRA + MID_URL_REST + endpoint))
                .header("Authorization", AUTHORIZATION_VALUE)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                            "fields": {
                                "project": { "key": "GRAP" },
                                "summary": "%s - Uso de %s atingiu nível %s",
                                "description": {
                                  "type": "doc",
                                  "version": 1,
                                  "content": [
                                    {
                                      "type": "paragraph",
                                      "content": [
                                        {
                                          "type": "text",
                                          "text": "%s - Uso de %s atingiu %.2f %%"
                                        }
                                      ]
                                    }
                                  ]
                                },
                                "issuetype": { "name": "[System] Incident" }
                            }
                        }""".formatted(hostName, componente, nivelChamado, LocalDateTime.now(), componente, uso)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return new JSONObject(response.body());
        } else {
            return null;
        }
    }
}
