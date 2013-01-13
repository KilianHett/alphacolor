
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
  * @author HETT KILIAN
  * Classe principale de l'affichage, comprends une PanelImage
  * une PanelTools et un MenuBar.
  * Aucune interaction souris
*/
public final class FrameView extends JFrame
{
	private PanelImage panelImage;
	private PanelTools panelTools;

	public FrameView()
	{
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(900,800);
		this.setPreferredSize(new Dimension(900,800));
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255,0,0));

		panelImage = new PanelImage();
		JScrollPane scroll = new JScrollPane(panelImage);
		panelTools = new PanelTools();
		panelTools.setPanelImage(panelImage);
	
		initEvent();

		this.add(scroll,BorderLayout.CENTER);
		this.add(panelTools,BorderLayout.EAST);
		
		this.repaint();
		this.pack();
	}
	
	/**
	 * Fonction initialisant les évenements liés à 
	 * l'interface grpahique.
	*/
	private	void initEvent()
	{
		panelImage.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				int v = panelImage.getPixel(e.getX(),e.getY());
				int cv = ColorUtils.complement(v);
				
				panelImage.setColor(v);

				Color back = new Color(ColorUtils.canalRouge(v),
					ColorUtils.canalVert(v),
					ColorUtils.canalBleu(v));
				Color text = new Color(ColorUtils.canalRouge(cv),
					ColorUtils.canalVert(cv),
					ColorUtils.canalBleu(cv));
				panelTools.modifyValue(back,text);
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}
		});
	}

	public void setImage(BufferedImage im)
	{
		panelImage.setImage(im);
		this.repaint();
	}

	public void paint(Graphics g)
	{ 	
		if (panelImage!=null)
			panelImage.repaint();
		if (panelTools!=null)
			panelTools.repaint();
	}
}

