package gameOfThrones.differentMain;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gameOfThrones.CerseiLannister;
import gameOfThrones.Character;
import gameOfThrones.DaenerysStormborn;
import gameOfThrones.JonSnow;
import gameOfThrones.TyrionLannister;

/*
 * Make characters using their constructors - inheritance example
 */

public class Main2 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				DaenerysStormborn danny = new DaenerysStormborn();
				JonSnow jon = new JonSnow();
				TyrionLannister tyrion = new TyrionLannister();
				CerseiLannister cersei = new CerseiLannister();

				playTheme();

				// check data
				System.out.println(
						cersei.getCharacterName() + ", " + cersei.getAllegiance() + ", " + cersei.getMessagesFile());

				System.out.println();
				Character.printAllMessages(danny);

				ImageIcon icon = new ImageIcon(
						Toolkit.getDefaultToolkit().getImage("resource/images/game-of-thrones-logo.png")
								.getScaledInstance(200, 150, Image.SCALE_DEFAULT));
				JOptionPane.showMessageDialog(null, Character.getStatisticMessagesInformation(),
						"Game of Thrones - statistic information", JOptionPane.INFORMATION_MESSAGE, icon);

				System.out.println();
				Character.printCharacterWhoHasTheMostTypeDisposition("happy");
				Character.printCharacterWhoHasTheMostTypeDisposition("sad");

				System.out.println();
				System.out.println("Tyron has more positive dispositon: " + tyrion.isDispositionPositive());

				System.out.println();
				System.out.println("Jon loves Daenerys more: " + Character.lovingCouple(jon, danny));

				try {
					Thread.sleep(23000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void playTheme() {
		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
				new File("resource/audio/looperman-l-1154425-0091309-haidarjasem-game-of-thrones-2.wav"))) {
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}