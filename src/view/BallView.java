package view;

import java.awt.Color;
import java.awt.Graphics;

import model.BallModel;


public class BallView implements Drawable {
	
	private BallModel ballModel;
	
	public BallView(BallModel model){
		this.ballModel = model;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillOval(ballModel.getPosX() - ballModel.getRadius(),ballModel.getPosY() - ballModel.getRadius(),
				ballModel.getRadius()*2, ballModel.getRadius()*2); //x, y position are the center of the ball
	}
	
	

}
