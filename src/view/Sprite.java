package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import utility.ResizeHelper;
import action.PlaySoundAction;
import model.SpriteModel;

public class Sprite implements Drawable {
	
	BufferedImage image;
	SpriteModel model;
	
	public Sprite(String filename, SpriteModel model){
		try {
			URL url = PlaySoundAction.class.getResource("/images/"+filename);
			image = ImageIO.read(url);
			this.model = model;
		} catch (IOException e) {
			e.printStackTrace();
			//log exception
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, (int)(model.getPosX()*ResizeHelper.getXfactor()),(int)( model.getPosY()*ResizeHelper.getYfactor()),
				(int) (model.getWidth()*ResizeHelper.getXfactor()), (int)(model.getHeight()*ResizeHelper.getYfactor()), null); 
	}

}
