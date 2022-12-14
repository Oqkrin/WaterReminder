package net.oqkring.remindwater;

import java.awt.AWTException;
import java.awt.SystemTray;
import net.oqkring.remindwater.childrens.*;
import net.oqkring.remindwater.childrens.ananke.Ananke;

public class ReminderMain extends Ananke {

	public static void main(String[] args) throws AWTException {
		if (SystemTray.isSupported())
			MenuMaker.createSystemTray();
		WindowMaker.createWindow();
		ScheduleMaker.scheduler();
	}

}