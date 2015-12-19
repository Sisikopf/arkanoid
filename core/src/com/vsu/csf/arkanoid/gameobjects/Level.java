package com.vsu.csf.arkanoid.gameobjects;

import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 19.12.2015.
 */
public class Level {
    private final static String TAG = "Level";

    private List<Block> blockList = new ArrayList<Block>();

    public void add(Block b) {
        blockList.add(b);
    }

    public void remove(Block b) {
        blockList.remove(b);
    }

    public List<Block> getBlocks() {
        return blockList;
    }

    public static Level loadLevel(String filePath, float blockWidth, float blockHeight) {
        Level level = new Level();
        FileReader fileReader = null;
        BufferedReader reader = null;
        String line = null;
        try {
            fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);
            int i = 0;
            while ((line = reader.readLine()) != null) {
                Gdx.app.log(TAG, line);
                for (int k = 0; k < line.length(); k++) {
                    float x = blockWidth * k;
                    float y = blockHeight * i;
                    switch (line.charAt(k)) {
                        case '*' :
                            level.add(new UndestructableBlock(x, y, blockWidth, blockHeight));
                            break;
                        case 'L' :
                            level.add(new DestructableBlock(x, y, blockWidth, blockHeight, BlockType.LIGHT));
                            break;
                        case 'M' :
                            level.add(new DestructableBlock(x, y, blockWidth, blockHeight, BlockType.MEDIUM));
                            break;
                        case 'H' :
                            level.add(new DestructableBlock(x, y, blockWidth, blockHeight, BlockType.HEAVY));
                            break;
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            Gdx.app.log(TAG, e.getMessage(), e);
        } catch (IOException e) {
            Gdx.app.log(TAG, e.getMessage(), e);
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    Gdx.app.log(TAG, e.getMessage(), e);
                }
            if (fileReader != null)
                try {
                    fileReader.close();
                } catch (IOException e) {
                    Gdx.app.log(TAG, e.getMessage(), e);
                }
        }
        return level;
    }
}
