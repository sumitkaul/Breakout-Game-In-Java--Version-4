package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import utility.ResizeHelper;

import behavior.Behavior;

public class SpriteModel implements GameObjectModel {

	private int posX, posY, speedX, speedY, width, height;
	private boolean hasCollisionOccured = false, updateX, updateY;
	private List<Behavior> behaviorList;
	private boolean isAlive = true;
	private String name;

	public SpriteModel(int posX, int posY, int speedX, int speedY, int width,
			int height, boolean updateX, boolean updateY, String name) {
		this.posX = posX;
		this.posY = posY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.width = width;
		this.height = height;
		this.updateX = updateX;
		this.updateY = updateY;
		this.name = name;
		this.behaviorList = new ArrayList<>();
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int) (posX* ResizeHelper.getXfactor()),
				(int) (posY*ResizeHelper.getYfactor()),
				(int) (width *ResizeHelper.getXfactor()),
				(int) (height*ResizeHelper.getYfactor() ));
	}

	public boolean isHasCollisionOccured() {
		return hasCollisionOccured;
	}

	public void setHasCollisionOccured(boolean hasCollisionOccured) {
		this.hasCollisionOccured = hasCollisionOccured;
	}

	public boolean isUpdateX() {
		return updateX;
	}

	public void setUpdateX(boolean updateX) {
		this.updateX = updateX;
	}

	public boolean isUpdateY() {
		return updateY;
	}

	public void setUpdateY(boolean updateY) {
		this.updateY = updateY;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBehaviorList(List<Behavior> behaviorList) {
		this.behaviorList = behaviorList;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	@Override
	public void collisionOccured(GameObjectModel model) {
		hasCollisionOccured = true;

	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getPosX() {
		return this.posX;
	}

	@Override
	public void setPosX(int posX) {
		this.posX = posX;

	}

	@Override
	public int getPosY() {
		return this.posY;
	}

	@Override
	public void setPosY(int posY) {
		this.posY = posY;

	}

	@Override
	public int getSpeedX() {
		return this.speedX;
	}

	@Override
	public void setSpeedX(int speedX) {
		this.speedX = speedX;

	}

	@Override
	public int getSpeedY() {
		return this.speedY;
	}

	@Override
	public void setSpeedY(int speedY) {
		this.speedY = speedY;

	}

	@Override
	public void update(Dimension dim) {
		if (updateX) {
			posX += speedX;
		}
		if (updateY) {
			posY += speedY;
		}

		if (posX < 0) {
			posX = 0;
			speedX = Math.abs(speedX);
		}

		 if (posX*ResizeHelper.getXfactor() > dim.width) {
		 posX = (int) (ResizeHelper.getDefaultWidth() - this.width);
		 speedX = -Math.abs(speedX);
		 }

//		if (posX + width + speedX + 5 > ResizeHelper.getDefaultWidth()) {
//			posX = (int) (ResizeHelper.getDefaultWidth() - this.width);
//			speedX = -Math.abs(speedX);
//		}

		if (posY < 0) {
			posY = 0;
			speedY = Math.abs(speedY);
		}

		 if (posY*ResizeHelper.getYfactor() > dim.height) {
		 posY = (int) (ResizeHelper.getDefaultHeight() - this.height);
		 speedY = -Math.abs(speedY);
		 }

//		if (posY + height + speedY + 5 > ResizeHelper.getDefaultHeight()) {
//			posY = (int) (ResizeHelper.getDefaultHeight() - this.height);
//			speedY = -Math.abs(speedY);
//		}

	}

	@Override
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;

	}

	@Override
	public boolean getIsAlive() {
		return this.isAlive;
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
		this.width = i;

	}

	@Override
	public void setHeight(int i) {
		this.height = i;

	}

}
