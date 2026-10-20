package cn.imaginary.toolkit.model;

import javaev.util.ListTool;
import javaxev.vecmath.Point3DPlus;

import java.util.ArrayList;

public class Model3D {
    private boolean is_right_mode;

    public void setRightMode(boolean isRightMode) {
        is_right_mode = isRightMode;
    }

    public boolean isRightMode() {
        return is_right_mode;
    }

    public Point3DPlus[] read(int[][] arrayXZ, int[][] arrayYZ, int[][] arrayXY) {
        if (!(null == arrayXZ && null == arrayYZ && null == arrayXY)) {
            int length = 0;
            int[][] arrXZ = null;
            if (null != arrayXZ) {
                length = arrayXZ.length;
                arrXZ = new int[length][length];
                for (int x = 0; x < length; x++) {
                    for (int z = 0; z < length; z++) {
                        arrXZ[x][z] = arrayXZ[x][length - 1 - z];
                    }
                }
            }
            System.out.println("m3d22:scan xy done!");
            int[][] arrYZ = null;
            if (null != arrayYZ) {
                length = arrayYZ.length;
                arrYZ = new int[length][length];
                for (int y = 0; y < length; y++) {
                    for (int z = 0; z < length; z++) {
                        if (isRightMode()) {
                            arrYZ[y][z] = arrayYZ[y][length - 1 - z];
                        } else {
                            arrYZ[y][z] = arrayYZ[length - 1 - y][length - 1 - z];
                        }
                    }
                }
            }
            System.out.println("m3d33:scan yz done!");
            int[][] arrXY = null;
            if (null != arrayXY) {
                length = arrayXY.length;
                arrXY = new int[length][length];
                for (int x = 0; x < length; x++) {
                    for (int y = 0; y < length; y++) {
                        arrXY[x][y] = arrayXY[x][length - 1 - y];
                    }
                }
            }
            System.out.println("m3d44:scan xy done!");
            ArrayList<Point3DPlus> arrayList = new ArrayList<>();
            for (int x = 0; x < length; x++) {
                for (int y = 0; y < length; y++) {
                    for (int z = 0; z < length; z++) {
                        Point3DPlus p = new Point3DPlus(x, y, z);
                        arrayList.add(p);
                        if ((null != arrXZ && arrXZ[x][z] == 0) || (null != arrYZ && arrYZ[y][z] == 0) || (null != arrXY && arrXY[x][y] == 0)) {
                            arrayList.remove(p);
                        }
                    }
                }
            }
            System.out.println("m3d57:scan vertices done!");
            return ListTool.toArray(arrayList);
        }
        return null;
    }
}
