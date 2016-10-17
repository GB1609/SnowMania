package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Graphics;
import it.mat.unical.igpe.abgbam.game.network.ImageProviderNetwork;
import it.mat.unical.igpe.abgbam.general.GeneralPanel;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public class WaitPanel extends GeneralPanel
{
	private static final long serialVersionUID = 1L;
	public WaitPanel(MainFrame frame)
	{
		super(frame);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(ImageProviderNetwork.getWaitPanel().getScaledInstance(this.getWidth(),
				this.getHeight(), 0), 0, 0, null);
	}
}
