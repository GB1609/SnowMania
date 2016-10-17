package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import it.mat.unical.igpe.abgbam.general.ImageProvider;
import it.mat.unical.igpe.abgbam.general.MainFrame;
import it.mat.unical.igpe.abgbam.general.ModifiedButton;
public class CreateCentrePanel extends JPanel
{
	private static LineBorder line = new LineBorder(Color.BLUE.darker().darker(), 5);
	private static final long serialVersionUID = 1L;
	private ModifiedButton close;
	private JDialog dialog;
	private Font font;
	private MainFrame frame;
	private JTextArea lengthT;
	private JTextArea nameT;
	private ModifiedButton okButton;
	private JLabel write;
	private JLabel write1;
	public CreateCentrePanel(MainFrame frame)
	{
		this.frame = frame;
		this.dialog = new JDialog(this.frame);
		this.dialog.setSize(370, 300);
		this.requestFocus();
		this.setSize(this.dialog.getWidth(), this.dialog.getHeight());
		this.font = new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.1));
		this.setBorder(line);
		this.setLayout(null);
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.write = new JLabel("Insert Track name");
		this.write.setForeground(Color.WHITE);
		this.write.setFont(this.font);
		this.write.setBounds(0, (int) (this.getHeight() * 0.2), this.getWidth(), 30);
		this.write1 = new JLabel("Insert Track's length");
		this.write1.setForeground(Color.WHITE);
		this.write1.setFont(this.font);
		this.write1.setBounds(0, (int) (this.getHeight() * 0.5), this.getWidth(), 30);
		this.write.setHorizontalAlignment(0);
		this.write1.setHorizontalAlignment(0);
		this.close = new ModifiedButton("", ImageProvider.getX1(), ImageProvider.getX2(), frame,
				e -> this.selectX());
		this.close.setEvidence(false);
		this.close.setBorderPainted(false);
		this.close.setBounds(this.getWidth() - (ImageProvider.getX1().getIconWidth() + 7), 7,
				ImageProvider.getX1().getIconWidth(), ImageProvider.getX1().getIconHeight());
		this.okButton = new ModifiedButton("", ImageProvider.getIconOk1(), ImageProvider.getIconOk2(),
				this.frame, e -> this.selectOk());
		this.okButton.setEvidence(false);
		this.okButton.setBorderPainted(false);
		this.okButton.setBounds(
				(this.getWidth() / 2) - (ImageProvider.getIconOk1().getIconWidth() / 2),
				(int) (this.getHeight() * 0.8), ImageProvider.getIconOk1().getIconWidth(),
				ImageProvider.getIconOk1().getIconHeight());
		this.okButton.setHorizontalAlignment(0);
		this.lengthT = new JTextArea("insert...");
		this.nameT = new JTextArea("insert...");
		this.nameT.setBackground(Color.WHITE);
		this.lengthT.setBackground(Color.WHITE);
		this.lengthT.setForeground(Color.BLUE.darker().darker());
		this.lengthT.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.05)));
		this.lengthT.setBounds(15, (int) (this.getHeight() * 0.6), this.getWidth() - 30, 30);
		this.nameT.setForeground(Color.BLUE.darker().darker());
		this.nameT.setFont(new Font("Showcard Gothic", 0, (int) (this.dialog.getHeight() * 0.05)));
		this.nameT.setBounds(15, (int) (this.getHeight() * 0.3), this.getWidth() - 30, 30);
		this.nameT.setBorder(line);
		this.lengthT.setBorder(line);
		this.lengthT.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				CreateCentrePanel.this.lengthT.setText("");
			}
		});
		this.nameT.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				CreateCentrePanel.this.nameT.setText("");
			}
		});
		this.add(this.okButton);
		this.add(this.write);
		this.add(this.write1);
		this.add(this.lengthT);
		this.add(this.nameT);
		this.add(this.close);
		this.dialog.setUndecorated(true);
		this.dialog.setContentPane(this);
		this.dialog.setLocationRelativeTo(this.frame);
		this.dialog.setCursor(this.frame.getCursor());
		this.dialog.setVisible(true);
	}
	private void selectOk()
	{
		this.dialog.dispose();
		try
		{
			String nameTrack = this.nameT.getText();
			int lengthTrack = Integer.parseInt(this.lengthT.getText());
			if (lengthTrack < 1000)
				new RequestPanelEditor(this.frame, null, 3);
			else
			{
				this.frame.enableAllButtons();
				this.frame.createMapEditor(nameTrack, lengthTrack);
			}
		}
		catch (Exception ex)
		{
			new RequestPanelEditor(this.frame, null, 3);
		}
	}
	private void selectX()
	{
		this.frame.switchPanelEditor();
		this.dialog.dispose();
	}
}
