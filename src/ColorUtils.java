
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

	public static boolean inInterval(int value, int sigma, int delta)
	{
		return (value<=sigma+delta) && (value>=sigma-delta);
	}

	public static boolean isInColorInterval(int value, int color, int delta)
	{
		return inInterval(canalRouge(value), canalRouge(color), delta) &&
			inInterval(canalVert(value), canalVert(color), delta) &&
			inInterval(canalBleu(value), canalBleu(color), delta);
	}

	public static void changeColour(BufferedImage im, int color, int delta)
	{
		for (int i=0;i<im.getWidth();i++)
		{
			for (int j=0;j<im.getHeight();j++)
			{
				if (isInColorInterval(im.getRGB(i,j),color,delta))
				{
					im.setRGB(i,j,ALPHA);
				}
			}
		}
	}

	public static void colorDiffusion(BufferedImage im, int x, int y, 
			int color, int delta)
	{
		int maxX=(x<im.getWidth()/2?im.getWidth()-x:x);
		int maxY=(y<im.getHeight()/2?im.getHeight()-y:y);
		int max = (maxX>maxY?maxX:maxY);

		for (int i=0;i<max;i++)
		{
			for (int m=0;m<i;m++)
			{
				if (hasNeighborColor(im, x+m-i/2, y+m-i/2, color, delta) 
					&& hasNeighborColor(im, x+m-i/2, y+m-i/2, ALPHA, 0) 
					&& isInImage(im, x+m-i/2, y+m-i/2) && isInColorInterval(im.getRGB(x+m-i/2, y+m-i/2), color, delta))
				{
					im.setRGB(x+m-(i/2),y+m-(i/2), ALPHA);
				}
				
				
				if (hasNeighborColor(im, x-m+i/2, y-m+i/2, color, delta) 
					&& hasNeighborColor(im, x-m+i/2, y-m+i/2, ALPHA, 0) 
					&& isInImage(im, x-m+i/2, y-m+i/2) && isInColorInterval(im.getRGB(x-m+i/2, y-m+i/2), color, delta))
				{
					im.setRGB(x-m+i/2,y-m+i/2,ALPHA);
				}
			}
		}
	}	

	public static boolean hasNeighborColor(BufferedImage im, int x, int y, int color, int delta)
	{
		boolean b = false;
		for (int i=x-1;i<=x+1;i++)
		{
			for (int j=y-1;j<=y+1;j++)
			{
				if (isInImage(im,x,y))
				{
					b = b || isInColorInterval(im.getRGB(x,y), color, delta);
				}
			}
		}
		return b;
	}

	public static boolean isInImage(BufferedImage im, int x, int y)
	{
		return x<im.getWidth() && y<im.getHeight() && x>=0 && y>=0;
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
