package com.arichardson.main.graphics;

public class TileMap {

	public int[][] tiles;
	public int[][] tileData;
	public Sprite[] sprites;
	
	private int width, height;
	private SpriteSheet ss;

	public TileMap(int width, int height, SpriteSheet spriteSheet, int[][] tileData, int[][] tileLayerData) {
		this.width = width;
		this.height = height;
		ss = spriteSheet;
		tiles = new int[width][height];
		this.tileData = new int[width][height];
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tiles[i][j] = tileData[i][j];
			}
		}
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.tileData[i][j] = tileLayerData[i][j];
			}
		}
		
		initializeSprites(spriteSheet);
	}

	private void initializeSprites(SpriteSheet spriteSheet) {
		int width = spriteSheet.getWidth()/spriteSheet.getSize();
		int height = spriteSheet.getHeight()/spriteSheet.getSize();
		sprites = new Sprite[width*height];
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				sprites[x + y * width] = new Sprite(spriteSheet, x+y*width);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public SpriteSheet getSpriteSheet(){
		return ss;
	}
	
	public int[] getTileNeighbors(int x, int y){
		int[] neighbors = new int[4];
		if(x != 0)
			neighbors[0] = tileData[x-1][y];
		else
			neighbors[0] = -1;
		if(y != 0)
			neighbors[1] = tileData[x][y-1];
		else
			neighbors[1] = -1;
		if(x != width-1)
			neighbors[2] = tileData[x+1][y];
		else
			neighbors[2] = -1;
		if(y != height-1)
			neighbors[3] = tileData[x][y+1];
		else
			neighbors[3] = -1;
		return neighbors;
	}
}
