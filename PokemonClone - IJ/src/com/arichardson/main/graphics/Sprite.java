package com.arichardson.main.graphics;

import java.awt.Image;

public class Sprite {

	private Image image;
	private int width;
	private int height;
	
	public Sprite(SpriteSheet spriteSheet, int index) {
		image = retrieveImage(spriteSheet, index);
		width = spriteSheet.getSize();
		height = spriteSheet.getSize();
	}
	
	private Image retrieveImage(SpriteSheet spriteSheet, int index){
		int x = index%spriteSheet.getColumns();
		int y = (int)Math.floor((double)index/spriteSheet.getColumns());
		x = (index < x) ? index : x;
		return spriteSheet.getImage().getSubimage(x*(spriteSheet.getSize()+spriteSheet.getMargin()), y*(spriteSheet.getSize()+spriteSheet.getMargin()), spriteSheet.getSize(), spriteSheet.getSize());
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
