package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class RightPanel extends AbstractSidePanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton comeToMenu;
	private ModifiedButton down;
	private ModifiedButton exitToWindows;
	private ModifiedButton save;
	private ModifiedButton up;
	public RightPanel(MainFrame frame)
	{
		super(frame);
		this.title = new TitledBorder(new LineBorder(Color.RED, 5), "Options");
		this.title.setTitleColor(Color.RED);
		this.setBounds(frame.getWidth() - this.getWidth(), 0, this.getWidth(), this.getHeight());
		this.title.setTitleFont(new Font("Showcard Gothic", 0, 17));
		this.setBorder(this.title);
		this.setLayout(new GridLayout(5, 1, 10, 5));
		this.setupButton();
	}
	private void setupButton()
	{
		this.save = new ModifiedButton("", ImageProvider.getIconSave(), ImageProvider.getIconSave(),
				this.frame, e -> this.frame.saveTrack());
		this.save.setBackground(Color.red);
		this.add(this.save);
		this.save.setEnabled(false);
		this.buttons.add(this.save);
		this.up = new ModifiedButton("", ImageProvider.getIconUp(), ImageProvider.getIconUp(),
				this.frame, e -> this.frame.getCentrePanel().nextStep());
		this.up.setBackground(Color.red);
		this.up.setEnabled(false);
		this.add(this.up);
		this.buttons.add(this.up);
		this.down = new ModifiedButton("", ImageProvider.getIconDown(), ImageProvider.getIconDown(),
				this.frame, e -> this.frame.getCentrePanel().previousStep());
		this.down.setBackground(Color.red);
		this.down.setEnabled(false);
		this.down.setEnabled(false);
		this.add(this.down);
		this.buttons.add(this.down);
		this.comeToMenu = new ModifiedButton("", ImageProvider.getIconComeBack(),
				ImageProvider.getIconComeBack(), this.frame, e -> this.frame.comeBackToMenu());
		this.comeToMenu.setBackground(Color.RED);
		this.comeToMenu.setBorder(BorderFactory.createLineBorder(Color.blue.darker().darker(), 4));
		this.add(this.comeToMenu);
		this.buttons.add(this.comeToMenu);
		this.exitToWindows = new ModifiedButton("", ImageProvider.getIconExit1(),
				ImageProvider.getIconExit1(), this.frame, e -> this.frame.showExitConfirm());
		this.exitToWindows.setBackground(Color.RED);
		this.add(this.exitToWindows);
		this.buttons.add(this.exitToWindows);
	}
	public void setDown(boolean b)
	{
		this.down.setEnabled(b);
	}
	public void setUp(boolean b)
	{
		this.up.setEnabled(b);
	}
}
