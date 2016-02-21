package com.arichardson.main.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.arichardson.main.graphics.Level;
import com.arichardson.main.graphics.SpriteSheet;

public class LevelLoader {
	/**
	 * Loads a Level object from the specified sub-folder. 
	 * The levelPath argument must specify a relative path of type {@link String}.
	 * <p>
	 * This method returns a Level object when finished loading.
	 *
	 * @param  levelPath  an relative String path of the level to be loaded
	 * @param  init the GameInit object used for its JFrame
	 * @return      Level
	 * @see         Level
	 */
	public static Level retrieveLevel(String levelPath){
		int width = 0;
		int height = 0;
		int tileSize = 0;
		int margin = 0;
		int bgTile = 0;
		int[][] newLevel = null;
		int[][] newLayer = null;
		String spriteSheetPath = null;
		Level level;
		try {
			Scanner scanner = new Scanner(new File(levelPath));
			
			if(scanner != null){
				Scanner scan = new Scanner(scanner.nextLine());
				scan.useDelimiter(", ");
				width = scan.nextInt();
				height = scan.nextInt();
				tileSize = scan.nextInt();
				margin = scan.nextInt();
				bgTile = scan.nextInt();
				spriteSheetPath = scan.next();
				
				scan.close();
				
				newLevel = new int[width][height];
				newLayer = new int[width][height];
				
				scanner.nextLine();
				
				for(int y = 0; y < height; y++){
					Scanner s = new Scanner(scanner.nextLine());
					for(int x = 0; x < width; x++){
						newLevel[x][y] = s.nextInt();
					}
					s.close();
				}
				
				scanner.nextLine();
				
				for(int y = 0; y < height; y++){
					Scanner s = new Scanner(scanner.nextLine());
					for(int x = 0; x < width; x++){
						newLayer[x][y] = s.nextInt();
					}
					s.close();
				}
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if(newLevel == null){
			 return null;
		}else{
			SpriteSheet spriteSheet = new SpriteSheet(spriteSheetPath, tileSize, margin);
			level = new Level(width, height, spriteSheet, bgTile, newLevel, newLayer);
		}
		return level;
	}
	
	/**
	 * Saves a Level object in the specific sub-folder. 
	 * The levelName argument must specify a relative path of type {@link String}.
	 * <p>
	 * This method returns when finished saving.
	 *
	 * @param  levelPath  an relative String path of the level to be saved
	 * @param  level the level object to be saved
	 * @return      void
	 * @see         Level
	 */
	public static void saveLevel(String levelPath, Level level) {
		try {
            FileWriter fileWriter = new FileWriter(new File(levelPath));

            PrintWriter pw = new PrintWriter(fileWriter);

           	pw.println(level.getTileMap().getWidth() + ", " + level.getTileMap().getHeight() + ", " + level.getSize() + ", " + level.getTileMap().getSpriteSheet().getMargin() + ", " + level.getBGTile() + ", " + level.getTileMap().getSpriteSheet().getPath() + " ");
            
            for(int y = 0; y < level.getTileMap().getWidth()/level.getSize(); y++){
    			for(int x = 0; x < level.getTileMap().getHeight()/level.getSize(); x++){
    				pw.print(level.getTileMap().tiles[x][y] + " ");
    			}
    			pw.println("");
    		}
            
            for(int y = 0; y < level.getTileMap().getWidth()/level.getSize(); y++){
    			for(int x = 0; x < level.getTileMap().getHeight()/level.getSize(); x++){
    				pw.print(level.getTileMap().tileData[x][y] + " ");
    			}
    			pw.println("");
    		}
            
            pw.close();
            fileWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + levelPath + "'");
        }
	}
	
}
