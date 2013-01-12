import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class AlphaColor
{
	public static void main(String[] args)
	{
		//FrameView frame = new FrameView();
		BufferedImage image = ImageIO.read(args[1]);
		if (!ColorUtils.changeColour(image, 0)
		{
			System.out.prinln("Petit probleme gros!");
		}	
		ColorsUtils.save(image,ColorUtils.FORMAT_PNG,args[2]);
	}
}
