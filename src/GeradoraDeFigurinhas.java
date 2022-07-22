import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo, String frase) throws Exception {

        BufferedImage bufferedImage = ImageIO.read(inputStream);
        Image imagemOriginal;

        if (bufferedImage.getHeight() - 100 > bufferedImage.getWidth()) {
            imagemOriginal = bufferedImage.getScaledInstance(600, 1000, Image.SCALE_DEFAULT);
        } else if (bufferedImage.getHeight() + 100 < bufferedImage.getWidth()) {
            imagemOriginal = bufferedImage.getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        } else {
            imagemOriginal = bufferedImage.getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        }

        int altura = imagemOriginal.getHeight(null);
        int largura = imagemOriginal.getWidth(null);
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
        graphics.setColor(Color.RED);

        FontMetrics fm = graphics.getFontMetrics();

        graphics.drawString(frase, (largura / 2) - (fm.stringWidth(frase) / 2), (novaAltura - 50) - (fm.getHeight() / 2));

        if (!new File("saida").exists()) new File("saida").mkdir();

        ImageIO.write(novaImagem, "png", new File(nomeArquivo));
    }

}
