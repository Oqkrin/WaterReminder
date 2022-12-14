package net.oqkring.remindwater.childrens;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.TooManyListenersException;

import javax.swing.ImageIcon;
import javax.swing.JWindow;

import net.oqkring.remindwater.childrens.ananke.Ananke;

public class DragAndDrop extends Ananke {

	protected static List<File> files;

	private DragAndDrop() {
	}

	public static void createDragAndDropMenu(String file) throws TooManyListenersException {

		var dndWindow = new JWindow();
		var menu = new ImageIcon(new ImageIcon(ASSETSPATH + "dndMenu.png").getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH));
		var litMenu = new ImageIcon(new ImageIcon(ASSETSPATH + "dndMenuLit.png").getImage().getScaledInstance(300,300, Image.SCALE_SMOOTH));
		WindowMaker.createWindow(dndWindow, menu);

		// Create a new DropTarget and specify that it should accept files
		DropTarget dropTarget = new DropTarget(dndWindow, DnDConstants.ACTION_COPY, null, true);

		// Implement a DropTargetListener to handle the dropped files
		dropTarget.addDropTargetListener(new DropTargetListener() {

			public void dragEnter(DropTargetDragEvent dtde) {
				
				dndLabel.setIcon(litMenu);
			}

			public void dragExit(DropTargetEvent dte) {
				dndLabel.setIcon(menu);
			}

			public void dragOver(DropTargetDragEvent dtde) {
			}

			public void dropActionChanged(DropTargetDragEvent dtde) {
			}

			@SuppressWarnings("unchecked")
			public void drop(DropTargetDropEvent dtde) {

				dtde.acceptDrop(dtde.getDropAction());
				try {
					files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
				} catch (UnsupportedFlavorException | IOException e) {
					e.printStackTrace();
				}

				for (File i : files) {

					try {
						// Copy the source file to the destination
						Files.delete(Paths.get(ASSETSPATH + file));
						Files.copy(Paths.get(i.getCanonicalPath()), Paths.get(ASSETSPATH + file));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
