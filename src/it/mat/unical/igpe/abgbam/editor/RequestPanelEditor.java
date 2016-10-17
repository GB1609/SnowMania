package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class RequestPanelEditor extends JPanel
{
	private static final long serialVersionUID = 1;
	private MapPanel centrePanel;
	private JDialog dialog;
	private MainFrame frame;
	private int i;
	private ModifiedButton noButton;
	private ModifiedButton okButton;
	private JLabel write;
	private JLabel write1;
	private ModifiedButton yesButton;
	public RequestPanelEditor(MainFrame frame, MapPanel centre, int i)
	{
		this.i = i;
		this.frame = frame;
		this.setCentrePanel(centre);
		this.dialog = new JDialog(this.frame);
		this.dialog.setSize(366, 168);
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.write = new JLabel();
		this.write1 = new JLabel();
		this.requestFocus();
		this.setSize(this.dialog.getWidth(), this.dialog.getHeight());
		this.setLayout(null);
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		if (i == 0)
		{
			this.write.setText("Are you sure to create a new track?");
			this.write1.setText("You lost all changes!!!");
			this.write.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.11)));
			this.write1
					.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.15)));
			this.noButton = new ModifiedButton("", ImageProvider.getIconNoExit1(),
					ImageProvider.getIconNoExit2(), this.frame, e -> this.selectNo());
			this.yesButton = new ModifiedButton("", ImageProvider.getIconYesExit1(),
					ImageProvider.getIconYesExit2(), this.frame, e -> this.selectYes());
			this.write.setForeground(Color.WHITE);
			this.noButton.setBounds(
					(int) (this.getWidth() * 0.9) - ImageProvider.getIconNoExit1().getIconWidth(),
					(int) (this.getHeight() * 0.7), ImageProvider.getIconNoExit1().getIconWidth(),
					ImageProvider.getIconNoExit1().getIconHeight());
			this.yesButton.setBounds((int) (this.getWidth() * 0.1), (int) (this.getHeight() * 0.7),
					ImageProvider.getIconYesExit1().getIconWidth(),
					ImageProvider.getIconYesExit1().getIconHeight());
			this.write.setBounds(0, (int) (this.getHeight() * 0.1), this.getWidth(), 30);
			this.write.setHorizontalAlignment(0);
			this.yesButton.setEvidence(false);
			this.yesButton.setBorderPainted(false);
			this.noButton.setEvidence(false);
			this.noButton.setBorderPainted(false);
			this.add(this.write);
			this.add(this.noButton);
			this.add(this.yesButton);
		}
		if (i == 1)
			this.write1.setText("Warning, No track to save.");
		else if (i == 2)
			this.write1.setText("Track save!");
		else
		{
			if (i == 3)
			{
				this.write1.setText("You insert wrong date");
				this.write.setText("Error.");
			}
			else if (i == 4)
			{
				this.write1.setText("Check if the server");
				JLabel write2 = new JLabel("is started.");
				write2.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.15)));
				write2.setForeground(Color.WHITE);
				write2.setBounds(0, (int) (this.getHeight() * 0.5), this.getWidth(), 30);
				write2.setHorizontalAlignment(0);
				this.add(write2);
				this.write.setText("No Server found!");
			}
			this.write.setForeground(Color.RED.brighter());
			this.write.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.11)));
			this.write.setBounds(0, (int) (this.getHeight() * 0.1), this.getWidth(), 30);
			this.write.setHorizontalAlignment(0);
			this.add(this.write);
		}
		this.okButton = new ModifiedButton("", ImageProvider.getIconOk1(), ImageProvider.getIconOk2(),
				this.frame, e -> this.selectOk());
		this.okButton.setEvidence(false);
		this.okButton.setBorderPainted(false);
		this.okButton.setBounds(
				(this.getWidth() / 2) - (ImageProvider.getIconOk1().getIconWidth() / 2),
				(int) (this.getHeight() * 0.7), ImageProvider.getIconOk1().getIconWidth(),
				ImageProvider.getIconOk1().getIconHeight());
		this.okButton.setHorizontalAlignment(0);
		this.add(this.okButton);
		this.write1.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.15)));
		this.write1.setForeground(Color.WHITE);
		this.write1.setBounds(0, (int) (this.getHeight() * 0.3), this.getWidth(), 30);
		this.write1.setHorizontalAlignment(0);
		this.add(this.write1);
		this.dialog.setUndecorated(true);
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setVisible(true);
	}
	private void selectNo()
	{
		this.dialog.dispose();
		this.frame.enableAllButtons();
	}
	private void selectOk()
	{
		this.dialog.dispose();
		this.frame.enableAllButtons();
		if (this.i == 2)
		{
			this.frame.resetCentrePanel();
			this.frame.switchPanelEditor();
		}
		else if (this.i == 3)
			this.frame.editorCentreCreator();
	}
	private void selectYes()
	{
		this.frame.resetCentrePanel();
		this.dialog.dispose();
		this.frame.enableAllButtons();
		this.dialog.dispose();
		this.frame.editorCentreCreator();
	}
	public MapPanel getCentrePanel()
	{
		return this.centrePanel;
	}
	public void setCentrePanel(MapPanel centre)
	{
		this.centrePanel = centre;
	}
}
