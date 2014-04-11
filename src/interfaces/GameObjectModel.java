package interfaces;

import java.awt.Dimension;
import java.awt.Rectangle;

public interface GameObjectModel {
	
	public Rectangle getBoundingBox();
	
	public void collisionOccured(GameObjectModel model);
	
	public void moveUp();
	
	public void moveDown();
	
	public void moveLeft();
	
	public void moveRight();
	
	public int getWidth();
	
	public int getHeight();
	
	public int getPosX() ;
	
	public void setPosX(int posX);
	
	public int getPosY();
	
	public void setPosY(int posY);
	
	public int getSpeedX();
	
	public void setSpeedX(int speedX) ;
	
	public int getSpeedY();
	
	public void setSpeedY(int speedY) ;
	
	public void blowUp();
	
	public void toggleXDirection();
	
	public void toggleYDirection();
	
	public void update(Dimension dim);

}
