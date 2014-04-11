package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import behavior.Behavior;

public class BrickModel implements GameObjectModel {
	
	private int posX, posY, width, height, speedx, speedy;
	private Color color;
	private boolean isAlive = true;
	private List<Behavior> behaviorList;
	
	public BrickModel(int posX, int posY, int width, int height, int speedx, int speedy ){
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.speedx = speedx;
		this.speedy = speedy;
		this.color = Color.RED;
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
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public boolean getIsAlive() {
		return isAlive;
	}
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	@Override
	public Rectangle getBoundingBox() {
		if(isAlive){
			return new Rectangle(posX, posY, width, height);	
		}else{
			return new Rectangle(0, 0, 0, 0);	
		}
		
	}
	
	@Override
	public void collisionOccured(GameObjectModel model) {
		this.isAlive = false;
		
	}
	
	@Override
	public int getSpeedX() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		
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
