package cn.imaginary.toolkit;

import cn.imaginary.toolkit.image.rgb.RGBArray;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTool {
    private RGBArray rgbArray = new RGBArray();

    public int[][] getArrays(BufferedImage image) {
        return rgbArray.getArrays(image);
    }

    public BufferedImage read(File imageFile) {
        if (null != imageFile) {
            try {
                return ImageIO.read(imageFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void write(BufferedImage image, String formatName, File file) {
        if (null != image && null != formatName && null != file) {
            try {
                ImageIO.write(image, formatName, file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
