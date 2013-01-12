
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

/**
 * @author HETT KILIAN
 * @see FrameView
 * Cette classe se charge d'afficher les images
 * elle supporte les comportements de clique de la souris
**/
public class PanelImage extends JPanel
{
	private BufferedImage image;

	public PanelImage(String url)
	{
		super();
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
		try
		{
			image = ImageIO.read(url);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	
	}

	/**
	  * @param Graphic g
	  * Surcharge de la méthode paint : permet d'afficher l'image
	*/
	public void paint(Graphics g)
	{
		g.drawImage(image,0,0,null);
	}

}
