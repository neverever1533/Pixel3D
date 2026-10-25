package cn.imaginary.toolkit.model;

import cn.imaginary.toolkit.ImageTool;

import javaev.util.ListTool;
import javaxev.vecmath.Point3DPlus;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Model3D {
    private boolean is_mode_right;

    private int length_x;
    private int length_y;
    private int length_z;

    private ArrayList<Point3DPlus> arrayList;

    private ImageTool imageTool = new ImageTool();

    public void setRightMode(boolean isRightMode) {
        is_mode_right = isRightMode;
    }

    public boolean isRightMode() {
        return is_mode_right;
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

    public ArrayList<Point3DPlus> getPointList() {
        return arrayList;
    }

    public void setPointList(ArrayList<Point3DPlus> arrayList) {
        this.arrayList = arrayList;
    }

    public void setPointList(Point3DPlus[] array) {
        setPointList(ListTool.toList(array));
    }

    public void read(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        update(imageXZ, imageYZ, imageXY);
        setPointList(readVertices(imageXZ, imageYZ, imageXY));
    }

    public void read(File imageFileXZ, File imageFileYZ, File imageFileXY) {
        read(imageTool.read(imageFileXZ), imageTool.read(imageFileYZ), imageTool.read(imageFileXY));
    }

    private ArrayList<Point3DPlus> readVertices(BufferedImage imageXZ, BufferedImage imageYZ, BufferedImage imageXY) {
        int len_x = getXLength();
        int len_y = getYLength();
        int len_z = getZLength();
        if (len_x == 0 || len_y == 0 || len_z == 0) {
            return null;
        }
        int[][] arrXZ = null;
        if (null != imageXZ) {
            arrXZ = new int[len_x][len_z];
            for (int x = 0; x < len_x; x++) {
                for (int z = 0; z < len_z; z++) {
                    arrXZ[x][z] = imageXZ.getRGB(x, len_z - 1 - z);
                }
            }
        }
        System.out.println("m3d92:scan xy done!");

        int[][] arrYZ = null;
        if (null != imageYZ) {
            arrYZ = new int[len_y][len_z];
            for (int y = 0; y < len_y; y++) {
                for (int z = 0; z < len_z; z++) {
                    if (isRightMode()) {
                        arrYZ[y][z] = imageYZ.getRGB(y, len_z - 1 - z);
                    } else {
                        arrYZ[y][z] = imageYZ.getRGB(len_y - 1 - y, len_z - 1 - z);
                    }
                }
            }
        }
        System.out.println("m3d110:scan yz done!");

        int[][] arrXY = null;
        if (null != imageXY) {
            arrXY = new int[len_x][len_y];
            for (int x = 0; x < len_x; x++) {
                for (int y = 0; y < len_y; y++) {
                    arrXY[x][y] = imageXY.getRGB(x, len_y - 1 - y);
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
        return arrayList;
    }

    public ArrayList<Point3DPlus> update(ArrayList<Point3DPlus> arrayList, boolean isVoxelMode) {
        if (null != arrayList) {
            if (!isVoxelMode) {
                ArrayList<Point3DPlus> alist = new ArrayList<>();
                for (Iterator<Point3DPlus> iterator = arrayList.iterator(); iterator.hasNext(); ) {
                    Point3DPlus p = iterator.next();
                    if (null != p) {
                        Point3DPlus p_f = new Point3DPlus(p.x, p.y - 1, p.z);
                        Point3DPlus p_b = new Point3DPlus(p.x, p.y + 1, p.z);
                        Point3DPlus p_l = new Point3DPlus(p.x - 1, p.y, p.z);
                        Point3DPlus p_r = new Point3DPlus(p.x + 1, p.y, p.z);
                        Point3DPlus p_u = new Point3DPlus(p.x, p.y, p.z + 1);
                        Point3DPlus p_d = new Point3DPlus(p.x, p.y, p.z - 1);
                        Point3DPlus p_ws_u = new Point3DPlus(p.x - 1, p.y - 1, p.z + 1);
                        Point3DPlus p_ws_d = new Point3DPlus(p.x - 1, p.y - 1, p.z - 1);
                        Point3DPlus p_wn_u = new Point3DPlus(p.x - 1, p.y + 1, p.z + 1);
                        Point3DPlus p_wn_d = new Point3DPlus(p.x - 1, p.y + 1, p.z - 1);
                        Point3DPlus p_es_u = new Point3DPlus(p.x + 1, p.y - 1, p.z + 1);
                        Point3DPlus p_es_d = new Point3DPlus(p.x + 1, p.y - 1, p.z - 1);
                        Point3DPlus p_en_u = new Point3DPlus(p.x + 1, p.y + 1, p.z + 1);
                        Point3DPlus p_en_d = new Point3DPlus(p.x + 1, p.y + 1, p.z - 1);
                        if (!ListTool.contains(arrayList, p_f) || !ListTool.contains(arrayList, p_b) || !ListTool.contains(arrayList, p_l)
                                || !ListTool.contains(arrayList, p_r) || !ListTool.contains(arrayList, p_u) || !ListTool.contains(arrayList, p_d)
                                || !ListTool.contains(arrayList, p_ws_u) || !ListTool.contains(arrayList, p_ws_d) || !ListTool.contains(arrayList, p_wn_u) || !ListTool.contains(arrayList, p_wn_d)
                                || !ListTool.contains(arrayList, p_es_u) || !ListTool.contains(arrayList, p_es_d) || !ListTool.contains(arrayList, p_en_u) || !ListTool.contains(arrayList, p_en_d)) {
                            alist.add(p);
                        }
                    }
                }
                return alist;
            }
        }
        return arrayList;
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
