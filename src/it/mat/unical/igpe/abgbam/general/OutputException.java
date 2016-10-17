package it.mat.unical.igpe.abgbam.general;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
public class OutputException extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JDialog dialog;
	private MainFrame frame;
	private ModifiedButton okButton;
	private JLabel write;
	private JLabel write1;
	public OutputException(MainFrame frame, int i)
	{
		if (frame == null)
			this.frame = frame;
		else
			this.frame = new MainFrame();
		frame.setCursor(frame.getCustomCursor());
		this.dialog = new JDialog(this.frame);
		this.dialog.setSize(600, 300);
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 5));
		this.write = new JLabel();
		this.write1 = new JLabel();
		this.requestFocus();
		this.setSize(this.dialog.getWidth(), this.dialog.getHeight());
		this.setLayout(null);
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.write.setText("Error!!!");
		if (i == 1)
			this.write1.setText("Cannot create file");
		else if (i == 2)
			this.write1.setText("Cannot save Track File");
		else if (i == 3)
			this.write1.setText("Error with timer!");
		else if (i == 4)
			this.write1.setText("Cannot update Scoreboard");
		else if (i == 5)
			this.write1.setText("There is another server!");
		this.write.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.10)));
		this.write1.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.08)));
		this.write.setForeground(Color.RED);
		this.write.setBounds(0, (int) (this.getHeight() * 0.15), this.getWidth(), 50);
		this.write.setHorizontalAlignment(0);
		this.add(this.write);
		this.okButton = new ModifiedButton("", ImageProvider.getIconOk1(), ImageProvider.getIconOk2(),
				null, e -> frame.switchPanelStart(this.dialog));
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
		this.write1.setBounds(0, (int) (this.getHeight() * 0.40), this.getWidth(), 50);
		this.write1.setHorizontalAlignment(0);
		this.add(this.write1);
		this.dialog.setUndecorated(true);
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.dialog.setVisible(true);
	}
}
