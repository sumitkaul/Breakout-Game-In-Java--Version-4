package gameMakerModel;
import interfaces.GameObjectModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;

public class GameMakerSpriteModel implements GameObjectModel {

	private int posX, posY, speedX, speedY, width, height;
	private String spriteName,spriteImage, spriteSound	;
	private Boolean updateX,updateY;
	public Boolean getUpdateY() {
		return updateY;
	}

	public void setUpdateY(Boolean updateY) {
		this.updateY = updateY;
	}

	public Boolean getUpdateX() {
		return updateX;
	}

	public void setUpdateX(Boolean updateX) {
		this.updateX = updateX;
	}

	private List<String> behaviourList;
	
	public List<String> getBehaviourList() {
		return behaviourList;
	}

	public void setBehaviourList(List<String> behaviourList) {
		this.behaviourList = behaviourList;
	}

	public String getSpriteImage() {
		return spriteImage;
	}

	public void setSpriteImage(String spriteImage) {
		this.spriteImage = spriteImage;
	}

	//private Color color;
	private boolean hasCollisionOccured;
	
	public GameMakerSpriteModel(String spriteName,String spriteImage,int posX, int posY, int speedX, int speedY, int width, int height, List<String> behaviourList,boolean updateX, boolean updateY){
		this.posX = posX;
		this.posY = posY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.width = width;
		this.height = height;
		this.spriteName=spriteName;
		this.spriteImage=spriteImage;
		this.behaviourList=behaviourList;
		this.updateX=updateX;
		this.updateY=updateY;
	}
	
	public String getSpriteName() {
		return spriteName;
	}

	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public Rectangle getBoundingBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void collisionOccured(GameObjectModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getPosX() {
		// TODO Auto-generated method stub
		return posX;
	}

	@Override
	public void setPosX(int posX) {
		// TODO Auto-generated method stub
				
		this.posX=posX;

	}

	@Override
	public int getPosY() {
		// TODO Auto-generated method stub
		return posY;
	}

	@Override
	public void setPosY(int posY) {
		// TODO Auto-generated method stub
		this.posY=posY;
	}

	@Override
	public int getSpeedX() {
		// TODO Auto-generated method stub
		return speedX;
	}

	@Override
	public void setSpeedX(int speedX) {
		// TODO Auto-generated method stub
		this.speedX=speedX;
	}

	@Override
	public int getSpeedY() {
		// TODO Auto-generated method stub
		return speedY;
	}

	@Override
	public void setSpeedY(int speedY) {
		// TODO Auto-generated method stub
		this.speedY=speedY;
	}

	@Override
	public void blowUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleXDirection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleYDirection() {
		// TODO Auto-generated method stub
		
	}
	
	public void setSpriteSound(String spriteSound){
		this.spriteSound = spriteSound;
	}
	
	public String getSpriteSound(){
		return spriteSound;
	}

	@Override
	public void update(Dimension dim) {
		// TODO Auto-generated method stub
		
	}

}
