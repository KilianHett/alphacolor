
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Color;

/**
 * @author HETT KILIAN
 * @see FrameView
 * Cette classe se charge d'afficher les images
 * elle supporte les comportements de clique de la souris
**/
public class PanelImage extends JPanel
{
	private BufferedImage image;
	private int color;

	public PanelImage(String url)
	{
		super();
		this.setVisible(true);
		this.setSize(400,800);
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
	}

	/**
	  * @param Graphic g
	  * Surcharge de la méthode paint : permet d'afficher l'image
	*/
	public void paint(Graphics g)
	{
		if (image!=null)
			g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
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
		return image.getRGB(x,y);
	}

	public void setColor(int value)
	{
		color = value;
	}
	
}
