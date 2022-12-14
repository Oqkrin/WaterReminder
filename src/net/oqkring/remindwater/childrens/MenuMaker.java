package net.oqkring.remindwater.childrens;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import net.oqkring.remindwater.childrens.ananke.Ananke;

public class MenuMaker extends Ananke {

	private static TrayIcon trayIcon;

	private MenuMaker() {

	}

	private static ArrayList<MenuItem> createMenuItems() {

		ArrayList<MenuItem> items = new ArrayList<>();

		// pop-up Menu components
		MenuItem exitItem = new MenuItem("Exit");
		MenuItem configItem = new MenuItem("Open Config");
		MenuItem dndImage = new MenuItem("change Image");
		MenuItem dndAudio = new MenuItem("change Audio");

		// Functions of components
		exitItem.addActionListener((ActionEvent e) -> System.exit(0));
		configItem.addActionListener((ActionEvent e) -> {
			try {
				Desktop.getDesktop().open(config);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		dndImage.addActionListener((ActionEvent e) -> {
			try {
				DragAndDrop.createDragAndDropMenu(IMAGEFILE);
			} catch (TooManyListenersException e1) {
				e1.printStackTrace();
			}
		});

		dndAudio.addActionListener((ActionEvent e) -> {
			try {
				DragAndDrop.createDragAndDropMenu(AUDIOFILE);
			} catch (TooManyListenersException e1) {
				e1.printStackTrace();
			}
		});

		// items

		items.add(dndImage);
		items.add(dndAudio);
		items.add(configItem);
		items.add(exitItem);

		return items;
	}

	public static <T extends Menu> T createMenu(List<MenuItem> items, T menu) {

		for (MenuItem i : items) {
			menu.add(i);
		}

		return menu;
	}

	private static void createTrayIcon(PopupMenu popup) {
		trayIcon = new TrayIcon(new ImageIcon(ASSETSPATH + ICONFILE).getImage(), "Water Reminder");
		trayIcon.setImageAutoSize(true);
		trayIcon.setPopupMenu(popup);
	}

	public static void createSystemTray() throws AWTException {
		createTrayIcon(createMenu(createMenuItems(), new PopupMenu()));
		SystemTray.getSystemTray().add(trayIcon);
	}
}
