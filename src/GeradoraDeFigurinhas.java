import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

//            InputStream inputStream = new FileInputStream(new File("entrada/filmeMaior.jpg"));
//            InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int altura = imagemOriginal.getHeight();
        int largura = imagemOriginal.getWidth();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        graphics.setColor(Color.magenta);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 100));

        graphics.drawString("Padriño", 546, novaAltura - 50);

        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
    }

}
