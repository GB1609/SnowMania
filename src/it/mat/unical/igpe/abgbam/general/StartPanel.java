package it.mat.unical.igpe.abgbam.general;
import java.awt.Graphics;
import it.mat.unical.igpe.abgbam.game.gui.SelectModality;
import it.mat.unical.igpe.abgbam.game.gui.SelectTrack;
public class StartPanel extends GeneralPanel
{
	private static final long serialVersionUID = 1;
	private final ModifiedButton editorButton;
	private final ModifiedButton exitButton;
	private final ModifiedButton optionButton;
	private final ModifiedButton playButton;
	private final ModifiedButton scoreBoardButton;
	public StartPanel(MainFrame frame)
	{
		super(frame);
		this.setLayout(null);
		this.exitButton = new ModifiedButton("exit", ImageProvider.getIconExit1(),
				ImageProvider.getIconExit2(), this.frame, e -> this.frame.showExitConfirm());
		this.exitButton.setBounds((int) (frame.getWidth() * 0.84), (int) (frame.getHeight() * 0.80),
				ImageProvider.getIconExit1().getIconWidth(),
				ImageProvider.getIconExit1().getIconHeight());
		this.optionButton = new ModifiedButton("option", ImageProvider.getIconOption1(),
				ImageProvider.getIconOption2(), this.frame, e -> frame.switchPanelOption());
		this.optionButton.setBounds((int) (frame.getWidth() * 0.69), (int) (frame.getHeight() * 0.80),
				this.optionButton.getWidth(), this.optionButton.getHeight());
		this.playButton = new ModifiedButton("play", ImageProvider.getIconPlay1(),
				ImageProvider.getIconPlay2(), this.frame, e -> new SelectModality(frame));
		this.playButton.setBounds((this.frame.getWidth() * 5) / 100,
				(this.frame.getHeight() * 5) / 100, ImageProvider.getIconPlay1().getIconWidth(),
				ImageProvider.getIconPlay1().getIconHeight());
		this.scoreBoardButton = new ModifiedButton("scoreboard", ImageProvider.getIconScoreBoard1(),
				ImageProvider.getIconScoreBoard2(), this.frame,
				e -> new SelectTrack(frame, false, false));
		this.scoreBoardButton.setBounds((this.frame.getWidth() * 35) / 100,
				(this.frame.getHeight() * 5) / 100, ImageProvider.getIconScoreBoard1().getIconWidth(),
				ImageProvider.getIconPlay1().getIconHeight());
		this.editorButton = new ModifiedButton("editor", ImageProvider.getIconEditor1(),
				ImageProvider.getIconEditor2(), this.frame, e -> this.frame.switchPanelEditor());
		this.editorButton.setBounds((this.frame.getWidth() * 20) / 100,
				(this.frame.getHeight() * 5) / 100, ImageProvider.getIconEditor1().getIconWidth(),
				ImageProvider.getIconEditor1().getIconHeight());
		this.buttons.add(this.exitButton);
		this.buttons.add(this.optionButton);
		this.buttons.add(this.scoreBoardButton);
		this.buttons.add(this.playButton);
		this.buttons.add(this.editorButton);
		this.addButtons();
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(ImageProvider.getStartImage(), 0, 0, this.getWidth(), this.getHeight(), null);
	}
}
