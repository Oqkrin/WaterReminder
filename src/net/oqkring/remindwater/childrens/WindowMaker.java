package net.oqkring.remindwater.childrens;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JWindow;

import net.oqkring.remindwater.childrens.ananke.Ananke;

public class WindowMaker extends Ananke {

	private static int randX;
	private static int randY;
	private static int iconWidth;
	private static int iconHeight;
	
	private WindowMaker() {

	}

	public static void createWindow() {
		window.add(new JLabel(new ImageIcon(reminderImage.getImage().getScaledInstance((int) IMAGEWIDTH, (int) IMAGEHEIGHT, Image.SCALE_DEFAULT))));
		window.pack();
		window.setAlwaysOnTop(true);
		window.setBackground(new Color(0, 0, 0, 0));
	}

	public static void createWindow(JWindow dndWindow, ImageIcon menu) {
		iconWidth = menu.getIconWidth();
		iconHeight = menu.getIconHeight();
		randX = random.nextInt((SCREENWIDTH - iconWidth));
		randY = random.nextInt((SCREENHEIGHT - iconHeight));
		dndWindow.setAlwaysOnTop(false);
		dndWindow.setBackground(new Color(0, 0, 0, 0));
		dndWindow.setLocation(randX, randY);
		dndLabel.setIcon(menu);
		dndWindow.add(dndLabel);
		dndWindow.pack();
		dndWindow.setVisible(true);
		JWindow buttonWindow = new JWindow();
		createButtonWindow(createXButton(dndWindow, buttonWindow), buttonWindow);
	}

	private static void createButtonWindow(JButton button, JWindow buttonWindow) {
		buttonWindow.setAlwaysOnTop(true);
		buttonWindow.setBackground(new Color(0, 0, 0, 0));
		buttonWindow.add(button);
		buttonWindow.pack();
		buttonWindow.setLocation(randX + iconWidth/2 - buttonWindow.getWidth()/2, randY + iconHeight - buttonWindow.getHeight());
		buttonWindow.setVisible(true);
	}

	private static JButton createXButton(JWindow dndWindow, JWindow copyWindow) {
		JButton button = new JButton("⭙");
		button.setForeground(new Color(0xD3A43E));
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.addActionListener(l -> { 
			dndWindow.dispose();
			copyWindow.dispose();
		});
		return button;
	}

}
