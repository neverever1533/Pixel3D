package cn.imaginary.toolkit.model;

import cn.imaginary.toolkit.ImageTool;

import javaev.util.ListTool;
import javaxev.vecmath.Point3DPlus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Model3D {
    private boolean is_right_mode;

    private int length_x;
    private int length_y;
    private int length_z;

    private Point3DPlus[] array;

    private ImageTool imageTool = new ImageTool();

    public void setRightMode(boolean isRightMode) {
        is_right_mode = isRightMode;
    }

    public boolean isRightMode() {
        return is_right_mode;
    }

    public int getXLength() {
        return length_x;
    }

    public void setXLength(int length) {
        length_x = length;
    }

    public int getYLength() {
        return length_y;
    }

    public void setYLength(int length) {
        length_y = length;
    }

    public int getZLength() {
        return length_z;
    }

    public void setZLength(int length) {
        length_z = length;
    }

    public Point3DPlus[] getPointArray() {
        return array;
    }

    public void setPointArray(Point3DPlus[] array) {
        this.array = array;
    }

    private int[][] getRGBArrays(BufferedImage image) {
        return imageTool.getArrays(image);
    }

    public void read(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        update(imageXZ, imageYZ, imageXY);
        Point3DPlus[] array = read(getRGBArrays((imageXZ)), getRGBArrays(imageYZ), getRGBArrays(imageXY));
        setPointArray(array);
    }

    public void read(File imageFileXZ, File imageFileYZ, File imageFileXY) {
        read(imageTool.read(imageFileXZ), imageTool.read(imageFileYZ), imageTool.read(imageFileXY));
    }

    private Point3DPlus[] read(int[][] arrayXZ, int[][] arrayYZ, int[][] arrayXY) {
        int len_x = getXLength();
        int len_y = getYLength();
        int len_z = getZLength();
        int[][] arrXZ = null;
        if (null != arrayXZ) {
            if (len_y == 0) {
                len_y = Math.min(len_x, len_z);
            }
            arrXZ = new int[len_x][len_z];
            for (int x = 0; x < len_x; x++) {
                for (int z = 0; z < len_z; z++) {
                    arrXZ[x][z] = arrayXZ[x][len_z - 1 - z];
                }
            }
        }
        System.out.println("m3d92:scan xy done!");

        int[][] arrYZ = null;
        if (null != arrayYZ) {
            if (len_x == 0) {
                len_x = Math.min(len_y, len_z);
            }
            arrYZ = new int[len_y][len_z];
            for (int y = 0; y < len_y; y++) {
                for (int z = 0; z < len_z; z++) {
                    if (isRightMode()) {
                        arrYZ[y][z] = arrayYZ[y][len_z - 1 - z];
                    } else {
                        arrYZ[y][z] = arrayYZ[len_y - 1 - y][len_z - 1 - z];
                    }
                }
            }
        }
        System.out.println("m3d110:scan yz done!");

        int[][] arrXY = null;
        if (null != arrayXY) {
            if (len_z == 0) {
                len_z = Math.min(len_x, len_y);
            }
            arrXY = new int[len_x][len_y];
            for (int x = 0; x < len_x; x++) {
                for (int y = 0; y < len_y; y++) {
                    arrXY[x][y] = arrayXY[x][len_y - 1 - y];
                }
            }
        }
        System.out.println("m3d124:scan xy done!");

        ArrayList<Point3DPlus> arrayList = new ArrayList<>();
        for (int x = 0; x < len_x; x++) {
            for (int y = 0; y < len_y; y++) {
                for (int z = 0; z < len_z; z++) {
                    Point3DPlus p = new Point3DPlus(x, y, z);
                    arrayList.add(p);
                    if ((null != arrXZ && arrXZ[x][z] == 0) || (null != arrYZ && arrYZ[y][z] == 0) || (null != arrXY && arrXY[x][y] == 0)) {
                        arrayList.remove(p);
                    }
                }
            }
        }
        System.out.println("m3d138:scan vertices done!");
        return ListTool.toArray(arrayList);
    }

    private void update(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        if (null != imageXZ || null != imageYZ || null != imageXY) {
            int len_x = 0;
            int len_y = 0;
            int len_z = 0;
            if (null != imageXZ) {
                len_x = imageXZ.getWidth();
                len_z = imageXZ.getHeight();
            }
            if (null != imageYZ) {
                len_y = imageYZ.getWidth();
                if (len_z != 0) {
                    len_z = Math.min(imageYZ.getHeight(), len_z);
                } else {
                    len_z = imageYZ.getHeight();
                }
            }
            if (null != imageXY) {
                if (len_x != 0) {
                    len_x = Math.min(imageXY.getWidth(), len_x);
                } else {
                    len_x = imageXY.getWidth();
                }
                if (len_y != 0) {
                    len_y = Math.min(imageXY.getHeight(), len_y);
                } else {
                    len_y = imageXY.getHeight();
                }
            }
            setXLength(len_x);
            setYLength(len_y);
            setZLength(len_z);
        }
    }
}
