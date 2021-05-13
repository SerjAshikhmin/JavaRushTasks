package com.javarush.task.task34.task3410.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {

    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (level != 60) level = level % 60;
        List<String> allLevels;
        try {
            allLevels = Files.readAllLines(levels);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        boolean isLevelFound = false;
        boolean isStartOfLevelFound = false;
        boolean isEndOfLevelFound = false;
        List<String> currentLevel = new ArrayList<>();
        for (int i = 0; i < allLevels.size(); i++) {
            if (allLevels.get(i).equals(String.format("Maze: %d", level))) {
                isLevelFound = true;
            }
            if (isLevelFound && !isStartOfLevelFound && allLevels.get(i).isEmpty()) {
                isStartOfLevelFound = true;
            }
            if (isStartOfLevelFound && !allLevels.get(i).isEmpty()) {
                currentLevel.add(allLevels.get(i));
            }
            if (isLevelFound && isStartOfLevelFound && allLevels.get(i).isEmpty() && !currentLevel.isEmpty()) {
                isEndOfLevelFound = true;
            }
            if (isEndOfLevelFound) {
                break;
            }
        }
        for (int i = 0; i < currentLevel.size(); i++) {
            for (int j = 0; j < currentLevel.get(i).length(); j++) {
                if (currentLevel.get(i).charAt(j) == 'X') {
                    walls.add(new Wall(j * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2, i * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2));
                }
                if (currentLevel.get(i).charAt(j) == '*') {
                    boxes.add(new Box(j * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2, i * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2));
                }
                if (currentLevel.get(i).charAt(j) == '.') {
                    homes.add(new Home(j * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2, i * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2));
                }
                if (currentLevel.get(i).charAt(j) == '&') {
                    boxes.add(new Box(j * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2, i * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2));
                    homes.add(new Home(j * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2, i * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2));
                }
                if (currentLevel.get(i).charAt(j) == '@') {
                    player = new Player(j * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2, i * GameObject.FIELD_CELL_SIZE + GameObject.FIELD_CELL_SIZE / 2);
                }
            }
        }
        GameObjects gameObjects = new GameObjects(walls, boxes, homes, player);
        return gameObjects;
    }
}
