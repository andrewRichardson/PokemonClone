package com.arichardson.main.graphics;

import com.arichardson.main.api.Graphics;

public class Level {

	private TileMap tileMap;
	private int width, height, size, offsetX, offsetY, bgTile;

	public Level(int width, int height, SpriteSheet spriteSheet, int bgTile, int[][] tileData, int[][] tileLayerData) {
		this.width = width;
		this.height = height;
		size = spriteSheet.getSize();
		tileMap = new TileMap(width, height, spriteSheet, tileData, tileLayerData);
		this.bgTile = bgTile;
	}
	
	public void updatePlayerPosition(int x, int y){
		offsetX = x;
		offsetY = y;
	}

	public void drawTileMapBottom(Graphics graphics) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(bgTile != -1)
					graphics.drawSprite(i*size-offsetX+graphics.getWidth()/2-size/2, j*size-offsetY+graphics.getHeight()/2-size/2, tileMap.sprites[bgTile]);
				if(tileMap.tileData[i][j] <= 2 && tileMap.tileData[i][j] != -1)
					graphics.drawSprite(i*size-offsetX+graphics.getWidth()/2-size/2, j*size-offsetY+graphics.getHeight()/2-size/2, tileMap.sprites[tileMap.tiles[i][j]]);
			}
		}
	}
	
	public void drawTileMapTop(Graphics graphics) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if(tileMap.tileData[i][j] > 1)
					graphics.drawSprite(i*size-offsetX+graphics.getWidth()/2-size/2, j*size-offsetY+graphics.getHeight()/2-size/2, tileMap.sprites[tileMap.tiles[i][j]]);
			}
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public TileMap getTileMap(){
		return tileMap;
	}
	
	public int getBGTile() {
		return bgTile;
	}
}
