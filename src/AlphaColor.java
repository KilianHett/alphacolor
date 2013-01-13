
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * @author HETT KILIAN
 * Classe principale du programme, fait appelle à FrameView
 * dans le cas ou l'utilisateur à préciser un chemin d'image
 * l'ouvrir
*/
final public class AlphaColor
{
	public static void main(String[] args)
	{
		FrameView frame = new FrameView();

		if (args.length == 1)
		{
			BufferedImage image = ColorUtils.loadImage(args[0]);
			if (!ColorUtils.changeColour(image, 0xff000000))
			{
				System.out.println("Petit probleme gros!");
			}
			frame.setImage(image);
		}
	}
}
