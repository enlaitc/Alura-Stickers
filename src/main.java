import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) throws Exception {

        String url = "https://imdb-api.com/en/API/Top250Movies/k_gxg5mdpc";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();

//        String url = "https://api.nasa.gov/planetary/apod?api_key=2b4z7FNXrB60Cl4gLp1VKUzeZIgbtKjfGFnHa7FM&start_date=2022-07-10&end_date=2022-07-20";
//        ExtratorDeconteudo extrator = new ExtratorDeConteudoDaNasa();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo>conteudos = extrator.extrai(json);

        System.out.println(conteudos.size());

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i <= 5; i++) {

            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();

//            float rating = Float.parseFloat(conteudo.get("imDbRating"));
//
//            String frase = "kkk tosco";
//            if (rating >= 9) frase = "Brabissimo d+";
//            if (rating >= 7 && rating < 9) frase = "Otimo";
//            if (rating >= 6 && rating < 7) frase = "Bonzin";

            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo, "frase");

            System.out.println("Titulo: " + conteudo.getTitulo());

        }
    }
}
