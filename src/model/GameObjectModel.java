package model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;

import behavior.Behavior;

public interface GameObjectModel {
	
	public Rectangle getBoundingBox();
	
	public void collisionOccured(GameObjectModel model);
	
	public int getWidth();
	
	public int getHeight();
	
	public void setWidth(int i);

	public void setHeight(int i);

	public int getPosX() ;
	
	public void setPosX(int posX);
	
	public int getPosY();
	
	public void setPosY(int posY);
	
	public int getSpeedX();
	
	public void setSpeedX(int speedX) ;
	
	public int getSpeedY();
	
	public void setSpeedY(int speedY) ;
	
	public void setIsAlive(boolean isAlive);
	
	public boolean getIsAlive();
	
	public void addBehavior(Behavior b);
	
	public void addBehavior(List<Behavior> b);
	
	public void removeBehavior(Behavior b);
	
	public List<Behavior> getBehaviorList();
	
	public void update(Dimension dim);

}
