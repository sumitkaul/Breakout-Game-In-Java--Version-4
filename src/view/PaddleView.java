package view;

import java.awt.Graphics;

import model.PaddleModel;


public class PaddleView implements Drawable {
	
	private PaddleModel paddleModel;
	
	public PaddleView(PaddleModel model){
		this.paddleModel = model;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(paddleModel.getColor());
		g.fillRect(paddleModel.getPosX(), paddleModel.getPosY(), paddleModel.getWidth(), paddleModel.getHeight());
	}

}
