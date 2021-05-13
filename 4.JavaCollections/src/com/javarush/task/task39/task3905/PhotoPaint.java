package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (x < 0 || y < 0 || x >= image.length || y >= image[x].length) {
            return false;
        }
        if (image[y][x].equals(desiredColor)) {
            return false;
        }
        Color oldColor = image[y][x];
        image[y][x] = desiredColor;
        if (x + 1 <= image[y].length - 1 && image[y][x + 1].equals(oldColor)) {
            paintFill(image, x + 1, y, desiredColor);
        }
        if (y + 1 <= image[x].length - 1 && image[y + 1][x].equals(oldColor)) {
            paintFill(image, x, y + 1, desiredColor);
        }
        if (x - 1 >= 0 && image[y][x - 1].equals(oldColor)) {
            paintFill(image, x - 1, y, desiredColor);
        }
        if (y - 1 >= 0 && image[y - 1][x].equals(oldColor)) {
            paintFill(image, x, y - 1, desiredColor);
        }
        return true;
    }
}
