
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFileChooser;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


/**
 * @author HETT KILIAN
 * Classe contenant les outils du programme
 * Interaction avec la souris seulement au niveau 
 * bouton
*/
public final class PanelTools extends JPanel
{
	private JTextField value;
	private JButton convert;
	private JButton load;
	private JButton save;
	private JButton quit;

	private PanelImage panelImage;

	public PanelTools()
	{
		super();

		value = new JTextField("None");
		convert = new JButton("Convertir");
		load = new JButton("Charger");
		save = new JButton("Sauver");
		quit = new JButton("Quitter");

		value.setEnabled(false);

		this.setLayout(new GridLayout(9,1));
		
		this.add(load);
		this.add(save);
		this.add(value);
		this.add(convert);
		this.add(quit);
		
		initEvent();
		repaint();
	}

	public void initEvent()
	{
		load.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				JFileChooser loader = new JFileChooser("~");
				if(loader.showOpenDialog(load)==JFileChooser.APPROVE_OPTION)
				{
					panelImage.loadImage(loader.getSelectedFile().getPath());
					panelImage.getParent().repaint();
				}
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}	
		});

		convert.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				panelImage.toAlpha();	
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}	
		});

		save.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				JFileChooser loader = new JFileChooser("~");
				if(loader.showSaveDialog(load)==JFileChooser.APPROVE_OPTION)
				{
					ColorUtils.saveImage(panelImage.getImage(),
						loader.getSelectedFile().getPath(),
						ColorUtils.FORMAT_PNG);
				}
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}	
		});

		quit.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);	
			}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e){}
			public void mouseReleased(MouseEvent e){}	
		});

	}

	public void setPanelImage(PanelImage pnlImg)
	{
		panelImage = pnlImg;
	}

	public void paint(Graphics g)
	{
		value.repaint();
		save.repaint();
		convert.repaint();
		load.repaint();
		quit.repaint();
	}

	public void modifyValue(Color back,Color text)
	{
		value.setBackground(back);
		value.setCaretColor(text);
		value.setDisabledTextColor(text);
		value.setText("("+back.getRed()+","+back.getGreen()+
				","+back.getBlue()+")");
	}

}
