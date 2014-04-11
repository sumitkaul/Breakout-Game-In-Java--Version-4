package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import behavior.Behavior;

public class PaddleModel implements GameObjectModel {

	private int posX, posY, width, height, speedx, speedy;
	private Color color;
	private List<Behavior> behaviorList;
	
	public PaddleModel(int posX, int posY, int width, int height, int speedx, int speedy ){
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.speedx = speedx;
		this.speedy = speedy;
		this.color = Color.BLUE;
		this.behaviorList = new ArrayList<>();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle(posX, posY, width, height);
	}

	@Override
	public void collisionOccured(GameObjectModel model) {
		// TODO Auto-generated method stub

	}


	@Override
	public int getSpeedX() {
		return speedx;
	}

	@Override
	public void setSpeedX(int speedX) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSpeedY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSpeedY(int speedY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Dimension dim) {
		if (posX < 0)
			posX = 0;
		if (posX + this.width > dim.width)
			posX = dim.width - this.width;
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

}
