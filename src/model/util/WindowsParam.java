package util;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class WindowsParam {
	
	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	static int width = gd.getDisplayMode().getWidth();
	static int height = gd.getDisplayMode().getHeight();
	
	
	public static int getWidth() {
		return (int)0.85*width;
	}
	public static int getHeight() {
		return (int)0.85*height;
	}
	
	
	
}
