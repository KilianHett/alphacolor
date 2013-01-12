
java.awt.image.BufferedImage:
javax.imageio.ImageIO;

public class ColorUtils 
{
	// Constante sur les formats d'image
	public final static String FORMAT_JPEG = "jpeg";
	public final static String FORMAT_PNG = "png";

	// Constante sur les couleurs
	public final static int ALPHA = 0xff000000;


	public static short canalRouge(short value)
	{
		return value&0x00ff0000;
	}

	public static short canalVert(short value)
	{
		return value&0x0000ff00;
	}

	public static short canalBleu(short value)
	{
		return value&0x000000ff;
	}

	public boolean changeColour(BufferedImage im, int color)
	{
		try
		{
			for (int i=0;i<im.getWidth();i++)
			{
				for (int j=0;j<im.getHeight();j++)
				{
					if (im.getRGB(i,j)==color)
					{
						im.setRGB(i,j,ALPHA);
					}
				}
			}
			return true;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static BufferedImage loadImage(String path)
	{
		return ImageIO.read(path);
	}


	public static void saveImage(BufferdImage im, String path, String format)
	{
		File out = new File(path);
		try
		{
			ImageIo.write(im, format, path);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
