package it.mat.unical.igpe.abgbam.general;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
public class ModifiedButton extends JButton
{
	private static final long serialVersionUID = 1;
	private boolean evidence;
	private MouseListener passOn;
	private TitledBorder title;
	public ModifiedButton(String name, Icon icon1, Icon icon2, MainFrame frame, ActionListener a)
	{
		super(icon1);
		this.passOn = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
			}
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				if (frame != null)
					if (frame.getAUDIO())
					{
						SoundProvider.getReproducerEnteredSound().loop(0);
						SoundProvider.getReproducerEnteredSound().setFramePosition(0);
					}
				if (ModifiedButton.this.evidence)
					ModifiedButton.this.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent arg0)
			{
				if (ModifiedButton.this.evidence)
					ModifiedButton.this.setContentAreaFilled(false);
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				ModifiedButton.this.setContentAreaFilled(false);
				if (frame != null)
					if (frame.getAUDIO())
					{
						SoundProvider.getReproducerClick().loop(0);
						SoundProvider.getReproducerClick().setFramePosition(0);
					}
			}
		};
		this.addActionListener(a);
		this.setSize(new Dimension(icon1.getIconWidth(), icon1.getIconHeight()));
		this.setBackground(Color.YELLOW);
		this.title = new TitledBorder(new LineBorder(Color.BLUE.darker().darker(), 3), name);
		this.title.setTitlePosition(5);
		this.title.setTitleColor(Color.BLUE.darker().darker());
		this.title.setTitleFont(new Font("Showcard Gothic", 0, icon1.getIconWidth() / 9));
		this.setBorder(this.title);
		this.evidence = true;
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setRolloverIcon(icon2);
		this.addMouseListener(this.passOn);
	}
	public MouseListener getPassOn()
	{
		return this.passOn;
	}
	public void setEvidence(boolean b)
	{
		this.evidence = false;
	}
}
