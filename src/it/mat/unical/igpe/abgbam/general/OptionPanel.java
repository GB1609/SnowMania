package it.mat.unical.igpe.abgbam.general;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
public class OptionPanel extends GeneralPanel
{
	private static final long serialVersionUID = 1;
	private int beginOrizontalString;
	private int beginVerticalString;
	private ButtonGroup buttonGroupFullSize = new ButtonGroup();
	private ButtonGroup buttonGroupResolution = new ButtonGroup();
	private ButtonGroup buttonGroupSound = new ButtonGroup();
	private ModifiedButton comeToMenuButton;
	private JLabel enter;
	private ModifiedButton exitButton;
	private Font font;
	private Font font1;
	private JCheckBox fullNo = new JCheckBox("No");
	private JLabel fullSize;
	private JCheckBox fullYes = new JCheckBox("Yes");
	private int heightString;
	private JLabel name;
	private JTextArea namePlayer;
	private JLabel resolution;
	private JCheckBox resolution1 = new JCheckBox("1024 x 768");
	private JCheckBox resolution2 = new JCheckBox("1366 x 768");
	private JCheckBox resolution3 = new JCheckBox("1600 x 900");
	private JCheckBox resolution4 = new JCheckBox("1920 x 1080");
	private JLabel sound;
	private JCheckBox soundsNo = new JCheckBox("No");
	private JCheckBox soundsYes = new JCheckBox("Yes");
	private int widthString;
	public OptionPanel(final MainFrame frame)
	{
		super(frame);
		this.setLayout(null);
		this.exitButton = new ModifiedButton("exit", ImageProvider.getIconExit1(),
				ImageProvider.getIconExit2(), frame, e -> frame.showExitConfirm());
		this.exitButton.setBounds(frame.getWidth() - ((frame.getWidth() * 15) / 100),
				frame.getHeight() - ((frame.getHeight() * 20) / 100),
				ImageProvider.getIconExit1().getIconWidth(),
				ImageProvider.getIconExit1().getIconHeight());
		this.comeToMenuButton = new ModifiedButton("Startpage", ImageProvider.getIconComeToMenu1(),
				ImageProvider.getIconComeToMenu2(), frame, e -> frame.comeBackToMenu());
		this.comeToMenuButton.setBounds(frame.getWidth() - ((frame.getWidth() * 30) / 100),
				frame.getHeight() - ((frame.getHeight() * 20) / 100),
				ImageProvider.getIconComeToMenu1().getIconWidth(),
				ImageProvider.getIconComeToMenu1().getIconHeight());
		this.font = new Font("Showcard Gothic", 0, (int) (frame.getHeight() * 0.04));
		this.font1 = new Font("Showcard Gothic", 0, (int) (frame.getHeight() * 0.02));
		this.widthString = (int) (frame.getWidth() * 0.3);
		this.heightString = (int) (frame.getHeight() * 0.14);
		this.beginOrizontalString = (int) (frame.getWidth() * 0.04);
		this.beginVerticalString = (int) (frame.getHeight() * 0.06);
		this.namePlayer = new JTextArea(frame.getNamePlayer());
		this.namePlayer.setBackground(Color.WHITE);
		this.namePlayer.setFont(this.font);
		this.namePlayer.setAlignmentY(CENTER_ALIGNMENT);
		this.namePlayer.setBounds(this.beginOrizontalString, (int) (this.getHeight() * 0.17),
				(int) (this.getWidth() * 0.25), (int) (this.getHeight() * 0.05));
		this.namePlayer.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					OptionPanel.this.requestFocus();
					frame.setNamePlayer(OptionPanel.this.namePlayer.getText());
				}
			}
		});
		this.namePlayer.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				OptionPanel.this.namePlayer.setText("");
			}
		});
		this.add(this.namePlayer);
		this.createString(this.name, "Enter your Name:", this.beginOrizontalString,
				this.beginVerticalString, this.font, this.widthString, this.heightString);
		this.createString(this.fullSize, "Full Size", this.beginOrizontalString,
				this.beginVerticalString + this.heightString, this.font, this.widthString,
				this.heightString);
		this.createString(this.resolution, "Resolution:", this.beginOrizontalString,
				this.beginVerticalString + (this.heightString * 2), this.font, this.widthString,
				this.heightString);
		this.createString(this.sound, "Sounds:", this.beginOrizontalString,
				this.beginVerticalString + (this.heightString * 3), this.font, this.widthString,
				this.heightString);
		this.createString(this.enter, "press enter for", (int) (this.getWidth() * 0.30),
				(int) (this.getHeight() * 0.12), this.font1, this.widthString, this.heightString);
		this.createString(this.enter, "change player's name", (int) (this.getWidth() * 0.30),
				(int) (this.getHeight() * 0.14), this.font1, this.widthString, this.heightString);
		this.createCheck(this.fullYes, this.font, this.beginOrizontalString * 2,
				this.beginVerticalString + this.heightString + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.fullNo, this.font, this.beginOrizontalString * 6,
				this.beginVerticalString + this.heightString + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.soundsYes, this.font, this.beginOrizontalString * 2,
				this.beginVerticalString + (this.heightString * 3) + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.soundsNo, this.font, this.beginOrizontalString * 6,
				this.beginVerticalString + (this.heightString * 3) + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.resolution1, this.font1, this.beginOrizontalString,
				this.beginVerticalString + (this.heightString * 2) + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.resolution2, this.font1, this.beginOrizontalString * 4,
				this.beginVerticalString + (this.heightString * 2) + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.resolution3, this.font1, this.beginOrizontalString * 7,
				this.beginVerticalString + (this.heightString * 2) + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		this.createCheck(this.resolution4, this.font1, this.beginOrizontalString * 10,
				this.beginVerticalString + (this.heightString * 2) + (this.heightString / 2),
				(int) (this.widthString * 0.3), this.heightString);
		if (frame.getScreensize() == this.getWidth())
			this.fullYes.setSelected(true);
		else
			this.fullNo.setSelected(true);
		if (frame.getAUDIO())
			this.soundsYes.setSelected(true);
		else
			this.soundsNo.setSelected(true);
		switch (frame.getWidth())
		{
		case 1024:
		{
			this.resolution1.setSelected(true);
			break;
		}
		case 1366:
		{
			this.resolution2.setSelected(true);
			break;
		}
		case 1600:
		{
			this.resolution3.setSelected(true);
			break;
		}
		case 1920:
		{
			this.resolution4.setSelected(true);
			break;
		}
		default:
		{
			this.resolution2.setSelected(true);
		}
		}
		this.verify(this.resolution1, 1024);
		this.verify(this.resolution2, 1366);
		this.verify(this.resolution3, 1600);
		this.verify(this.resolution4, 1920);
		this.fullNo.addActionListener(e -> frame.setFull(false));
		this.fullYes.addActionListener(e -> frame.setFull(true));
		this.soundsYes.addActionListener(e -> frame.setAUDIO(true));
		this.soundsNo.addActionListener(e -> frame.setAUDIO(false));
		this.resolution1.addActionListener(e -> frame.setResolution(1024));
		this.resolution2.addActionListener(e -> frame.setResolution(1366));
		this.resolution3.addActionListener(e -> frame.setResolution(1600));
		this.resolution4.addActionListener(e -> frame.setResolution(1920));
		this.buttonGroupFullSize.add(this.fullNo);
		this.buttonGroupFullSize.add(this.fullYes);
		this.buttonGroupSound.add(this.soundsNo);
		this.buttonGroupSound.add(this.soundsYes);
		this.buttonGroupResolution.add(this.resolution1);
		this.buttonGroupResolution.add(this.resolution2);
		this.buttonGroupResolution.add(this.resolution3);
		this.buttonGroupResolution.add(this.resolution4);
		this.buttons.add(this.exitButton);
		this.buttons.add(this.comeToMenuButton);
		this.addButtons();
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
				{
					frame.comeBackToMenu();
					break;
				}
				case KeyEvent.VK_ESCAPE:
				{
					frame.showExitConfirm();
					break;
				}
				}
			}
		});
	}
	private void createCheck(JCheckBox checkButton, Font font, int x, int y, int widthButton,
			int heightButton)
	{
		checkButton.setFont(font);
		checkButton.setContentAreaFilled(false);
		checkButton.setForeground(Color.WHITE);
		checkButton.setBorderPaintedFlat(false);
		checkButton.setBounds(x, y, widthButton, heightButton);
		this.add(checkButton);
	}
	private void createString(JLabel write, String name, int x, int y, Font font, int widthString,
			int heightString)
	{
		write = new JLabel(name);
		write.setForeground(Color.WHITE);
		write.setFont(font);
		write.setBounds(x, y, widthString, heightString);
		this.add(write);
	}
	private void verify(JCheckBox resolutionI, int resolution)
	{
		if (resolution > this.frame.getScreensize())
			resolutionI.setEnabled(false);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(ImageProvider.getOptionImage(), 0, 0, this.frame.getWidth(),
				this.frame.getHeight(), null);
	}
}
