package it.mat.unical.igpe.abgbam.general;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class RequestPanel extends JPanel
{
	private static final long serialVersionUID = 1;
	private ModifiedButton closeButton;
	private JDialog dialog;
	private MainFrame frame;
	private String name;
	private ModifiedButton noButton;
	private int requestType;
	private JLabel write;
	private ModifiedButton yesButton;
	public RequestPanel(MainFrame f, int rt, String name)
	{
		f.setCursor(f.getCustomCursor());
		this.name = name;
		this.frame = f;
		this.requestType = rt;
		this.dialog = new JDialog(this.frame);
		if (rt == 1)
			this.dialog.setSize(ImageProvider.getRequestBackGroundExit().getWidth(null),
					ImageProvider.getRequestBackGroundExit().getHeight(null));
		else if (rt == 2)
			this.dialog.setSize(ImageProvider.getRequestBackGroundMenu().getWidth(null),
					ImageProvider.getRequestBackGroundMenu().getHeight(null));
		else
			this.dialog.setSize(ImageProvider.getScorePanel().getWidth(null),
					ImageProvider.getScorePanel().getHeight(null)
							+ (ImageProvider.getClose().getIconHeight() / 2));
		this.requestFocus();
		this.setSize(this.dialog.getWidth(), this.dialog.getHeight());
		this.setLayout(null);
		if ((rt == 1) || (rt == 2))
		{
			if (rt == 1)
			{
				this.noButton = new ModifiedButton("", ImageProvider.getIconNoExit1(),
						ImageProvider.getIconNoExit2(), this.frame, e -> this.selectNo());
				this.yesButton = new ModifiedButton("", ImageProvider.getIconYesExit1(),
						ImageProvider.getIconYesExit2(), this.frame, e -> this.selectYes());
				this.noButton.setBounds(10, 185, ImageProvider.getIconNoExit1().getIconWidth(),
						ImageProvider.getIconNoExit1().getIconHeight());
				this.yesButton.setBounds(10, 227, ImageProvider.getIconYesExit1().getIconWidth(),
						ImageProvider.getIconYesExit1().getIconHeight());
			}
			else
			{
				this.noButton = new ModifiedButton("", ImageProvider.getIconNoMenu1(),
						ImageProvider.getIconNoMenu2(), this.frame, e -> this.selectNo());
				this.yesButton = new ModifiedButton("", ImageProvider.getIconYesMenu1(),
						ImageProvider.getIconYesMenu2(), this.frame,
						e -> this.frame.switchPanelStart(this.dialog));
				this.noButton.setBounds(20,
						this.getHeight() - (ImageProvider.getIconYesMenu1().getIconHeight() + 20),
						ImageProvider.getIconNoMenu1().getIconWidth(),
						ImageProvider.getIconNoMenu1().getIconHeight());
				this.yesButton.setBounds(
						this.getWidth() - (20 + ImageProvider.getIconYesMenu1().getIconWidth()),
						this.getHeight() - (ImageProvider.getIconYesMenu1().getIconHeight() + 20),
						ImageProvider.getIconYesMenu1().getIconWidth(),
						ImageProvider.getIconYesMenu1().getIconHeight());
			}
			this.yesButton.setEvidence(false);
			this.yesButton.setBorderPainted(false);
			this.noButton.setEvidence(false);
			this.noButton.setBorderPainted(false);
			this.add(this.noButton);
			this.add(this.yesButton);
		}
		else
		{
			JLabel nameTRACK = new JLabel(name);
			nameTRACK.setForeground(Color.BLACK);
			nameTRACK.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.05)));
			nameTRACK.setBounds(102, 97, 331, 102);
			nameTRACK.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			this.add(nameTRACK);
			if (this.frame.getScoreboard().isEmpty())
			{
				this.write = new JLabel("Sorry! No score to visualizate");
				this.write.setForeground(Color.WHITE);
				this.write.setBounds((this.getWidth() / 8), this.getHeight() / 2, this.getWidth(),
						this.dialog.getHeight() / 15);
				this.write
						.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.03)));
				this.add(this.write);
			}
			else
			{
				int i = 1;
				int gap = (this.getHeight() / 3);
				for (ScoreBoardLine scoreEntry : this.frame.getScoreboard())
				{
					this.write = new JLabel("Pos " + i + "   ->   " + scoreEntry.getLine());
					this.write.setForeground(Color.WHITE);
					this.write.setBounds((this.getWidth() / 8), gap, this.getWidth(),
							this.dialog.getHeight() / 15);
					this.write.setFont(
							new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.03)));
					this.add(this.write);
					gap += 30;
					i++;
				}
			}
			this.closeButton = new ModifiedButton("", ImageProvider.getClose(),
					ImageProvider.getClose1(), this.frame, e -> this.selectNo());
			this.closeButton.setBounds(
					(this.getWidth() / 2) - (ImageProvider.getClose().getIconWidth() / 2),
					(int) (this.getHeight() * 0.87), ImageProvider.getClose().getIconWidth(),
					ImageProvider.getClose().getIconHeight());
			this.closeButton.setEvidence(false);
			this.closeButton.setBorderPainted(false);
			this.add(this.closeButton);
		}
		this.setVisible(true);
		this.dialog.setUndecorated(true);
		this.dialog.setBackground(new Color(0, 0, 0, 2));
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setVisible(true);
	}
	private void selectNo()
	{
		if ((this.requestType == 1) && this.frame.getAUDIO())
			SoundProvider.getReproducerExcellent().loop(0);
		if ((this.name != null) && (this.requestType == 3))
			this.frame.switchPanelStart(this.dialog);
		this.dialog.dispose();
		this.frame.enableAllButtons();
	}
	private void selectYes()
	{
		if (this.frame.getAUDIO())
		{
			SoundProvider.getReproducerBye().loop(1);
			while (SoundProvider.getReproducerBye()
					.getFramePosition() < (SoundProvider.getReproducerBye().getFrameLength() * 2))
			{
			}
		}
		System.exit(0);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		if (this.requestType == 1)
			g.drawImage(ImageProvider.getRequestBackGroundExit(), 0, 0, this.getWidth(),
					this.getHeight(), null);
		else if (this.requestType == 2)
			g.drawImage(ImageProvider.getRequestBackGroundMenu(), 0, 0, this.getWidth(),
					this.getHeight(), null);
		else
			g.drawImage(ImageProvider.getScorePanel(), 0, 0,
					ImageProvider.getScorePanel().getWidth(null),
					ImageProvider.getScorePanel().getHeight(null), null);
	}
}
