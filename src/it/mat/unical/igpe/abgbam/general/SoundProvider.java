package it.mat.unical.igpe.abgbam.general;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class SoundProvider
{
	private static AudioInputStream airPlane;
	private static AudioInputStream applauseSound;
	private static AudioInputStream bye;
	private static AudioInputStream click;
	private static AudioInputStream doh;
	private static AudioInputStream enteredSound;
	private static AudioInputStream excellent;
	private static AudioInputStream lost;
	private static AudioInputStream menu;
	private static Clip reproducerAirPlane;
	private static Clip reproducerApplauseSound;
	private static Clip reproducerBye;
	private static Clip reproducerClick;
	private static Clip reproducerDoh;
	private static Clip reproducerEnteredSound;
	private static Clip reproducerExcellent;
	private static Clip reproducerLost;
	private static Clip reproducerMenu;
	private static Clip reproducerStartSong;
	private static Clip reproducerTimer;
	private static AudioInputStream startSong;
	private static AudioInputStream timer;
	public static void chargeMusic(MainFrame mainFrame)
	{
		try
		{
			timer = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/timer.wav"));
			applauseSound = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/applauseSound.wav"));
			bye = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/bye.wav"));
			click = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/click.wav"));
			startSong = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/sottofondo.wav"));
			enteredSound = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/enteredSound.wav"));
			excellent = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/excellent.wav"));
			lost = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/lost.wav"));
			menu = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/menu.wav"));
			doh = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/doh.wav"));
			airPlane = AudioSystem.getAudioInputStream(
					SoundProvider.class.getClassLoader().getResource("sounds/airPlane.wav"));
			reproducerAirPlane = AudioSystem.getClip();
			reproducerDoh = AudioSystem.getClip();
			reproducerTimer = AudioSystem.getClip();
			reproducerApplauseSound = AudioSystem.getClip();
			reproducerBye = AudioSystem.getClip();
			reproducerClick = AudioSystem.getClip();
			reproducerEnteredSound = AudioSystem.getClip();
			reproducerStartSong = AudioSystem.getClip();
			reproducerExcellent = AudioSystem.getClip();
			reproducerLost = AudioSystem.getClip();
			reproducerMenu = AudioSystem.getClip();
			reproducerAirPlane.open(airPlane);
			reproducerDoh.open(doh);
			reproducerTimer.open(timer);
			reproducerApplauseSound.open(applauseSound);
			reproducerBye.open(bye);
			reproducerStartSong.open(startSong);
			reproducerExcellent.open(excellent);
			reproducerLost.open(lost);
			reproducerMenu.open(menu);
			reproducerClick.open(click);
			reproducerEnteredSound.open(enteredSound);
			Begin.setEvolve(true);
		}
		catch (Exception e)
		{
			mainFrame.setError();
			new InputException(mainFrame, 2);
		}
	}
	public static Clip getReproducerAirPlane()
	{
		return reproducerAirPlane;
	}
	public static Clip getReproducerApplauseSound()
	{
		return reproducerApplauseSound;
	}
	public static Clip getReproducerBye()
	{
		return reproducerBye;
	}
	public static Clip getReproducerClick()
	{
		return reproducerClick;
	}
	public static Clip getReproducerDoh()
	{
		return reproducerDoh;
	}
	public static Clip getReproducerEnteredSound()
	{
		return reproducerEnteredSound;
	}
	public static Clip getReproducerExcellent()
	{
		return reproducerExcellent;
	}
	public static Clip getReproducerLost()
	{
		return reproducerLost;
	}
	public static Clip getReproducerMenu()
	{
		return reproducerMenu;
	}
	public static Clip getReproducerStartSong()
	{
		return reproducerStartSong;
	}
	public static Clip getReproducerTimer()
	{
		return reproducerTimer;
	}
	public static void stopAll()
	{
		reproducerDoh.stop();
		reproducerStartSong.stop();
		reproducerMenu.stop();
		reproducerLost.stop();
		reproducerExcellent.stop();
		reproducerEnteredSound.stop();
		reproducerClick.stop();
		reproducerBye.stop();
		reproducerAirPlane.stop();
		reproducerApplauseSound.stop();
		reproducerTimer.stop();
	}
}
