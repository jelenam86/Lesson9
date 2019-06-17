package gameOfThrones.differentMain.application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import gameOfThrones.Character;
import gameOfThrones.ResourceFile;

public class AppMain {

	private static void loader() {
		JFrame frame = new JFrame("Game of Thrones");
		frame.setSize(new Dimension(600, 600));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screen.width / 2 - frame.getWidth() / 2, screen.height / 2 - frame.getHeight() / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		addComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

	private static void addComponentsToPane(Container pane) {
		BorderLayout border = new BorderLayout(3, 2);
		border.setHgap(50);
		border.setVgap(50);
		pane.setLayout(border);

		JButton quit, statistic, love, sad, happy;
		JPanel south, north, center;

		south = new JPanel();
		south.setLayout(new GridLayout(1, 2));

		quit = new JButton("QUIT");
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		south.add(quit);
		pane.add(south, BorderLayout.SOUTH);

		JPanel helper = new JPanel();
		JLabel label = new JLabel("Characters:");
		label.setLayout(new FlowLayout());
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
		label.setHorizontalAlignment(JLabel.CENTER);
		helper.add(label);

		north = new JPanel();
		north.setBounds(150, 150, 300, 300);
		north.setBorder(BorderFactory.createEmptyBorder());
		GridLayout layout = new GridLayout(3, 3);
		layout.setHgap(15);
		layout.setVgap(15);
		north.setLayout(layout);

		north.add(helper);
		north.add(new JLabel("           "));
		north.add(new CustomButton("Daenerys Stormborn"));
		north.add(new CustomButton("Jon Snow"));
		north.add(new CustomButton("Tyrion Lannister"));
		north.add(new CustomButton("Cersei Lannister"));

		pane.add(north, BorderLayout.NORTH);

		center = new JPanel();
		center.setLayout(layout);

		love = new JButton("Check the loving couple");
		love.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage("resource/images/jondanny.jpg")
						.getScaledInstance(300, 200, Image.SCALE_DEFAULT));
				Character jon = Character.findCharacterByName("Jon Snow");
				Character danny = Character.findCharacterByName("Daenerys Stormborn");
				String text = "";
				if (Character.lovingCouple(jon, danny))
					text += "more";
				else
					text += "less";
				String message = jon.getCharacterName() + " loves " + danny.getCharacterName() + " " + text
						+ "  than she loves him.";
				JOptionPane.showMessageDialog(null, message, "\u2766 Jon & Danny \u2766",
						JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		center.add(love);

		statistic = new JButton("Statistics");
		statistic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon(
						Toolkit.getDefaultToolkit().getImage("resource/images/game-of-thrones-logo.png")
								.getScaledInstance(200, 150, Image.SCALE_SMOOTH));
				JOptionPane.showMessageDialog(null, Character.getStatisticMessagesInformation(),
						"Game of Thrones - statistic information", JOptionPane.INFORMATION_MESSAGE, icon);

			}
		});
		center.add(statistic);

		happy = new JButton("The happiest character");
		happy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "The happiest character is "
						+ Character.characterWhoHasTheMostTypeOfDisposition("happy").getCharacterName() + ".");
			}
		});
		center.add(happy);

		sad = new JButton("The saddest character");
		sad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "The saddest character is "
						+ Character.characterWhoHasTheMostTypeOfDisposition("sad").getCharacterName() + ".");
			}
		});
		center.add(sad);

		pane.add(center, BorderLayout.CENTER);
		JPanel empty = new JPanel();
		JLabel lbl = new JLabel("     ");
		empty.add(lbl);
		JPanel empty2 = new JPanel();
		empty2.add(lbl);
		pane.add(empty, BorderLayout.EAST);
		pane.add(empty2, BorderLayout.WEST);
	}

	private static void playTheme() {

		try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
				new File("resource/audio/looperman-l-1154425-0091309-haidarjasem-game-of-thrones-2.wav"))) {
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		} catch (UnsupportedAudioFileException | LineUnavailableException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ResourceFile.makeCharactersFromTheFile();
				playTheme();
				loader();
			}
		});
	}

}
