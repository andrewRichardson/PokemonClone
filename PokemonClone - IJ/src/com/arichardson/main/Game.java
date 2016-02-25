package com.arichardson.main;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import com.arichardson.main.entities.Player;
import com.arichardson.main.graphics.Level;
import com.arichardson.main.graphics.SpriteSheet;
import com.arichardson.main.input.InputHandler;
import com.arichardson.main.util.Graphics;
import com.arichardson.main.util.LevelLoader;

public class Game {
	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	static int realWidth = gd.getDisplayMode().getWidth();
	static int realHeight = gd.getDisplayMode().getHeight();
	static GameInit init;
	int mouseX, mouseY;
	int width, height;
	int tileSize = 16;
	double scale = 4.0;
	InputHandler input;
	Graphics graphics;
	Level level1;
	SpriteSheet ssLevel, ssPlayer;
	Player player;
	
	public Game(GameInit gameInit) {
		init = gameInit;
		width = (int)(realWidth/scale);
		height = (int)(realHeight/scale);
		System.out.println(realWidth + " " + realHeight + " " + width + " " + height);
		input = new InputHandler();
		init.addKeyListener(input);
		init.addMouseListener(input);
		init.addMouseWheelListener(input);
		graphics = new Graphics(init, realWidth, realHeight, scale);
		ssLevel = new SpriteSheet("res/firered-ss.png", tileSize, 0);
		ssPlayer = new SpriteSheet("res/sprites-firered-ss.png", 20, 0);
		level1 = LevelLoader.retrieveLevel("res/a.txt");
		player = new Player(level1, input, ssPlayer, 0, tileSize, level1.getTileMap().getWidth()/2, level1.getTileMap().getHeight()/2, .075);
		
		render();
	}

	public static void main(String[] args) {
		init = new GameInit("Pokemon Clone", realWidth, realHeight);
		init.main();
		@SuppressWarnings("unused")
		Game game = new Game(init);
	}
	
	public void update(double deltaTime){
		mouseX = init.getMouseX();
		mouseY = init.getMouseY();
		
		input.update();
		
		player.update((int)(deltaTime*1000));
		level1.updatePlayerPosition(player.getX(), player.getY());
	}
	
	public void render(){
		int updates = 0;
		while(true){
			boolean flag = false;
			if(init.render){
				flag = graphics.startRender();
				
				if(!flag){
					if(init.ups > updates){
						updates++;
						update(init.deltaTime);
					}
					if(init.ups < updates){
						updates = 0;
					}
					
					level1.drawTileMapBottom(graphics);
					player.render(graphics);
					level1.drawTileMapTop(graphics);
					graphics.finishRender();
				}
			}
		}
	}
	
}
