
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author HETT KILIAN
 * Classe contenant uniquement des méthodes static
 * ces méthodes on un lien direct avec le comportement
 * des images et de la manipulation des couleurs
**/
public class ColorUtils 
{
	// Constante sur les formats d'image
	public final static String FORMAT_JPEG = "jpeg";
	public final static String FORMAT_PNG = "png";

	// Constante sur les couleurs
	public final static int ALPHA = 0x00000000;

	public static int complement(int value)
	{
		return (value^0xffffffff);
	}

	public static int canalAlpha(int value)
	{
		return (value&0xff000000)>>24;
	}

	public static int canalRouge(int value)
	{
		return (value&0x00ff0000)>>16;
	}

	public static int canalVert(int value)
	{
		return (value&0x0000ff00)>>8;
	}

	public static int canalBleu(int value)
	{
		return value&0x000000ff;
	}

	public static String typeToString(BufferedImage im)
	{
		switch (im.getType())
		{
			case BufferedImage.TYPE_INT_RGB:return "Mode RGB";
			case BufferedImage.TYPE_INT_ARGB:return "Mode ARGB";
			default: return "Mode inconue";
		}
	}

	public static BufferedImage convertToAlphaMode(BufferedImage im)
	{
		BufferedImage tmp = new BufferedImage(im.getWidth(),
				im.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int i=0;i<im.getWidth();i++)
		{
			for (int j=0;j<im.getHeight();j++)
			{
				tmp.setRGB(i, j, im.getRGB(i,j)); 
			}
		}
		return tmp;
	}


	public static boolean changeColour(BufferedImage im, int color)
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
			return false;
		}
	}

	public static BufferedImage loadImage(String path)
	{
		try
		{
			return convertToAlphaMode(ImageIO.read(new File(path)));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}


	public static void saveImage(BufferedImage im, String path, String format)
	{
		File out = new File(path);
		try
		{
			ImageIO.write (im, format, out);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
