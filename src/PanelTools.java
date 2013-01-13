
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

	private PanelImage panelImage;

	public PanelTools()
	{
		super();

		value = new JTextField("None");
		convert = new JButton("Convertir");
		load = new JButton("Charger");
		save = new JButton("Sauver");
	
		value.setEnabled(false);

		this.setLayout(new GridLayout(9,1));
		
		this.add(load);
		this.add(save);
		this.add(value);
		this.add(convert);
		
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
					this.getParent().repaint();
				}
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
