
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Graphics;
import java.awt.Dimension;


/**
  * @author HETT KILIAN
  * Classe principale de l'affichage, comprends une PanelImage
  * une PanelTools et un MenuBar.
  * Aucune interaction souris
*/
public class FrameView extends JFrame
{
	private PanelImage panelImage;
	private PanelTools panelTools;

	public FrameView()
	{
		super();
		this.setPreferredSize(
				new Dimension(900,800,));
		this.setLayout(new BorderLayout());

		panelImage = new PanelImage();
		JScrollPane scroll = new JScrollPane();
		
		panelTools = new PanelTools();

		this.add(scroll,BorderLayout.CENTER);
		this.add(panelTools,BorderLayout.EAST);
	}

	public void paint(Graphics g)
	{
		// TODO Dessiner l'arriere plan ( motif de transparence ) 	

		panelImage.repaint();
		panelTools.repaint();
	}
}

