package net.oqkring.remindwater.childrens.ananke;

import java.awt.Toolkit;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

import com.moandjiezana.toml.Toml;

public class Ananke {
	
	protected static final String ASSETSPATH = "./assets/";
    private static final String CONFIGPATH = "./configuration/";
    private static final String CONFIGFILE = "config.toml";
    protected static final File config = new File(CONFIGPATH + CONFIGFILE);

    protected static final Toml toml = new Toml().read(config);
    protected static final Random random = new Random();
    protected static final JWindow window = new JWindow();
    protected static JLabel dndLabel = new JLabel();

    protected static final String AUDIOFILE = toml.getString("AudioFile");
    protected static final String ICONFILE = toml.getString("IconFile");
    protected static final String IMAGEFILE = toml.getString("ImageFile");
    protected static final File fileAudio = new File(ASSETSPATH + AUDIOFILE);
    protected static final ImageIcon reminderImage = new ImageIcon(ASSETSPATH + IMAGEFILE);

    protected static final int SCREENWIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    protected static final int SCREENHEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    protected static final double IMAGEHEIGHT = reminderImage.getIconHeight() * toml.getDouble("ImageScalingFactor");
    protected static final double IMAGEWIDTH = reminderImage.getIconWidth() * toml.getDouble("ImageScalingFactor");
    protected static final double VOLUME = toml.getDouble("Volume");
    protected static final long REPETITIONTIME = toml.getLong("waitTimeForRepetition");
    
    protected Ananke() {
		toml.read(config);
	}
}
