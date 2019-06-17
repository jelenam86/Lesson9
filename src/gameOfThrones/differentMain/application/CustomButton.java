package gameOfThrones.differentMain.application;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.curiousworks.lesson9.FileHelper;

import gameOfThrones.Character;

public class CustomButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private String name;
	private Character character;

	public CustomButton(String name) {
		super(name);
		this.name = name;
		this.character = Character.findCharacterByName(name);
		this.setSize(new Dimension(100, 30));
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loader();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void loader() throws IOException {
		frame = new JFrame(name);
		frame.setSize(new Dimension(600, 300));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		addComponentsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);

	}

	private void addComponentsToPane(Container pane) throws IOException {
		pane.setLayout(new BorderLayout(3, 2));

		JButton close, seeSentMesssages, seeRecievedMessages;
		JLabel data, statistic;
		JPanel west, south, east;

		west = new JPanel();
		String path = ImagePath.getPath(character.getCharacterName());
		ImageIcon image = new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(path).getScaledInstance(250, 250, Image.SCALE_SMOOTH));
		JLabel characterImage = new JLabel(toCircle(image));
		west.add(characterImage);
		pane.add(west, BorderLayout.WEST);

		east = new JPanel();
		east.setLayout(new GridLayout(2, 1));
		data = new JLabel();
		data.setHorizontalAlignment(JLabel.LEFT);
		String forData = "<html><br>Data:<br><br>Character name: " + character.getCharacterName() + "<br>"
				+ character.getAllegiance() + "<br>Message file: " + character.getMessagesFile() + "</html>";
		data.setText(forData);
		east.add(data);
		String text = "";
		if (character.isDispositionPositive())
			text = " My disposition is positive.";
		else
			text = " My disposition is negative.";
		String statistics = "<html>I have sent " + character.getNumberOfSentMessages()
				+ " messages.<br>I have recieved " + Character.getNumberOfRecievedMessages(character) + " messages.<br>"
				+ text + "</html>";
		statistic = new JLabel(statistics);
		statistic.setHorizontalAlignment(JLabel.CENTER);
		east.add(statistic);
		pane.add(east, BorderLayout.EAST);

		south = new JPanel();
		seeSentMesssages = new JButton("View my sent messages");
		seeSentMesssages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "";
				List<String> messages = FileHelper.loadMessages(character.getMessagesFile());
				messages.remove(0);
				for (String s : messages) {
					text += divide(s) + "\n\n";
				}
				UIManager.put("OptionPane.messageFont", new Font("Symbola", Font.PLAIN, 16));
				JOptionPane.showMessageDialog(null, text, "My sent messages", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		south.add(seeSentMesssages);
		seeRecievedMessages = new JButton("View my recieved messages");
		seeRecievedMessages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = "";
				for (Character c : Character.getAllCharacters()) {
					for (String line : FileHelper.loadMessages(c.getMessagesFile())) {
						try {
							String beginningOfTheLine = line.substring(0,
									character.getCharacterName().split("\\s+")[0].length());
							if (beginningOfTheLine.equals(character.getCharacterName().split("\\s+")[0])) {
								text += "Message from " + c.getCharacterName() + ", "
										+ divide(line.substring(character.getCharacterName().length() + 2)) + "\n\n";
							}
						} catch (StringIndexOutOfBoundsException ex) {
							continue;
						}
					}
				}
				UIManager.put("OptionPane.messageFont", new Font("Symbola", Font.PLAIN, 16));
				JOptionPane.showMessageDialog(null, text, "My recieved messages", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		south.add(seeRecievedMessages);
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UIManager.put("OptionPane.messageFont", new Font("Calibri", Font.BOLD, 12));
				frame.dispose();
			}
		});
		south.add(close);
		pane.add(south, BorderLayout.SOUTH);
	}

	private static String divide(String text) {
		String s = text;
		text = "";
		while (s.length() > 110) {
			text += s.substring(0, 110) + "\n";
			s = s.substring(110);
		}
		text += s;
		return text;
	}

	public ImageIcon toCircle(ImageIcon logo) {
		BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillOval(1, 1, 248, 248);
		g.setComposite(AlphaComposite.SrcIn);
		g.drawImage(logo.getImage(), 0, 0, null);
		g.dispose();

		return new ImageIcon(image);
	}
}