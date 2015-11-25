package org.noip.andrewvasiliev.makemaximum.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.noip.andrewvasiliev.makemaximum.MakeMaximum;
import org.noip.andrewvasiliev.makemaximum.Utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		config.title = Constants.TITLE;
		new LwjglApplication(new MakeMaximum(), config);
	}
}
