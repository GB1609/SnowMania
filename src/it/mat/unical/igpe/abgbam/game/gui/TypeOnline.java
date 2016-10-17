package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.game.network.ImageProviderNetwork;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class TypeOnline extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton client;
	private ModifiedButton close;
	private JDialog dialog;
	private MainFrame frame;
	private ModifiedButton server;
	public TypeOnline(MainFrame frame)
	{
		this.frame = frame;
		this.requestFocus();
		this.dialog = new JDialog(this.frame);
		this.dialog.setSize(400, 300);
		this.setSize(this.dialog.getSize());
		this.client = new ModifiedButton("", ImageProviderNetwork.getClient1(),
				ImageProviderNetwork.getClient2(), frame, e -> this.showConnectionPanel(false));
		this.server = new ModifiedButton("", ImageProviderNetwork.getServer1(),
				ImageProviderNetwork.getServer2(), frame, e -> this.showConnectionPanel(true));
		this.close = new ModifiedButton("", ImageProvider.getX1(), ImageProvider.getX2(), frame,
				e -> this.dialog.dispose());
		this.close.setBorderPainted(false);
		this.close.setEvidence(false);
		this.setLayout(null);
		this.close.setBounds(this.getWidth() - (ImageProvider.getX1().getIconWidth() + 7), 7,
				ImageProvider.getX1().getIconWidth(), ImageProvider.getX1().getIconHeight());
		this.client.setBounds(
				((this.getWidth() / 2) - (ImageProviderNetwork.getClient1().getIconWidth() / 2)), 50,
				ImageProviderNetwork.getClient1().getIconWidth(),
				ImageProviderNetwork.getClient1().getIconHeight());
		this.server.setBounds(
				((this.getWidth() / 2) - (ImageProviderNetwork.getServer1().getIconWidth() / 2)),
				(this.getHeight() - 50 - ImageProviderNetwork.getServer1().getIconHeight()),
				ImageProviderNetwork.getServer1().getIconWidth(),
				ImageProviderNetwork.getServer1().getIconHeight());
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.dialog.setUndecorated(true);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.setBackground(Color.BLUE);
		this.add(this.close);
		this.add(this.client);
		this.add(this.server);
		this.setVisible(true);
		this.dialog.setVisible(true);
	}
	private void showConnectionPanel(boolean server)
	{
		if (server)
			new SelectTrack(this.frame, true, true);
		else
			new ConnectionPanel(this.frame, false);
		this.dialog.dispose();
	}
}
