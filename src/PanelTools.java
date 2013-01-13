
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFileChooser;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


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
	private JMenu menu;
	private JMenu option;
	private JMenu aide;
	private JMenuBar menubar;
	private JMenuItem load;
	private JMenuItem save;
	private JMenuItem quit;

	private PanelImage panelImage;

	public PanelTools()
	{
		super();

		value = new JTextField("None");
		convert = new JButton("Convertir");
		load = new JMenuItem("Charger");
		save = new JMenuItem("Enregistrer sous");
		quit = new JMenuItem("Quitter");

		
		load.setEnabled(true);
		save.setEnabled(false);
		quit.setEnabled(true);
		
		JPanel pnl = new JPanel();
		pnl.setLayout(new GridLayout(14,1));
		pnl.add(value);
		pnl.add(convert);

		aide = new JMenu("Aide");
		menu = new JMenu("Fichier");
		menubar = new JMenuBar();
		menubar.add(menu);
		menubar.add(aide);
		menu.add(load);
		menu.add(save);
		menu.addSeparator();
		menu.add(quit);
		value.setEnabled(false);

		this.setLayout(new BorderLayout());
		this.add(menubar,BorderLayout.NORTH);
		this.add(pnl,BorderLayout.CENTER);

		initEvent();
		repaint();
	}

	public void initEvent()
	{
		load.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser loader = new JFileChooser("~");
				if(loader.showOpenDialog(load)==JFileChooser.APPROVE_OPTION)
				{
					panelImage.loadImage(loader.getSelectedFile().getPath());
					panelImage.getParent().repaint();
					save.setEnabled(true);
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

		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
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

		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
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
		menu.repaint();
		aide.repaint();

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
