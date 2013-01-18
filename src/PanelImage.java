
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author HETT KILIAN
 * @see FrameView
 * Cette classe se charge d'afficher les images
 * et d'appliquer des transformations sur cette 
 * dernière
**/
public final class PanelImage extends JPanel
{
	private BufferedImage image;
	private BufferedImage alpha;
	private int color=-102;
	private int x=-102;
	private int y=-102;

	public PanelImage(String url)
	{
		super();
		this.setVisible(true);
		this.setOpaque(false);
		alpha = ColorUtils.loadImage("./img/alpha.bmp");
		if (url!="")
			loadImage(url);
	}

	public PanelImage()
	{
		this("");
	}

	/**
	 * @param String url -- Chemin vers l'image à charger
	 * Fonction chargeant une image et l'affichant
	*/
	public void loadImage(String url)
	{
		image = ColorUtils.loadImage(url);
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	}

	/**
	  * @param Graphic g
	  * Surcharge de la méthode paint : permet d'afficher l'image
	*/
	public void paint(Graphics g)
	{
		if (image!=null)
		{
			for (int i=0;i<(image.getWidth()/alpha.getWidth());i++)
			{
				for (int j=0;j<(image.getHeight()/alpha.getHeight());j++)
				{
					g.drawImage(alpha,i*alpha.getWidth(),j*alpha.getHeight(),
						alpha.getWidth(),alpha.getHeight(),null);
				}
			}
			g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
		}
	}

	public void setImage(BufferedImage im)
	{
		image = im;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public int getPixel(int x, int y)
	{
		if (image!=null)
			return image.getRGB(x,y);
		else
			return 0;
	}

	public void setColor(int value)
	{
		color = value;
	}

	public void setCoordinate(int _x, int _y)
	{
		x = _x;
		y = _y;
	}

	public void toAlpha(int delta)
	{
		if (isGreat(color))
		{
			ColorUtils.changeColour(image,color,delta);
			repaint();
		}
	}

	public void toAlphaDiffusion(int delta)
	{
		if (isGreat(color) && isGreat(x) && isGreat(y))
		{
			ColorUtils.colorDiffusion(image, x, y, color, delta);
			repaint();
		}
	}

	private boolean isGreat(int x)
	{
		return x!=-102;
	}
}
