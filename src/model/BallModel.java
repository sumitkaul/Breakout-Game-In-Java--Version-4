package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import behavior.Behavior;

public class BallModel implements GameObjectModel {
	
	private int posX, posY, speedX, speedY, radius, width, height;
	private Color color;
	private boolean hasCollisionOccured;
	private List<Behavior> behaviorList;
	
	public BallModel(int posX, int posY, int speedX, int speedY, int width, int height, int radius){
		this.posX = posX;
		this.posY = posY;
		this.speedX = speedX;
		this.speedY = speedY;
		this. width = width;
		this.height = height;
		this.radius = radius;
		this.behaviorList = new ArrayList<>();
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getSpeedX() {
		return speedX;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(posX - radius, posY + radius, radius * 2, radius * 2);
	}
	
	@Override
	public void collisionOccured(GameObjectModel model) {
		hasCollisionOccured = true;
		
	}
	
	
	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}
	
	
	
	@Override
	public void update(Dimension dim) {
		posX += speedX;
		posY += speedY;
		
		if (posX < 0)
			speedX = Math.abs(speedX);
		if (posX > dim.width)
			speedX = -Math.abs(speedX);
		if (posY < 0)
			speedY = Math.abs(speedY);
		if (posY > dim.height)
			speedY = -Math.abs(speedY);	
		
	}

	@Override
	public void setIsAlive(boolean isAlive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addBehavior(Behavior b) {
		this.behaviorList.add(b);
		
	}

	@Override
	public void addBehavior(List<Behavior> b) {
		this.behaviorList.addAll(b);
		
	}

	@Override
	public void removeBehavior(Behavior b) {
		this.behaviorList.remove(b);
		
	}

	@Override
	public List<Behavior> getBehaviorList() {
		return this.behaviorList;
	}

	@Override
	public void setWidth(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(int i) {
		// TODO Auto-generated method stub
		
	}
	
	

}
