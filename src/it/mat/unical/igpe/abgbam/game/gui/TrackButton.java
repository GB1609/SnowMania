package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.SoundProvider;
public class TrackButton extends JButton
{
	private static final long serialVersionUID = 1;
	private boolean evidence;
	private Font font;
	private MainFrame frame;
	private MouseListener passOn;
	public TrackButton(String name, MainFrame frame, ActionListener a)
	{
		super(name);
		this.passOn = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
			}
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				if (TrackButton.this.frame.getAUDIO())
				{
					SoundProvider.getReproducerEnteredSound().loop(0);
					SoundProvider.getReproducerEnteredSound().setFramePosition(0);
				}
				if (TrackButton.this.evidence)
					TrackButton.this.setContentAreaFilled(true);
			}
			@Override
			public void mouseExited(MouseEvent arg0)
			{
				if (TrackButton.this.evidence)
					TrackButton.this.setContentAreaFilled(false);
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
			}
			@Override
			public void mouseReleased(MouseEvent e)
			{
				TrackButton.this.setContentAreaFilled(false);
				if (TrackButton.this.frame.getAUDIO())
				{
					SoundProvider.getReproducerClick().loop(0);
					SoundProvider.getReproducerClick().setFramePosition(0);
				}
			}
		};
		this.frame = frame;
		this.addActionListener(a);
		this.setBackground(Color.BLUE.darker().darker());
		this.evidence = true;
		this.setContentAreaFilled(false);
		this.setForeground(Color.WHITE);
		this.setBorderPainted(false);
		this.font = new Font("Showcard Gothic", 0, 30);
		this.setFont(this.font);
		this.setFocusPainted(false);
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
