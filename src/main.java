import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class main {
    public static void main(String[] args) throws Exception {

//        String url = "https://imdb-api.com/en/API/Top250Movies/k_gxg5mdpc";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoImdb();

//        String url = "https://api.nasa.gov/planetary/apod?api_key=2b4z7FNXrB60Cl4gLp1VKUzeZIgbtKjfGFnHa7FM&start_date=2022-07-10&end_date=2022-07-20";
//        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        String url = "https://tc-linguagens-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDeLinguagens();

        ClienteHttp http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo>conteudos = extrator.extrai(json);

        System.out.println(conteudos.size());

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i <= 4; i++) {

            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.urlImage()).openStream();

            String texto = conteudo.texto();

            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo, texto);

            System.out.println("Titulo: " + conteudo.titulo());

        }
    }
}
