package it.mat.unical.igpe.abgbam.game.core;
import it.mat.unical.igpe.abgbam.general.OutputException;
public class Crono implements Runnable
{
	private Thread thread;
	private String crono = " ";
	private boolean cronoActive;
	private String min = "", sec = "", mil = "";
	private Integer minutes = 0, seconds = 0, thousandths = 0;
	public Crono()
	{
	}
	public Crono(int i, int j, int k)
	{
		this.crono = (i + ":" + j + ":" + k);
	}
	public String getCrono()
	{
		return this.crono;
	}
	public void inCronometro()
	{
		this.cronoActive = true;
		this.thread = new Thread(this);
		this.thread.start();
	}
	public boolean isCronoActive()
	{
		return this.cronoActive;
	}
	public String newCrono(Crono c, int n)
	{
		String s;
		String m;
		String ss;
		int min = c.minutes;
		int secon = 0;
		for (int i = 0; i < (n + c.seconds); i++)
		{
			secon++;
			if (secon == 60)
			{
				secon = 0;
				min++;
			}
		}
		m = "0" + min;
		if (secon < 10)
			ss = "0" + secon;
		else
			ss = "" + secon;
		s = (m + ":" + ss + ":" + c.mil);
		return s;
	}
	public Integer newInt(Crono c, int n)
	{
		int t = c.thousandths;
		int min = c.minutes;
		int secon = 0;
		int tot;
		for (int i = 0; i < (n + c.seconds); i++)
		{
			secon++;
			if (secon == 60)
			{
				secon = 0;
				min++;
			}
		}
		tot = t + (secon * 1000) + (min * 100000);
		return tot;
	}
	@Override
	public void run()
	{
		try
		{
			while (this.cronoActive)
			{
				Thread.sleep(4);
				this.thousandths += 4;
				if (this.thousandths == 1000)
				{
					this.thousandths = 0;
					this.seconds += 1;
					if (this.seconds == 60)
					{
						this.seconds = 0;
						this.minutes++;
					}
				}
				if (this.minutes < 10)
					this.min = "0" + this.minutes;
				else
					this.min = this.minutes.toString();
				if (this.seconds < 10)
					this.sec = "0" + this.seconds;
				else
					this.sec = this.seconds.toString();
				if (this.thousandths < 10)
					this.mil = "00" + this.thousandths;
				else if (this.thousandths < 100)
					this.mil = "0" + this.thousandths;
				else
					this.mil = this.thousandths.toString();
				this.crono = (this.min + ":" + this.sec + ":" + this.mil);
			}
		}
		catch (Exception e)
		{
			new OutputException(null, 3);
		}
		this.crono = (this.min + ":" + this.sec + ":" + this.mil);
	}
	public void setCrono(String crono)
	{
		this.crono = crono;
	}
	public void setCronoActive(boolean cronoActive)
	{
		this.cronoActive = cronoActive;
	}
}
