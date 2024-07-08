package Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatGPTAPI {
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");

    public static String checkHateSpeech(String content) throws IOException, InterruptedException {
        String prompt = """
            Does the following text contain hate speech or any form of violence, harassment, or threats? 
            Analyze it carefully and respond with a clear 'yes' or 'no':
            %s
            """.formatted(content);

        String requestBody = """
            {
                "model": "gpt-4",
                "messages": [
                    {"role": "system", "content": "You are an assistant trained to detect hate speech in text."},
                    {"role": "user", "content": "%s"}
                ],
                "max_tokens": 5,
                "temperature": 0
            }
            """.formatted(prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/engines/gpt-4/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
