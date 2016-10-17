package it.mat.unical.igpe.abgbam.game.gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class SelectModality extends JPanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton comeBack;
	private JDialog dialog;
	private ModifiedButton downhill;
	private MainFrame frame;
	private ModifiedButton multiplayer;
	private JLabel write;
	public SelectModality(MainFrame frame)
	{
		frame.setCursor(frame.getCustomCursor());
		this.frame = frame;
		this.requestFocus();
		this.downhill = new ModifiedButton("", ImageProviderGame.getDownhill(),
				ImageProviderGame.getDownhill2(), frame, e -> this.selectTrackDownhill());
		this.multiplayer = new ModifiedButton("", ImageProviderGame.getMultiplayer(),
				ImageProviderGame.getMultiplayer2(), frame, e -> this.showType());
		this.comeBack = new ModifiedButton("", ImageProviderGame.getClose(),
				ImageProviderGame.getClose2(), this.frame, e -> this.dialog.dispose());
		this.write = new JLabel("Select modality of game");
		this.write.setForeground(Color.WHITE);
		this.dialog = new JDialog(this.frame);
		this.dialog.setSize((int) (frame.getWidth() * 0.5), (int) ((this.frame.getHeight() * 0.1)
				+ ImageProviderGame.getMultiplayer().getIconHeight()));
		if (frame.getWidth() == 1024)
			this.dialog.setSize((int) (frame.getWidth() * 0.8), (int) ((this.frame.getHeight() * 0.1)
					+ ImageProviderGame.getMultiplayer().getIconHeight()));
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.dialog.setUndecorated(true);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.setSize(this.dialog.getSize());
		this.write.setFont(new Font("Showcard Gothic", 0, (int) (this.frame.getHeight() * 0.05)));
		this.setBackground(Color.BLUE);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(this.downhill);
		this.add(Box.createHorizontalStrut((int) (this.getWidth() * 0.03)));
		this.add(this.multiplayer);
		this.add(Box.createHorizontalStrut((int) (this.getWidth() * 0.03)));
		this.add(this.comeBack);
		this.add(BorderLayout.SOUTH, this.write);
		this.setVisible(true);
		this.dialog.setVisible(true);
	}
	private void selectTrackDownhill()
	{
		this.dialog.dispose();
		new SelectTrack(this.frame, true, false);
	}
	private void showType()
	{
		new TypeOnline(this.frame);
		this.dialog.dispose();
	}
}
