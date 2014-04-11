package view;

import java.awt.Graphics;

import model.BrickModel;


public class BrickView implements Drawable {

	private BrickModel brickModel;

	public BrickView(BrickModel model){
		this.brickModel = model;
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(brickModel.getColor());
		g.fillRect(brickModel.getPosX(), brickModel.getPosY(), brickModel.getWidth(), brickModel.getHeight());

	}

}
