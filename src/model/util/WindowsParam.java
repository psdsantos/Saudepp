package model.util;

import java.awt.GraphicsEnvironment;

public class WindowsParam {
	
	public static int getWidth() {
		return (int) (0.85*GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth());
	}
	public static int getHeight() {
		return (int) (0.85*GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight());
	}
	
	
	
}
