package utility;

import java.awt.Dimension;

import view.GamePanel;


public class ResizeHelper {
	
	private static double DEFAULT_WIDTH = 542;
	private static double DEFAULT_HEIGHT = 662;
	
	private static double presentWidth = DEFAULT_WIDTH;
	private static double presentHeight = DEFAULT_HEIGHT;
	
	private static float xfactor = 1.0f;
	private static float yfactor = 1.0f;
	
	public ResizeHelper(GamePanel gamePanel){
		/*Dimension dim = gamePanel.getSize();
		DEFAULT_HEIGHT = dim.getHeight();
		DEFAULT_WIDTH = dim.getWidth();*/
	}


	public static double getPresentWidth() {
		return presentWidth;
	}

	public static void setPresentWidth(double presentWidth) {
		ResizeHelper.presentWidth = presentWidth;
	}

	public static double getPresentHeight() {
		return presentHeight;
	}

	public static void setPresentHeight(double presentHeight) {
		ResizeHelper.presentHeight = presentHeight;
	}

	public static float getXfactor() {
		return (float) (presentWidth/DEFAULT_WIDTH);
	}

	public static void setXfactor(float xfactor) {
		ResizeHelper.xfactor = xfactor;
	}

	public static float getYfactor() {
		return (float) (presentHeight/DEFAULT_HEIGHT);
	}

	public static void setYfactor(float yfactor) {
		ResizeHelper.yfactor = yfactor;
	}

	public static double getDefaultWidth() {
		return DEFAULT_WIDTH;
	}

	public static double getDefaultHeight() {
		return DEFAULT_HEIGHT;
	}
	
	

}
