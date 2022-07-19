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
        URI endereco = URI.create("https://imdb-api.com/en/API/MostPopularMovies/k_gxg5mdpc");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(response.body());
        System.out.println(listaDeFilmes.size());

        for (Map<String, String> filmes : listaDeFilmes) {
            InputStream inputStream = new URL(filmes.get("image")).openStream();

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream,filmes.get("title")+".png");

            System.out.println("Titulo: " + filmes.get("title"));
//            System.out.println("Rating: "+ filmes.get("imDbRating"));
//            for (int i = 0; i <= Float.parseFloat(filmes.get("imDbRating"))-1; i++){
//                System.out.printf("*");
//            }
//            System.out.println();
        }
    }
}
