import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoImdb implements ExtratorDeConteudo{

    public List<Conteudo> extrai(String json) {

        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String url = parser.urlParse(atributos.get("image"));
            conteudos.add(new Conteudo(titulo, url));
        }
        return conteudos;
    }

}
