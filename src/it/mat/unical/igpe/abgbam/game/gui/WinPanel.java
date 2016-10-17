package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JDialog;
import it.mat.unical.igpe.abgbam.game.network.ImageProviderNetwork;
import it.mat.unical.igpe.abgbam.general.GeneralPanel;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class WinPanel extends GeneralPanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton close;
	private JDialog dialog;
	private ModifiedButton restart;
	int i;
	public WinPanel(MainFrame frame, int i)
	{
		super(frame);
		frame.setCursor(frame.getCustomCursor());
		this.i = i;
		this.setLayout(null);
		this.dialog = new JDialog();
		this.dialog.setSize(800, 700);
		this.setSize(this.dialog.getSize());
		this.dialog.setUndecorated(true);
		this.dialog.setBackground(new Color(0, 0, 0, 2));
		this.setOpaque(true);
		this.close = new ModifiedButton("", ImageProviderNetwork.getClose1(),
				ImageProviderNetwork.getClose2(), frame, e -> this.frame.switchPanelStart(this.dialog));
		this.restart = new ModifiedButton("", ImageProviderNetwork.getRestart1(),
				ImageProviderNetwork.getRestart2(), frame, e -> this.selectRestart());
		this.close.setBounds((int) (this.getWidth() * 0.1),
				this.getHeight() - (ImageProviderNetwork.getClose2().getIconHeight() * 2),
				ImageProviderNetwork.getClose2().getIconWidth(),
				ImageProviderNetwork.getClose2().getIconHeight());
		this.restart.setBounds(
				(int) (this.getWidth() * 0.9) - (ImageProviderNetwork.getRestart2().getIconWidth()),
				this.getHeight() - (ImageProviderNetwork.getClose2().getIconHeight() * 2),
				ImageProviderNetwork.getRestart2().getIconWidth(),
				ImageProviderNetwork.getRestart2().getIconHeight());
		this.add(this.close);
		this.add(this.restart);
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setVisible(true);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		if (this.i == 1)
			g.drawImage(ImageProviderNetwork.getWinPanel(), 0, 0, null);
		else
			g.drawImage(ImageProviderNetwork.getLosePanel(), 0, 0, null);
	}
	private void selectRestart()
	{
		this.dialog.dispose();
		new TypeOnline(this.frame);
	}
}
