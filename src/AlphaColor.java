
import java.awt.image.BufferedImage;
import javax.swing.UIManager;

/**
 * @author HETT KILIAN
 * Classe principale du programme, fait appelle à FrameView
 * dans le cas ou l'utilisateur à préciser un chemin d'image
 * l'ouvrir
**/
final public class AlphaColor
{
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		FrameView frame = new FrameView();
		if (args.length == 1)
		{
			BufferedImage image = ColorUtils.loadImage(args[0]);
			frame.setImage(image);
		}
	}
}
