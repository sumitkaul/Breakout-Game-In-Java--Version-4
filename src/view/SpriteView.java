package view;

import java.awt.Color;
import java.awt.Graphics;

import model.SpriteModel;

public class SpriteView implements Drawable {	
	
	private SpriteModel spriteModel;
	
	public SpriteView(SpriteModel model){
		this.spriteModel = model;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(spriteModel.getPosX(),spriteModel.getPosY(),
				spriteModel.getWidth(), spriteModel.getHeight()); //x, y position are the center of the ball
	}


}
