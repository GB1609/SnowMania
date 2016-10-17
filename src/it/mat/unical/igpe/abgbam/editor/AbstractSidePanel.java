package it.mat.unical.igpe.abgbam.editor;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import it.mat.unical.igpe.abgbam.general.GeneralPanel;
import it.mat.unical.igpe.abgbam.general.MainFrame;
public abstract class AbstractSidePanel extends GeneralPanel
{
	private static final long serialVersionUID = 1;
	TitledBorder title;
	public AbstractSidePanel(MainFrame frame)
	{
		super(frame);
		this.setSize(200, frame.getHeight() - frame.getEditorPanel().getMenuBar().getHeight());
		this.setBackground(Color.WHITE);
	}
}
