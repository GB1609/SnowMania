package it.mat.unical.igpe.abgbam.general;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
public class ChargePanel extends JPanel
{
	private static final long serialVersionUID = 1;
	private JFrame frame;
	private JProgressBar progressBar;
	private BufferedImage startPoint;
	private JLabel write;
	public ChargePanel(JFrame frame, BufferedImage startPoint)
	{
		this.setLayout(null);
		this.frame = frame;
		this.setBorder(new LineBorder(Color.BLUE.darker().darker(), 3));
		this.startPoint = startPoint;
		this.setSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.write = new JLabel("loading...");
		this.write.setForeground(Color.BLUE.darker().darker());
		this.write.setFont(new Font("Showcard Gothic", 0, (int) (frame.getHeight() * 0.07)));
		this.write.setBounds((int) (this.getWidth() * 0.01), (int) (this.getHeight() * 0.85),
				this.getWidth() / 2, (int) (this.getHeight() * 0.1));
		this.add(this.write);
		this.progressBar = new JProgressBar(0, 4);
		this.progressBar.setValue(0);
		this.progressBar.setString("Loading....");
		this.progressBar.setSize(this.getWidth(), (int) (this.getHeight() * 0.06));
		this.progressBar.setForeground(Color.green);
		this.progressBar.setBackground(Color.BLUE);
		this.progressBar.setBorder(new LineBorder(Color.BLUE.darker().darker(), 2));
		this.progressBar.setBounds(0, this.getHeight() - this.progressBar.getHeight(),
				this.progressBar.getWidth(), this.progressBar.getHeight());
		this.add(this.progressBar, "South");
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(this.startPoint, 0, 0, this.frame.getWidth(), this.frame.getHeight(), null);
	}
	public void evolve()
	{
		this.progressBar.setValue(this.progressBar.getValue() + 1);
		this.repaint();
	}
	public JProgressBar getProgressBar()
	{
		return this.progressBar;
	}
}
