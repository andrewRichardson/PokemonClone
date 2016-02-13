package com.arichardson.main.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private int width;
	private int height;
	private int rows, columns;
	private int size;
	private int margin;
	private BufferedImage image;
	private String path;
	
	public SpriteSheet(String filePath, int size, int margin) {
		image = retrieveImage(filePath);
		width = image.getWidth();
		height = image.getHeight();
		this.size = size;
		path = filePath;
		this.margin = margin;
		columns = width / (size + margin);
		rows = height / (size + margin);
	}
	
	private BufferedImage retrieveImage(String filePath) {
		BufferedImage image = null;
		try {
			File file = new File(filePath);
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getMargin(){
		return margin;
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public String getPath(){
		return path;
	}
	
}
