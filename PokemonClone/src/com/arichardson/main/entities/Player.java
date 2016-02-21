package com.arichardson.main.entities;

import com.arichardson.main.graphics.Level;
import com.arichardson.main.graphics.Sprite;
import com.arichardson.main.graphics.SpriteSheet;
import com.arichardson.main.input.InputHandler;
import com.arichardson.main.util.Graphics;

public class Player {
	
	InputHandler input;
	public Level level;

	private SpriteSheet spriteSheet;
	private Sprite sprite;
	private int spriteIndex = 0;
	private int spriteIndexOffset;
	private int tileSize = 0;
	private int x, y, nextX, nextY, currentX, currentY;
	private int movementTimer = 0;
	private final int MOVEMENT_TIMER_MAX = 100;
	private double progress, speed;
	private boolean isMovingVertical = false;
	private boolean isMoving = false;
	
	public Player(Level level, InputHandler inputHandler, SpriteSheet spriteSheet, int startingIndex, int tileSize, int initX, int initY, double speed){
		this.level = level;
		input = inputHandler;
		this.spriteSheet = spriteSheet;
		sprite = new Sprite(spriteSheet, spriteIndex);
		this.tileSize = tileSize;
		spriteIndex = startingIndex;
		spriteIndexOffset = startingIndex;
		
		x = initX;
		y = initY;
		nextX = initX;
		nextY = initY;
		this.speed = speed;
		progress = 0.0;
	}
	
	public void update(int deltaTime){
		sprite = new Sprite(spriteSheet, spriteIndex);
		
		
		int[] tiles = new int[4];
		for(int i = 0; i < 4; i++){
			tiles[i] = level.getTileMap().getTileNeighbors(x, y)[i];
		}
		
		if(progress == 0.0){
			if(input.up || input.down){
				isMovingVertical = true;
			}
			else if(input.left || input.right){
				isMovingVertical = false;
			}
			if(input.up || input.down || input.left || input.right){
				if(input.up){
					spriteIndex = spriteIndexOffset+1;
				}
				else if(input.down){
					spriteIndex = spriteIndexOffset;
				}
				else if(input.left){
					spriteIndex = spriteIndexOffset+2;
				}
				else if(input.right){
					spriteIndex = spriteIndexOffset+3;
				}
				if(movementTimer >= MOVEMENT_TIMER_MAX || isMoving){
					movementTimer = 0;
					isMoving = true;
					if(input.up){
						spriteIndex = spriteIndexOffset+1;
						if(tiles[1] != -1 && tiles[1] != 1){
								nextY = y-1;
						}
					}
					else if(input.down){
						spriteIndex = spriteIndexOffset;
						if(tiles[3] != -1 && tiles[3] != 1){
								nextY = y+1;
						}
					}
					else if(input.left){
						spriteIndex = spriteIndexOffset+2;
						if(tiles[0] != -1 && tiles[0] != 1){
								nextX = x-1;
						}
					}
					else if(input.right){
						spriteIndex = spriteIndexOffset+3;
						if(tiles[2] != -1 && tiles[2] != 1){
								nextX = x+1;
						}
					}
				} else {
					movementTimer += deltaTime;
				}
			} else {
				isMoving = false;
			}
		}
		
		if(nextX != x || nextY != y){
			progress += speed;
		}
		if(progress > 1.0){
			progress = 0.0;
			x = nextX;
			y = nextY;
		}
		
		currentX = x*level.getSize();
		currentY = y*level.getSize();
		if(nextX != x || nextY != y){
			if(!isMovingVertical){
				currentX = (int)(lerp(x, nextX, progress)*level.getSize());
			}
			else
				currentY = (int)(lerp(y, nextY, progress)*level.getSize());
		}
	}
	
	double lerp(double a, double b, double t)
	{
	    return a + t * (b - a);
	}
	
	public void render(Graphics graphics){
		graphics.drawSprite(graphics.getWidth()/2 - tileSize/2 - (spriteSheet.getSize()-tileSize)/2, graphics.getHeight()/2 - tileSize/2 - (spriteSheet.getSize()-tileSize), sprite);
	}
	
	public int getX(){
		return currentX;
	}
	
	public int getY(){
		return currentY;
	}
}
