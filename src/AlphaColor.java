
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AlphaColor
{
	public static void main(String[] args)
	{
		FrameView frame = new FrameView();

		if (args.length != 2)
		{
			System.out.println("Usage : java AlphaColor <input> <output>");
			System.exit(0);
		}

		BufferedImage image = ColorUtils.loadImage(args[0]);
		if (!ColorUtils.changeColour(image, 0xff000000))
		{
			System.out.println("Petit probleme gros!");
		}
		
		frame.setImage(image);
			
		ColorUtils.saveImage(image, ColorUtils.FORMAT_PNG, args[1]);
	}
}
