package cn.imaginary.toolkit.image.rgb;

import java.awt.image.BufferedImage;

public class RGBArray {
    public int[][] getArrays(BufferedImage root) {
        if (null != root) {
            int w = root.getWidth();
            int h = root.getHeight();
            int[][] array = new int[w][h];
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    array[x][y] = root.getRGB(x, y);
                }
            }
            return array;
        }
        return null;
    }

    public BufferedImage getImage(int[][] array) {
        if (null != array) {
            int width = array.length;
            int height = array[width - 1].length;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, array[x][y]);
                }
            }
            return image;
        }
        return null;
    }
}
