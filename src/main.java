import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) throws Exception {
        URI endereco = URI.create("https://imdb-api.com/en/API/Top250Movies/k_gxg5mdpc");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(response.body());
        System.out.println(listaDeFilmes.size());

        for (Map<String, String> filmes : listaDeFilmes) {
            InputStream inputStream = new URL(parser.urlParse(filmes.get("image"))).openStream();

            float rating = Float.parseFloat(filmes.get("imDbRating"));

            String frase = "kkk tosco";
            if (rating >= 9) frase = "Brabissimo d+";
            if (rating >= 7 && rating < 9) frase = "Otimo";
            if (rating >= 6 && rating < 7) frase = "Bonzin";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, filmes.get("title") + ".png", frase);

            System.out.println("Titulo: " + filmes.get("title"));
            break;
        }
    }
}
