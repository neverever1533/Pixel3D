package cn.imaginary.toolkit;

import cn.imaginary.toolkit.image.rgb.RGBArray;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageTool {
    public static int Color_Size_2 = 2;
    public static int Color_Size_4 = 4;
    public static int Color_Size_8 = 8;
    public static int Color_Size_16 = 16;
    public static int Color_Size_32 = 32;
    public static int Color_Size_64 = 64;
    public static int Color_Size_128 = 128;
    public static int Color_Size_256 = 256;
    public static int Depth_Default = 8;

    private int depth = 4;
    private int depth_item = 2;

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

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        if (depth > 0 && depth <= Depth_Default) {
            this.depth = depth;
        }
    }

    public BufferedImage updateColors(BufferedImage image, int colorSize) {
        if (colorSize >= Color_Size_256) {
            colorSize = Color_Size_256;
        } else if (colorSize >= Color_Size_128) {
            colorSize = Color_Size_128;
        } else if (colorSize >= Color_Size_64) {
            colorSize = Color_Size_64;
        } else if (colorSize >= Color_Size_32) {
            colorSize = Color_Size_32;
        } else if (colorSize >= Color_Size_16) {
            colorSize = Color_Size_16;
        } else if (colorSize >= Color_Size_8) {
            colorSize = Color_Size_8;
        } else if (colorSize >= Color_Size_4) {
            colorSize = Color_Size_4;
        } else if (colorSize >= Color_Size_2) {
            colorSize = Color_Size_2;
        }
        int depth = (int) (Math.log(colorSize) / Math.log(depth_item));
        return updateRGB(image, depth);
    }

    public BufferedImage getImage(BufferedImage image, int depth) {
        return updateRGB(image, depth);
    }

    private BufferedImage updateRGB(BufferedImage image, int depth) {
        if (null != image) {
            setDepth(depth);
            depth = getDepth();
            int size = (int) Math.pow(depth_item, depth);
            int item = Color_Size_256 / size;
            BufferedImage img = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            int pixel;
            int rgb;
            int a;
            int r;
            int b;
            int g;
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    rgb = image.getRGB(i, j);
                    a = (rgb >> 24) & 0xff;
                    r = (rgb >> 16) & 0xff;
                    g = (rgb >> 8) & 0xff;
                    b = rgb & 0xff;
                    r = updateDepth(r, size, item) & 0xff;
                    g = updateDepth(g, size, item) & 0xff;
                    b = updateDepth(b, size, item) & 0xff;
                    pixel = (a << 24) | (r << 16) | (g << 8) | b;
                    img.setRGB(i, j, pixel);
                }
            }
            return img;
        }
        return null;
    }

    private int updateDepth(int pixel, int size, int item) {
        int p = -1;
        for (int i = size; i > 0; i--) {
            int temp = item * i - 1;
            if (pixel >= temp) {
                p = temp;
            }
            if (p != -1) {
                pixel = p;
                break;
            }
        }
        return pixel;
    }

    public BufferedImage[] toArray(ArrayList<BufferedImage> arrayList) {
        if (null != arrayList) {
            BufferedImage[] array = new BufferedImage[arrayList.size()];
            arrayList.toArray(array);
            return array;
        }
        return null;
    }

    public ArrayList<BufferedImage> toList(File[] array) {
        if (null != array) {
            ArrayList<BufferedImage> arrayList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                BufferedImage image = read(array[i]);
                if (null != image) {
                    arrayList.add(image);
                }
            }
            return arrayList;
        }
        return null;
    }
}
