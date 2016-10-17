package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class LeftPanel extends AbstractSidePanel
{
	private static final long serialVersionUID = 1L;
	private ModifiedButton advertising;
	private ModifiedButton bonusF1;
	private ModifiedButton bonusImmune;
	private ModifiedButton ramp;
	private ModifiedButton rock;
	private ModifiedButton slalomDelimiter;
	public LeftPanel(MainFrame frame)
	{
		super(frame);
		this.title = new TitledBorder(new LineBorder(Color.GREEN.darker(), 5), "Track's Objects");
		this.title.setTitleColor(Color.GREEN.darker());
		this.title.setTitleFont(new Font("Showcard Gothic", 0, 17));
		this.setBorder(this.title);
		this.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.setLayout(new GridLayout(6, 1, 0, 5));
		this.setupButton();
		this.setAlignmentX(0.5f);
		this.setVisible(true);
	}
	private void setupButton()
	{
		this.advertising = new ModifiedButton("", ImageProvider.getIconAdvertising(),
				ImageProvider.getIconAdvertising(), this.frame,
				e -> this.frame.getCentrePanel().setSelected(1));
		this.advertising.setBackground(Color.GREEN);
		this.add(this.advertising);
		this.advertising.setEnabled(false);
		this.add(this.advertising);
		this.buttons.add(this.advertising);
		this.rock = new ModifiedButton("", ImageProvider.getIconRock(), ImageProvider.getIconRock(),
				this.frame, e -> this.frame.getCentrePanel().setSelected(2));
		this.rock.setBackground(Color.green);
		this.add(this.rock);
		this.buttons.add(this.rock);
		this.rock.setEnabled(false);
		this.ramp = new ModifiedButton("", ImageProvider.getIconRampUp(),
				ImageProvider.getIconRampUp(), this.frame,
				e -> this.frame.getCentrePanel().setSelected(3));
		this.ramp.setBackground(Color.green);
		this.ramp.setEnabled(false);
		this.add(this.ramp);
		this.buttons.add(this.ramp);
		this.bonusF1 = new ModifiedButton("", ImageProvider.getIconBonusF1(),
				ImageProvider.getIconBonusF1(), this.frame,
				e -> this.frame.getCentrePanel().setSelected(4));
		this.bonusF1.setBackground(Color.green);
		this.bonusF1.setEnabled(false);
		this.add(this.bonusF1);
		this.buttons.add(this.bonusF1);
		this.bonusImmune = new ModifiedButton("", ImageProvider.getIconBonusImmune(),
				ImageProvider.getIconBonusImmune(), this.frame,
				e -> this.frame.getCentrePanel().setSelected(6));
		this.bonusImmune.setBackground(Color.green);
		this.bonusImmune.setEnabled(false);
		this.add(this.bonusImmune);
		this.buttons.add(this.bonusImmune);
		this.slalomDelimiter = new ModifiedButton("", ImageProvider.getIconSlalomDelimiter(),
				ImageProvider.getIconSlalomDelimiter(), this.frame,
				e -> this.frame.getCentrePanel().setSelected(5));
		this.slalomDelimiter.setBackground(Color.green);
		this.slalomDelimiter.setEnabled(false);
		this.add(this.slalomDelimiter);
		this.buttons.add(this.slalomDelimiter);
	}
}
