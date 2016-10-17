package it.mat.unical.igpe.abgbam.game.core;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import it.mat.unical.igpe.abgbam.general.OutputException;
public class UpdateRank
{
	private String insert;
	private String namePlayer;
	private int number;
	private ArrayList<Integer> presentsInt;
	private ArrayList<String> presentsString;
	private File rank;
	private BufferedWriter rankBuffered;
	private BufferedReader reader;
	private String[] timeStrings;
	private boolean yes;
	public UpdateRank(String name, String insert, String namePlayer)
	{
		this.insert = insert;
		this.namePlayer = namePlayer;
		this.rank = new File("data/scoreBoard/scoreboard" + name + ".txt");
		this.presentsString = new ArrayList<String>();
		this.presentsInt = new ArrayList<Integer>();
		this.yes = false;
		try
		{
			this.reader = new BufferedReader(new FileReader(this.rank));
			String st = this.reader.readLine();
			while (st != null)
				if (st.contains("-"))
				{
					String[] separator = st.split("-");
					this.presentsInt.add(Integer.parseInt(separator[0]));
					this.presentsString.add(separator[1]);
					st = this.reader.readLine();
				}
			this.reader.close();
		}
		catch (Exception e)
		{
			new OutputException(null, 1);
		}
		this.updateWrite();
	}
	private void updateWrite()
	{
		this.timeStrings = this.insert.split(":");
		this.number = (Integer.parseInt(this.timeStrings[0]) * 100000)
				+ (Integer.parseInt(this.timeStrings[1]) * 1000)
				+ (Integer.parseInt(this.timeStrings[2]));
		if (this.presentsInt.isEmpty())
		{
			this.presentsInt.add(this.number);
			this.presentsString.add(this.namePlayer + "  " + this.insert);
			this.yes = true;
		}
		else
			for (Integer i : this.presentsInt)
				if (this.number < i)
				{
					this.presentsString.add(this.presentsInt.indexOf(i),
							this.namePlayer + "  " + this.insert);
					this.presentsInt.add(this.presentsInt.indexOf(i), this.number);
					if (this.presentsInt.size() == 11)
					{
						this.presentsInt.remove(10);
						this.presentsString.remove(10);
					}
					this.yes = true;
					break;
				}
		if (!this.yes && (this.presentsInt.size() < 10))
		{
			this.presentsInt.add(this.number);
			this.presentsString.add(this.namePlayer + "  " + this.insert);
			this.yes = true;
		}
		if (this.yes)
			try
			{
				this.rankBuffered = new BufferedWriter(new FileWriter(this.rank));
				for (int a = 0; a < this.presentsInt.size(); a++)
					this.rankBuffered
							.write(this.presentsInt.get(a) + "-" + this.presentsString.get(a) + "\n");
				this.rankBuffered.flush();
				this.rankBuffered.close();
			}
			catch (Exception e)
			{
				new OutputException(null, 4);
			}
	}
}
